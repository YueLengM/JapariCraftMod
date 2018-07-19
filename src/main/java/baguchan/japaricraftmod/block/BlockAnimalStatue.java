package baguchan.japaricraftmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockAnimalStatue extends Block {
    protected static final AxisAlignedBB Statue_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9D, 1.0D);

    public BlockAnimalStatue() {
        super(Material.ROCK);
        setUnlocalizedName("AnimalStatue");
        this.setBlockUnbreakable();
        setResistance(40.0F);/*爆破耐性*/
        setSoundType(SoundType.STONE);
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

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

}
