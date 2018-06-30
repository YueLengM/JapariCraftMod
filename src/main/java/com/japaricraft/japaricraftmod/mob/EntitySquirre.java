package com.japaricraft.japaricraftmod.mob;

import com.google.common.collect.Sets;
import com.japaricraft.japaricraftmod.handler.JapariItems;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class EntitySquirre extends EntityFriend {
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.APPLE, JapariItems.japariman, JapariItems.japarimanapple, JapariItems.japarimancocoa, JapariItems.japarimanfruit);

    private static final DataParameter<Boolean> SLEEPING = EntityDataManager.createKey(EntitySquirre.class, DataSerializers.BOOLEAN);

    private boolean isSleeping;

    public EntitySquirre(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.5F);
        this.setTamed(false);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }


    protected void initEntityAI() {
        super.initEntityAI();
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 11.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityCerulean.class, false));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityBlackCerulean.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityCeruleanEye.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityEnderCerulean.class, false));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(22D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SLEEPING, Boolean.FALSE);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Sleeping", this.isSleeping());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSleeping(compound.getBoolean("Sleeping"));
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }


    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    public Item getDropItem() {

        return null;//なにも落とさない
    }

    public boolean isSleeping() {
        if (world.isRemote) {
            boolean isSleeping = this.dataManager.get(SLEEPING);
            this.isSleeping = isSleeping;
            return isSleeping;
        }
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.dataManager.set(SLEEPING, sleeping);
        if (!world.isRemote) {
            this.isSleeping = sleeping;
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!world.isRemote && !this.isInWater() && !this.isSleeping() && this.getRNG().nextInt(250) == 0 && !this.isRiding() && (!this.isTamed() && this.getAttackTarget() == null || this.isTamed() && this.isSitting()) || this.onGround && this.getAttackTarget() == null && !this.world.isDaytime()) {
            setSleeping(true);
        }
        if (!world.isRemote && this.isSleeping() && (this.isRiding() || !this.isSitting() && this.isTamed() || this.isInWater() || (this.world.canBlockSeeSky(new BlockPos(this)) && this.world.isDaytime()) || this.getAttackTarget() != null)) {
            setSleeping(false);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.isSleeping()) {
            this.getNavigator().clearPath();
        }
    }


    @Override
    public boolean canDespawn() {
        return false;
    }


}
