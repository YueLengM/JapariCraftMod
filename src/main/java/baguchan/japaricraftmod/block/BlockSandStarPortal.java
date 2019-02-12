package baguchan.japaricraftmod.block;

import baguchan.japaricraftmod.client.render.tileentity.SandStarPortalRenderer;
import baguchan.japaricraftmod.tileentity.TileEntitySandStarPortal;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSandStarPortal extends Block implements ITileEntityProvider {
    protected static final AxisAlignedBB Statue_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

    public BlockSandStarPortal() {
        super(Material.ROCK);
        setUnlocalizedName("sandstar_portal");
        this.setBlockUnbreakable();
        this.setResistance(60.0F);/*爆破耐性*/
        this.setLightLevel(0.6F);
        this.setSoundType(SoundType.GLASS);
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

    public EnumBlockRenderType getRenderType(IBlockState state) {

        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;

    }


    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {

        return new TileEntitySandStarPortal();

    }

    @SideOnly(Side.CLIENT)
    public void initModel() {

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySandStarPortal.class, new SandStarPortalRenderer());

        Item.getItemFromBlock(this).setTileEntityItemStackRenderer(new SandStarPortalRenderer.SandStarTEISR());

    }
}
