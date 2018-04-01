package com.japaricraft.japaricraftmod.item;

import com.japaricraft.japaricraftmod.model.ModelKabanHat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;
import static com.japaricraft.japaricraftmod.JapariCraftMod.tabJapariCraft;
import static com.japaricraft.japaricraftmod.handler.JapariItems.KabanHatMaterial;


public class ItemKabanHat extends ItemArmor {
    public ItemKabanHat(ArmorMaterial kabanHatMaterial, int i, EntityEquipmentSlot head) {
        super(KabanHatMaterial, 0, EntityEquipmentSlot.HEAD);
        setCreativeTab(tabJapariCraft);
        setUnlocalizedName("Kabanhat");
    }

    @SideOnly(Side.CLIENT)
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
        return new ModelKabanHat();
    }
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return MODID + ":" + "textures/armor/hat.png";
    }
}
