package baguchan.japaricraftmod.mob.ai;

import baguchan.japaricraftmod.mob.EntityPlayFriend;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIStopPlayFollowOwner extends EntityAIBase {
    //場合によっては廃止予定・・・？
    private final EntityPlayFriend playfriend;
    private EntityLivingBase owner;
    World world;
    private final double followSpeed;
    private final PathNavigate petPathfinder;
    private int timeToRecalcPath;
    float maxDist;
    float minDist;
    private float oldWaterCost;

    public EntityAIStopPlayFollowOwner(EntityPlayFriend playfriendIn, double followSpeedIn, float minDistIn, float maxDistIn) {
        this.playfriend = playfriendIn;
        this.world = playfriendIn.world;
        this.followSpeed = followSpeedIn;
        this.petPathfinder = playfriendIn.getNavigator();
        this.minDist = minDistIn;
        this.maxDist = maxDistIn;
        this.setMutexBits(3);

        if (!(playfriendIn.getNavigator() instanceof PathNavigateGround) && !(playfriendIn.getNavigator() instanceof PathNavigateFlying)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.playfriend.getOwner();

        if (entitylivingbase == null) {
            return false;
        } else if (entitylivingbase instanceof EntityPlayer && ((EntityPlayer) entitylivingbase).isSpectator()) {
            return false;
        } else if (this.playfriend.isSitting()) {
            return false;
        } else if (this.playfriend.getDistanceSq(entitylivingbase) < (double) (this.minDist * this.minDist)) {
            return false;
        } else {
            this.owner = entitylivingbase;

            playfriend.setPlaying(false);
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return !this.petPathfinder.noPath() && this.playfriend.getDistanceSq(this.owner) > (double) (this.maxDist * this.maxDist) && !this.playfriend.isSitting();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.playfriend.getPathPriority(PathNodeType.WATER);
        this.playfriend.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.owner = null;
        this.petPathfinder.clearPath();
        this.playfriend.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
        this.playfriend.getLookHelper().setLookPositionWithEntity(this.owner, 10.0F, (float) this.playfriend.getVerticalFaceSpeed());

        if (!this.playfriend.isSitting()) {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;

                if (!this.petPathfinder.tryMoveToEntityLiving(this.owner, this.followSpeed)) {
                    if (!this.playfriend.getLeashed() && !this.playfriend.isRiding()) {
                        if (this.playfriend.getDistanceSq(this.owner) >= 144.0D) {
                            int i = MathHelper.floor(this.owner.posX) - 2;
                            int j = MathHelper.floor(this.owner.posZ) - 2;
                            int k = MathHelper.floor(this.owner.getEntityBoundingBox().minY);

                            for (int l = 0; l <= 4; ++l) {
                                for (int i1 = 0; i1 <= 4; ++i1) {
                                    if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.isTeleportFriendlyBlock(i, j, k, l, i1)) {
                                        this.playfriend.setLocationAndAngles((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), this.playfriend.rotationYaw, this.playfriend.rotationPitch);
                                        this.petPathfinder.clearPath();
                                        return;
                                    }
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
        return iblockstate.getBlockFaceShape(this.world, blockpos, EnumFacing.DOWN) == BlockFaceShape.SOLID && iblockstate.canEntitySpawn(this.playfriend) && this.world.isAirBlock(blockpos.up()) && this.world.isAirBlock(blockpos.up(2));
    }
}