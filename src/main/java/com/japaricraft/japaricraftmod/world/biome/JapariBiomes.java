package com.japaricraft.japaricraftmod.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.IForgeRegistry;

public class JapariBiomes {
    public static final BiomeSandStarIsland SAND_STAR_ISLAND = new BiomeSandStarIsland();

    public static void registerBiomes(IForgeRegistry<Biome> registry) {
        registry.register(SAND_STAR_ISLAND.setRegistryName("sandstar_island"));
    }

    public static void registerBiomeTypes() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SAND_STAR_ISLAND, 10));
        BiomeManager.addSpawnBiome(SAND_STAR_ISLAND);
        BiomeManager.addVillageBiome(SAND_STAR_ISLAND, true);
        BiomeDictionary.addTypes(SAND_STAR_ISLAND, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MAGICAL);
    }
}