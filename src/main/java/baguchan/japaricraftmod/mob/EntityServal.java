package baguchan.japaricraftmod.mob;

import baguchan.japaricraftmod.handler.JapariItems;
import baguchan.japaricraftmod.mob.ai.EntityAIAttackSweep;
import baguchan.japaricraftmod.mob.ai.EntityAIFriendCollectItem;
import baguchan.japaricraftmod.mob.ai.EntityAIServalBeg;
import com.google.common.collect.Sets;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;


public class EntityServal extends EntityFriend {
    private static final DataParameter<Boolean> BEGGING = EntityDataManager.createKey(EntityServal.class, DataSerializers.BOOLEAN);

    public static final Set<Item> TAME_ITEMS = Sets.newHashSet(JapariItems.japariman, JapariItems.japarimanapple, JapariItems.japarimancocoa, JapariItems.japarimanfruit);
    private static final DataParameter<Boolean> STRETCHING = EntityDataManager.createKey(EntityServal.class, DataSerializers.BOOLEAN);

    private float headRotationCourse;
    private float headRotationCourseOld;
    private boolean isStretching;

    public EntityServal(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.6F);
        this.setTamed(false);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }


    protected void initEntityAI() {
        super.initEntityAI();
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(2, new EntityAIAttackSweep(this, 1.16D, true));
        this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.1D, 10.0F, 2.0F));
        this.tasks.addTask(5, new EntityAIFriendCollectItem(this, 1.0F));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIServalBeg(this, 8.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityCerulean.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityCeruleanEye.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityEnderCerulean.class, false));

    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(BEGGING, Boolean.FALSE);
        this.dataManager.register(STRETCHING, Boolean.FALSE);
    }


    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Stretching", this.isStretching());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setStretching(compound.getBoolean("Stretching"));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.headRotationCourseOld = this.headRotationCourse;

        if (this.isBegging()) {
            this.headRotationCourse += (1.0F - this.headRotationCourse) * 0.4F;
        } else {
            this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
        }

        if (!world.isRemote && !this.isInWater() && !this.isStretching() && this.getRNG().nextInt(520) == 0 && !this.isRiding() && !this.isTamed() && (this.onGround && this.getAttackTarget() == null)) {
            setStretching(true);
        }
        if (!world.isRemote && this.isStretching() && (this.isRiding() || !this.isSitting() && this.isTamed() || this.isInWater() || this.getAttackTarget() != null || this.getRNG().nextInt(120) == 0 && !this.isTamed() || this.getRNG().nextInt(120) == 0 && this.isTamed())) {
            setStretching(false);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.isStretching()) {
            this.getNavigator().clearPath();
        }
    }

    public boolean isStretching() {
        if (world.isRemote) {
            boolean isStretching = this.dataManager.get(STRETCHING);
            this.isStretching = isStretching;
            return isStretching;
        }
        return isStretching;
    }

    public void setStretching(boolean stretching) {
        this.dataManager.set(STRETCHING, stretching);
        if (!world.isRemote) {
            this.isStretching = stretching;
        }
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float p_70917_1_) {
        return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_70917_1_) * 0.15F * (float) Math.PI;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CAT_DEATH;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CAT_AMBIENT;
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    public Item getDropItem() {

        return null;//なにも落とさない
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {

        if (source == DamageSource.FALL)
        {

            damage *= 0.4F;

        }

        return super.attackEntityFrom(source, damage);
    }


    public boolean isBegging() {
        return this.dataManager.get(BEGGING);
    }

    public void setBegging(boolean beg) {
        this.dataManager.set(BEGGING, beg);
    }


    @Override
    public boolean canDespawn() {
        return false;
    }


    @SideOnly(Side.CLIENT)
    public EntityFriend.ArmPose getArmPose() {
        if (this.isAttacking() && (this.motionX > -0.1D || this.motionZ > -0.1D)) {
            return EntityFriend.ArmPose.ATTACKING;
        } else {
            return EntityFriend.ArmPose.NORMAL;
        }
    }

}
