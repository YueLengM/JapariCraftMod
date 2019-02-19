package baguchan.japaricraftmod.item.armor;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelServalEar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static baguchan.japaricraftmod.handler.JapariItems.animalCostumeMaterial;

public class ItemServalEarCostume extends ItemArmor {
    public ItemServalEarCostume(ArmorMaterial material, int i, EntityEquipmentSlot head) {
        super(animalCostumeMaterial, 0, EntityEquipmentSlot.HEAD);
        setMaxDamage(-1);
        setCreativeTab(JapariCraftMod.tabJapariCraft);
        setUnlocalizedName("ServalEarCostume");
    }

    @SideOnly(Side.CLIENT)
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
        return new ModelServalEar();
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return JapariCraftMod.MODID + ":" + "textures/armor/servalear.png";
    }
}