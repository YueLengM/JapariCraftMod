package com.japaricraft.japaricraftmod.world.structure;


import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MapGenBlueDragonDungeon extends MapGenStructure {

    private int distance;
    private static List<Biome> SPAWN_BIOMES = Arrays.asList(Biomes.OCEAN,Biomes.DEEP_OCEAN);
    public MapGenBlueDragonDungeon(){
        this.distance = 32;
    }
    @Override
    public String getStructureName() {
        return "SandStarDungeon";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ)
    {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0)
        {
            chunkX -= this.distance - 1;
        }

        if (chunkZ < 0)
        {
            chunkZ -= this.distance - 1;
        }

        int k = chunkX / this.distance;
        int l = chunkZ / this.distance;
        Random random = new Random(world.getSeed() + chunkX + chunkZ * 31);
        k = k * this.distance;
        l = l * this.distance;
        k = k + random.nextInt(this.distance - 8);
        l = l + random.nextInt(this.distance - 8);

        if (i == k && j == l)
        {
            boolean flag = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, SPAWN_BIOMES);

            if(flag) {
                return true;
            }
        }

        return false;
    }
    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored)
    {
        return null;
    }

    @Override
    protected StructureStart getStructureStart(int i, int j) {
        return new StructureBlueDragonDungeonStart(this.world,this.rand, i, j);
    }

}