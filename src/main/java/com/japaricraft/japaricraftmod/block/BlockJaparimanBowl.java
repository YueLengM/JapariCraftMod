package com.japaricraft.japaricraftmod.block;

import com.japaricraft.japaricraftmod.JapariCraftMod;
import com.japaricraft.japaricraftmod.handler.JapariItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockJaparimanBowl extends Block {
    protected static final AxisAlignedBB Statue_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.4D, 0.8D);

    public BlockJaparimanBowl() {
        super(Material.WOOD);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        setUnlocalizedName("JaparimanBowl");
        setResistance(1.0F);/*爆破耐性*/
        setHardness(0.4F);
        setSoundType(SoundType.SNOW);
    }


    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return Statue_AABB;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return JapariItems.japariman;
    }

    @Override
    public int quantityDropped(Random random) {
        return 3;
    }
    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

}
