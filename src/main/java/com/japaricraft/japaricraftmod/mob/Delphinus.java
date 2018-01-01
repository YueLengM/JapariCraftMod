package com.japaricraft.japaricraftmod.mob;

import com.japaricraft.japaricraftmod.mob.ai.EntityFriend;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;

public class Delphinus extends EntityFriend {
    //動くためのdatamanager
    private static final DataParameter<Boolean> MOVING = EntityDataManager.<Boolean>createKey(Delphinus.class, DataSerializers.BOOLEAN);
    protected EntityAIWander wander;

    public Delphinus(World worldIn) {
        super(worldIn);
        this.experienceValue = 10;
        this.setSize(0.85F, 0.85F);
        if (isInWater()) {
            this.moveHelper = new Delphinus.DelphinusMoveHelper(this);
        } else {
            this.moveHelper = new EntityMoveHelper(this);
        }
    }

    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);
        EntityAIMoveTowardsRestriction entityaimovetowardsrestriction = new EntityAIMoveTowardsRestriction(this, 1.0D);
        this.wander = new EntityAIWander(this, 1.0D, 80);
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(5, entityaimovetowardsrestriction);
        this.tasks.addTask(6, this.wander);
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this) {
            public boolean apply(@Nullable EntityLiving p_apply_1_) {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityFriend);
            }
        });
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, Cerulean.class, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, CeruleanBird.class, false));
        this.wander.setMutexBits(3);
        entityaimovetowardsrestriction.setMutexBits(3);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.45D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(22.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
    }

    //registerFixesDelphinusのところはいじらないで
    public static void registerFixesDelphinus(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, Delphinus.class);
    }

    /**
     * Returns new PathNavigateGround instance
     */
    protected PathNavigate createNavigator(World worldIn) {
        if (this.isInWater()) {
            return new PathNavigateSwimmer(this, worldIn);
        } else {
            return new PathNavigateGround(this, worldIn);
        }
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(MOVING, Boolean.valueOf(false));
    }

    public boolean isMoving() {
        return this.dataManager.get(MOVING);
    }

    private void setMoving(boolean moving) {
        this.dataManager.set(MOVING, Boolean.valueOf(moving));
    }


    public void notifyDataManagerChange(DataParameter<?> key) {
        super.notifyDataManagerChange(key);

    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval() {
        return 160;
    }

    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? SoundEvents.ENTITY_GUARDIAN_AMBIENT : SoundEvents.ENTITY_GUARDIAN_AMBIENT_LAND;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return this.isInWater() ? SoundEvents.ENTITY_GUARDIAN_HURT : SoundEvents.ENTITY_GUARDIAN_HURT_LAND;
    }

    protected SoundEvent getDeathSound() {
        return this.isInWater() ? SoundEvents.ENTITY_GUARDIAN_DEATH : SoundEvents.ENTITY_GUARDIAN_DEATH_LAND;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking() {
        return true;
    }

    public float getEyeHeight() {
        return this.height * 0.5F;
    }

    public float getBlockPathWeight(BlockPos pos) {
        return this.world.getBlockState(pos).getMaterial() == Material.WATER ? 10.0F + this.world.getLightBrightness(pos) - 0.5F : super.getBlockPathWeight(pos);
    }

    public void onLivingUpdate() {
        if (this.world.isRemote) {
            //水の中で動いてるとき泡のパーティクルを出す
            if (this.isMoving() && this.isInWater()) {
                Vec3d vec3d = this.getLook(0.0F);

                for (int i = 0; i < 2; ++i) {
                    this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + (this.rand.nextDouble() - 0.5D) * (double) this.width - vec3d.x * 1.5D, this.posY + this.rand.nextDouble() * (double) this.height - vec3d.y * 1.5D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double) this.width - vec3d.z * 1.5D, 0.0D, 0.0D, 0.0D);
                }
            }


        }
        //水の中でも呼吸できるように
        if (this.inWater) {
            this.setAir(300);
        }
        super.onLivingUpdate();
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_GUARDIAN_FLOP;
    }


    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_GUARDIAN;
    }

    /**
     * Checks that the entity is not colliding with any blocks / liquids
     */
    public boolean isNotColliding() {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty();
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere() {
        return (this.rand.nextInt(20) == 0 || !this.world.canBlockSeeSky(new BlockPos(this))) && super.getCanSpawnHere();
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {

        if (this.wander != null) {
            this.wander.makeUpdate();
        }

        return super.attackEntityFrom(source, amount);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    /**
     * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
     * use in wolves.
     */
    public int getVerticalFaceSpeed() {
        return 180;
    }

    public void travel(float strafe, float vertical, float forward) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(strafe, vertical, forward, 0.1F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.8999999761581421D;
            this.motionY *= 0.8999999761581421D;
            this.motionZ *= 0.8999999761581421D;

            if (!this.isMoving() && this.getAttackTarget() == null) {
                this.motionY -= 0.005D;
            }
        } else {
            super.travel(strafe, vertical, forward);
        }
    }


    static class DelphinusMoveHelper extends EntityMoveHelper {
        private final Delphinus entityDelphinus;

        public DelphinusMoveHelper(Delphinus delphinus) {
            super(delphinus);
            this.entityDelphinus = delphinus;
        }

        public void onUpdateMoveHelper() {
            if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.entityDelphinus.getNavigator().noPath()) {
                double d0 = this.posX - this.entityDelphinus.posX;
                double d1 = this.posY - this.entityDelphinus.posY;
                double d2 = this.posZ - this.entityDelphinus.posZ;
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float) (MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
                this.entityDelphinus.rotationYaw = this.limitAngle(this.entityDelphinus.rotationYaw, f, 90.0F);
                this.entityDelphinus.renderYawOffset = this.entityDelphinus.rotationYaw;
                float f1 = (float) (this.speed * this.entityDelphinus.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                this.entityDelphinus.setAIMoveSpeed(this.entityDelphinus.getAIMoveSpeed() + (f1 - this.entityDelphinus.getAIMoveSpeed()) * 0.125F);
                double d4 = Math.sin((double) (this.entityDelphinus.ticksExisted + this.entityDelphinus.getEntityId()) * 0.5D) * 0.05D;
                double d5 = Math.cos((double) (this.entityDelphinus.rotationYaw * 0.017453292F));
                double d6 = Math.sin((double) (this.entityDelphinus.rotationYaw * 0.017453292F));
                this.entityDelphinus.motionX += d4 * d5;
                this.entityDelphinus.motionZ += d4 * d6;
                d4 = Math.sin((double) (this.entityDelphinus.ticksExisted + this.entityDelphinus.getEntityId()) * 0.75D) * 0.05D;
                this.entityDelphinus.motionY += d4 * (d6 + d5) * 0.25D;
                this.entityDelphinus.motionY += (double) this.entityDelphinus.getAIMoveSpeed() * d1 * 0.1D;
                EntityLookHelper entitylookhelper = this.entityDelphinus.getLookHelper();
                double d7 = this.entityDelphinus.posX + d0 / d3 * 2.0D;
                double d8 = (double) this.entityDelphinus.getEyeHeight() + this.entityDelphinus.posY + d1 / d3;
                double d9 = this.entityDelphinus.posZ + d2 / d3 * 2.0D;
                double d10 = entitylookhelper.getLookPosX();
                double d11 = entitylookhelper.getLookPosY();
                double d12 = entitylookhelper.getLookPosZ();

                if (!entitylookhelper.getIsLooking()) {
                    d10 = d7;
                    d11 = d8;
                    d12 = d9;
                }

                this.entityDelphinus.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
                this.entityDelphinus.setMoving(true);
            } else {
                this.entityDelphinus.setAIMoveSpeed(0.0F);
                this.entityDelphinus.setMoving(false);
            }
        }
    }

}