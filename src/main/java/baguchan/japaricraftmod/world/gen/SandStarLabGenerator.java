package baguchan.japaricraftmod.world.gen;

import baguchan.japaricraftmod.JapariConfig;
import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class SandStarLabGenerator implements IWorldGenerator {
    public static final ResourceLocation SANDSTARLAB = new ResourceLocation(JapariCraftMod.MODID, "sandstarlab");

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!(world instanceof WorldServer))
            return;
        WorldServer sWorld = (WorldServer) world;

        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);

        BlockPos pos = getHeight(world, new BlockPos(x, 0, z));
        if (world.provider.getDimensionType() == DimensionType.OVERWORLD) {
            if (BiomeDictionary.hasType(world.getBiome(pos), BiomeDictionary.Type.PLAINS) || BiomeDictionary.hasType(world.getBiome(pos), BiomeDictionary.Type.WASTELAND)) {
                if (random.nextInt(JapariConfig.sandstarlabGen) == 0) {

                    IBlockState state = world.getBlockState(pos.down());

                    pos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());

                    generateLabAt(sWorld, random, pos);
                }
            }
        }

    }

    public static BlockPos getHeight(World world, BlockPos pos) {

        for (int y = 0; y < 256; y++) {

            BlockPos pos1 = pos.up(y);

            if (world.getBlockState(pos1.up()).getBlock() == Blocks.AIR && world.getBlockState(pos1.down()).getBlock() != Blocks.AIR) {

                return pos1;

            }

        }

        return pos;

    }

    public static void generateLabAt(WorldServer world, Random random, BlockPos pos) {
        MinecraftServer server = world.getMinecraftServer();
        Template template = world.getStructureTemplateManager().getTemplate(server, SANDSTARLAB);
        PlacementSettings settings = new PlacementSettings().setIgnoreEntities(true);
        settings.setRotation(Rotation.values()[random.nextInt(Rotation.values().length)]);

        BlockPos size = template.getSize();
        for (int x = 0; x < size.getX(); x++)
            for (int y = 0; y < size.getY(); y++)
                for (int z = 0; z < size.getZ(); z++) {

                    template.addBlocksToWorld(world, pos, settings);
                }
    }
}