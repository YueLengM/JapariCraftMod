package com.japaricraft.japaricraftmod.mob;

import com.japaricraft.japaricraftmod.JapariCraftMod;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityAlpacaCafe extends EntityCreature {

    public EntityAlpacaCafe(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.9F);
    }


    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(6, new EntityAIWatchClosest2(this, EntityPlayer.class, 5.0F, 1.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityCreature.class, 6.0F));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        player.openGui(JapariCraftMod.instance, JapariCraftMod.ID_CAFE, this.getEntityWorld(), this.getEntityId(), 0, 0);
        return true;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PLAYER_DEATH;
    }


    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEFINED;
    }

    public Item getDropItem() {

        return null;//なにも落とさない
    }

    @Override
    public boolean canDespawn() {
        return false;
    }


}