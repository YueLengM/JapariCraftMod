package com.japaricraft.japaricraftmod.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.japaricraft.japaricraftmod.JapariCraftMod.tabJapariCraft;

public class ItemJapariCoin extends Item {
    public ItemJapariCoin() {
        this.setCreativeTab(tabJapariCraft);
        this.setUnlocalizedName("japaricoin");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltip, flags);
        tooltip.add(I18n.format(getUnlocalizedName() + ".tooltip"));
    }
}
