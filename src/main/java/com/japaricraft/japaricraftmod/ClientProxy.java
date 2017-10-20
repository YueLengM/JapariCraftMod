package com.japaricraft.japaricraftmod;


import com.google.common.collect.Lists;
import com.japaricraft.japaricraftmod.hander.IColoredItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{
    private static List<Item> itemsToColor = Lists.newArrayList();

    @Override
    public void registerItemSided(Item item) {
        // register sub types if there are any
        if (item.getHasSubtypes()) {
            NonNullList<ItemStack> subItems = NonNullList.create();
            item.getSubItems(JapariCraftMod.tabJapariCraft, subItems);
            for (ItemStack subItem : subItems) {
                String subItemName = item.getUnlocalizedName(subItem);
                subItemName = subItemName.substring(subItemName.indexOf(".") + 1); // remove 'item.' from the front

                ModelLoader.registerItemVariants(item, new ResourceLocation(JapariCraftMod.MODID, subItemName));
                ModelLoader.setCustomModelResourceLocation(item, subItem.getMetadata(), new ModelResourceLocation(JapariCraftMod.MODID + ":" + subItemName, "inventory"));
            }
        } else {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(JapariCraftMod.MODID + ":" + item.delegate.name().getResourcePath(), "inventory"));
        }

        //Register colour handlers
        if (item instanceof IColoredItem && ((IColoredItem) item).getItemColor() != null) {
            this.itemsToColor.add(item);
        }
    }
    public void init(){
    }

    public void postInit() {
    }
}
