package baguchan.japaricraftmod.world.gen.structure.sandstarlab;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.handler.JapariTreasure;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponentTemplate;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import javax.annotation.Nonnull;
import java.util.Random;

public class SandStarLabPieces {
    public static final ResourceLocation SANDSTARLAB = new ResourceLocation(JapariCraftMod.MODID, "sandstarlab");

    public static void registerSandStarLab() {
        MapGenStructureIO.registerStructure(MapGenSandStarLab.Start.class, "sandstarlab");
        MapGenStructureIO.registerStructureComponent(SandStarLabPieces.SandStarLabTemplate.class, String.valueOf(new ResourceLocation(JapariCraftMod.MODID, "sandstarlab_template")));
    }

    public static class SandStarLabTemplate extends StructureComponentTemplate {
        private Rotation rotation;
        private Mirror mirror;
        private int zombieSpawned;

        public SandStarLabTemplate() { //Needs empty constructor
        }

        SandStarLabTemplate(TemplateManager manager, BlockPos pos, Rotation rotation) {
            this(manager, pos, rotation, Mirror.NONE);
        }

        private SandStarLabTemplate(TemplateManager manager, BlockPos pos, Rotation rotation, Mirror mirror) {
            super(0);
            this.templatePosition = pos;
            this.rotation = rotation;
            this.mirror = mirror;
            this.loadTemplate(manager);
        }

        private void loadTemplate(TemplateManager manager) {
            Template template = manager.getTemplate(null, SANDSTARLAB);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(this.mirror);
            this.setup(template, this.templatePosition, placementsettings);
        }

        @Override
        public boolean addComponentParts(@Nonnull World world, @Nonnull Random random, @Nonnull StructureBoundingBox box) {
            super.addComponentParts(world, random, box);
            return true;
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, World worldIn, Random rand, StructureBoundingBox sbb) {
            if (function.startsWith("Chest")) {
                Rotation rotation = this.placeSettings.getRotation();
                IBlockState iblockstate = Blocks.CHEST.getDefaultState();

                if ("ChestWest".equals(function)) {
                    iblockstate = iblockstate.withProperty(BlockChest.FACING, rotation.rotate(EnumFacing.WEST));
                } else if ("ChestEast".equals(function)) {
                    iblockstate = iblockstate.withProperty(BlockChest.FACING, rotation.rotate(EnumFacing.EAST));
                } else if ("ChestSouth".equals(function)) {
                    iblockstate = iblockstate.withProperty(BlockChest.FACING, rotation.rotate(EnumFacing.SOUTH));
                } else if ("ChestNorth".equals(function)) {
                    iblockstate = iblockstate.withProperty(BlockChest.FACING, rotation.rotate(EnumFacing.NORTH));
                }

                this.generateChest(worldIn, sbb, rand, pos, JapariTreasure.lab, iblockstate);
            }
        }


        @Override
        protected void writeStructureToNBT(NBTTagCompound compound) {
            super.writeStructureToNBT(compound);
            compound.setString("Rot", this.placeSettings.getRotation().name());
            compound.setString("Mi", this.placeSettings.getMirror().name());
        }

        @Override
        protected void readStructureFromNBT(NBTTagCompound compound, TemplateManager manager) {
            super.readStructureFromNBT(compound, manager);
            this.rotation = Rotation.valueOf(compound.getString("Rot"));
            this.mirror = Mirror.valueOf(compound.getString("Mi"));
            this.loadTemplate(manager);
        }
    }
}