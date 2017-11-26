package com.japaricraft.japaricraftmod.block.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockSandStarWater extends BlockFluidClassic {
    public BlockSandStarWater(Fluid fluid) {
        super(fluid, Material.WATER);
        this.setLightOpacity(3);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100));
        }
        if (entity instanceof EntityPlayer) {
            ((EntityPlayer) entity).getFoodStats().addStats(1, 0);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        super.randomDisplayTick(state, world, pos, rand);
        if (rand.nextInt(25) == 0) {
            world.spawnParticle(EnumParticleTypes.CLOUD, pos.getX() + rand.nextFloat(), pos.getY() + 1.0F, pos.getZ() + rand.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FULL_BLOCK_AABB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockAdded(worldIn, pos, state);
        this.checkForMixing(worldIn, pos, state);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos neighborPos) {
        super.neighborChanged(state, worldIn, pos, blockIn, neighborPos);
        this.checkForMixing(worldIn, pos, state);
    }

    public boolean checkForMixing(World worldIn, BlockPos pos, IBlockState state) {
        boolean flag = false;

        for (EnumFacing enumfacing : EnumFacing.values()) {
            if (enumfacing != EnumFacing.DOWN && (worldIn.getBlockState(pos.offset(enumfacing)).getMaterial().isLiquid() == true)) {
                if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() != this.getBlockState().getBlock()) {
                    flag = true;
                    break;
                }
            }
        }

        if (flag) {
            Integer integer = state.getValue(LEVEL);

            if (integer.intValue() == 0) {
                worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
                return true;
            }

            if (integer.intValue() <= 4) {
                worldIn.setBlockState(pos, Blocks.STONE.getDefaultState());
                return true;
            }
        }

        return false;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing) {
        return BlockFaceShape.UNDEFINED;
    }
}