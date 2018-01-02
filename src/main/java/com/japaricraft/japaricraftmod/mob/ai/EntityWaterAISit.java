package com.japaricraft.japaricraftmod.mob.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityWaterAISit extends EntityAIBase {
    private final EntityTameable tameable;
    /**
     * If the EntityTameable is sitting.
     */
    private boolean isSitting;

    public EntityWaterAISit(EntityTameable entityIn) {
        this.tameable = entityIn;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (!this.tameable.isTamed()) {
            return false;
        } else {
            EntityLivingBase entitylivingbase = this.tameable.getOwner();

            if (entitylivingbase == null) {
                return true;
            } else {
                return this.tameable.getDistanceSq(entitylivingbase) < 144.0D && entitylivingbase.getRevengeTarget() != null ? false : this.isSitting;
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.tameable.getNavigator().clearPath();
        this.tameable.setSitting(true);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.tameable.setSitting(false);
    }

    /**
     * Sets the sitting flag.
     */
    public void setSitting(boolean sitting) {
        this.isSitting = sitting;
    }
}