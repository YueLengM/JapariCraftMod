package baguchan.japaricraftmod.mob;


import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityCerulean extends EntityMob {
    private static final DataParameter<Integer> CERULEAN_SIZE = EntityDataManager.createKey(EntityCerulean.class, DataSerializers.VARINT);

    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(JapariCraftMod.MODID, "entitys/cerulean");

    public EntityCerulean(World worldIn)
    {
        super(worldIn);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
    }

    protected void initEntityAI(){

        this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.45F));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false) {
            protected double getAttackReachSqr(EntityLivingBase attackTarget) {
                return (double) (this.attacker.width * 1.2F * this.attacker.width * 1.2F + attackTarget.width);
            }
        });
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPigZombie.class));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityFriend.class, true));
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(CERULEAN_SIZE, Integer.valueOf(1));
    }

    protected void setCeruleanSize(int size, boolean resetHealth) {
        this.dataManager.set(CERULEAN_SIZE, Integer.valueOf(size));
        this.setSize(0.43000005F + 0.14F * (float) size, 0.71000005F + 0.18F * (float) size);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((1.0D + (2.1F * size) - (0.85F * size)));
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) (0.18F + 0.005F * (float) size));
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((this.getAttackStrength() - 1) * 0.5);

        if (size > 1) {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(size * 0.5F);
        }

        if (resetHealth) {
            this.setHealth(this.getMaxHealth());
        }

        this.experienceValue = size + size;
    }

    public int getCeruleanSize() {
        return this.dataManager.get(CERULEAN_SIZE).intValue();
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Size", this.getCeruleanSize() - 1);
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        int i = compound.getInteger("Size");

        if (i < 0) {
            i = 0;
        }

        this.setCeruleanSize(i + 1, false);
    }

    public float getEyeHeight() {
        return 0.625F * this.height;
    }

    protected int getAttackStrength() {
        return this.getCeruleanSize();
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (CERULEAN_SIZE.equals(key)) {
            int i = this.getCeruleanSize();
            this.setSize(0.43000005F + 0.14F * (float) i, 0.71000005F + 0.18F * (float) i);
            this.rotationYaw = this.rotationYawHead;
            this.renderYawOffset = this.rotationYawHead;

            if (this.isInWater() && this.rand.nextInt(20) == 0) {
                this.doWaterSplashEffect();
            }
        }

        super.notifyDataManagerChange(key);
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SLIME_DEATH;
    }
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SLIME_SQUISH;
    }


    protected void updateAITasks()
    {
        if (this.isWet())
        {
            this.attackEntityFrom(DamageSource.DROWN, 8.0F);
        }

        super.updateAITasks();
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        int i = this.rand.nextInt(4);

        if (i < 2 && this.rand.nextFloat() < 0.5F * difficulty.getClampedAdditionalDifficulty()) {
            ++i;
        }

        int j = 1 << i;
        this.setCeruleanSize(j, true);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public boolean getCanSpawnHere() {
        return !this.world.isRaining() && super.getCanSpawnHere();
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }


    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }
}