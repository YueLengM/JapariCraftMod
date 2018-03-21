package com.japaricraft.japaricraftmod.item;

import com.japaricraft.japaricraftmod.handler.JapariItems;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;
import static com.japaricraft.japaricraftmod.JapariCraftMod.tabJapariCraft;

public class ItemSandStarChestPlate extends ItemArmor {
    public ItemSandStarChestPlate(ArmorMaterial kabanHatMaterial, int i, EntityEquipmentSlot head) {
        super(JapariItems.SandStarArmorMaterial, 1, head);
        setCreativeTab(tabJapariCraft);
        setUnlocalizedName("SandStarChestPlate");
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return MODID + ":" + "textures/armor/sandstar_layer_1.png";
    }
}
