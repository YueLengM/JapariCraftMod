package com.japaricraft.japaricraftmod.gui;

import com.japaricraft.japaricraftmod.mob.ai.EntityFriend;
import net.minecraft.item.ItemStack;

public class InventoryFriendEquipment extends InventoryFriend
{

    public InventoryFriendEquipment(EntityFriend friend)
    {
        super(friend, 2);
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

}