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

public class ComponentSandStarRuinMiniHole extends StructureComponent {
    //入り口とはまた別のやつで通路的なやつ
    protected int averageGroundLvl = -1;
    private EnumFacing facing;

    // 構成パーツリストを記憶するためのリスト

    public ComponentSandStarRuinMiniHole() {
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {

    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {

    }

    public ComponentSandStarRuinMiniHole(int height, Random par2Random, int par3, int par4, int par5) {
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
        this.fillWithAir(world, structureboundingbox, 0, 0, 0, 6, 4, 7);
        //土台
        this.fillWithBlocks(world, structureboundingbox, 0, 0, 0, 6, 4, 7, iblockstate, air, false);
        //中を空気に
        this.fillWithAir(world, structureboundingbox, 1, 1, 1, 5, 3, 6);

        //入り口とかの位置を確保
        this.fillWithAir(world, structureboundingbox, 2, 1, 0, 4, 3, 0);
        this.fillWithAir(world, structureboundingbox, 2, 1, 7, 4, 3, 7);
        //サンドスターランプの柱
        this.fillWithBlocks(world, structureboundingbox, 1, 1, 0, 1, 3, 0, lamp, lamp, false);
        this.fillWithBlocks(world, structureboundingbox, 5, 1, 0, 5, 3, 0, lamp, lamp, false);
        this.fillWithBlocks(world, structureboundingbox, 1, 1, 7, 1, 3, 7, lamp, lamp, false);
        this.fillWithBlocks(world, structureboundingbox, 5, 1, 7, 5, 3, 7, lamp, lamp, false);
        //穴
        this.fillWithBlocks(world, structureboundingbox, 2, -6, 3, 6, 0, 7, iblockstate, air, false);
        this.fillWithAir(world, structureboundingbox, 3, -6, 4, 5, 0, 6);
        this.setBlockState(world, iblockstate, 3, 0, 6, structureboundingbox);

        return true;
    }


}