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

public class ComponentSandStarRuinTreasureRoom extends StructureComponent {
    private EnumFacing facing;

    int par1;

    public ComponentSandStarRuinTreasureRoom() {
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {

    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {

    }

    public ComponentSandStarRuinTreasureRoom(int height, Random par2Random, int par3, int par4, int par5) {
        super();
        this.facing = EnumFacing.NORTH;
        this.setCoordBaseMode(facing);
        this.boundingBox = new StructureBoundingBox(par3, height, par4, par3 + 6, height + 4, par4 + 7);
    }

    @Override
    public void buildComponent(StructureComponent par1StructureComponent, List par2List, Random par3Random) {

    }

    @Override
    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {
        IBlockState iblockstate = Blocks.STONEBRICK.getDefaultState();
        IBlockState lamp = JapariBlocks.SANDSTAR_Lamp.getDefaultState();
        IBlockState air = Blocks.AIR.getDefaultState();
        IBlockState bookshelf = Blocks.BOOKSHELF.getDefaultState();
        IBlockState crafting_table = Blocks.CRAFTING_TABLE.getDefaultState();
        IBlockState ironbar = Blocks.IRON_BARS.getDefaultState();
        this.fillWithAir(world, structureboundingbox, 0, 0, 0, 6, 4, 7);
        //土台
        this.fillWithBlocks(world, structureboundingbox, 0, 0, 0, 8, 4, 7, iblockstate, air, false);
        //中を空気に
        this.fillWithAir(world, structureboundingbox, 1, 1, 1, 7, 3, 6);

        //入り口とかの位置を確保
        this.fillWithAir(world, structureboundingbox, 3, 1, 0, 5, 3, 0);
        this.fillWithAir(world, structureboundingbox, 3, 1, 7, 5, 3, 7);
        //サンドスターランプの柱
        this.fillWithBlocks(world, structureboundingbox, 2, 1, 0, 2, 3, 0, lamp, lamp, false);
        this.fillWithBlocks(world, structureboundingbox, 5, 1, 0, 5, 3, 0, lamp, lamp, false);
        this.fillWithBlocks(world, structureboundingbox, 2, 1, 7, 2, 3, 7, lamp, lamp, false);
        this.fillWithBlocks(world, structureboundingbox, 6, 1, 7, 6, 3, 7, lamp, lamp, false);
        //机とか
        this.fillWithBlocks(world, structureboundingbox, 1, 1, 1, 1, 1, 3, iblockstate, iblockstate, false);
        this.setBlockState(world, crafting_table, 1, 1, 4, structureboundingbox);
        this.setBlockState(world, Blocks.FLOWER_POT.getDefaultState(), 1, 2, 2, structureboundingbox);
        this.setBlockState(world, bookshelf, 1, 1, 5, structureboundingbox);
        this.setBlockState(world, bookshelf, 1, 1, 6, structureboundingbox);
        //鉄柵
        this.fillWithBlocks(world, structureboundingbox, 6, 1, 1, 6, 3, 6, ironbar, ironbar, false);
        //鉄ドア
        this.fillWithBlocks(world, structureboundingbox, 6, 1, 0, 6, 3, 3, iblockstate, iblockstate, false);
        this.fillWithAir(world, structureboundingbox, 6, 1, 2, 6, 2, 2);
        this.generateDoor(world, structureboundingbox, random, 6, 1, 2, EnumFacing.EAST, Blocks.IRON_DOOR);

        return true;
    }

}