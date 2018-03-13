package com.japaricraft.japaricraftmod.world.structure;

import com.japaricraft.japaricraftmod.handler.JapariBlocks;
import com.japaricraft.japaricraftmod.mob.EntityCeruleanEye;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class ComponentSandStarRuinCorridor extends StructureComponent {
    //入り口とはまた別のやつで通路的なやつ
    protected int averageGroundLvl = -1;
    private EnumFacing facing;

    // 構成パーツリストを記憶するためのリスト

    public ComponentSandStarRuinCorridor() {
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {

    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {

    }

    public ComponentSandStarRuinCorridor(int height, Random par2Random, int par3, int par4, int par5) {
        super();
        this.facing = EnumFacing.NORTH;
        this.setCoordBaseMode(facing);
        this.boundingBox = new StructureBoundingBox(par3, height, par4, par3 + 6, height + 4, par4 + 7);
    }

    @Override
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {
        IBlockState iblockstate = Blocks.STONEBRICK.getDefaultState();
        IBlockState air = Blocks.AIR.getDefaultState();
        this.fillWithAir(world, structureboundingbox, 0, 0, 0, 6, 4, 7);
        //土台と中
        this.fillWithBlocks(world, structureboundingbox, 0, 0, 0, 6, 4, 7, iblockstate, air, false);
        //中を空気に
        this.fillWithAir(world, structureboundingbox, 1, 1, 1, 5, 3, 6);

        //入り口とかの位置を確保
        this.fillWithAir(world, structureboundingbox, 2, 1, 0, 4, 3, 0);
        this.fillWithAir(world, structureboundingbox, 2, 1, 7, 4, 3, 7);
        if (random.nextBoolean()) {
            this.setBlockState(world, JapariBlocks.SANDSTAR_Lamp.getDefaultState(), 3, 0, 4, structureboundingbox);
        } else {
            this.generateSpawner(world, structureboundingbox, random, 3, 0, 4);
        }

        return true;
    }

    protected void generateSpawner(World worldIn, StructureBoundingBox sbb, Random rand, int x, int y, int z) {
        BlockPos blockpos =
                new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));

        if (sbb.isVecInside(blockpos) && worldIn.getBlockState(blockpos).getBlock() != Blocks.MOB_SPAWNER) {
            this.setBlockState(worldIn, Blocks.MOB_SPAWNER.getDefaultState(), x, y, z, sbb);
            TileEntity tileentity = worldIn.getTileEntity(blockpos);

            if (tileentity instanceof TileEntityMobSpawner) {
                ((TileEntityMobSpawner) tileentity).getSpawnerBaseLogic().setEntityId(EntityList.getKey(EntityCeruleanEye.class));
            }
        }
    }

}