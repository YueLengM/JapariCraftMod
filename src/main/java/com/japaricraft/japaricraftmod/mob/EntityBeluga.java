package com.japaricraft.japaricraftmod.mob;

import com.japaricraft.japaricraftmod.mob.ai.EntityAIWaterFollowOwner;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityBeluga extends EntityFriend {
    protected EntityAIWander wander;

    public EntityBeluga(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.6F);
        this.setTamed(false);
        this.moveHelper = new EntityBeluga.BelugaMoveHelper(this);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);
        this.wander = new EntityAIWander(this, 1.0D, 80);

        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.05D, true));
        this.tasks.addTask(4, new EntityAIWaterFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(5, this.wander);
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F, 1.0F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityCerulean.class, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityBlackCerulean.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityCeruleanEye.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityEnderCerulean.class, false));
        this.wander.setMutexBits(1);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    @Override
    public void onLivingUpdate() {
        if (this.inWater) {
            this.setAir(300);
        }
        super.onLivingUpdate();
    }

    public Item getDropItem() {

        return null;//なにも落とさない
    }


    public boolean canDespawn() {
        return false;
    }

    public float getBlockPathWeight(BlockPos pos) {
        return world.getBlockState(pos).getMaterial() == Material.WATER ? 10F : super.getBlockPathWeight(pos);
    }

    public boolean isNotColliding() {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty();
    }

    public boolean getCanSpawnHere() {
        return (this.rand.nextInt(20) == 0 || !this.world.canBlockSeeSky(new BlockPos(this))) && super.getCanSpawnHere();
    }

    public int getVerticalFaceSpeed() {
        return 180;
    }

    public void travel(float strafe, float vertical, float forward) {
        if (this.isServerWorld() && this.isInWater()) {
            this.moveRelative(strafe, vertical, forward, 0.1F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.8999999761581421D;
            this.motionY *= 1D;
            this.motionZ *= 0.8999999761581421D;

        } else {
            super.travel(strafe, vertical, forward);
        }
    }

    static class BelugaMoveHelper extends EntityMoveHelper {
        private final EntityBeluga entityBeluga;

        public BelugaMoveHelper(EntityBeluga beluga) {
            super(beluga);
            this.entityBeluga = beluga;
        }

        public void onUpdateMoveHelper() {
            if (this.entityBeluga.isInWater()) {
                if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.entityBeluga.getNavigator().noPath()) {
                    double d0 = this.posX - this.entityBeluga.posX;
                    double d1 = this.posY - this.entityBeluga.posY;
                    double d2 = this.posZ - this.entityBeluga.posZ;
                    double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    d1 = d1 / d3;
                    float f = (float) (MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
                    this.entityBeluga.rotationYaw = this.limitAngle(this.entityBeluga.rotationYaw, f, 90.0F);
                    this.entityBeluga.renderYawOffset = this.entityBeluga.rotationYaw;
                    float f1 = (float) (this.speed * this.entityBeluga.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                    this.entityBeluga.setAIMoveSpeed(this.entityBeluga.getAIMoveSpeed() + (f1 - this.entityBeluga.getAIMoveSpeed()) * 0.125F);
                    double d4 = Math.sin((double) (this.entityBeluga.ticksExisted + this.entityBeluga.getEntityId()) * 0.5D) * 0.05D;
                    double d5 = Math.cos((double) (this.entityBeluga.rotationYaw * 0.017453292F));
                    double d6 = Math.sin((double) (this.entityBeluga.rotationYaw * 0.017453292F));
                    this.entityBeluga.motionX += d4 * d5;
                    this.entityBeluga.motionZ += d4 * d6;
                    d4 = Math.sin((double) (this.entityBeluga.ticksExisted + this.entityBeluga.getEntityId()) * 0.75D) * 0.05D;
                    this.entityBeluga.motionY += d4 * (d6 + d5) * 0.25D;
                    this.entityBeluga.motionY += (double) this.entityBeluga.getAIMoveSpeed() * d1 * 0.1D;
                    EntityLookHelper entitylookhelper = this.entityBeluga.getLookHelper();
                    double d7 = this.entityBeluga.posX + d0 / d3 * 2.0D;
                    double d8 = (double) this.entityBeluga.getEyeHeight() + this.entityBeluga.posY + d1 / d3;
                    double d9 = this.entityBeluga.posZ + d2 / d3 * 2.0D;
                    double d10 = entitylookhelper.getLookPosX();
                    double d11 = entitylookhelper.getLookPosY();
                    double d12 = entitylookhelper.getLookPosZ();

                    if (!entitylookhelper.getIsLooking()) {
                        d10 = d7;
                        d11 = d8;
                        d12 = d9;
                    }

                    this.entityBeluga.getLookHelper().setLookPosition(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
                } else {
                    this.entityBeluga.setAIMoveSpeed(0.0F);
                }
            } else {
                super.onUpdateMoveHelper();
            }
        }
    }
}