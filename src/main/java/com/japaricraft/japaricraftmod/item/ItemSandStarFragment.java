package com.japaricraft.japaricraftmod.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static com.japaricraft.japaricraftmod.JapariCraftMod.tabJapariCraft;


public class ItemSandStarFragment extends Item {
    public ItemSandStarFragment() {
        this.setCreativeTab(tabJapariCraft);
        this.setUnlocalizedName("SandStarFragment");
    }
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }
}

