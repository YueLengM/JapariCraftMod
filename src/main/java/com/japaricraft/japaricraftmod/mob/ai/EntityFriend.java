package com.japaricraft.japaricraftmod.mob.ai;

import com.japaricraft.japaricraftmod.gui.InventoryFriendEquipment;
import com.japaricraft.japaricraftmod.gui.InventoryFriendMain;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityFriend extends EntityTameable{
    public EntityFriend(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }
    public InventoryFriendMain getInventoryFriendMain()
    {
        return null;
    }
    public InventoryFriendEquipment getInventoryFriendEquipment()
    {
        return null;
    }
    public EntityFriend.Condition getCondition()
    {
        int health = (int) this.getHealth();
        int healthMax = (int) this.getMaxHealth();

        EntityFriend.Condition condition = EntityFriend.Condition.FINE;

        if (health < (healthMax / 2))
        {
            condition = EntityFriend.Condition.HURT;

            if (health < (healthMax / 4))
            {
                condition = EntityFriend.Condition.DYING;
            }
        }

        return condition;
    }

    public enum Condition
    {

        FINE,
        HURT,
        DYING,

    }
}
