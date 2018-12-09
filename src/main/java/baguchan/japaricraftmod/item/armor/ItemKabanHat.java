package baguchan.japaricraftmod.item.armor;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelKabanHat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static baguchan.japaricraftmod.handler.JapariItems.KabanHatMaterial;


public class ItemKabanHat extends ItemArmor {
    public ItemKabanHat(ArmorMaterial kabanHatMaterial, int i, EntityEquipmentSlot head) {
        super(KabanHatMaterial, 0, EntityEquipmentSlot.HEAD);
        setCreativeTab(JapariCraftMod.tabJapariCraft);
        setUnlocalizedName("Kabanhat");
    }

    @SideOnly(Side.CLIENT)
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
        return new ModelKabanHat();
    }
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {
        return JapariCraftMod.MODID + ":" + "textures/armor/hat.png";
    }
}
