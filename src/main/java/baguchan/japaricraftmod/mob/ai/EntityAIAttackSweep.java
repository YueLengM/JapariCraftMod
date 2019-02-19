package baguchan.japaricraftmod.mob.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityAIAttackSweep extends EntityAIBase {
    World world;
    protected EntityCreature attacker;
    /**
     * An amount of decrementing ticks that allows the entity to attack once the tick reaches 0.
     */
    protected int attackTick;
    /**
     * The speed with which the mob will approach the target
     */
    double speedTowardsTarget;
    /**
     * When true, the mob will continue chasing its target, even if it can't find a path to them right now.
     */
    boolean longMemory;
    /**
     * The PathEntity of our entity.
     */
    Path path;
    private int delayCounter;
    private double targetX;
    private double targetY;
    private double targetZ;
    protected final int attackInterval = 20;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;

    public EntityAIAttackSweep(EntityCreature creature, double speedIn, boolean useLongMemory) {
        this.attacker = creature;
        this.world = creature.world;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase == null) {
            return false;
        } else if (!entitylivingbase.isEntityAlive()) {
            return false;
        } else {
            if (canPenalize) {
                if (--this.delayCounter <= 0) {
                    this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
                    this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
                    return this.path != null;
                } else {
                    return true;
                }
            }
            this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);

            if (this.path != null) {
                return true;
            } else {
                return this.getAttackReachSqr(entitylivingbase) >= this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
            }
        }
    }


    @Override
    public boolean shouldContinueExecuting() {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase == null) {
            return false;
        } else if (!entitylivingbase.isEntityAlive()) {
            return false;
        } else if (!this.longMemory) {
            return !this.attacker.getNavigator().noPath();
        } else if (!this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(entitylivingbase))) {
            return false;
        } else {
            return !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer) entitylivingbase).isSpectator() && !((EntityPlayer) entitylivingbase).isCreative();
        }
    }


    @Override
    public void startExecuting() {
        this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
        this.delayCounter = 0;
    }


    @Override
    public void resetTask() {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase instanceof EntityPlayer && (((EntityPlayer) entitylivingbase).isSpectator() || ((EntityPlayer) entitylivingbase).isCreative())) {
            this.attacker.setAttackTarget((EntityLivingBase) null);
        }

        this.attacker.getNavigator().clearPath();
    }


    @Override
    public void updateTask() {
        EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
        this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
        double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
        --this.delayCounter;

        if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || entitylivingbase.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F)) {
            this.targetX = entitylivingbase.posX;
            this.targetY = entitylivingbase.getEntityBoundingBox().minY;
            this.targetZ = entitylivingbase.posZ;
            this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);

            if (this.canPenalize) {
                this.delayCounter += failedPathFindingPenalty;
                if (this.attacker.getNavigator().getPath() != null) {
                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
                    if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                        failedPathFindingPenalty = 0;
                    else
                        failedPathFindingPenalty += 10;
                } else {
                    failedPathFindingPenalty += 10;
                }
            }

            if (d0 > 1024.0D) {
                this.delayCounter += 10;
            } else if (d0 > 256.0D) {
                this.delayCounter += 5;
            }

            if (!this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget)) {
                this.delayCounter += 15;
            }
        }

        //Prevents Friends getting too close and getting damaged
        if (entitylivingbase != null) {

            if (d0 <= this.getAttackReachSqr(entitylivingbase)) {

                this.attacker.getNavigator().clearPath();

            }

        }

        this.attackTick = Math.max(this.attackTick - 1, 0);
        this.checkAndPerformAttack(entitylivingbase, d0);
    }

    protected void checkAndPerformAttack(EntityLivingBase p_190102_1_, double p_190102_2_) {
        double d0 = this.getAttackReachSqr(p_190102_1_);

        if (p_190102_2_ <= d0 && this.attackTick <= 0) {
            float f3 = 0.6F * (float) attacker.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
            //Range attack on other enemy mobs
            for (EntityMob entitymob : this.world.getEntitiesWithinAABB(EntityMob.class, attacker.getAttackTarget().getEntityBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
                if (entitymob != attacker && entitymob != attacker.getAttackTarget() && !entitymob.isOnSameTeam(entitymob) && entitymob.getDistanceSq(entitymob) < 9.0D) {
                    entitymob.knockBack(attacker, 0.4F, (double) MathHelper.sin(attacker.rotationYaw * 0.017453292F), (double) (-MathHelper.cos(attacker.rotationYaw * 0.017453292F)));
                    entitymob.attackEntityFrom(DamageSource.causeMobDamage(attacker), ((Float) f3));
                }
            }
            this.world.playSound((EntityPlayer) null, attacker.posX, attacker.posY, attacker.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, attacker.getSoundCategory(), 1.0F, 1.0F);

            this.spawnSweepParticles();
            this.attackTick = 16;
            this.attacker.swingArm(EnumHand.MAIN_HAND);
            this.attacker.attackEntityAsMob(p_190102_1_);
        }
    }

    //スイープ攻撃時ののエフェクト
    public void spawnSweepParticles() {
        double d0 = (double) (-MathHelper.sin(attacker.rotationYaw * 0.017453292F));
        double d1 = (double) MathHelper.cos(attacker.rotationYaw * 0.017453292F);

        if (this.world instanceof WorldServer) {
            ((WorldServer) this.world).spawnParticle(EnumParticleTypes.SWEEP_ATTACK, attacker.posX + d0, attacker.posY + (double) attacker.height * 0.5D, attacker.posZ + d1, 0, d0, 0.0D, d1, 0.0D);
        }
    }

    protected double getAttackReachSqr(EntityLivingBase attackTarget) {
        return (double) (this.attacker.width * 2.82F * this.attacker.width * 2.82F + attackTarget.width);
    }
}