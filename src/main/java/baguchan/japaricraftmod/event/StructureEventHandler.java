package baguchan.japaricraftmod.event;

import baguchan.japaricraftmod.world.structure.MapGenSandStarRuin;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StructureEventHandler {
    MapGenSandStarRuin mapGenSandStarRuin = new MapGenSandStarRuin();

    public static BlockPos getHeight(World world, BlockPos pos) {
        for (int y = 0; y < 256; y++) {
            BlockPos pos1 = pos.up(y);
            if (world.getBlockState(pos1.up()).getBlock() == Blocks.AIR && world.getBlockState(pos1.down()).getBlock() != Blocks.AIR) {
                return pos1;
            }
        }
        return pos;
    }

    // generateStructuresInChunk相当
    @SubscribeEvent
    public void onPopulateChunkEvent(PopulateChunkEvent.Pre event) {

        int x = (event.getChunkX() * 16) + event.getRand().nextInt(16);
        int z = (event.getChunkZ() * 16) + event.getRand().nextInt(16);
        BlockPos height = getHeight(event.getWorld(), new BlockPos(x, 0, z));
        if (event.getWorld().provider.getDimensionType() == DimensionType.OVERWORLD && BiomeDictionary.hasType(event.getWorld().getBiome(height), BiomeDictionary.Type.FOREST)) {
            mapGenSandStarRuin.generate(event.getWorld(), event.getChunkX(), event.getChunkZ(), null);
            mapGenSandStarRuin.generateStructure(event.getWorld(), event.getRand(), event.getWorld().getChunkFromChunkCoords(event.getChunkX(), event.getChunkZ()).getPos());
        }
    }
}

