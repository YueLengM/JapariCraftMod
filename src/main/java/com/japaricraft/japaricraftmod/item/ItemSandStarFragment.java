package com.japaricraft.japaricraftmod.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static com.japaricraft.japaricraftmod.JapariCraftMod.tabJapariCraft;


public class ItemSandStarFragment extends Item {
    private final Map<Class<? extends EntityLivingBase>, Class<? extends EntityLivingBase>> transformMap = new HashMap<>();
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

