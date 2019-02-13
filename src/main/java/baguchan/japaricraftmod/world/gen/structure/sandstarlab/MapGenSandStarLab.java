package baguchan.japaricraftmod.world.gen.structure.sandstarlab;

import net.minecraft.init.Blocks;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Random;

public class MapGenSandStarLab extends MapGenStructure {

    private int distance = 9;

    private int seed = 11387319;


    public MapGenSandStarLab() {
    }


    public MapGenSandStarLab(Map<String, String> map) {
        this();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals("distance")) {
                this.distance = MathHelper.getInt(entry.getValue(), this.distance, 9);
            }
        }
    }

    public String getStructureName() {
        return "sandstarlab";
    }

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0) {
            chunkX -= this.distance - 1;
        }

        if (chunkZ < 0) {
            chunkZ -= this.distance - 1;
        }

        int k = chunkX / this.distance;
        int l = chunkZ / this.distance;
        Random random = this.world.setRandomSeed(k, l, this.seed);
        k = k * this.distance;
        l = l * this.distance;
        k = k + random.nextInt(this.distance - 8);
        l = l + random.nextInt(this.distance - 8);

        return i == k && j == l;

    }

    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        this.world = worldIn;
        return findNearestStructurePosBySpacing(worldIn, this, pos, this.distance, 8, seed, false, 100, findUnexplored);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new Start(this.world, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart {
        private boolean isValid;

        public Start() {
        }

        Start(World world, Random random, int x, int z) {
            super(x, z);
            this.create(world, random, x, z);
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

        private void create(World world, Random random, int chunkX, int chunkZ) {
            Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
            ChunkPrimer chunkPrimer = new ChunkPrimer();

            int x = 5;
            int z = 5;


            if (rotation == Rotation.CLOCKWISE_90) {
                x = -5;
            } else if (rotation == Rotation.CLOCKWISE_180) {
                x = -5;
                z = -5;
            } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                z = -5;
            }

            BlockPos height = getHeight(world, new BlockPos(chunkX, 60, chunkZ));

            if (height.getY() < 60) {
                this.isValid = false;
            } else {
                SandStarLabPieces.SandStarLabTemplate sandstarlab = new SandStarLabPieces.SandStarLabTemplate(world.getSaveHandler().getStructureTemplateManager(), height, rotation);
                this.components.add(sandstarlab);
                sandstarlab.buildComponent(sandstarlab, components, random);
                this.updateBoundingBox();
                this.isValid = true;
            }
        }

        @Override
        public void generateStructure(@Nonnull World world, @Nonnull Random rand, @Nonnull StructureBoundingBox box) {
            super.generateStructure(world, rand, box);
            int y = this.boundingBox.minY;

            for (int x = box.minX; x <= box.maxX; ++x) {
                for (int z = box.minZ; z <= box.maxZ; ++z) {
                    BlockPos pos = new BlockPos(x, y, z);

                    if (!world.isAirBlock(pos) && this.boundingBox.isVecInside(pos)) {
                        boolean isVecInside = false;

                        for (StructureComponent component : this.components) {
                            if (component.getBoundingBox().isVecInside(pos)) {
                                component.addComponentParts(world, rand, box);
                                isVecInside = true;
                                break;
                            }
                        }

                        if (isVecInside) {
                            for (int lighthouseY = y - 1; lighthouseY > 1; --lighthouseY) {
                                BlockPos labPos = new BlockPos(x, lighthouseY, z);

                                if (!world.isAirBlock(labPos) && !world.getBlockState(labPos).getMaterial().isLiquid()) {
                                    break;
                                }
                                world.setBlockState(labPos, Blocks.DIRT.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }
        }

        @Override
        public boolean isSizeableStructure() {
            return this.isValid;
        }
    }
}