package baguchan.japaricraftmod.event;

import baguchan.japaricraftmod.handler.*;
import baguchan.japaricraftmod.mob.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraftforge.event.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class EntityEventHandler {
    //エンティティのAI関係
    @SubscribeEvent
    public void onEntityJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityVillager) {
            EntityVillager villager = (EntityVillager) event.getEntity();

            villager.tasks.addTask(1, new EntityAIAvoidEntity<>(villager, EntityCerulean.class, 12.0F, 0.8D, 0.8D));
            villager.tasks.addTask(1, new EntityAIAvoidEntity<>(villager, EntityScocel.class, 12.0F, 0.8D, 0.8D));
            villager.tasks.addTask(1, new EntityAIAvoidEntity<>(villager, EntityCeruleanEye.class, 12.0F, 0.8D, 0.8D));
            villager.tasks.addTask(1, new EntityAIAvoidEntity<>(villager, EntityStarSorcerger.class, 12.0F, 0.8D, 0.8D));
        }
        if (event.getEntity() instanceof EntityCreeper) {
            EntityCreeper creeper = (EntityCreeper) event.getEntity();

            creeper.tasks.addTask(1, new EntityAIAvoidEntity<>(creeper, EntityServal.class, 12.0F, 1.1D, 1.1D));
        }
        if (event.getEntity() instanceof EntityZombie) {
            EntityZombie zombie = (EntityZombie) event.getEntity();
            if (zombie.getRNG().nextInt(30) == 0) {
                zombie.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(JapariItems.kabanhat));

            }
        }
    }
}
