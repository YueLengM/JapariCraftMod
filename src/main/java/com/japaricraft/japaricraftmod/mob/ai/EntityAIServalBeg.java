package com.japaricraft.japaricraftmod.mob.ai;

import com.japaricraft.japaricraftmod.handler.JapariItems;
import com.japaricraft.japaricraftmod.mob.EntityServal;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityAIServalBeg extends EntityAIBase {
    private final EntityServal entityServal;
    private EntityPlayer player;
    private final World world;
    private final float minPlayerDistance;
    private int timeoutCounter;

    public EntityAIServalBeg(EntityServal entityServal, float minDistance) {
        this.entityServal = entityServal;
        this.world = entityServal.world;
        this.minPlayerDistance = minDistance;
        this.setMutexBits(2);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        this.player = this.world.getClosestPlayerToEntity(this.entityServal, (double) this.minPlayerDistance);
        return this.player != null && this.hasTemptationItemInHand(this.player);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        if (!this.player.isEntityAlive()) {
            return false;
        } else if (this.entityServal.getDistanceSq(this.player) > (double) (this.minPlayerDistance * this.minPlayerDistance)) {
            return false;
        } else {
            return this.timeoutCounter > 0 && this.hasTemptationItemInHand(this.player);
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.entityServal.setBegging(true);
        this.timeoutCounter = 40 + this.entityServal.getRNG().nextInt(40);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.entityServal.setBegging(false);
        this.player = null;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask() {
        this.entityServal.getLookHelper().setLookPosition(this.player.posX, this.player.posY + (double) this.player.getEyeHeight(), this.player.posZ, 10.0F, (float) this.entityServal.getVerticalFaceSpeed());
        --this.timeoutCounter;
    }

    /**
     * Gets if the Player has the Bone in the hand.
     */
    private boolean hasTemptationItemInHand(EntityPlayer player) {
        for (EnumHand enumhand : EnumHand.values()) {
            ItemStack itemstack = player.getHeldItem(enumhand);

            if (itemstack.getItem() == JapariItems.japariman)
            {
                return true;
            }
            if (itemstack.getItem() == JapariItems.japarimancocoa) {
                return true;
            }
            if (itemstack.getItem() == JapariItems.japarimanapple) {
                return true;
            }

        }

        return false;
    }
}