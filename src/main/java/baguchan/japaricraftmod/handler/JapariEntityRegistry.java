package baguchan.japaricraftmod.handler;


import baguchan.japaricraftmod.JapariConfig;
import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.mob.*;
import baguchan.japaricraftmod.mob.projectile.EntityDarkSandStarball;
import com.google.common.collect.Lists;
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

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class JapariEntityRegistry {
    public static void registerEntities() {
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "kouteipenguin"), EntityKouteiPenguin.class, "KouteiPenguin", 1, JapariCraftMod.instance, 70, 3, false, 2243405, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "cerulean"), EntityCerulean.class, "Cerulean", 2, JapariCraftMod.instance, 70, 3, false, 0x87CFFF, 0x7AC6FF);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "serval"), EntityServal.class, "Serval", 3, JapariCraftMod.instance, 70, 3, false, 16703405, 6375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "shoebill"), EntityShoebill.class, "Shoebill", 5, JapariCraftMod.instance, 70, 3, false, 7375001, 10000);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "whiteOwl"), EntityWhiteOwl.class, "WhiteOwl", 6, JapariCraftMod.instance, 70, 3, false, 7375001, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "brownOwl"), EntityBrownOwl.class, "BrownOwl", 7, JapariCraftMod.instance, 70, 3, false, 5243410, 5243405);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "sandstarslime"), EntitySandStarSlime.class, "SandStarSlime", 8, JapariCraftMod.instance, 70, 3, false, 0x45FFE0, 0x39D4BA);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "guide"), EntityGuide.class, "Guide", 11, JapariCraftMod.instance, 60, 1, false);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "araisan"), EntityAraisan.class, "Araisan", 12, JapariCraftMod.instance, 60, 3, false, 0x666699, 0x969696);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "poisoncerulean"), PoisonEntityCerulean.class, "PoisonCerulean", 13, JapariCraftMod.instance, 70, 3, false, 0x87CFFF, 0x7AC6FF);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "fennec"), EntityFennec.class, "Fennec", 14, JapariCraftMod.instance, 70, 3, false, 0xFFEFC4, 0xFFF4AB);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "sandcat"), EntitySandCat.class, "SandCat", 15, JapariCraftMod.instance, 70, 3, false, 0xF5F5DC, 0x969696);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "royalpenguin"), RoyalPenguinEntity.class, "RoyalPenguin", 16, JapariCraftMod.instance, 70, 3, false, 2243405, 7375001);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "alpaca"), EntityAlpaca.class, "Alpaca", 17, JapariCraftMod.instance, 70, 3, false, 0xf0f5f0, 0xf5f5dc);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "squirre"), EntitySquirre.class, "Squirre", 18, JapariCraftMod.instance, 70, 3, false, 0xf0f5f0, 0x993300);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "tutinoko"), EntityTutinoko.class, "Tutinoko", 19, JapariCraftMod.instance, 70, 3, false, 0x5C2A2C, 0xC92C2C);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "otter"), EntityOtter.class, "Otter", 20, JapariCraftMod.instance, 70, 3, false, 0xADADAD, 0x949494);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "ceruleaneye"), EntityCeruleanEye.class, "CeruleanEye", 21, JapariCraftMod.instance, 70, 3, false, 0x333333, 0x339966);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "endercerulean"), EntityEnderCerulean.class, "EnderCerulean", 22, JapariCraftMod.instance, 70, 3, false, 0x333333, 0x808080);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "darksandstarball"), EntityDarkSandStarball.class, "DarkSandStarBall", 23, JapariCraftMod.instance, 120, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "beluga"), EntityBeluga.class, "Beluga", 24, JapariCraftMod.instance, 80, 3, false, 0xF2FFFF, 0xB8FAFF);
        EntityRegistry.registerModEntity(new ResourceLocation(JapariCraftMod.MODID, "twilightkobolt"), EntityTwilightKobolt.class, "TwilightKobolt", 25, JapariCraftMod.instance, 80, 3, false, 0x372096, 0x895d1b);
    }

    public static void addSpawns() {
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
            if (types.contains(SNOWY) && !types.contains(FOREST) && !types.contains(SANDY) && !types.contains(NETHER) && !biome.getSpawnableList(EnumCreatureType.CREATURE).isEmpty()) {
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
        List<Biome> jungle_biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY) {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
            if (types.contains(JUNGLE) && !types.contains(WASTELAND) && !types.contains(NETHER) && !biome.getSpawnableList(EnumCreatureType.CREATURE).isEmpty()) {
                jungle_biomes.add(biome);
            }
        }
        List<Biome> spooky_biomes = Lists.newArrayList();
        for (Biome biome : Biome.REGISTRY) {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
            if (types.contains(SPOOKY) && !types.contains(NETHER) && !biome.getSpawnableList(EnumCreatureType.CREATURE).isEmpty()) {
                spooky_biomes.add(biome);
            }
        }
        EntityRegistry.addSpawn(EntityFennec.class, 3, 1, 2, EnumCreatureType.CREATURE, sandy_biomes.toArray(new Biome[sandy_biomes.size()]));
        EntityRegistry.addSpawn(EntitySandCat.class, 2, 1, 2, EnumCreatureType.CREATURE, sandy_biomes.toArray(new Biome[sandy_biomes.size()]));
        EntityRegistry.addSpawn(EntityKouteiPenguin.class, 3, 1, 2, EnumCreatureType.CREATURE, snow_biomes.toArray(new Biome[snow_biomes.size()]));
        EntityRegistry.addSpawn(RoyalPenguinEntity.class, 3, 1, 2, EnumCreatureType.CREATURE, snow_biomes.toArray(new Biome[snow_biomes.size()]));
        EntityRegistry.addSpawn(EntityServal.class, 7, 2, 3, EnumCreatureType.CREATURE, Biome.getBiome(35), Biome.getBiome(36), Biome.getBiome(163));
        EntityRegistry.addSpawn(EntityShoebill.class, JapariConfig.plainsfriends_wight, 2, 3, EnumCreatureType.CREATURE, plain_biomes.toArray(new Biome[plain_biomes.size()]));
        EntityRegistry.addSpawn(EntityAraisan.class, JapariConfig.plainsfriends_wight, 2, 3, EnumCreatureType.CREATURE, plain_biomes.toArray(new Biome[plain_biomes.size()]));
        EntityRegistry.addSpawn(EntityWhiteOwl.class, JapariConfig.plainsfriends_wight, 2, 2, EnumCreatureType.CREATURE, forest_biomes.toArray(new Biome[forest_biomes.size()]));
        EntityRegistry.addSpawn(EntityBrownOwl.class, JapariConfig.plainsfriends_wight, 2, 2, EnumCreatureType.CREATURE, forest_biomes.toArray(new Biome[forest_biomes.size()]));
        EntityRegistry.addSpawn(EntitySquirre.class, JapariConfig.plainsfriends_wight, 2, 3, EnumCreatureType.CREATURE, forest_biomes.toArray(new Biome[forest_biomes.size()]));
        EntityRegistry.addSpawn(EntityOtter.class, 3, 2, 3, EnumCreatureType.CREATURE, jungle_biomes.toArray(new Biome[jungle_biomes.size()]));
        EntityRegistry.addSpawn(EntityCerulean.class, JapariConfig.cerulean_wight, 2, 4, EnumCreatureType.MONSTER, biomes.toArray(new Biome[biomes.size()]));
        EntityRegistry.addSpawn(PoisonEntityCerulean.class, JapariConfig.poisoncerulean_wight, 2, 4, EnumCreatureType.MONSTER, Biomes.SWAMPLAND);
        EntityRegistry.addSpawn(EntityAlpaca.class, 6, 2, 3, EnumCreatureType.CREATURE, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.MUTATED_EXTREME_HILLS_WITH_TREES);
        EntityRegistry.addSpawn(EntityTutinoko.class, 6, 1, 2, EnumCreatureType.CREATURE, spooky_biomes.toArray(new Biome[spooky_biomes.size()]));

    }
}
