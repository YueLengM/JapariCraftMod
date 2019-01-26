package baguchan.japaricraftmod.mob.ai;

import baguchan.japaricraftmod.mob.EntityFlyFriend;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIFollowOwnerFlyFriend extends EntityAIBase {
    private final EntityFlyFriend tameable;
    private EntityLivingBase owner;
    World world;
    private final double followSpeed;
    private int timeToRecalcPath;
    float maxDist;
    float minDist;
    private float oldWaterCost;
    Path path;
    private int flytick;

    public EntityAIFollowOwnerFlyFriend(EntityFlyFriend tameableIn, double followSpeedIn, float minDistIn, float maxDistIn) {
        this.tameable = tameableIn;
        this.world = tameableIn.world;
        this.followSpeed = followSpeedIn;
        this.path = tameableIn.getNavigator().getPath();
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
            this.path = this.tameable.getNavigator().getPathToEntityLiving(entitylivingbase);
            this.owner = entitylivingbase;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return path != null && this.tameable.getDistanceSq(this.owner) > (double) (this.maxDist * this.maxDist) && !this.tameable.isSitting();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.flytick = 0;
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.tameable.getPathPriority(PathNodeType.WATER);
        this.tameable.getNavigator().setPath(this.path, this.followSpeed);
        this.tameable.setPathPriority(PathNodeType.WATER, 0.0F);
        this.tameable.setFlying(false);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.owner = null;
        this.tameable.getNavigator().clearPath();
        this.tameable.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
        this.tameable.setFlying(false);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
        this.tameable.getLookHelper().setLookPositionWithEntity(this.owner, 10.0F, (float) this.tameable.getVerticalFaceSpeed());
        //If she judge that can not cross a cliff, she will fly to this side
        if (this.tameable.getNavigator().noPath() || this.tameable.getNavigator().noPath() && owner.posY - 8 > this.tameable.posY && this.flytick == 0 && this.world.rand.nextInt(20) == 0) {
            this.tameable.setFlying(true);
        }

        if (this.tameable.isFlying()) {
            ++this.flytick;
        }

        if (this.flytick >= 80 && this.world.rand.nextInt(120) == 0) {
            this.tameable.setFlying(false);

            this.flytick = 0;
        }


        if (!this.tameable.isSitting()) {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;

            }

            if (!this.tameable.getNavigator().tryMoveToEntityLiving(this.owner, this.followSpeed)) {
                if (!this.tameable.getLeashed() && !this.tameable.isRiding()) {
                    if (this.tameable.getDistanceSq(this.owner) >= 160.0D) {
                        int i = MathHelper.floor(this.owner.posX) - 2;
                        int j = MathHelper.floor(this.owner.posZ) - 2;
                        int k = MathHelper.floor(this.owner.getEntityBoundingBox().minY);

                        for (int l = 0; l <= 4; ++l) {
                            for (int i1 = 0; i1 <= 4; ++i1) {
                                if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.isTeleportFriendlyBlock(i, j, k, l, i1)) {
                                    this.tameable.setLocationAndAngles((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), this.tameable.rotationYaw, this.tameable.rotationPitch);
                                    this.tameable.getNavigator().clearPath();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected boolean isTeleportFriendlyBlock(int x, int p_192381_2_, int y, int p_192381_4_, int p_192381_5_) {
        BlockPos blockpos = new BlockPos(x + p_192381_4_, y - 1, p_192381_2_ + p_192381_5_);
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        return iblockstate.getBlockFaceShape(this.world, blockpos, EnumFacing.DOWN) == BlockFaceShape.SOLID && iblockstate.canEntitySpawn(this.tameable) && this.world.isAirBlock(blockpos.up()) && this.world.isAirBlock(blockpos.up(2));
    }
}