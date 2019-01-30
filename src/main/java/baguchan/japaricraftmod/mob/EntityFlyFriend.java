package baguchan.japaricraftmod.mob;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFlyFriend extends EntityFriend {
    private static final DataParameter<Boolean> FLYING = EntityDataManager.createKey(EntityFlyFriend.class, DataSerializers.BOOLEAN);
    private boolean isLandNavigator;

    public EntityFlyFriend(World worldIn) {
        super(worldIn);
        this.switchNavigator(true);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(FLYING, Boolean.FALSE);
    }

    public boolean isFlying() {
        return this.dataManager.get(FLYING);
    }

    public void setFlying(boolean flying) {
        this.dataManager.set(FLYING, flying);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.isFlying() && this.isLandNavigator) {

            switchNavigator(false);

        }

        if (!this.isFlying() && !this.isLandNavigator) {

            switchNavigator(true);

        }
    }

    private void switchNavigator(boolean onLand) {

        if (onLand) {
            PathNavigateGround pathnavigate = new PathNavigateGround(this, world);
            pathnavigate.setBreakDoors(true);

            this.navigator = pathnavigate;
            this.moveHelper = new EntityMoveHelper(this);

            this.isLandNavigator = true;

        } else {

            PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, world);
            pathnavigateflying.setCanOpenDoors(true);
            pathnavigateflying.setCanFloat(true);
            pathnavigateflying.setCanEnterDoors(true);

            this.navigator = pathnavigateflying;
            this.moveHelper = new EntityFlyFriend.FlyFriendMoveHelper(this);


            this.isLandNavigator = false;
        }

    }

    @Override
    public void travel(float strafe, float vertical, float forward) {
        //VanillaCopy
        if (this.isFlying()) {
            if (this.isInWater()) {
                this.moveRelative(strafe, vertical, forward, 0.02F);
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                this.motionX *= 0.800000011920929D;
                this.motionY *= 0.800000011920929D;
                this.motionZ *= 0.800000011920929D;
            } else if (this.isInLava()) {
                this.moveRelative(strafe, vertical, forward, 0.02F);
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                this.motionX *= 0.5D;
                this.motionY *= 0.5D;
                this.motionZ *= 0.5D;
            } else {
                float f = 0.91F;


                float f1 = 0.16277136F / (f * f * f);
                this.moveRelative(strafe, vertical, forward, this.onGround ? 0.1F * f1 : 0.02F);
                f = 0.91F;

                if (this.onGround) {

                    f = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.91F;

                }

                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                this.motionX *= (double) f;
                this.motionY *= (double) f;
                this.motionZ *= (double) f;
            }
            this.prevLimbSwingAmount = this.limbSwingAmount;

            double d1 = this.posX - this.prevPosX;
            double d0 = this.posZ - this.prevPosZ;

            float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

            if (f2 > 1.0F) {

                f2 = 1.0F;

            }

            this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
        } else {
            super.travel(strafe, vertical, forward);
        }
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }


    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
    }

    static class FlyFriendMoveHelper extends EntityMoveHelper {
        private final EntityFlyFriend parentEntity;
        private int courseChangeCooldown;

        public FlyFriendMoveHelper(EntityFlyFriend ghast) {
            super(ghast);
            this.parentEntity = ghast;
        }

        public void onUpdateMoveHelper() {
            if (this.parentEntity.isFlying()) {
                if (this.action == EntityMoveHelper.Action.MOVE_TO) {

                    double d0 = this.posX - this.parentEntity.posX;
                    double d1 = this.posY - this.parentEntity.posY;
                    double d2 = this.posZ - this.parentEntity.posZ;
                    double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                    EntityLookHelper entitylookhelper = this.parentEntity.getLookHelper();

                    if (this.courseChangeCooldown-- <= 0) {
                        this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                        d3 = (double) MathHelper.sqrt(d3);

                        if (this.isNotColliding(this.posX, this.posY, this.posZ, d3)) {
                            this.parentEntity.motionX += d0 / d3 * this.entity.getAIMoveSpeed() * 0.7F;
                            this.parentEntity.motionY += d1 / d3 * this.entity.getAIMoveSpeed() * 0.7F;
                            this.parentEntity.motionZ += d2 / d3 * this.entity.getAIMoveSpeed() * 0.7F;
                        } else {
                            this.action = EntityMoveHelper.Action.WAIT;
                        }
                    }

                    float f2;

                    if (this.entity.onGround) {
                        f2 = (float) (this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
                    } else {
                        f2 = (float) (this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

                    }

                    if (this.parentEntity.getAttackTarget() == null) {
                        this.parentEntity.rotationYaw = -((float) MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float) Math.PI);
                        this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                    } else {
                        double d4 = this.parentEntity.getAttackTarget().posX - this.parentEntity.posX;
                        double d5 = this.parentEntity.getAttackTarget().posZ - this.parentEntity.posZ;
                        this.parentEntity.rotationYaw = -((float) MathHelper.atan2(d4, d5)) * (180F / (float) Math.PI);
                        this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                    }

                    this.entity.setAIMoveSpeed(f2);
                }
            } else {
                super.onUpdateMoveHelper();
            }
        }

        private boolean isNotColliding(double x, double y, double z, double p_179926_7_) {
            double d0 = (x - this.parentEntity.posX) / p_179926_7_;
            double d1 = (y - this.parentEntity.posY) / p_179926_7_;
            double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
            AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();

            for (int i = 1; (double) i < p_179926_7_; ++i) {
                axisalignedbb = axisalignedbb.offset(d0, d1, d2);

                if (!this.parentEntity.world.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty()) {
                    return false;
                }
            }

            return true;
        }
    }

}
