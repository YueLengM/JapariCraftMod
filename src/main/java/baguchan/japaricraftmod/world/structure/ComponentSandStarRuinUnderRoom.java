package baguchan.japaricraftmod.world.structure;

import baguchan.japaricraftmod.handler.JapariBlocks;
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

public class ComponentSandStarRuinUnderRoom extends StructureComponent {
    private EnumFacing facing;

    int par1;

    public ComponentSandStarRuinUnderRoom() {
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {

    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {

    }

    public ComponentSandStarRuinUnderRoom(int height, Random par2Random, int par3, int par4, int par5) {
        super();
        this.facing = EnumFacing.NORTH;
        this.setCoordBaseMode(facing);
        this.boundingBox = new StructureBoundingBox(par3, height, par4, par3 + 8, height + 5, par4 + 8);
    }


    @Override
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {
        StructureComponent structureComponent = new ComponentSandStarRuinCorridor(this.boundingBox.minY, par3Random, this.boundingBox.minX + 1, this.boundingBox.minZ - 7, 0);
        ((ComponentSandStarRuin1) par1StructureComponent).structureComponents.add(structureComponent);
        par2List.add(structureComponent);
        StructureComponent structureComponent2 = new ComponentSandStarRuinCorridor(this.boundingBox.minY, par3Random, this.boundingBox.minX + 1, this.boundingBox.maxZ, 0);
        ((ComponentSandStarRuin1) par1StructureComponent).structureComponents.add(structureComponent2);
        par2List.add(structureComponent2);
        StructureComponent structureComponent3 = new ComponentSandStarRuinMiniHole(this.boundingBox.minY, par3Random, this.boundingBox.minX + 1, this.boundingBox.minZ - 14, 0);
        ((ComponentSandStarRuin1) par1StructureComponent).structureComponents.add(structureComponent3);
        par2List.add(structureComponent3);
        StructureComponent structureComponent4 = new ComponentSandStarRuinMiniHole(this.boundingBox.minY, par3Random, this.boundingBox.minX + 1, this.boundingBox.maxZ + 7, 0);
        ((ComponentSandStarRuin1) par1StructureComponent).structureComponents.add(structureComponent4);
        par2List.add(structureComponent4);

        if (par3Random.nextBoolean()) {
            StructureComponent structureComponent5 = new ComponentSandStarRuinTreasureRoom(this.boundingBox.minY, par3Random, this.boundingBox.minX + 1, this.boundingBox.minZ - 21, 0);
            ((ComponentSandStarRuin1) par1StructureComponent).structureComponents.add(structureComponent5);
            par2List.add(structureComponent5);
        }
        StructureComponent structureComponent6 = new ComponentSandStarRuinBossRoom(this.boundingBox.minY - 12, par3Random, this.boundingBox.minX + 1, this.boundingBox.minZ - 14, 0);
        ((ComponentSandStarRuin1) par1StructureComponent).structureComponents.add(structureComponent6);
        par2List.add(structureComponent6);
    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {
        IBlockState iblockstate = Blocks.STONEBRICK.getDefaultState();
        IBlockState air = Blocks.AIR.getDefaultState();
        IBlockState sandstarlamp = JapariBlocks.SANDSTAR_Lamp.getDefaultState();
        IBlockState ironbar = Blocks.IRON_BARS.getDefaultState();
        this.fillWithAir(world, structureboundingbox, 0, 0, 0, 8, 5, 8);
        //土台と中
        this.fillWithBlocks(world, structureboundingbox, 0, 0, 0, 8, 5, 8, iblockstate, air, false);
        //中を空気に
        this.fillWithAir(world, structureboundingbox, 1, 1, 1, 7, 4, 7);
        //穴
        this.fillWithAir(world, structureboundingbox, 3, 5, 3, 5, 5, 5);
        //入り口とかの位置を確保
        this.fillWithAir(world, structureboundingbox, 3, 1, 0, 5, 3, 0);
        this.fillWithAir(world, structureboundingbox, 3, 1, 8, 5, 3, 8);
        //台
        this.fillWithBlocks(world, structureboundingbox, 3, 1, 3, 5, 1, 5, iblockstate, iblockstate, false);
        //鉄作
        this.fillWithBlocks(world, structureboundingbox, 0, 2, 2, 0, 4, 2, ironbar, ironbar, false);
        this.fillWithBlocks(world, structureboundingbox, 0, 2, 6, 0, 4, 6, ironbar, ironbar, false);
        this.fillWithBlocks(world, structureboundingbox, 8, 2, 2, 8, 4, 2, ironbar, ironbar, false);
        this.fillWithBlocks(world, structureboundingbox, 8, 2, 6, 8, 4, 6, ironbar, ironbar, false);
        return true;
    }
}