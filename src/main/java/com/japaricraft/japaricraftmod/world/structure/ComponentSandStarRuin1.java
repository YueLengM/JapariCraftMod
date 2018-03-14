package com.japaricraft.japaricraftmod.world.structure;

import com.japaricraft.japaricraftmod.handler.JapariBlocks;
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

public class ComponentSandStarRuin1 extends StructureComponent {
    protected int averageGroundLvl = -1;
    private EnumFacing facing;

    // 構成パーツリストを記憶するためのリスト
    public List<StructureComponent> structureComponents = new ArrayList<>();

    public ComponentSandStarRuin1() {
    }

    public ComponentSandStarRuin1(int par1, Random par2Random, int par3, int par4) {
        super();
        this.facing = EnumFacing.NORTH;
        this.setCoordBaseMode(facing);
        this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 8, 64 + 5, par4 + 8);
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
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
        StructureComponent structureComponent = new ComponentSandStarRuinHole(this.boundingBox.minY + 1, par3Random, this.boundingBox.minX, this.boundingBox.maxZ, 0);
        ((ComponentSandStarRuin1) par1StructureComponent).structureComponents.add(structureComponent);
        par2List.add(structureComponent);
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
        IBlockState air = Blocks.AIR.getDefaultState();
        IBlockState ironBar = Blocks.IRON_BARS.getDefaultState();
        this.fillWithAir(world, structureboundingbox, 0, 0, 0, 8, 6, 8);
        //土台
        this.fillWithBlocks(world, structureboundingbox, 0, 0, 0, 8, 5, 8, iblockstate, air, false);
        //中を空気に
        this.fillWithAir(world, structureboundingbox, 1, 1, 1, 7, 4, 7);

        //サンドスター
        this.setBlockState(world, JapariBlocks.SANDSTAR_BLOCK.getDefaultState(), 4, 4, 4, structureboundingbox);
        //入り口とかの位置を確保
        this.fillWithAir(world, structureboundingbox, 3, 1, 0, 5, 3, 0);
        this.fillWithAir(world, structureboundingbox, 3, 1, 8, 5, 3, 8);
        this.fillWithBlocks(world, structureboundingbox, 3, 1, 3, 5, 1, 5, iblockstate, iblockstate, false);
        //鉄作
        this.fillWithBlocks(world, structureboundingbox, 0, 2, 3, 0, 4, 3, ironBar, ironBar, false);
        this.fillWithBlocks(world, structureboundingbox, 0, 2, 6, 0, 4, 6, ironBar, ironBar, false);
        this.fillWithBlocks(world, structureboundingbox, 8, 2, 3, 8, 4, 3, ironBar, ironBar, false);
        this.fillWithBlocks(world, structureboundingbox, 8, 2, 6, 8, 4, 6, ironBar, ironBar, false);

        this.setBlockState(world, JapariBlocks.ANIMAL_STATUE.getDefaultState(), 4, 2, 4, structureboundingbox);

        return true;
    }

}