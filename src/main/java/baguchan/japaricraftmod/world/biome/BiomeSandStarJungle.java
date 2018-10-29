package baguchan.japaricraftmod.world.biome;

import baguchan.japaricraftmod.handler.JapariBlocks;
import baguchan.japaricraftmod.mob.*;
import baguchan.japaricraftmod.world.biome.gen.WorldGenAppleTree;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;

import java.util.Random;

public class BiomeSandStarJungle extends Biome {
    private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
    private static final IBlockState OAK_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));

    public BiomeSandStarJungle() {
        super(new Biome.BiomeProperties("SandStarJungle").setTemperature(0.85F).setRainfall(0.9F).setBaseHeight(0.0F).setHeightVariation(0.35F));
        this.spawnableCreatureList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntityShoebill.class, 2, 2, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityBrownOwl.class, 2, 2, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWhiteOwl.class, 2, 2, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySquirre.class, 2, 2, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityAraisan.class, 2, 2, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 4, 2, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 4, 2, 3));

        this.spawnableMonsterList.add(new SpawnListEntry(EntitySandStarSlime.class, 50, 1, 2));

        this.decorator.treesPerChunk = 4;
        this.decorator.grassPerChunk = 8;
        this.decorator.reedsPerChunk = 3;
        this.decorator.flowersPerChunk = 5;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        if (rand.nextInt(4) == 0) {
            return new WorldGenAppleTree(5 + rand.nextInt(2), JUNGLE_LOG, OAK_LEAF, false, JapariBlocks.TREE_APPLE.getDefaultState());
        } else if (rand.nextInt(2) == 0) {
            return new WorldGenShrub(JUNGLE_LOG, OAK_LEAF);
        } else {
            return (WorldGenAbstractTree) (new WorldGenTrees(false, 4 + rand.nextInt(3), JUNGLE_LOG, JUNGLE_LEAF, true));
        }
    }


    @Override
    public int getGrassColorAtPos(BlockPos pos) {
        return 0x29B369;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0x29B369;
    }

}