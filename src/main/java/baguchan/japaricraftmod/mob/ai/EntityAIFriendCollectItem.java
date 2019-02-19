package baguchan.japaricraftmod.mob.ai;

import baguchan.japaricraftmod.mob.EntityFriend;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class EntityAIFriendCollectItem extends EntityAIBase {

    protected EntityFriend friend;
    protected float moveSpeed;
    protected EntityItem targetItem;
    protected boolean lastAvoidWater;


    public EntityAIFriendCollectItem(EntityFriend entityFriends, float pmoveSpeed) {
        friend = entityFriends;
        moveSpeed = pmoveSpeed;
        setMutexBits(3);
    }


    @Override
    public boolean shouldExecute() {
        if (!friend.isTamed()) return false;
        if (friend.isSitting()) return false;
        if (findItem()) {
            List llist = friend.world.getEntitiesWithinAABB(EntityItem.class, friend.getEntityBoundingBox().grow(8F, 2D, 8F));
            if (!llist.isEmpty()) {
                int li = friend.getRNG().nextInt(llist.size());
                EntityItem ei = (EntityItem) llist.get(li);
                EntityPlayer ep = friend.world.getClosestPlayerToEntity(friend, 16F);

                NBTTagCompound p = new NBTTagCompound();
                ei.writeEntityToNBT(p);
                if (!ei.isDead && ei.onGround && p.getShort("PickupDelay") <= 0 && !ei.isBurning()
                        && canEntityItemBeSeen(ei) && (ep == null ||
                        ep.getDistanceSq(
                                ei.posX + MathHelper.sin(ep.rotationYaw * 0.01745329252F) * 2.0D,
                                ei.posY,
                                ei.posZ - MathHelper.cos(ep.rotationYaw * 0.01745329252F) * 2.0D) > 7.5D)) {
                    targetItem = ei;
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findItem() {
        ItemStack friendsstack;

        for (int i = 0; i < friend.getInventoryFriendMain().getSizeInventory(); ++i) {
            friendsstack = friend.getInventoryFriendMain().getStackInSlot(i);

            if (friendsstack == ItemStack.EMPTY) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();

    }

    @Override
    public boolean shouldContinueExecuting() {
        return !targetItem.isDead && findItem() && friend.getDistanceSq(targetItem) < 100D;
    }

    @Override
    public void resetTask() {
        targetItem = null;
        friend.getNavigator().clearPath();

    }

    @Override
    public void updateTask() {
        friend.getLookHelper().setLookPositionWithEntity(targetItem, 30F, friend.getVerticalFaceSpeed());

        PathNavigate lnavigater = friend.getNavigator();
        if (lnavigater.noPath()) {
            lnavigater.tryMoveToXYZ(targetItem.posX, targetItem.posY, targetItem.posZ, moveSpeed);
        }
    }

    public boolean canEntityItemBeSeen(Entity entity) {
        // アイテムの可視判定
        return true;
    }

}