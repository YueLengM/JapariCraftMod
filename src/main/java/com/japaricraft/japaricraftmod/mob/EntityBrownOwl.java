package com.japaricraft.japaricraftmod.mob;

import com.google.common.collect.Sets;
import com.japaricraft.japaricraftmod.JapariCraftMod;
import com.japaricraft.japaricraftmod.advancements.AchievementsJapari;
import com.japaricraft.japaricraftmod.handler.JapariItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Set;

public class EntityBrownOwl extends EntityFriend {

    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(JapariItems.curry,Items.RABBIT_STEW,Items.MUSHROOM_STEW);
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    private float wingRotDelta = 1.0F;

    public EntityBrownOwl(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.8F);
        this.setTamed(false);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }

    @Override
    protected void initEntityAI()  {
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest2(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityCerulean.class, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityBlackCerulean.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityCeruleanEye.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityEnderCerulean.class, false));


    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    protected void updateAITasks()
    {
        if (this.ticksExisted % 5 == 0)
        {
            this.heal(0.06F);
        }
    }
    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(26.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(22D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);

        if (this.isTamed() && !this.isRiding())
        {
            if(player.isSneaking()&&!this.isSitting()){
                player.openGui(JapariCraftMod.instance,JapariCraftMod.ID_JAPARI_INVENTORY,this.getEntityWorld(), this.getEntityId(), 0, 0);
            }
            if (!stack.isEmpty()) {
                if (this.isOwner(player) && TAME_ITEMS.contains(stack.getItem())) {
                    ItemFood itemfood = (ItemFood) stack.getItem();
                    if(this.getHealth()<this.getMaxHealth()) {
                        if (!player.capabilities.isCreativeMode) {
                            stack.shrink(1);
                        }

                        this.heal((float) itemfood.getHealAmount(stack));
                        this.playSound(SoundEvents.ENTITY_GENERIC_EAT, this.getSoundVolume(), this.getSoundPitch());

                        for (int i = 0; i < 7; ++i) {
                            double d0 = this.rand.nextGaussian() * 0.02D;
                            double d1 = this.rand.nextGaussian() * 0.02D;
                            double d2 = this.rand.nextGaussian() * 0.02D;
                            this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.5D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
                        }
                        return true;
                    }
                }
                if (!this.isRiding() && player.getPassengers().size() <= 0 && this.isOwner(player) && stack.getItem() == Items.SADDLE) {
                    this.startRiding(player, true);

                    return true;
                }

                if (this.isOwner(player) && stack.getItem() == JapariItems.wildliberationpotion) {
                    if (!player.capabilities.isCreativeMode) {
                        stack.shrink(1);
                    }
                    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getMaxHealth() + 4.0D);
                    this.playSound(SoundEvents.ENTITY_GENERIC_DRINK, this.getSoundVolume(), this.getSoundPitch());

                    for (int i = 0; i < 7; ++i) {
                        double d0 = this.rand.nextGaussian() * 0.02D;
                        double d1 = this.rand.nextGaussian() * 0.02D;
                        double d2 = this.rand.nextGaussian() * 0.02D;
                        this.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 0.8D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
                    }
                    return true;
                }
            }
            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack))
            {
                this.aiSit.setSitting(!this.isSitting());
                return true;
            }
        }
        else if (!this.isTamed() && TAME_ITEMS.contains(stack.getItem()))
        {
            if (!player.capabilities.isCreativeMode)
            {
                stack.setCount(stack.getCount()-1);
            }

            if (!this.world.isRemote)
            {
                if (this.rand.nextInt(2) == 0)
                {
                    this.setTamed(true);
                    this.setOwnerId(player.getUniqueID());
                    this.playTameEffect(true);
                    this.aiSit.setSitting(true);
                    this.world.setEntityState(this, (byte)7);
                    AchievementsJapari.grantAdvancement(player, "tame_friends");
                }
                else
                {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte)6);
                }



            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public double getYOffset() {
        return this.getRidingEntity() != null ? -0.40D : 0.0D;
    }


    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.calculateFlapping();
    }

    private void calculateFlapping() {
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

        if (!this.onGround && this.wingRotDelta < 1.0F) {
            this.wingRotDelta = 1.0F;
        }

        this.wingRotDelta = (float)((double)this.wingRotDelta * 0.9D);

        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }

        this.wingRotation += this.wingRotDelta * 2.0F;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        //when get attacktaget
        //もしアタックターゲットを見つけたら
        if (!isOnLadder() && this.getAttackTarget() != null) {
            double a = this.getAttackTarget().posX - posX;
            double b = this.getAttackTarget().posZ - posZ;
            double d3 = a * a + b * b;
            d3 = (double) MathHelper.sqrt(d3);

            if ((this.getAttackTarget().posY > posY)) {
                this.motionY += 0.09F;
            }
            if (!onGround) {
                this.motionX += a / d3 * 0.09D * this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
                this.motionZ += b / d3 * 0.09D * this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
                this.rotationYaw = -((float) MathHelper.atan2(a, b)) * (180F / (float) Math.PI);
            }
        }
        //フクロウが(いろんな意味で)乗ってる時に特定の操作で下ろす処理
        if (this.isRiding()) {
            final Entity entity = this.getRidingEntity();

            if (entity.isSneaking() && entity.onGround) {
                this.dismountRidingEntity();
            }

            if (entity.motionY < 0) {
                entity.motionY *= entity.isSneaking() ? 0.9D : 0.7D;

                entity.fallDistance = 0;
            }

        }

    }

    @Override
    public void updateRidden() {
        super.updateRidden();

        if (getRidingEntity() instanceof EntityPlayer) {
            EntityPlayer lep = (EntityPlayer) getRidingEntity();

            renderYawOffset = lep.renderYawOffset;
            prevRenderYawOffset = lep.prevRenderYawOffset;

            renderYawOffset = lep.renderYawOffset;
            if (((rotationYaw - renderYawOffset) % 360F) > 90F) {
                rotationYaw = renderYawOffset + 90F;
            }
            if (((rotationYaw - renderYawOffset) % 360F) < -90F) {
                rotationYaw = renderYawOffset - 90F;
            }
            if (((rotationYawHead - renderYawOffset) % 360F) > 90F) {
                rotationYawHead = renderYawOffset + 90F;
            }
            if (((rotationYawHead - renderYawOffset) % 360F) < -90F) {
                rotationYawHead = renderYawOffset - 90F;
            }
            double dx, dz;
            dx = Math.sin((lep.renderYawOffset * Math.PI) / 180D) * 0.35;
            dz = Math.cos((lep.renderYawOffset * Math.PI) / 180D) * 0.35;
            posX += dx;
            posZ -= dz;
        }
    }

    @Override
    public boolean canBeAttackedWithItem() {
        if (getRidingEntity() != null && getRidingEntity() == this.getOwner()) {
            return false;
        }
        return super.canBeAttackedWithItem();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    public void fall(float distance, float damageMultiplier)
    {
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.UNDEFINED; }

    @Override
    public Item getDropItem () {

        return null;//なにも落とさない
    }
    @Override
    protected void dropFewItems(boolean parRecentlyHit, int parLootingLevel) {
        {
            this.entityDropItem(new ItemStack(Items.FEATHER, 2, 0), 0.0F);

        }
    }

    @Override
    public boolean canDespawn()
    {
        return false;
    }

}
