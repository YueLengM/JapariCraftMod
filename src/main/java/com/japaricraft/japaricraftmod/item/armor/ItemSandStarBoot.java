package com.japaricraft.japaricraftmod.item.armor;

import com.japaricraft.japaricraftmod.handler.JapariItems;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;
import static com.japaricraft.japaricraftmod.JapariCraftMod.tabJapariCraft;

public class ItemSandStarBoot extends ItemArmor {
    public ItemSandStarBoot(ArmorMaterial kabanHatMaterial, int i, EntityEquipmentSlot head) {
        super(JapariItems.SandStarArmorMaterial, 3, head);
        setCreativeTab(tabJapariCraft);
        setUnlocalizedName("SandStarIronBoot");
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return MODID + ":" + "textures/armor/sandstar_layer_1.png";
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.getItem() == JapariItems.sandstarfragment || super.getIsRepairable(toRepair, repair);
    }
}
