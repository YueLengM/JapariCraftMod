package baguchan.japaricraftmod.handler;


import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.block.*;
import baguchan.japaricraftmod.tileentity.TileEntitySandStarPortal;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;


public class JapariBlocks {
    public static final BlockSandStar SANDSTAR_BLOCK = new BlockSandStar();
    public static final BlockCrackedSandStar CRACKED_SANDSTAR_BLOCK = new BlockCrackedSandStar();
    public static final BlockSandStarPortal SANDSTAR_PORTAL = new BlockSandStarPortal();
    public static final BlockSandStarOre SANDSTAR_ORE = new BlockSandStarOre();
    public static final BlockSandStarLamp SANDSTAR_Lamp = new BlockSandStarLamp();
    public static final BlockWoodenBox BLOCK_WOODEN_BOX = new BlockWoodenBox();
    public static final BlockJaparimanBowl Japariman_Bowl = new BlockJaparimanBowl();
    public static final BlockCeruleanStatue Cerulean_STATUE = new BlockCeruleanStatue();
    public static final BlockSandStarTreeApple TREE_APPLE = new BlockSandStarTreeApple();
    public static final BlockRedStarFlower REDSTAR_FLOWER = new BlockRedStarFlower();
    public static void registerBlocks(IForgeRegistry<Block> registry)
    {
        registry.register(SANDSTAR_ORE.setRegistryName("sandstar_ore"));
        registry.register(SANDSTAR_BLOCK.setRegistryName("sandstar_block"));
        registry.register(CRACKED_SANDSTAR_BLOCK.setRegistryName("cracked_sandstar"));
        registry.register(SANDSTAR_PORTAL.setRegistryName("sandstar_portal"));
        registry.register(SANDSTAR_Lamp.setRegistryName("sandstar_lamp"));
        registry.register(BLOCK_WOODEN_BOX.setRegistryName("woodenbox"));
        registry.register(Japariman_Bowl.setRegistryName("japarimanbowl"));
        registry.register(Cerulean_STATUE.setRegistryName("ceruleanstatue"));
        registry.register(TREE_APPLE.setRegistryName("sandstar_apple"));
        registry.register(REDSTAR_FLOWER.setRegistryName("redstar_flower"));
        GameRegistry.registerTileEntity(TileEntitySandStarPortal.class, JapariCraftMod.MODID + ":tileEntitySandStarPortal");
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry)
    {
        JapariItems.register(registry, new ItemBlock(SANDSTAR_ORE));
        JapariItems.register(registry, new ItemBlock(SANDSTAR_BLOCK));
        JapariItems.register(registry, new ItemBlock(CRACKED_SANDSTAR_BLOCK));
        JapariItems.register(registry, new ItemBlock(SANDSTAR_PORTAL));
        JapariItems.register(registry, new ItemBlock(SANDSTAR_Lamp));
        JapariItems.register(registry, new ItemBlock(Japariman_Bowl));
        JapariItems.register(registry, new ItemBlock(BLOCK_WOODEN_BOX));
        JapariItems.register(registry, new ItemBlock(REDSTAR_FLOWER));
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
        registerModel(CRACKED_SANDSTAR_BLOCK);
        registerModel(SANDSTAR_Lamp);
        registerModel(Japariman_Bowl);
        registerModel(BLOCK_WOODEN_BOX);
        registerModel(Cerulean_STATUE);
        registerModel(TREE_APPLE);
        registerModel(REDSTAR_FLOWER);
        SANDSTAR_PORTAL.initModel();
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
