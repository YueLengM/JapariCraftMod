package com.japaricraft.japaricraftmod.world.structure;

import com.japaricraft.japaricraftmod.handler.JapariBlocks;
import com.japaricraft.japaricraftmod.handler.JapariTreasure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class ComponentSandStarRuinRoof extends StructureComponent {
    private EnumFacing facing;

    int par1;

    public ComponentSandStarRuinRoof() {
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {

    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {

    }

    public ComponentSandStarRuinRoof(int height, Random par2Random, int par3, int par4, int par5) {
        super();
        this.facing = EnumFacing.NORTH;
        this.setCoordBaseMode(facing);
        this.boundingBox = new StructureBoundingBox(par3, height, par4, par3 + 8, height + 5, par4 + 8);
    }


    @Override
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {
        IBlockState iblockstate = Blocks.STONEBRICK.getDefaultState();
        IBlockState air = Blocks.AIR.getDefaultState();
        IBlockState ironBar = Blocks.IRON_BARS.getDefaultState();
        this.fillWithAir(world, structureboundingbox, 0, 1, 0, 8, 6, 8);
        //土台と中
        this.fillWithBlocks(world, structureboundingbox, 1, 1, 1, 7, 4, 7, iblockstate, air, false);
        //中を空気に
        this.fillWithAir(world, structureboundingbox, 2, 1, 2, 6, 3, 6);
        //穴
        this.fillWithAir(world, structureboundingbox, 3, 0, 3, 5, 0, 5);
        //鉄作
        this.fillWithBlocks(world, structureboundingbox, 1, 2, 4, 1, 2, 5, ironBar, ironBar, false);
        this.fillWithBlocks(world, structureboundingbox, 7, 2, 4, 7, 2, 5, ironBar, ironBar, false);
        //チェスト
        this.setBlockState(world, iblockstate, 3, 1, 4, structureboundingbox);
        this.generateChest(world, structureboundingbox, random, 3, 1, 3, JapariTreasure.roof);
        //飾り
        this.fillWithBlocks(world, structureboundingbox, 3, 5, 3, 5, 5, 5, iblockstate, iblockstate, false);
        this.setBlockState(world, iblockstate, 4, 6, 4, structureboundingbox);
        this.setBlockState(world, JapariBlocks.SANDSTAR_BLOCK.getDefaultState(), 4, 7, 4, structureboundingbox);
        return true;
    }
}