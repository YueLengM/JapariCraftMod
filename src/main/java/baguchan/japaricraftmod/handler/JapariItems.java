package baguchan.japaricraftmod.handler;


import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.item.*;
import baguchan.japaricraftmod.item.armor.ItemKabanHat;
import baguchan.japaricraftmod.item.armor.ItemServalEarCostume;
import baguchan.japaricraftmod.item.summon.DarkSandStar;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collections;
import java.util.List;

public class JapariItems {
    private static final NonNullList<Item> ITEMS = NonNullList.create();

    public static final ItemArmor.ArmorMaterial animalCostumeMaterial = EnumHelper.addArmorMaterial("animalcostume", JapariCraftMod.MODID + ":" + "textures/models/armor/kabanhat_layer_1.png", 8, new int[]{0, 0, 0, 0}, 12, net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
    public static final ItemArmor.ArmorMaterial KabanHatMaterial = EnumHelper.addArmorMaterial("kabanhatmaterial", JapariCraftMod.MODID + ":" + "textures/models/armor/kabanhat_layer_1.png", 8, new int[]{2, 0, 0, 2}, 30, net.minecraft.init.SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);


    public static final Item japariman = new Japariman();
    public static final Item japarimancocoa = new JaparimanCocoa();
    public static final Item japarimanapple = new JaparimanApple();
    public static final Item japarimanfruit = new JaparimanFruit();
    public static final Item goldenjapariman = new GoldenJapariman();
    public static final Item curry = new Curry();
    public static final Item starjapariman = new StarJapariman();
    public static final Item sandstarfragment = new ItemSandStarFragment();
    public static final Item darksandstar = new DarkSandStar();
    public static final Item sandstar_powder = new SugarStar();
    public static final Item sandstarcake_115 = new SandStarCake115();
    public static final Item starcaramel = new StarCaramel();
    public static final Item wildliberationsource = new WildLiberationSource();
    public static final Item wildliberationpotion = new WildLiberationPotion();
    public static final Item kabanhat = new ItemKabanHat(KabanHatMaterial, 0, EntityEquipmentSlot.HEAD);
    public static final Item servalEarCostume = new ItemServalEarCostume(animalCostumeMaterial, 0, EntityEquipmentSlot.HEAD);
    public static final Item japaricoin = new ItemJapariCoin();
    public static final Item itemTutorialPaper = new ItemTutorialPaper();
    public static final Item cerulean_cube = new Item().setCreativeTab(JapariCraftMod.tabJapariCraft).setUnlocalizedName("cerulean_cube");

    public static List<Item> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }


    public static void register(IForgeRegistry<Item> registry, Item item) {
        ITEMS.add(item);

        if (item instanceof ItemBlock && item.getRegistryName() == null) {
            item.setRegistryName(((ItemBlock) item).getBlock().getRegistryName());
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
        register(registry, sandstar_powder.setRegistryName("sandstar_powder"));
        register(registry, sandstarfragment.setRegistryName("sandstar_fragment"));
        register(registry, wildliberationpotion.setRegistryName("wildliberationpotion"));
        register(registry, wildliberationsource.setRegistryName("wildliberationsource"));
        register(registry, kabanhat.setRegistryName("kabanhat"));
        register(registry, servalEarCostume.setRegistryName("serval_earcostume"));
        register(registry, japaricoin.setRegistryName("japaricoin"));
        register(registry, itemTutorialPaper.setRegistryName("tutorialpaper"));
        register(registry, cerulean_cube.setRegistryName("cerulean_cube"));
        OreDictionary.registerOre("sandstar", sandstarfragment);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        registerModel(japariman, "japariman");
        registerModel(japarimancocoa, "japariman_cocoa");
        registerModel(japarimanapple, "japariman_apple");
        registerModel(japarimanfruit, "japariman_fruit");
        registerModel(goldenjapariman, "golden_japariman");
        registerModel(curry, "curry");
        registerModel(starjapariman, "star_japariman");
        registerModel(darksandstar, "darksandstar");
        registerModel(starcaramel, "star_caramel");
        registerModel(sandstar_powder, "sandstar_powder");
        registerModel(wildliberationpotion, "wildliberation_potion");
        registerModel(wildliberationsource, "wildliberation_source");
        registerModel(kabanhat, "kabanhat");
        registerModel(servalEarCostume, "serval_earcostume");
        registerModel(sandstarfragment, "sandstar_fragment");
        registerModel(japaricoin, "japaricoin");
        registerModel(sandstarcake_115, "sandstarcake_115");
        registerModel(itemTutorialPaper, "tutorialpaper");
        registerModel(cerulean_cube, "cerulean_cube");
    }

    @SideOnly(Side.CLIENT)
    public static void registerModel(Item item, String modelName) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(JapariCraftMod.MODID + ":" + modelName, "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public static void registerModel(Item item) {
        registerModel(item, item.getRegistryName().getResourcePath());
    }
}
