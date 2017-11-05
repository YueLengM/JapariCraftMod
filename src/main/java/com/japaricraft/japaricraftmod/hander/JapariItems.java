package com.japaricraft.japaricraftmod.hander;


import com.japaricraft.japaricraftmod.JapariCraftMod;
import com.japaricraft.japaricraftmod.RecipeHelper;
import com.japaricraft.japaricraftmod.SandStarSword;
import com.japaricraft.japaricraftmod.item.*;
import com.japaricraft.japaricraftmod.item.summon.DarkSandStar;
import com.japaricraft.japaricraftmod.tool.SandStarPickaxe;
import com.japaricraft.japaricraftmod.tool.SandStarShovel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collections;
import java.util.List;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class JapariItems {
    private static final NonNullList<Item> ITEMS = NonNullList.create();

    private static Item.ToolMaterial SandStar = EnumHelper.addToolMaterial("SandStar", 3, 800, 9F, 4F, 16).setRepairItem(new ItemStack(JapariItems.sandstarfragment));
    public static final ItemArmor.ArmorMaterial KabanHatMaterial = EnumHelper.addArmorMaterial("kabanhatmaterial", MODID +":"+"textures/models/armor/kabanhat_layer_1.png", 8, new int[]{2,0,0,2}, 30, net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0);


    public static final Item japariman = new Japariman();
    public static final Item japarimancocoa = new JaparimanCocoa();
    public static final Item japarimanapple = new JaparimanApple();
    public static final Item japarimanfruit = new JaparimanFruit();
    public static final Item goldenjapariman = new GoldenJapariman();
    public static final Item curry = new Curry();
    public static final Item starjapariman = new StarJapariman();
    public static final Item sandstarfragment = new ItemSandStarFragment();
    public static final Item darksandstar = new DarkSandStar();
    public static final Item sugarstar = new SugarStar();
    public static final Item starcaramel = new StarCaramel();
    public static final Item sandstarsword = new SandStarSword(SandStar);
    public static final Item sandstarshovel = new SandStarShovel(SandStar);
    public static final Item sandstarpickaxe = new SandStarPickaxe(SandStar);
    public static final Item wildliberationsource = new WildLiberationSource();
    public static final Item wildliberationpotion = new WildLiberationPotion();
    public static final Item kabanhat= new ItemKabanHat(KabanHatMaterial, 0, EntityEquipmentSlot.HEAD);

    public static List<Item> getItems()
    {
        return Collections.unmodifiableList(ITEMS);
    }

    public static Item registerItem(Item item, String name) {
        return registerItem(item, name, JapariCraftMod.tabJapariCraft);
    }

    public static Item registerItem(Item item, String name, CreativeTabs tab) {
        item.setUnlocalizedName(name);
        if (tab != null) {
            item.setCreativeTab(JapariCraftMod.tabJapariCraft);
        }

        item.setRegistryName(new ResourceLocation(JapariCraftMod.MODID, name));
        ForgeRegistries.ITEMS.register(item);
        JapariCraftMod.proxy.registerItemSided(item);

        return item;
    }

    public static void register(IForgeRegistry<Item> registry, Item item)
    {
        ITEMS.add(item);

        if (item instanceof ItemBlock && item.getRegistryName() == null)
        {
            item.setRegistryName(((ItemBlock)item).getBlock().getRegistryName());
        }

        registry.register(item);
    }

    public static void registerItems(IForgeRegistry<Item> registry) {
        register(registry, japariman.setRegistryName("japariman"));
        register(registry, japarimancocoa.setRegistryName("japarimancocoa"));
        register(registry, japarimanapple.setRegistryName("japarimanapple"));
        register(registry, japarimanfruit.setRegistryName("japarimanfruit"));
        register(registry, goldenjapariman.setRegistryName("goldenjapariman"));
        register(registry, curry.setRegistryName("curry"));
        register(registry, starjapariman.setRegistryName("starjapariman"));
        register(registry, darksandstar.setRegistryName("darksandstar"));
        register(registry, starcaramel.setRegistryName("starcaramel"));
        register(registry, sugarstar.setRegistryName("sugarstar"));
        register(registry, sandstarsword.setRegistryName("sandstarsword"));
        register(registry, sandstarpickaxe.setRegistryName("sandstarpickaxe"));
        register(registry, sandstarshovel.setRegistryName("sandstarshovel"));
        register(registry, sandstarfragment.setRegistryName("sandstar_fragment"));
        register(registry, wildliberationpotion.setRegistryName("wildliberationpotion"));
        register(registry, wildliberationsource.setRegistryName("wildliberationsource"));
        register(registry, kabanhat.setRegistryName("kabanhat"));
        registerItem(new Item(), "japaricoin", JapariCraftMod.tabJapariCraft);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels()
    {
        registerModel(japariman,"japariman");
        registerModel(japarimancocoa,"japariman_cocoa");
        registerModel(japarimanapple,"japariman_apple");
        registerModel(japarimanfruit,"japariman_fruit");
        registerModel(goldenjapariman,"golden_japariman");
        registerModel(curry,"curry");
        registerModel(starjapariman,"star_japariman");
        registerModel(darksandstar,"darksandstar");
        registerModel(starcaramel,"star_caramel");
        registerModel(sugarstar,"sugarstar");
        registerModel(sandstarsword,"sandstar_sword");
        registerModel(sandstarpickaxe,"sandstar_pickaxe");
        registerModel(sandstarshovel,"sandstar_shovel");
        registerModel(wildliberationpotion,"wildliberation_potion");
        registerModel(wildliberationsource,"wildliberation_source");
        registerModel(kabanhat,"kabanhat");
        registerModel(sandstarfragment,"sandstar_fragment");
    }

    @SideOnly(Side.CLIENT)
    public static void registerModel(Item item, String modelName)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(MODID + ":" + modelName, "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public static void registerModel(Item item)
    {
        registerModel(item, item.getRegistryName().getResourcePath());
    }
    public static void registerRecipes(IForgeRegistry<IRecipe> registry)
    {
        registry.register(RecipeHelper.getShapelessRecipe("curry",
                new ItemStack(curry),Items.WHEAT,Items.POTIONITEM,Items.POTATO,Items.CARROT));
        registry.register(RecipeHelper.getShapelessRecipe("japariman_apple",
                new ItemStack(japarimanapple),Items.WHEAT,Items.APPLE));
        registry.register(RecipeHelper.getShapelessRecipe("japariman_goldenapple",
                new ItemStack(goldenjapariman),Items.WHEAT,Items.GOLDEN_APPLE));
        registry.register(RecipeHelper.getShapelessRecipe("japariman",
                new ItemStack(japariman),Items.WHEAT,Items.SUGAR));
        registry.register(RecipeHelper.getShapelessRecipe("starjapariman",
                new ItemStack(starjapariman),Items.WHEAT,JapariItems.sugarstar));
        registry.register(RecipeHelper.getShapelessRecipe("japariman_cocoa",
                new ItemStack(japarimancocoa),Items.WHEAT,new ItemStack(Items.DYE,1,3)));
        registry.register(RecipeHelper.getSwordRecipe("sandstarsword",
                new ItemStack(sandstarsword),JapariItems.sandstarfragment));
        registry.register(RecipeHelper.getPickaxeRecipe("sandstarpickaxe",
                new ItemStack(sandstarpickaxe),JapariItems.sandstarfragment));
        registry.register(RecipeHelper.getShovelRecipe("sandstarshovel",
                new ItemStack(sandstarshovel),JapariItems.sandstarfragment));
        addOredictRecipe(new ItemStack(JapariItems.kabanhat, 1, 0), "sms", "m m", 's', Items.FEATHER, 'm', Blocks.WOOL);
        registry.register(RecipeHelper.getShapelessRecipe("wildliberationpotion",
                new ItemStack(wildliberationpotion), Items.GOLDEN_APPLE,Items.POTIONITEM,JapariItems.wildliberationsource));
        registry.register(RecipeHelper.getSquareRecipe("sandstarblock",new ItemStack(JapariBlocks.SANDSTAR_BLOCK),JapariItems.sandstarfragment));
        registry.register(RecipeHelper.getShapelessRecipe("sandstar_fragment",
                new ItemStack(sandstarfragment,9), JapariBlocks.SANDSTAR_BLOCK));
        registry.register(RecipeHelper.getShapelessRecipe("sandstarsclean",
                new ItemStack(sandstarfragment), Items.WATER_BUCKET, JapariItems.darksandstar));
        registry.register(RecipeHelper.getShapelessRecipe("sandstars",
                new ItemStack(sugarstar, 5), Items.WATER_BUCKET, JapariItems.sandstarfragment, Items.SUGAR, Items.SUGAR));
        OreDictionary.registerOre("sandstar", sandstarfragment);
    }
    public static ShapedOreRecipe addOredictRecipe(final ItemStack output, final Object... recipe) {
        final ShapedOreRecipe sor = new ShapedOreRecipe(getRecipeGroup(output), output, recipe);
        ForgeRegistries.RECIPES.register(sor.setRegistryName(sor.getGroup()));
        return sor;
    }

    public static void addShapelessOredictRecipe(final ItemStack output, final Object... recipe) {
        final ShapelessOreRecipe sor = new ShapelessOreRecipe(getRecipeGroup(output), output, recipe);
        ForgeRegistries.RECIPES.register(sor.setRegistryName(sor.getGroup()));
    }
    public static ResourceLocation getRecipeGroup(final ItemStack stack) {
        String s = stack.getUnlocalizedName();
        final int idx = s.lastIndexOf(":");
        if (idx>=0)
            s = s.substring(idx+1);
        return new ResourceLocation(MODID, s);
    }
}
