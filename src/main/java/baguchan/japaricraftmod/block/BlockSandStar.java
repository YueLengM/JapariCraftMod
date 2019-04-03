package baguchan.japaricraftmod.block;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.JapariParticleTypes;
import baguchan.japaricraftmod.handler.JapariSounds;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;


public class BlockSandStar extends Block {
    public BlockSandStar()
    {
        super(Material.ROCK);
        setCreativeTab(JapariCraftMod.tabJapariCraft);
        setUnlocalizedName("SandStarBlock");
        setHardness(4.0F);/*硬さ*/
        setResistance(20.0F);/*爆破耐性*/
        setSoundType(SoundType.GLASS);
        setLightLevel(6F);
        setHarvestLevel("pickaxe",1);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(300) == 0) {
            worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, JapariSounds.SANDSTAR_ACTIVE, SoundCategory.BLOCKS, 0.3F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        if (rand.nextInt(3) == 0) {
            int j = rand.nextInt(2) * 2 - 1;
            int k = rand.nextInt(2) * 2 - 1;
            double d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
            double d1 = (double) ((float) pos.getY() + rand.nextFloat());
            double d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) k;
            double d3 = (double) (rand.nextFloat() * (float) j);
            double d4 = ((double) rand.nextFloat() - 0.5D) * 0.125D;
            double d5 = (double) (rand.nextFloat() * (float) k);
            JapariCraftMod.proxy.spawnParticle(JapariParticleTypes.SANDSTAR, worldIn, d0, d1, d2, d3, d4, d5);
        }
    }

    //ブロックの描画
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state)
    {
        return false;
    }

}