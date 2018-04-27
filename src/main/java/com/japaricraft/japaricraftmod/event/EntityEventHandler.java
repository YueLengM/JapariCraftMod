package com.japaricraft.japaricraftmod.event;

import com.japaricraft.japaricraftmod.handler.JapariItems;
import com.japaricraft.japaricraftmod.mob.EntityBlackCerulean;
import com.japaricraft.japaricraftmod.mob.EntityCerulean;
import com.japaricraft.japaricraftmod.mob.EntityServal;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityEventHandler {
    //エンティティのAI関係
    @SubscribeEvent
    public void onEntityJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityVillager) {
            EntityVillager villager = (EntityVillager) event.getEntity();

            villager.tasks.addTask(1, new EntityAIAvoidEntity<>(villager, EntityCerulean.class, 12.0F, 0.8D, 0.8D));
            villager.tasks.addTask(1, new EntityAIAvoidEntity<>(villager, EntityBlackCerulean.class, 12.0F, 0.8D, 0.8D));
        }
        if (event.getEntity() instanceof EntityCreeper) {
            EntityCreeper creeper = (EntityCreeper) event.getEntity();

            creeper.tasks.addTask(1, new EntityAIAvoidEntity<>(creeper, EntityServal.class, 12.0F, 1.1D, 1.1D));
        }
        if (event.getEntity() instanceof EntityVindicator) {
            EntityVindicator vindicator = (EntityVindicator) event.getEntity();

            vindicator.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(vindicator, EntityCerulean.class, true));
            vindicator.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(vindicator, EntityBlackCerulean.class, true));
        }
        if (event.getEntity() instanceof EntityZombie) {
            EntityZombie zombie = (EntityZombie) event.getEntity();
            if (zombie.getRNG().nextInt(18) == 0) {
                zombie.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(JapariItems.kabanhat));

            }
        }
    }
}
