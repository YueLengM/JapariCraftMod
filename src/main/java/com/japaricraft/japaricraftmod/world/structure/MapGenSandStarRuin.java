package com.japaricraft.japaricraftmod.world.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.Map;
import java.util.Random;

public class MapGenSandStarRuin extends MapGenStructure {
    /**
     * None
     */
    private int distance;

    public MapGenSandStarRuin() {
        this.distance = 34;
    }

    public MapGenSandStarRuin(Map<String, String> map) {
        this();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (((String) entry.getKey()).equals("distance")) {
                this.distance = MathHelper.getInt(entry.getValue(), this.distance, 9);
            }
        }
    }

    public String getStructureName() {
        return "SandStarRuin";
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
        Random random = this.world.setRandomSeed(k, l, 12357617);
        k = k * this.distance;
        l = l * this.distance;
        k = k + random.nextInt(this.distance - 8);
        l = l + random.nextInt(this.distance - 8);

        if (i == k && j == l) {
            return true;

        }

        return false;
    }

    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        this.world = worldIn;
        return findNearestStructurePosBySpacing(worldIn, this, pos, this.distance, 8, 12357617, false, 100, findUnexplored);
    }

    @Override
    protected StructureStart getStructureStart(int i, int j) {
        return new StructureSandStarRuinStart(this.world, this.rand, i, j);
    }
}