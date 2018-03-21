package com.japaricraft.japaricraftmod.item;

import com.japaricraft.japaricraftmod.handler.JapariItems;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;
import static com.japaricraft.japaricraftmod.JapariCraftMod.tabJapariCraft;

public class ItemSandStarLeggings extends ItemArmor {
    public ItemSandStarLeggings(ArmorMaterial kabanHatMaterial, int i, EntityEquipmentSlot head) {
        super(JapariItems.SandStarArmorMaterial, 2, head);
        setCreativeTab(tabJapariCraft);
        setUnlocalizedName("SandStarLeggings");
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return MODID + ":" + "textures/armor/sandstar_layer_2.png";
    }
}
