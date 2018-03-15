package com.japaricraft.japaricraftmod.handler;


import com.japaricraft.japaricraftmod.JapariCraftMod;
import com.japaricraft.japaricraftmod.block.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

public class JapariBlocks {
    public static final BlockSandStar SANDSTAR_BLOCK = new BlockSandStar();
    public static final BlockSandStarOre SANDSTAR_ORE = new BlockSandStarOre();
    public static final BlockSandStarLamp SANDSTAR_Lamp = new BlockSandStarLamp();
    public static final BlockWoodenBox BLOCK_WOODEN_BOX = new BlockWoodenBox();
    public static final BlockAnimalStatue ANIMAL_STATUE = new BlockAnimalStatue();
    public static final BlockCeruleanStatue Cerulean_STATUE = new BlockCeruleanStatue();
    public static void registerBlocks(IForgeRegistry<Block> registry)
    {
        registry.register(SANDSTAR_ORE.setRegistryName("sandstar_ore"));
        registry.register(SANDSTAR_BLOCK.setRegistryName("sandstar_block"));
        registry.register(SANDSTAR_Lamp.setRegistryName("sandstar_lamp"));
        registry.register(BLOCK_WOODEN_BOX.setRegistryName("woodenbox"));
        registry.register(Cerulean_STATUE.setRegistryName("ceruleanstatue"));
        registry.register(ANIMAL_STATUE.setRegistryName("animalstatue"));
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry)
    {
        JapariItems.register(registry, new ItemBlock(SANDSTAR_ORE));
        JapariItems.register(registry, new ItemBlock(SANDSTAR_BLOCK));
        JapariItems.register(registry, new ItemBlock(SANDSTAR_Lamp));
        JapariItems.register(registry, new ItemBlock(BLOCK_WOODEN_BOX));
        JapariItems.register(registry, new ItemBlock(ANIMAL_STATUE));
    }


    public static Block registerFluidBlock(Fluid fluid, Block fluidBlock, String name) {
        fluidBlock.setRegistryName(new ResourceLocation(JapariCraftMod.MODID, name));
        ForgeRegistries.BLOCKS.register(fluidBlock);
        JapariCraftMod.proxy.registerFluidBlockRendering(fluidBlock, name);
        fluid.setBlock(fluidBlock);
        return fluidBlock;
    }
    @SideOnly(Side.CLIENT)
    public static void registerModels()
    {
        registerModel(SANDSTAR_ORE);
        registerModel(SANDSTAR_BLOCK);
        registerModel(SANDSTAR_Lamp);
        registerModel(BLOCK_WOODEN_BOX);
        registerModel(Cerulean_STATUE);
        registerModel(ANIMAL_STATUE);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModel(Block block, String modelName)
    {
        JapariItems.registerModel(Item.getItemFromBlock(block), modelName);
    }
    @SideOnly(Side.CLIENT)
    public static void registerModel(Block block)
    {
        JapariItems.registerModel(Item.getItemFromBlock(block));
    }


}
