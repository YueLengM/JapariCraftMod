package baguchan.japaricraftmod.mob;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityTutinoko extends EntityFriend {
    private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.createKey(EntityTutinoko.class, DataSerializers.VARINT);

    private EntityLivingBase targetedEntity;
    private int clientSideAttackTime;

    public EntityTutinoko(World worldIn) {
        super(worldIn);
        this.setSize(0.59F, 1.7F);
        this.setTamed(false);
        ((PathNavigateGround) this.getNavigator()).setBreakDoors(true);
    }

    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiSit);
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.95D, true));
        this.tasks.addTask(3, new EntityTutinoko.AIBeamAttack(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.1D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityCerulean.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityCeruleanEye.class, false));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntityEnderCerulean.class, false));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(26.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(TARGET_ENTITY, 0);
    }

    private void setTargetedEntity(int entityId) {
        this.dataManager.set(TARGET_ENTITY, Integer.valueOf(entityId));
    }

    public boolean hasTargetedEntity() {
        return this.dataManager.get(TARGET_ENTITY).intValue() != 0;
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        super.notifyDataManagerChange(key);

        if (TARGET_ENTITY.equals(key)) {
            this.clientSideAttackTime = 0;
            this.targetedEntity = null;
        }
    }

    @Nullable
    public EntityLivingBase getTargetedEntity() {
        if (!this.hasTargetedEntity()) {
            return null;
        } else if (this.world.isRemote) {
            if (this.targetedEntity != null) {
                return this.targetedEntity;
            } else {
                Entity entity = this.world.getEntityByID(this.dataManager.get(TARGET_ENTITY).intValue());

                if (entity instanceof EntityLivingBase) {
                    this.targetedEntity = (EntityLivingBase) entity;
                    return this.targetedEntity;
                } else {
                    return null;
                }
            }
        } else {
            return this.getAttackTarget();
        }
    }

    public float getAttackAnimationScale(float p_175477_1_) {
        return ((float) this.clientSideAttackTime + p_175477_1_) / (float) this.getAttackDuration();
    }

    @Override
    public void onLivingUpdate() {
        if (world.isRemote) {
            if (this.hasTargetedEntity()) {
                if (this.clientSideAttackTime < this.getAttackDuration()) {
                    ++this.clientSideAttackTime;
                }

                EntityLivingBase entitylivingbase = this.getTargetedEntity();

                if (entitylivingbase != null) {
                    double d5 = (double) this.getAttackAnimationScale(0.0F);
                    double d0 = entitylivingbase.posX - this.posX;
                    double d1 = entitylivingbase.posY + (double) (entitylivingbase.height * 0.5F) - (this.posY + (double) this.getEyeHeight());
                    double d2 = entitylivingbase.posZ - this.posZ;
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    d0 = d0 / d3;
                    d1 = d1 / d3;
                    d2 = d2 / d3;
                    double d4 = this.rand.nextDouble();

                }
            }
        }
        super.onLivingUpdate();
    }


    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }


    public Item getDropItem() {

        return null;//なにも落とさない
    }


    public boolean canDespawn() {
        return false;
    }

    private int getAttackDuration() {
        return 50;
    }


    static class AIBeamAttack extends EntityAIBase {
        private final EntityTutinoko guardian;
        private int tickCounter;

        public AIBeamAttack(EntityTutinoko guardian) {
            this.guardian = guardian;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.guardian.getAttackTarget();

            if (this.guardian.getAttackTarget() == null) {
                return false;
            } else return entitylivingbase.isEntityAlive();

        }

        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting() && (this.guardian.getDistanceSq(this.guardian.getAttackTarget()) > 8.0D || this.guardian.posY < this.guardian.getAttackTarget().posY + 3);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.tickCounter = -15;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.guardian.setTargetedEntity(0);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask() {

            EntityLivingBase entitylivingbase = this.guardian.getAttackTarget();

            if (this.guardian.canEntityBeSeen(entitylivingbase)) {
                {
                    ++this.tickCounter;

                    if (this.tickCounter == 0) {
                        this.guardian.setTargetedEntity(entitylivingbase.getEntityId());
                    } else if (this.tickCounter >= this.guardian.getAttackDuration()) {
                        float f = 2.0F;

                        entitylivingbase.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.guardian, this.guardian), f);
                        entitylivingbase.attackEntityFrom(DamageSource.causeMobDamage(this.guardian), (float) this.guardian.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
                        this.tickCounter = -15;
                    }
                }

                super.updateTask();
            }
        }
    }
}