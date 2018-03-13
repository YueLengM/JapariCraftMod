package com.japaricraft.japaricraftmod.block;

import com.japaricraft.japaricraftmod.JapariCraftMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSandStarLamp extends Block {
    public BlockSandStarLamp() {
        super(Material.ICE);
        setCreativeTab(JapariCraftMod.tabJapariCraft);
        setUnlocalizedName("SandStarLamp");
        setHardness(4.1F);/*硬さ*/
        setResistance(21.0F);/*爆破耐性*/
        setSoundType(SoundType.GLASS);
        setLightLevel(6F);
        setHarvestLevel("pickaxe", 1);
    }

    //ブロックの描画
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
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