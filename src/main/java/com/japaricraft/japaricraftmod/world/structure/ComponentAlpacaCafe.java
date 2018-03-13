package com.japaricraft.japaricraftmod.world.structure;

import com.japaricraft.japaricraftmod.handler.JapariBlocks;
import com.japaricraft.japaricraftmod.handler.JapariTreasure;
import com.japaricraft.japaricraftmod.mob.EntityAlpacaCafe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
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

public class ComponentAlpacaCafe extends StructureComponent {
    protected int averageGroundLvl = -1;
    private EnumFacing facing;

    int par1;
    // 構成パーツリストを記憶するためのリスト
    public List<StructureComponent> structureComponents = new ArrayList<>();

    public ComponentAlpacaCafe() {
    }

    public ComponentAlpacaCafe(int par1, Random par2Random, int par3, int par4) {
        super();
        this.facing = EnumFacing.NORTH;
        this.setCoordBaseMode(facing);
        this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 10, 64 + 6, par4 + 10);
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
        if (this.isLiquidInStructureBoundingBox(world, structureboundingbox)) {
            return false;
        }

        if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureboundingbox);

            if (this.averageGroundLvl < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 6 - 1, 0);
        }

        IBlockState iblockstate = Blocks.STONEBRICK.getDefaultState();
        IBlockState iblockstate2 = Blocks.PLANKS.getDefaultState();
        IBlockState iblockstate3 = Blocks.LOG.getDefaultState();
        IBlockState iBlockState4 = Blocks.GLASS_PANE.getDefaultState();
        this.fillWithAir(world, structureboundingbox, 1, 1, -2, 10, 5, 10);
        //土台と中
        this.fillWithBlocks(world, structureboundingbox, 1, 0, 1, 9, 1, 9, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, structureboundingbox, 1, 2, 1, 9, 5, 9, iblockstate2, iblockstate2, false);
        //柱
        this.fillWithBlocks(world, structureboundingbox, 1, 2, 1, 1, 5, 1, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, structureboundingbox, 1, 2, 9, 1, 5, 9, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, structureboundingbox, 9, 2, 1, 9, 5, 1, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, structureboundingbox, 9, 2, 9, 9, 5, 9, iblockstate3, iblockstate3, false);
        //横の柱
        this.fillWithBlocks(world, structureboundingbox, 1, 5, 1, 1, 5, 9, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, structureboundingbox, 1, 5, 1, 9, 5, 1, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, structureboundingbox, 9, 5, 1, 9, 5, 9, iblockstate3, iblockstate3, false);
        this.fillWithBlocks(world, structureboundingbox, 1, 5, 9, 9, 5, 9, iblockstate3, iblockstate3, false);

        //中を空気に
        this.fillWithAir(world, structureboundingbox, 2, 1, 2, 8, 4, 8);
        //カウンターテーブル
        this.fillWithBlocks(world, structureboundingbox, 2, 1, 6, 7, 1, 6, iblockstate2, iblockstate2, false);
        //本棚
        this.fillWithBlocks(world, structureboundingbox, 2, 1, 8, 2, 4, 8, Blocks.BOOKSHELF.getDefaultState(), Blocks.BOOKSHELF.getDefaultState(), false);
        //ジュークボックス
        this.setBlockState(world, Blocks.JUKEBOX.getDefaultState(), 7, 1, 8, structureboundingbox);
        //サンドスター
        this.setBlockState(world, JapariBlocks.SANDSTAR_BLOCK.getDefaultState(), 4, 0, 4, structureboundingbox);
        this.setBlockState(world, JapariBlocks.SANDSTAR_BLOCK.getDefaultState(), 4, 0, 5, structureboundingbox);
        this.setBlockState(world, JapariBlocks.SANDSTAR_BLOCK.getDefaultState(), 5, 0, 4, structureboundingbox);
        this.setBlockState(world, JapariBlocks.SANDSTAR_BLOCK.getDefaultState(), 5, 0, 5, structureboundingbox);
        //ドアの位置を確保
        this.fillWithAir(world, structureboundingbox, 5, 1, 1, 5, 2, 1);
        this.generateDoor(world, structureboundingbox, random, 5, 1, 1, EnumFacing.NORTH, Blocks.SPRUCE_DOOR);
        //窓
        this.setBlockState(world, iBlockState4, 3, 2, 1, structureboundingbox);
        this.setBlockState(world, iBlockState4, 7, 2, 1, structureboundingbox);
        this.fillWithBlocks(world, structureboundingbox, 1, 2, 3, 1, 2, 7, iBlockState4, iBlockState4, false);
        this.fillWithBlocks(world, structureboundingbox, 9, 2, 3, 9, 2, 7, iBlockState4, iBlockState4, false);
        //チェスト
        this.generateChest(world, structureboundingbox, random, 8, 1, 8, JapariTreasure.cafe);
        this.spawnAlpaca(world, structureboundingbox, 5, 2, 7);

        return true;
    }

    private void spawnAlpaca(World worldIn, StructureBoundingBox structureBoundingBox, int x, int y, int z) {
        int i = this.getXWithOffset(x, z);
        int j = this.getYWithOffset(y);
        int k = this.getZWithOffset(x, z);

        if (structureBoundingBox.isVecInside(new BlockPos(i, j, k))) {
            EntityAlpacaCafe entityAlpaca = new EntityAlpacaCafe(worldIn);

            entityAlpaca.setLocationAndAngles((double) i + 0.5D, (double) j, (double) k + 0.5D, 0.0F, 0.0F);
            entityAlpaca.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityAlpaca)), (IEntityLivingData) null);
            entityAlpaca.enablePersistence();
            worldIn.spawnEntity(entityAlpaca);
        }
    }
}