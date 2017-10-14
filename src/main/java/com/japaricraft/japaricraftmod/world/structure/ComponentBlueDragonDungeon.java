package com.japaricraft.japaricraftmod.world.structure;

import com.japaricraft.japaricraftmod.hander.JapariBlocks;
import com.japaricraft.japaricraftmod.hander.JapariTreasure;
import com.japaricraft.japaricraftmod.mob.Cerulean;
import com.japaricraft.japaricraftmod.mob.SandStarHandler;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityIllusionIllager;
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


public class ComponentBlueDragonDungeon extends StructureComponent {
    private EnumFacing facing;

    int par1;
    // 構成パーツリストを記憶するためのリスト
    public List<StructureComponent> structureComponents = new ArrayList<>();

    public ComponentBlueDragonDungeon() {}

    public ComponentBlueDragonDungeon(int par1, Random par2Random, int par3, int par4) {
        super();
        this.facing = EnumFacing.NORTH;
        this.setCoordBaseMode(facing);
        this.boundingBox = new StructureBoundingBox(par3, 25, par4, par3 + 12, 25+45, par4 + 12);
    }
    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {

    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {

    }


    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {
        IBlockState iblockstate= Blocks.STONEBRICK.getDefaultState();
        IBlockState iblockstate2= JapariBlocks.BLOCK_WOODEN_BOX.getDefaultState();
        IBlockState iblockstate3=Blocks.LADDER.getDefaultState();
        IBlockState iblockstate4=Blocks.IRON_BARS.getDefaultState();
        //本体
        this.fillWithBlocks(world, structureboundingbox, 0, 0, 0, 12, 40, 12, iblockstate,iblockstate, false);
        this.fillWithAir(world, structureboundingbox, 1, 35, 1, 11, 39, 11);
        this.fillWithBlocks(world, structureboundingbox, 1, 35, 1, 2, 36, 5, iblockstate2,iblockstate2, false);
        //見張り塔から地下一階までのはしご
        this.fillWithBlocks(world, structureboundingbox, 5, 35, 5, 7, 45, 7, iblockstate,iblockstate, false);
        //入り口を作る
        this.fillWithAir(world, structureboundingbox, 6, 41, 7, 6, 42, 7);
        this.fillWithAir(world, structureboundingbox, 6, 35, 7, 6, 36, 7);
        this.fillWithBlocks(world, structureboundingbox, 6, 35, 6, 6, 45, 6, iblockstate3,iblockstate3, false);
        //地下一階の松明
        this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH), 5, 35, 8, structureboundingbox);
        this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockLadder.FACING, EnumFacing.SOUTH), 7, 35, 8, structureboundingbox);
        //窓
        this.fillWithBlocks(world, structureboundingbox, 3, 36, 3, 9, 38, 9, iblockstate4,iblockstate4, false);
        //下への階段と空間
        this.fillWithBlocks(world, structureboundingbox, 11, 30, 11, 11, 35, 11, iblockstate3,iblockstate3, false);
        this.fillWithAir(world, structureboundingbox, 1, 30, 1, 11, 34, 11);
        this.fillWithBlocks(world, structureboundingbox, 2, 30, 11, 11, 35, 11, iblockstate3,iblockstate3, false);


        this.spawnSnaiperIllager(world, structureboundingbox, 3, 40, 3);
        this.spawnSnaiperIllager(world, structureboundingbox, 10, 40, 4);
        this.spawnHandler(world, structureboundingbox, 3, 35, 3);
        return true;
    }
    protected boolean spawnHandler(World worldIn, StructureBoundingBox p_175817_2_, int p_175817_3_, int p_175817_4_, int p_175817_5_)
    {
        int i = this.getXWithOffset(p_175817_3_, p_175817_5_);
        int j = this.getYWithOffset(p_175817_4_);
        int k = this.getZWithOffset(p_175817_3_, p_175817_5_);

        if (p_175817_2_.isVecInside(new BlockPos(i, j, k)))
        {
            SandStarHandler entityhandler = new SandStarHandler(worldIn);
            entityhandler.heal(entityhandler.getMaxHealth());
            entityhandler.setLocationAndAngles((double)i + 0.5D, (double)j, (double)k + 0.5D, 0.0F, 0.0F);
            entityhandler.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityhandler)), null);
            worldIn.spawnEntity(entityhandler);
            return true;
        }
        else
        {
            return false;
        }
    }
    protected boolean spawnCelurean(World worldIn, StructureBoundingBox p_175817_2_, int p_175817_3_, int p_175817_4_, int p_175817_5_)
    {
        int i = this.getXWithOffset(p_175817_3_, p_175817_5_);
        int j = this.getYWithOffset(p_175817_4_);
        int k = this.getZWithOffset(p_175817_3_, p_175817_5_);

        if (p_175817_2_.isVecInside(new BlockPos(i, j, k)))
        {
            Cerulean entitycerulean = new Cerulean(worldIn);
            entitycerulean.heal(entitycerulean.getMaxHealth());
            entitycerulean.setLocationAndAngles((double)i + 0.5D, (double)j, (double)k + 0.5D, 0.0F, 0.0F);
            entitycerulean.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entitycerulean)), null);
            worldIn.spawnEntity(entitycerulean);
            return true;
        }
        else
        {
            return false;
        }
    }
    protected boolean spawnSnaiperIllager(World worldIn, StructureBoundingBox p_175817_2_, int p_175817_3_, int p_175817_4_, int p_175817_5_)
    {
        int i = this.getXWithOffset(p_175817_3_, p_175817_5_);
        int j = this.getYWithOffset(p_175817_4_);
        int k = this.getZWithOffset(p_175817_3_, p_175817_5_);

        if (p_175817_2_.isVecInside(new BlockPos(i, j, k)))
        {
            EntityIllusionIllager entityIllusionIllager = new EntityIllusionIllager(worldIn);
            entityIllusionIllager.heal(entityIllusionIllager.getMaxHealth());
            entityIllusionIllager.setLocationAndAngles((double)i + 0.5D, (double)j, (double)k + 0.5D, 0.0F, 0.0F);
            entityIllusionIllager.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityIllusionIllager)), null);
            worldIn.spawnEntity(entityIllusionIllager);
            return true;
        }
        else
        {
            return false;
        }
    }
}