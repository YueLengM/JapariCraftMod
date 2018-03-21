package com.japaricraft.japaricraftmod.handler;


import com.japaricraft.japaricraftmod.JapariCraftMod;
import com.japaricraft.japaricraftmod.item.*;
import com.japaricraft.japaricraftmod.item.summon.DarkSandStar;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collections;
import java.util.List;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class JapariItems {
    private static final NonNullList<Item> ITEMS = NonNullList.create();

    private static Item.ToolMaterial SandStar = EnumHelper.addToolMaterial("SandStar", 3, 800, 9F, 4F, 16).setRepairItem(new ItemStack(JapariItems.sandstarfragment));
    public static final ItemArmor.ArmorMaterial KabanHatMaterial = EnumHelper.addArmorMaterial("kabanhatmaterial", MODID +":"+"textures/models/armor/kabanhat_layer_1.png", 8, new int[]{2,0,0,2}, 30, net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,0);
    public static final ItemArmor.ArmorMaterial SandStarArmorMaterial = EnumHelper.addArmorMaterial("sandstarmaterial", MODID + ":" + "textures/models/armor/sandstar_layer_1.png", 18, new int[]{2, 6, 5, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1);


    public static final Item japariman = new Japariman();
    public static final Item japarimancocoa = new JaparimanCocoa();
    public static final Item japarimanapple = new JaparimanApple();
    public static final Item japarimanfruit = new JaparimanFruit();
    public static final Item goldenjapariman = new GoldenJapariman();
    public static final Item curry = new Curry();
    public static final Item starjapariman = new StarJapariman();
    public static final Item sandstarfragment = new ItemSandStarFragment();
    public static final Item sandstarnecklace = new ItemSandStarNeckLace();
    public static final Item darksandstar = new DarkSandStar();
    public static final Item sandstarHelmet = new ItemSandStarHelmet(SandStarArmorMaterial, 0, EntityEquipmentSlot.HEAD);
    public static final Item sandstarChestplate = new ItemSandStarChestPlate(SandStarArmorMaterial, 1, EntityEquipmentSlot.CHEST);
    public static final Item sandstarLeggings = new ItemSandStarLeggings(SandStarArmorMaterial, 2, EntityEquipmentSlot.LEGS);
    public static final Item sandstarBoot = new ItemSandStarBoot(SandStarArmorMaterial, 3, EntityEquipmentSlot.FEET);
    public static final Item sugarstar = new SugarStar();
    public static final Item sandstarcake_115 = new SandStarCake115();
    public static final Item starcaramel = new StarCaramel();
    public static final Item wildliberationsource = new WildLiberationSource();
    public static final Item wildliberationpotion = new WildLiberationPotion();
    public static final Item kabanhat= new ItemKabanHat(KabanHatMaterial, 0, EntityEquipmentSlot.HEAD);
    public static final Item japaricoin = new ItemJapariCoin();
    public static final Item researchbook = new ResearchBook();
    public static final Item sandstarbow = new SandStarBow();

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
        register(registry, sandstarcake_115.setRegistryName("sandstarcake_115"));
        register(registry, sugarstar.setRegistryName("sugarstar"));
        register(registry, sandstarfragment.setRegistryName("sandstar_fragment"));
        register(registry, sandstarnecklace.setRegistryName("sandstarnecklace"));
        register(registry, sandstarbow.setRegistryName("sandstarbow"));
        register(registry, sandstarHelmet.setRegistryName("sandstarhelmet"));
        register(registry, sandstarChestplate.setRegistryName("sandstarchestplate"));
        register(registry, sandstarLeggings.setRegistryName("sandstarleggings"));
        register(registry, sandstarBoot.setRegistryName("sandstarboots"));
        register(registry, wildliberationpotion.setRegistryName("wildliberationpotion"));
        register(registry, wildliberationsource.setRegistryName("wildliberationsource"));
        register(registry, kabanhat.setRegistryName("kabanhat"));
        register(registry, japaricoin.setRegistryName("japaricoin"));
        register(registry, researchbook.setRegistryName("researchbook"));
        OreDictionary.registerOre("sandstar", sandstarfragment);
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
        registerModel(wildliberationpotion,"wildliberation_potion");
        registerModel(wildliberationsource,"wildliberation_source");
        registerModel(kabanhat,"kabanhat");
        registerModel(sandstarfragment,"sandstar_fragment");
        registerModel(sandstarHelmet, "sandstarhelmet");
        registerModel(sandstarChestplate, "sandstarchestplate");
        registerModel(sandstarLeggings, "sandstarleggings");
        registerModel(sandstarBoot, "sandstarboots");
        registerModel(japaricoin, "japaricoin");
        registerModel(sandstarcake_115, "sandstarcake_115");
        registerModel(researchbook, "researchbook");
        registerModel(sandstarbow, "sandstarbow");
        registerModel(sandstarnecklace, "sandstarnecklace");
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
}
