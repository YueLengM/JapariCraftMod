package baguchan.japaricraftmod.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.IForgeRegistry;

public class JapariBiomes {
    public static final BiomeSandStarJungle SAND_STAR_JUNGLE = new BiomeSandStarJungle();

    public static void registerBiomes(IForgeRegistry<Biome> registry) {
        registry.register(SAND_STAR_JUNGLE.setRegistryName("sandstar_jungle"));
    }

    public static void registerBiomeTypes() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SAND_STAR_JUNGLE, 5));
        BiomeManager.addSpawnBiome(SAND_STAR_JUNGLE);
        BiomeManager.addVillageBiome(SAND_STAR_JUNGLE, true);
        BiomeDictionary.addTypes(SAND_STAR_JUNGLE, BiomeDictionary.Type.HOT, BiomeDictionary.Type.WET, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MAGICAL);
    }
}