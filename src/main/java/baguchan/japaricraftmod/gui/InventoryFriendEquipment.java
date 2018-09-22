package baguchan.japaricraftmod.gui;

import baguchan.japaricraftmod.mob.EntityFriend;
import net.minecraft.item.ItemStack;

public class InventoryFriendEquipment extends InventoryFriend
{
    //slotCountでスロットの合計を入力
    public InventoryFriendEquipment(EntityFriend friend)
    {
        super(friend, 4);
    }

    // TODO /* ======================================== MOD START =====================================*/
    public ItemStack getChestItem()
    {
        return this.getStackInSlot(0);
    }
    public ItemStack getbootItem()
    {
        return this.getStackInSlot(1);
    }

    public ItemStack getheadItem() {
        return this.getStackInSlot(2);
    }

    public ItemStack getLegItem() {
        return this.getStackInSlot(3);
    }

}