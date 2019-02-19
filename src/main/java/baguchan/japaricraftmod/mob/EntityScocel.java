package baguchan.japaricraftmod.mob;

import baguchan.japaricraftmod.handler.*;
import baguchan.japaricraftmod.mob.projectile.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import javax.annotation.*;

public class EntityScocel extends EntityMob {


    public EntityScocel(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 0.55F);
        this.experienceValue = 5;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityScocel.AIAttackAndPoisonSpit(this));
        this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityScocel.class));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityRabbit.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityFriend.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(26.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
    }

    public void onLivingUpdate() {
        if (this.world.isRemote) {
            if (this.rand.nextInt(520) == 0 && this.deathTime == 0) {
                //random heal
                this.heal(1.0F);
            }
        }
        super.onLivingUpdate();
    }

    @Override
    protected float getSoundPitch() {
        return 0.75F + 0.1F * this.rand.nextFloat();
    }

    @Override
    protected float getSoundVolume() {
        return 0.9F;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
            if (entityIn instanceof EntityLivingBase) {
                int i = 0;

                if (this.world.getDifficulty() == EnumDifficulty.EASY) {
                    i = 3;
                } else if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
                    i = 5;
                } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
                    i = 8;
                }

                if (i > 0) {
                    ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, i * 20, 0));
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return JapariTreasure.scocel;
    }

    private class AIAttackAndPoisonSpit extends EntityAIBase {
        private final EntityScocel scorger;
        private int attackStep;
        private int spitTime;
        private boolean strafingClockwise;
        private boolean strafingBackwards;


        public AIAttackAndPoisonSpit(EntityScocel scorgerIn) {
            this.scorger = scorgerIn;
            this.setMutexBits(3);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.scorger.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.attackStep = 0;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask() {
            --this.spitTime;
            EntityLivingBase entitylivingbase = this.scorger.getAttackTarget();
            double d0 = this.scorger.getDistanceSq(entitylivingbase);

            if (d0 < 3.3D) {
                if (this.spitTime <= 0) {
                    this.spitTime = 20;
                    this.scorger.attackEntityAsMob(entitylivingbase);
                }

            } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && d0 > 30.0D) {
                //近くに行くと近接攻撃してくる。遠ざかると遠距離攻撃してくる
                //If you go nearby it will come near melee attack. As you move away you will ranged attack.
                if (d0 > 145.0D) {
                    this.scorger.getNavigator().tryMoveToEntityLiving(entitylivingbase, 0.9D);
                } else if (d0 < 135.0D) {
                    this.scorger.getNavigator().clearPath();
                    this.scorger.getMoveHelper().strafe(this.strafingBackwards ? -0.6F : -0.6F, this.strafingClockwise ? 0.6F : -0.6F);
                }
                if (this.spitTime <= 0) {
                    ++this.attackStep;

                    if (this.attackStep == 1) {
                        this.spitTime = 60;
                    } else if (this.attackStep <= 4) {
                        this.spitTime = 6;
                    } else {
                        this.spitTime = 100;
                        this.attackStep = 0;
                    }

                    if (this.attackStep > 1) {
                        for (int i = 0; i < 1; ++i) {
                            EntityPoisonBall poisonBall = new EntityPoisonBall(this.scorger.world, this.scorger);
                            //VanillaCopy
                            double d4 = entitylivingbase.posX - this.scorger.posX;
                            double d3 = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height / 2.0F) - (this.scorger.posY + (double) (this.scorger.height / 2.0F));
                            double d2 = entitylivingbase.posZ - this.scorger.posZ;
                            float f2 = MathHelper.sqrt(d4 * d4 + d2 * d2) * 0.2F;
                            poisonBall.shoot(d4, d3 + (double) f2, d2, 0.65F, 0.12F);
                            this.scorger.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, 1.0F / (this.scorger.getRNG().nextFloat() * 0.4F + 0.8F));
                            this.scorger.world.spawnEntity(poisonBall);
                        }
                    }
                }

                this.scorger.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            } else if (this.scorger.getHealth() > this.scorger.getMaxHealth() / 3 || this.attackStep <= 2) {
                this.scorger.getNavigator().tryMoveToEntityLiving(entitylivingbase, 1.0D);

            } else {
                //stay back! health is not good !
                this.scorger.getNavigator().clearPath();
                this.scorger.getMoveHelper().strafe(this.strafingBackwards ? -1.2F : -1.2F, this.strafingClockwise ? 1.2F : -1.2F);
            }


            super.updateTask();
        }

        private double getFollowDistance() {
            IAttributeInstance iattributeinstance = this.scorger.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
        }
    }

}
