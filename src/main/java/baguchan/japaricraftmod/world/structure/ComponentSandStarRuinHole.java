package baguchan.japaricraftmod.world.structure;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class ComponentSandStarRuinHole extends StructureComponent {
    private EnumFacing facing;

    int par1;

    public ComponentSandStarRuinHole() {
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {

    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {

    }

    public ComponentSandStarRuinHole(int height, Random par2Random, int par3, int par4, int par5) {
        super();
        this.facing = EnumFacing.NORTH;
        this.setCoordBaseMode(facing);
        this.boundingBox = new StructureBoundingBox(par3, height, par4, par3 + 8, height + 5, par4 + 8);
    }


    @Override
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
        StructureComponent structureComponent = new ComponentSandStarRuinRoof(this.boundingBox.maxY, par3Random, this.boundingBox.minX, this.boundingBox.minZ, 0);
        ((ComponentSandStarRuin1) par1StructureComponent).structureComponents.add(structureComponent);
        par2List.add(structureComponent);
        StructureComponent structureComponent2 = new ComponentSandStarRuinUnderRoom(this.boundingBox.minY - 5, par3Random, this.boundingBox.minX, this.boundingBox.minZ, 0);
        ((ComponentSandStarRuin1) par1StructureComponent).structureComponents.add(structureComponent2);
        par2List.add(structureComponent2);
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {
        IBlockState iblockstate = Blocks.STONEBRICK.getDefaultState();
        IBlockState air = Blocks.AIR.getDefaultState();
        IBlockState ironBar = Blocks.IRON_BARS.getDefaultState();
        this.fillWithAir(world, structureboundingbox, 0, 0, 0, 8, 6, 8);
        //土台
        this.fillWithBlocks(world, structureboundingbox, 0, 0, 0, 8, 5, 8, iblockstate, air, false);
        //中を空気に
        this.fillWithAir(world, structureboundingbox, 1, 1, 1, 7, 4, 7);
        //穴
        this.fillWithAir(world, structureboundingbox, 3, 0, 3, 5, 0, 5);
        //入り口とかの位置を確保
        this.fillWithAir(world, structureboundingbox, 3, 1, 0, 5, 3, 0);
        this.fillWithAir(world, structureboundingbox, 3, 1, 8, 5, 3, 8);
        //鉄作
        this.fillWithBlocks(world, structureboundingbox, 0, 2, 3, 0, 4, 3, ironBar, ironBar, false);
        this.fillWithBlocks(world, structureboundingbox, 0, 2, 6, 0, 4, 6, ironBar, ironBar, false);
        this.fillWithBlocks(world, structureboundingbox, 8, 2, 3, 8, 4, 3, ironBar, ironBar, false);
        this.fillWithBlocks(world, structureboundingbox, 8, 2, 6, 8, 4, 6, ironBar, ironBar, false);

        return true;
    }

}