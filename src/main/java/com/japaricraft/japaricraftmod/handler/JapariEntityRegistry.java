package com.japaricraft.japaricraftmod.handler;


import com.google.common.collect.Lists;
import com.japaricraft.japaricraftmod.JapariConfig;
import com.japaricraft.japaricraftmod.JapariCraftMod;
import com.japaricraft.japaricraftmod.mob.*;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.japaricraft.japaricraftmod.JapariCraftMod.instance;
import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class JapariEntityRegistry {
    public static void registerEntities() {
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "kouteipenguin"), EntityKouteiPenguin.class, "KouteiPenguin", 1, instance, 60, 1, false, 2243405, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "cerulean"), EntityCerulean.class, "Cerulean", 2, instance, 60, 1, false, 4243405, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "serval"), EntityServal.class, "Serval", 3, instance, 60, 1, false, 16703405, 6375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "shoebill"), EntityShoebill.class, "Shoebill", 5, instance, 70, 1, false, 7375001, 10000);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "whiteOwl"), EntityWhiteOwl.class, "WhiteOwl", 6, instance, 70, 1, false, 7375001, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "brownOwl"), EntityBrownOwl.class, "BrownOwl", 7, instance, 70, 1, false, 5243410, 5243405);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "blackcerulean"), EntityBlackCerulean.class, "BlackCerulean", 10, instance, 120, 1, false, 0x000000, 0x333333);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "guide"), EntityGuide.class, "Guide", 11, instance, 60, 1, false);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "araisan"), EntityAraisan.class, "Araisan", 12, instance, 60, 1, false, 0x666699, 0x969696);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "poisoncerulean"), PoisonEntityCerulean.class, "PoisonCerulean", 13, instance, 60, 1, false, 4243405, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "fennec"), EntityFennec.class, "Fennec", 14, instance, 60, 1, false, 13434879, 13408767);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "sandcat"), EntitySandCat.class, "SandCat", 15, instance, 60, 1, false, 0xF5F5DC, 0x969696);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "royalpenguin"), RoyalPenguinEntity.class, "RoyalPenguin", 16, instance, 60, 1, false, 2243405, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "alpaca"), EntityAlpaca.class, "Alpaca", 17, instance, 60, 1, false, 0xf0f5f0, 0xf5f5dc);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "squirre"), EntitySquirre.class, "Squirre", 18, instance, 60, 1, false, 0xf0f5f0, 0x993300);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "tutinoko"), EntityTutinoko.class, "Tutinoko", 19, instance, 60, 1, false);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "ceruleaneye"), EntityCeruleanEye.class, "CeruleanEye", 21, instance, 70, 1, false, 0x333333, 0x339966);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "endercerulean"), EntityEnderCerulean.class, "EnderCerulean", 22, instance, 70, 1, false, 0x333333, 0x808080);
    }
    public static void addSpawns()
    {
        //Biomeの種類で湧くように
        List<BiomeManager.BiomeEntry> biomeEntries = new ArrayList<BiomeManager.BiomeEntry>();
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeManager.BiomeType.COOL));
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeManager.BiomeType.DESERT));
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeManager.BiomeType.ICY));
        biomeEntries.addAll(BiomeManager.getBiomes(BiomeManager.BiomeType.WARM));
        List<Biome> biomes = new ArrayList<Biome>();
        for (BiomeManager.BiomeEntry b : biomeEntries) {
            biomes.add(b.biome);
        }
        biomes.addAll(BiomeManager.oceanBiomes);
        List<Biome> snow_biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY) {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
            if (types.contains(SNOWY) && !types.contains(FOREST) && !types.contains(SANDY)&& !types.contains(NETHER) && !biome.getSpawnableList(EnumCreatureType.CREATURE).isEmpty()) {
                snow_biomes.add(biome);
            }
        }
        List<Biome> plain_biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY) {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
            if (types.contains(PLAINS) && !types.contains(SNOWY) && !types.contains(NETHER) && !biome.getSpawnableList(EnumCreatureType.CREATURE).isEmpty()) {
                plain_biomes.add(biome);
            }
        }
        List<Biome> forest_biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY) {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
            if (types.contains(FOREST) && !types.contains(SNOWY) && !types.contains(NETHER) && !biome.getSpawnableList(EnumCreatureType.CREATURE).isEmpty()) {
                forest_biomes.add(biome);
            }
        }
        List<Biome> sandy_biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY) {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
            if (types.contains(SANDY) && !types.contains(SNOWY) && !types.contains(WASTELAND) && !types.contains(NETHER) && !biome.getSpawnableList(EnumCreatureType.CREATURE).isEmpty()) {
                sandy_biomes.add(biome);
            }
        }
        EntityRegistry.addSpawn(EntityFennec.class, 3, 1, 2, EnumCreatureType.CREATURE, sandy_biomes.toArray(new Biome[sandy_biomes.size()]));
        EntityRegistry.addSpawn(EntitySandCat.class, 2, 1, 2, EnumCreatureType.CREATURE, sandy_biomes.toArray(new Biome[sandy_biomes.size()]));
        EntityRegistry.addSpawn(EntityKouteiPenguin.class, 3, 1, 2, EnumCreatureType.CREATURE, snow_biomes.toArray(new Biome[snow_biomes.size()]));
        EntityRegistry.addSpawn(RoyalPenguinEntity.class, 3, 1, 2, EnumCreatureType.CREATURE, snow_biomes.toArray(new Biome[snow_biomes.size()]));
        EntityRegistry.addSpawn(EntityServal.class, 7, 2, 3, EnumCreatureType.CREATURE, Biome.getBiome(35), Biome.getBiome(36), Biome.getBiome(163));
        EntityRegistry.addSpawn(EntityShoebill.class, 7, 2, 3, EnumCreatureType.CREATURE, plain_biomes.toArray(new Biome[plain_biomes.size()]));
        EntityRegistry.addSpawn(EntityAraisan.class, 7, 2, 3, EnumCreatureType.CREATURE, plain_biomes.toArray(new Biome[plain_biomes.size()]));
        EntityRegistry.addSpawn(EntityWhiteOwl.class, 7, 2, 2, EnumCreatureType.CREATURE, forest_biomes.toArray(new Biome[forest_biomes.size()]));
        EntityRegistry.addSpawn(EntityBrownOwl.class, 7, 2, 2, EnumCreatureType.CREATURE, forest_biomes.toArray(new Biome[forest_biomes.size()]));
        EntityRegistry.addSpawn(EntitySquirre.class, 7, 2, 3, EnumCreatureType.CREATURE, forest_biomes.toArray(new Biome[forest_biomes.size()]));
        EntityRegistry.addSpawn(EntityCerulean.class, JapariConfig.cerulean_wight, 2, 4, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
        EntityRegistry.addSpawn(PoisonEntityCerulean.class, JapariConfig.poisoncerulean_wight, 2, 4, EnumCreatureType.MONSTER, Biomes.SWAMPLAND);
        EntityRegistry.addSpawn(EntityAlpaca.class, 7, 2, 3, EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.MUTATED_EXTREME_HILLS_WITH_TREES);
        EntityRegistry.addSpawn(EntityBlackCerulean.class, JapariConfig.blackcerulean_wight, 1, 1, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
    }
}
