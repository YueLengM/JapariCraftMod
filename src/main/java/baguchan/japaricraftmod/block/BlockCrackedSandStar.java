package baguchan.japaricraftmod.block;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.handler.JapariItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCrackedSandStar extends Block {
    public BlockCrackedSandStar() {
        super(Material.ICE);
        setCreativeTab(JapariCraftMod.tabJapariCraft);
        setUnlocalizedName("cracked_sandstar");
        setHardness(3.9F);/*硬さ*/
        setResistance(19.0F);/*爆破耐性*/
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

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return JapariItems.sandstarfragment;
    }

    public int quantityDropped(Random random) {
        return 3 + random.nextInt(4);
    }

}