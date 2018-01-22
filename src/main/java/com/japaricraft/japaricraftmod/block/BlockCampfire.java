package com.japaricraft.japaricraftmod.block;

import com.japaricraft.japaricraftmod.JapariCraftMod;
import com.japaricraft.japaricraftmod.handler.JapariBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCampfire extends Block {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 4);
    public static final PropertyBool BURNING = PropertyBool.create("burning");
    protected static final AxisAlignedBB AABB_CAMPFIRE = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.2D, 0.9D);

    public BlockCampfire() {
        super(Material.WOOD);
        setCreativeTab(JapariCraftMod.tabJapariCraft);
        setUnlocalizedName("Campfire");
        setHardness(0.2F);
        setResistance(0.3F);
        setSoundType(SoundType.WOOD);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);

        int age = ((Integer) state.getValue(AGE)).intValue();

        if (state.getValue(BURNING) == true) {
            if (worldIn.isRainingAt(pos)) {
                worldIn.setBlockState(pos, state.withProperty(BURNING, false).withProperty(AGE, 15), 2);
                worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
                for (int i = 0; i < 6; ++i) {
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double) ((float) pos.getX() + 0.75F - (rand.nextFloat() / 2.0F)), (double) ((float) pos.getY() + 0.9F), (double) ((float) pos.getZ() + 0.75F - (rand.nextFloat() / 2.0F)), 0.0D, 0.0D, 0.0D, new int[]{Block.getStateId(state)});
                }
            }
            if (age < 4) {
                if (rand.nextInt(8) == 0) {
                    worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(age + 1)), 2);
                }
            }
            if (age == 4) {
                if (rand.nextInt(8) == 0) {
                    worldIn.setBlockState(pos, state.withProperty(BURNING, false), 2);
                }
            }
        }
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state.getValue(BURNING) == true) {
            return 14;
        } else {
            return 0;
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        if (state.getValue(BURNING) == true) {
            Item chicken = Items.CHICKEN;
            Item beef = Items.BEEF;
            Item pork = Items.PORKCHOP;
            if (entity instanceof EntityLivingBase) {
                entity.setFire(1);
            }

            if (entity instanceof EntityItem) {
                if (chicken == ((EntityItem) entity).getItem().getItem()) {
                    ((EntityItem) entity).setItem(new ItemStack(Items.COOKED_CHICKEN));
                }
                if (beef == ((EntityItem) entity).getItem().getItem()) {
                    ((EntityItem) entity).setItem(new ItemStack(Items.COOKED_BEEF));
                }
                if (pork == ((EntityItem) entity).getItem().getItem()) {
                    ((EntityItem) entity).setItem(new ItemStack(Items.COOKED_PORKCHOP));
                }
            }
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (playerIn.getHeldItem(hand) != null) {
            Item item = playerIn.getHeldItem(hand).getItem();
            int age = ((Integer) state.getValue(AGE)).intValue();

            if (age == 0) {
                if (state.getValue(BURNING) == false) {
                    if (!worldIn.isRainingAt(pos)) {
                        if (item == Items.STICK) {
                            if (worldIn.rand.nextInt(7) == 0) {
                                worldIn.setBlockState(pos, JapariBlocks.BLOCK_CAMPFIRE.getDefaultState().withProperty(BURNING, true));
                            }

                            if (item == Items.STICK) {
                                playerIn.getHeldItem(hand).setCount(playerIn.getHeldItem(hand).getCount() - 1);
                            }

                            return true;
                        }

                        if (item == Items.FLINT_AND_STEEL) {
                            worldIn.playSound(playerIn, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
                            worldIn.setBlockState(pos, JapariBlocks.BLOCK_CAMPFIRE.getDefaultState().withProperty(BURNING, true));

                            if (item == Items.FLINT_AND_STEEL) {
                                playerIn.getHeldItem(hand).damageItem(1, playerIn);
                            }

                            return true;
                        }
                    }
                }
            }
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        // randomly throw up some particles so it looks like the flesh is bubbling
        super.randomDisplayTick(state, worldIn, pos, rand);

        if (state.getValue(BURNING) == true) {
            if (rand.nextInt(24) == 0) {
                worldIn.playSound((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F), (double) ((float) pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
            }

            worldIn.spawnParticle(EnumParticleTypes.FLAME, (double) ((float) pos.getX() + 0.75F - (rand.nextFloat() / 2.0F)), (double) ((float) pos.getY() + 0.25F + (rand.nextFloat() / 2.0F)), (double) ((float) pos.getZ() + 0.75F - (rand.nextFloat() / 2.0F)), 0.0D, 0.0D, 0.0D, new int[]{Block.getStateId(state)});

            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) ((float) pos.getX() + 0.75F - (rand.nextFloat() / 2.0F)), (double) ((float) pos.getY() + 0.9F), (double) ((float) pos.getZ() + 0.75F - (rand.nextFloat() / 2.0F)), 0.0D, 0.0D, 0.0D, new int[]{Block.getStateId(state)});

        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(AGE, meta >> 1).withProperty(BURNING, Boolean.valueOf((meta & 1) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = state.getValue(AGE) << 1;
        return ((Boolean) state.getValue(BURNING)).booleanValue() ? meta | 1 : meta;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{AGE, BURNING});
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB_CAMPFIRE;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }
}
