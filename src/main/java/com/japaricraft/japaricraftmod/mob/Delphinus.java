package com.japaricraft.japaricraftmod.mob;

import com.google.common.collect.Sets;
import com.japaricraft.japaricraftmod.JapariCraftMod;
import com.japaricraft.japaricraftmod.mob.ai.EntityFriend;
import com.japaricraft.japaricraftmod.mob.ai.EntityWaterAIFollowOwner;
import com.japaricraft.japaricraftmod.mob.ai.EntityWaterAISit;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Set;

public class Delphinus extends EntityFriend {
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.FISH, Items.COOKED_FISH);

    protected EntityWaterAISit aiWaterSit;

    private static final DataParameter<Boolean> MOVING = EntityDataManager.createKey(Delphinus.class, DataSerializers.BOOLEAN);
    protected EntityAIWander wander;

    public Delphinus(World worldIn) {
        super(worldIn);
        this.experienceValue = 10;
        this.setSize(0.7F, 0.8F);
        this.moveHelper = new Delphinus.DelphinusMoveHelper(this);
    }

    protected void initEntityAI() {
        this.aiWaterSit = new EntityWaterAISit(this);
        EntityAIMoveTowardsRestriction entityaimovetowardsrestriction = new EntityAIMoveTowardsRestriction(this, 1.0D);
        this.wander = new EntityAIWander(this, 1.0D, 80);
        this.tasks.addTask(1, this.aiWaterSit);
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(3, new EntityWaterAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(4, entityaimovetowardsrestriction);
        this.tasks.addTask(5, this.wander);
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityCreature.class, 12.0F, 0.01F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this) {
            public boolean apply(@Nullable EntityLiving p_apply_1_) {
                return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_) && !(p_apply_1_ instanceof EntityFriend);
            }
        });
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.wander.setMutexBits(1);
        entityaimovetowardsrestriction.setMutexBits(1);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.45D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(22.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
    }


    @Override
    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateSwimmer(this, worldIn);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(MOVING, Boolean.FALSE);
    }

    public boolean isMoving() {
        return this.dataManager.get(MOVING);
    }

    private void setMoving(boolean moving) {
        this.dataManager.set(MOVING, moving);
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval() {
        return 160;
    }


    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking() {
        return false;
    }

    protected boolean isValidLightLevel() {
        return true;
    }


    public float getBlockPathWeight(BlockPos pos) {
        return this.world.getBlockState(pos).getMaterial() == Material.WATER ? 10.0F + this.world.getLightBrightness(pos) - 0.5F : super.getBlockPathWeight(pos);
    }

    protected void updateAITasks() {
        if (this.ticksExisted % 5 == 0) {
            this.heal(0.06F);
        }
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
        } else if (this.onGround) {
            this.motionY += 0.5D;
            this.motionX += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
            this.motionZ += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
            this.rotationYaw = this.rand.nextFloat() * 360.0F;
            this.onGround = false;
            this.isAirBorne = true;
        }
        super.onLivingUpdate();
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_GUARDIAN_FLOP;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (this.isTamed()) {
            if (player.isSneaking() && !this.isSitting()) {
                player.openGui(JapariCraftMod.instance, JapariCraftMod.ID_JAPARI_INVENTORY, this.getEntityWorld(), this.getEntityId(), 0, 0);
            }
            if (!stack.isEmpty()) {
                if (this.isOwner(player) && TAME_ITEMS.contains(stack.getItem())) {
                    ItemFood itemfood = (ItemFood) stack.getItem();
                    if (this.getHealth() < this.getMaxHealth()) {
                        if (!player.capabilities.isCreativeMode) {
                            stack.shrink(1);
                        }

                        this.heal((float) itemfood.getHealAmount(stack));
                        for (int i = 0; i < 7; ++i) {
                            double d0 = this.rand.nextGaussian() * 0.02D;
                            double d1 = this.rand.nextGaussian() * 0.02D;
                            double d2 = this.rand.nextGaussian() * 0.02D;
                            this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
                        }
                        return true;
                    }
                }
            }
            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack)) {
                this.aiWaterSit.setSitting(!this.isSitting());
                return true;
            }
        } else if (!this.isTamed() && TAME_ITEMS.contains(stack.getItem())) {
            if (!player.capabilities.isCreativeMode) {
                stack.setCount(stack.getCount() - 1);
            }

            if (!this.world.isRemote) {
                if (this.rand.nextInt(3) == 0) {
                    this.setTamed(true);
                    this.setOwnerId(player.getUniqueID());
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte) 7);
                } else {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte) 6);
                }


            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public boolean isNotColliding() {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty();
    }

    @Override
    public boolean isPushedByWater() {
        return false;
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