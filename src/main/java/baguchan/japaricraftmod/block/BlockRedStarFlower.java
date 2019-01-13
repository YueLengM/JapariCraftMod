package baguchan.japaricraftmod.block;

import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockRedStarFlower extends BlockBush {
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.20000001192092896D, 0.0D, 0.20000001192092896D, 0.799999988079071D, 0.5000000059604645D, 0.799999988079071D);

    public BlockRedStarFlower() {
        super();
        setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setSoundType(SoundType.PLANT);
        this.setUnlocalizedName("redstar_flower");
        this.setResistance(0.5F);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {

        Block block = state.getBlock();
        return block == Blocks.GRASS || block == Blocks.DIRT || state.getMaterial() == Material.GRASS;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
        BlockPos down = pos.down();

        IBlockState soil = world.getBlockState(down);

        return soil.getBlock().canSustainPlant(soil, world, down, EnumFacing.UP, this);

    }


    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

}
