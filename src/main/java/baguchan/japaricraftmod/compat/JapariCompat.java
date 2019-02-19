package baguchan.japaricraftmod.compat;

import baguchan.japaricraftmod.mob.EntityTwilightKobold;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import twilightforest.biomes.TFBiomes;

public class JapariCompat {
    public static void twilightCompat() {

        EntityRegistry.addSpawn(EntityTwilightKobold.class, 6, 1, 2, EnumCreatureType.CREATURE, TFBiomes.twilightForest, TFBiomes.enchantedForest, TFBiomes.clearing, TFBiomes.deepMushrooms, TFBiomes.fireflyForest, TFBiomes.mushrooms);
    }
}
