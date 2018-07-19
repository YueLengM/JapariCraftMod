package baguchan.japaricraftmod;

import baguchan.japaricraftmod.handler.JapariItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;


public class TabJapariCraft extends CreativeTabs {
    public TabJapariCraft(String label)
    {
        super(label);
    }


    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(JapariItems.starjapariman);
    }


}