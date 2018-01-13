package com.japaricraft.japaricraftmod.event;

import com.japaricraft.japaricraftmod.mob.BlackCerulean;
import com.japaricraft.japaricraftmod.mob.Cerulean;
import com.japaricraft.japaricraftmod.mob.Serval;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityEventHandler {
    @SubscribeEvent
    public void onEntityJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityVillager) {
            EntityVillager villager = (EntityVillager) event.getEntity();

            villager.tasks.addTask(1, new EntityAIAvoidEntity<>(villager, Cerulean.class, 12.0F, 0.8D, 0.8D));
            villager.tasks.addTask(1, new EntityAIAvoidEntity<>(villager, BlackCerulean.class, 12.0F, 0.8D, 0.8D));
        }
        if (event.getEntity() instanceof EntityCreeper) {
            EntityCreeper creeper = (EntityCreeper) event.getEntity();

            creeper.tasks.addTask(1, new EntityAIAvoidEntity<>(creeper, Serval.class, 12.0F, 1.1D, 1.1D));
        }
        if (event.getEntity() instanceof EntityVindicator) {
            EntityVindicator vindicator = (EntityVindicator) event.getEntity();

            vindicator.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(vindicator, Cerulean.class, true));
            vindicator.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(vindicator, BlackCerulean.class, true));
        }
    }
}
