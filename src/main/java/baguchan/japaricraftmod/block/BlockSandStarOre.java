package baguchan.japaricraftmod.block;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.JapariParticleTypes;
import baguchan.japaricraftmod.handler.JapariItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockSandStarOre extends Block {

    public BlockSandStarOre() {
        super(Material.ROCK);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("sandstar_ore");
        this.setHardness(5f);
        this.setResistance(0f);
        setLightLevel(6F);
        this.setHarvestLevel("pickaxe", 2);
    }

    @Override
    public int quantityDropped(Random random) {
        return 1 + random.nextInt(2);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

        if (rand.nextInt(4) == 0) {
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

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return JapariItems.sandstarfragment;
    }

    // 幸運でドロップする量の設定。(幸運で掘った時にドロップする量をランダムにできる)
    @Override
    public int quantityDroppedWithBonus(int level, Random random) {
        if (level > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getDefaultState(), random, level)) {
            int j = random.nextInt(level + 2) - 1;
            if (j < 0) {
                j = 0;
            }
            return this.quantityDropped(random) * (j + 1);
        } else {
            return this.quantityDropped(random);
        }
    }


    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return true;
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = new Random();
        return rand.nextInt(5) + 3;
    }
}
