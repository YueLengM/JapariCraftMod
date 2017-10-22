package com.japaricraft.japaricraftmod.mob;


import com.google.common.collect.Lists;
import com.japaricraft.japaricraftmod.JapariCraftMod;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.List;
import java.util.Set;

import static com.japaricraft.japaricraftmod.JapariCraftMod.instance;
import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class JapariEntityRegistry {
    public static void registerEntities() {
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.KouteiPenguin"), KouteiPenguin.class, "KouteiPenguin", 1, instance, 30, 3, false, 2243405, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.Cerulean"), Cerulean.class, "Cerulean", 2, instance, 30, 2, false, 4243405, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.Serval"), Serval.class, "Serval", 3, instance, 30, 3, true, 16703405, 6375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.Shoebill"), Shoebill.class, "Shoebill", 5, instance, 30, 3, true, 7375001, 10000);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.WhiteOwl"), WhiteOwl.class, "WhiteOwl", 6, instance, 30, 3, true, 7375001, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.BrownOwl"), BrownOwl.class, "BrownOwl", 7, instance, 30, 3, true, 5243410, 5243405);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.Araisan"), Araisan.class, "Araisan", 12, instance, 30, 3, true, 8421504, 4243405);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.Fennec"), Fennec.class, "Fennec", 14, instance, 30, 3, true, 13434879, 13408767);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.PoisonCerulean"), PoisonCerulean.class, "PoisonCerulean", 13, instance, 30, 2, false, 4243405, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.LuckyBeast"), LuckyBeast.class, "LuckyBeast", 8, instance, 35, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.Guide"), Guide.class, "Guide", 11, instance, 30, 3, false);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.SandStarHandler"), SandStarHandler.class, "SandStarHandler", 15, instance, 30, 3, false,9868950,13434879);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "mobs.RoyalPenguin"), RoyalPenguin.class, "RoyalPenguin", 16, instance, 30, 3, false, 2243405, 7375001);

    }
    public static void addSpawns()
    {
        //Biomeの種類で湧くように
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
            if (types.contains(FOREST) &&types.contains(PLAINS) && !types.contains(SNOWY) && !types.contains(NETHER) && !biome.getSpawnableList(EnumCreatureType.CREATURE).isEmpty()) {
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
        List<Biome> swap_biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY) {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
            if (types.contains(SWAMP) && !types.contains(WASTELAND) && !types.contains(NETHER) && !biome.getSpawnableList(EnumCreatureType.CREATURE).isEmpty()) {
                swap_biomes.add(biome);
            }
        }
        EntityRegistry.addSpawn(Fennec.class, 7, 1, 3, EnumCreatureType.CREATURE,sandy_biomes.toArray(new Biome[sandy_biomes.size()]));
        EntityRegistry.addSpawn(KouteiPenguin.class, 7, 1, 3, EnumCreatureType.CREATURE,snow_biomes.toArray(new Biome[snow_biomes.size()]));
        EntityRegistry.addSpawn(Cerulean.class, 40, 2, 4, EnumCreatureType.MONSTER, plain_biomes.toArray(new Biome[plain_biomes.size()]));
        EntityRegistry.addSpawn(Serval.class, 12, 2, 3, EnumCreatureType.CREATURE, Biome.getBiome(35), Biome.getBiome(36), Biome.getBiome(163));
        EntityRegistry.addSpawn(Shoebill.class, 12, 2, 3, EnumCreatureType.CREATURE,forest_biomes.toArray(new Biome[forest_biomes.size()]));
        EntityRegistry.addSpawn(Araisan.class, 12, 2, 3, EnumCreatureType.CREATURE,plain_biomes.toArray(new Biome[plain_biomes.size()]));
        EntityRegistry.addSpawn(WhiteOwl.class, 10, 1, 3, EnumCreatureType.CREATURE,forest_biomes.toArray(new Biome[forest_biomes.size()]));
        EntityRegistry.addSpawn(BrownOwl.class, 10, 1, 3, EnumCreatureType.CREATURE,forest_biomes.toArray(new Biome[forest_biomes.size()]));
        EntityRegistry.addSpawn(PoisonCerulean.class, 40, 3, 5, EnumCreatureType.MONSTER, swap_biomes.toArray(new Biome[swap_biomes.size()]));
    }
}
