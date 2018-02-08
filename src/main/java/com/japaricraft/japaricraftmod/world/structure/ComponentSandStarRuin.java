package com.japaricraft.japaricraftmod.world.structure;

import com.japaricraft.japaricraftmod.handler.JapariBlocks;
import com.japaricraft.japaricraftmod.handler.JapariTreasure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComponentSandStarRuin extends StructureComponent {
    protected int averageGroundLvl = -1;
    private EnumFacing facing;

    int par1;
    // 構成パーツリストを記憶するためのリスト
    public List<StructureComponent> structureComponents = new ArrayList<>();

    public ComponentSandStarRuin() {
    }

    public ComponentSandStarRuin(int par1, Random par2Random, int par3, int par4) {
        super();
        this.facing = EnumFacing.NORTH;
        this.setCoordBaseMode(facing);
        this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 10, 64 + 15, par4 + 10);
    }

    protected int getAverageGroundLevel(World worldIn, StructureBoundingBox structurebb) {
        int i = 0;
        int j = 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k) {
            for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l) {
                blockpos$mutableblockpos.setPos(l, 64, k);

                if (structurebb.isVecInside(blockpos$mutableblockpos)) {
                    i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), worldIn.provider.getAverageGroundLevel() - 1);
                    ++j;
                }
            }
        }

        if (j == 0) {
            return -1;
        } else {
            return i / j;
        }
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {
        tagCompound.setInteger("HPos", this.averageGroundLvl);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
        this.averageGroundLvl = tagCompound.getInteger("HPos");
    }


    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureboundingbox);

            if (this.averageGroundLvl < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
        }

        IBlockState iblockstate = Blocks.STONEBRICK.getDefaultState();
        IBlockState iblockstate2 = JapariBlocks.SANDSTAR_BLOCK.getDefaultState();
        IBlockState iblockstate3 = Blocks.WATER.getDefaultState();
        //土台と柱
        this.fillWithBlocks(world, structureboundingbox, 0, -1, 0, 10, 8, 10, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, structureboundingbox, 1, 8, 9, 1, 10, 9, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, structureboundingbox, 1, 8, 1, 1, 12, 1, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, structureboundingbox, 9, 8, 1, 9, 11, 1, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, structureboundingbox, 9, 8, 9, 9, 14, 9, iblockstate, iblockstate, false);
        //余計な部分を削る
        this.fillWithAir(world, structureboundingbox, 0, 8, 0, 0, 8, 10);
        this.fillWithAir(world, structureboundingbox, 0, 8, 0, 10, 8, 0);
        this.fillWithAir(world, structureboundingbox, 10, 8, 0, 10, 8, 10);
        this.fillWithAir(world, structureboundingbox, 0, 8, 10, 10, 8, 10);
        //祭壇
        this.fillWithBlocks(world, structureboundingbox, 2, 8, 2, 8, 8, 8, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, structureboundingbox, 3, 8, 3, 7, 9, 7, iblockstate, iblockstate, false);
        this.setBlockState(world, iblockstate2, 2, 8, 2, structureboundingbox);
        this.setBlockState(world, iblockstate2, 2, 8, 8, structureboundingbox);
        this.setBlockState(world, iblockstate2, 8, 8, 2, structureboundingbox);
        this.setBlockState(world, iblockstate2, 8, 8, 8, structureboundingbox);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 3, 9, 3, structureboundingbox);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 3, 9, 7, structureboundingbox);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 7, 9, 3, structureboundingbox);
        this.setBlockState(world, Blocks.AIR.getDefaultState(), 7, 9, 7, structureboundingbox);
        this.setBlockState(world, JapariBlocks.ANIMAL_STATUE.getDefaultState(), 5, 10, 3, structureboundingbox);
        this.setBlockState(world, JapariBlocks.ANIMAL_STATUE.getDefaultState(), 3, 10, 5, structureboundingbox);
        this.setBlockState(world, JapariBlocks.ANIMAL_STATUE.getDefaultState(), 5, 10, 7, structureboundingbox);
        this.setBlockState(world, JapariBlocks.ANIMAL_STATUE.getDefaultState(), 7, 10, 5, structureboundingbox);
        //サンドスターの設置
        this.setBlockState(world, iblockstate2, 1, 11, 9, structureboundingbox);
        this.setBlockState(world, iblockstate2, 1, 13, 1, structureboundingbox);
        this.setBlockState(world, iblockstate2, 9, 12, 1, structureboundingbox);
        this.setBlockState(world, iblockstate2, 9, 15, 9, structureboundingbox);
        this.setBlockState(world, iblockstate2, 5, 9, 5, structureboundingbox);
        //チェスト
        this.generateChest(world, structureboundingbox, random, 5, 8, 5, JapariTreasure.ruin);

        return true;
    }
}