package baguchan.japaricraftmod.mob.ai;

import baguchan.japaricraftmod.mob.EntityPlayFriend;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class EntityAIPlayWithFriend extends EntityAIBase {
    private final EntityPlayFriend friend;
    private EntityLivingBase targetfriend;
    private final double speed;
    private int playTime;

    public EntityAIPlayWithFriend(EntityPlayFriend friendIn, double speedIn) {
        this.friend = friendIn;
        this.speed = speedIn;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (this.friend.getRNG().nextInt(400) != 0) {
            return false;
        } else {
            List<EntityPlayFriend> list = this.friend.world.<EntityPlayFriend>getEntitiesWithinAABB(EntityPlayFriend.class, this.friend.getEntityBoundingBox().grow(6.0D, 3.0D, 6.0D));
            double d0 = Double.MAX_VALUE;

            EntityLivingBase attackTarget = this.friend.getAttackTarget();

            for (EntityPlayFriend entityfriend : list) {
                if (entityfriend != this.friend && !entityfriend.isPlaying()) {
                    double d1 = entityfriend.getDistanceSq(this.friend);

                    if (d1 <= d0) {
                        d0 = d1;
                        this.targetfriend = entityfriend;
                    }
                }
            }

            if (this.targetfriend == null) {
                Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.friend, 16, 3);

                if (vec3d == null) {
                    return false;
                }
            }

            if (attackTarget != null) {
                return false;
            }

            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return this.playTime > 0;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        if (this.targetfriend != null) {
            this.friend.setPlaying(true);
        }

        this.playTime = 1000;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.friend.setPlaying(false);
        this.targetfriend = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
        --this.playTime;

        if (this.targetfriend != null) {
            if (this.friend.getDistanceSq(this.targetfriend) > 4.0D) {
                this.friend.getNavigator().tryMoveToEntityLiving(this.targetfriend, this.speed);
            }
        } else if (this.friend.getNavigator().noPath()) {
            Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.friend, 16, 3);

            if (vec3d == null) {
                return;
            }

            this.friend.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, this.speed);
        }
    }
}