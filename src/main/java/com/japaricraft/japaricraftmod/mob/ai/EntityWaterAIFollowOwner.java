package com.japaricraft.japaricraftmod.mob.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityWaterAIFollowOwner extends EntityAIBase {
    private final EntityTameable tameable;
    private EntityLivingBase owner;
    World world;
    private final double followSpeed;
    private final PathNavigate petPathfinder;
    private int timeToRecalcPath;
    float maxDist;
    float minDist;
    private float oldWaterCost;

    public EntityWaterAIFollowOwner(EntityTameable tameableIn, double followSpeedIn, float minDistIn, float maxDistIn) {
        this.tameable = tameableIn;
        this.world = tameableIn.world;
        this.followSpeed = followSpeedIn;
        this.petPathfinder = tameableIn.getNavigator();
        this.minDist = minDistIn;
        this.maxDist = maxDistIn;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.tameable.getOwner();

        if (entitylivingbase == null) {
            return false;
        } else if (entitylivingbase instanceof EntityPlayer && ((EntityPlayer) entitylivingbase).isSpectator()) {
            return false;
        } else if (this.tameable.isSitting()) {
            return false;
        } else if (this.tameable.getDistanceSq(entitylivingbase) < (double) (this.minDist * this.minDist)) {
            return false;
        } else {
            this.owner = entitylivingbase;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return !this.petPathfinder.noPath() && this.tameable.getDistanceSq(this.owner) > (double) (this.maxDist * this.maxDist) && !this.tameable.isSitting();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.timeToRecalcPath = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.owner = null;
        this.petPathfinder.clearPath();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
        this.tameable.getLookHelper().setLookPositionWithEntity(this.owner, 10.0F, (float) this.tameable.getVerticalFaceSpeed());

        if (!this.tameable.isSitting()) {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;

                if (!this.petPathfinder.tryMoveToEntityLiving(this.owner, this.followSpeed)) {
                    if (!this.tameable.getLeashed() && !this.tameable.isRiding()) {
                        if (this.tameable.getDistanceSq(this.owner) >= 144.0D) {
                            int i = MathHelper.floor(this.owner.posX) - 2;
                            int j = MathHelper.floor(this.owner.posZ) - 2;
                            int k = MathHelper.floor(this.owner.getEntityBoundingBox().minY);

                        }
                    }
                }
            }
        }
    }
}