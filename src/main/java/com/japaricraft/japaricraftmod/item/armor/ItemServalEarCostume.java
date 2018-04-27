package com.japaricraft.japaricraftmod.item.armor;

import com.japaricraft.japaricraftmod.model.ModelServalEar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;
import static com.japaricraft.japaricraftmod.JapariCraftMod.tabJapariCraft;
import static com.japaricraft.japaricraftmod.handler.JapariItems.animalCostumeMaterial;

public class ItemServalEarCostume extends ItemArmor {
    public ItemServalEarCostume(ArmorMaterial material, int i, EntityEquipmentSlot head) {
        super(animalCostumeMaterial, 0, EntityEquipmentSlot.HEAD);
        setMaxDamage(-1);
        setCreativeTab(tabJapariCraft);
        setUnlocalizedName("ServalEarCostume");
    }

    @SideOnly(Side.CLIENT)
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
        return new ModelServalEar();
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return MODID + ":" + "textures/armor/servalear.png";
    }
}