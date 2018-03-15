package com.japaricraft.japaricraftmod.world.structure;

import com.japaricraft.japaricraftmod.handler.JapariBlocks;
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

public class ComponentSandStarRuinBossRoom extends StructureComponent {
    private EnumFacing facing;

    int par1;

    public ComponentSandStarRuinBossRoom() {
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {

    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {

    }

    public ComponentSandStarRuinBossRoom(int height, Random par2Random, int par3, int par4, int par5) {
        super();
        this.facing = EnumFacing.NORTH;
        this.setCoordBaseMode(facing);
        this.boundingBox = new StructureBoundingBox(par3, height, par4, par3 + 20, height + 7, par4 + 20);
    }


    @Override
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {
        IBlockState iblockstate = Blocks.STONEBRICK.getDefaultState();
        IBlockState air = Blocks.AIR.getDefaultState();
        IBlockState ironBar = Blocks.IRON_BARS.getDefaultState();
        IBlockState lamp = JapariBlocks.SANDSTAR_Lamp.getDefaultState();
        IBlockState lava = Blocks.FLOWING_LAVA.getDefaultState();
        this.fillWithAir(world, structureboundingbox, 0, 0, 0, 20, 7, 20);
        //土台
        this.fillWithBlocks(world, structureboundingbox, 0, 0, 0, 20, 7, 20, iblockstate, air, false);
        //中を空気に
        this.fillWithAir(world, structureboundingbox, 1, 1, 1, 19, 6, 19);
        //柱とランプ
        this.fillWithBlocks(world, structureboundingbox, 3, 1, 3, 3, 6, 3, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, structureboundingbox, 17, 1, 3, 17, 6, 3, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, structureboundingbox, 3, 1, 17, 3, 6, 17, iblockstate, iblockstate, false);
        this.fillWithBlocks(world, structureboundingbox, 17, 1, 17, 17, 6, 17, iblockstate, iblockstate, false);
        this.setBlockState(world, lamp, 3, 4, 3, structureboundingbox);
        this.setBlockState(world, lamp, 3, 5, 3, structureboundingbox);
        this.setBlockState(world, lamp, 3, 4, 17, structureboundingbox);
        this.setBlockState(world, lamp, 3, 5, 17, structureboundingbox);
        this.setBlockState(world, lamp, 17, 4, 3, structureboundingbox);
        this.setBlockState(world, lamp, 17, 5, 3, structureboundingbox);
        this.setBlockState(world, lamp, 17, 4, 17, structureboundingbox);
        this.setBlockState(world, lamp, 17, 5, 17, structureboundingbox);

        this.setBlockState(world, lamp, 8, 0, 8, structureboundingbox);
        this.setBlockState(world, lamp, 8, 0, 12, structureboundingbox);
        this.setBlockState(world, lamp, 12, 0, 8, structureboundingbox);
        this.setBlockState(world, lamp, 12, 0, 12, structureboundingbox);
        this.setBlockState(world, JapariBlocks.Cerulean_STATUE.getDefaultState(), 10, 1, 10, structureboundingbox);

        this.fillWithAir(world, structureboundingbox, 3, 7, 17, 5, 7, 19);

        return true;
    }
}