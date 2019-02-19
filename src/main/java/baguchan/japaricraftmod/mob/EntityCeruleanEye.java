package baguchan.japaricraftmod.mob;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.pathfinding.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

public class EntityCeruleanEye extends EntityMob {
    private EntityLiving owner;

    public EntityCeruleanEye(World worldIn) {
        super(worldIn);
        this.experienceValue = 8;
        this.setSize(0.8F, 0.8F);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
    }

    @Override
    protected void initEntityAI() {

        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest2(this, EntityPlayer.class, 6.0F, 1.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityCreature.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityCeruleanEye.class));
        this.targetTasks.addTask(2, new EntityCeruleanEye.AICopyOwnerTarget(this));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityFriend.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, false));
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.8F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BAT_TAKEOFF;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1.0D);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        //when get attacktaget
        //もしアタックターゲットを見つけたら
        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }
        if (!isOnLadder() && this.getAttackTarget() != null) {
            double a = this.getAttackTarget().posX - posX;
            double b = this.getAttackTarget().posZ - posZ;
            double d3 = a * a + b * b;
            d3 = (double) MathHelper.sqrt(d3);

            if ((this.getAttackTarget().posY > posY)) {
                this.motionY += 0.08F;
            }
            if (!onGround) {
                double d1 = getAttackTarget().posX - this.posX;
                double d2 = getAttackTarget().posZ - this.posZ;

                this.motionX += a / d3 * 0.08D * this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
                this.motionZ += b / d3 * 0.08D * this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
                this.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float) Math.PI);
                this.renderYawOffset = this.rotationYaw;
            }
        }
    }

    public EntityLiving getOwner() {
        return this.owner;
    }

    public void setOwner(EntityLiving ownerIn) {
        this.owner = ownerIn;
    }

    protected void updateAITasks() {
        if (this.isWet()) {
            this.attackEntityFrom(DamageSource.DROWN, 3.0F);
        }

        super.updateAITasks();
    }


    public void fall(float distance, float damageMultiplier) {
    }

    class AICopyOwnerTarget extends EntityAITarget {
        public AICopyOwnerTarget(EntityCreature creature) {
            super(creature, false);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            return EntityCeruleanEye.this.owner != null && EntityCeruleanEye.this.owner.getAttackTarget() != null && this.isSuitableTarget(EntityCeruleanEye.this.owner.getAttackTarget(), false);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            EntityCeruleanEye.this.setAttackTarget(EntityCeruleanEye.this.owner.getAttackTarget());
            super.startExecuting();
        }
    }

}
