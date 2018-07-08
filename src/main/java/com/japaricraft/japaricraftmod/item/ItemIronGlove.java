package com.japaricraft.japaricraftmod.item;

import com.google.common.collect.Multimap;
import com.japaricraft.japaricraftmod.JapariCraftMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemIronGlove extends Item {
    protected float attackDamage;
    protected float attackSpeed;

    public ItemIronGlove() {
        this.attackDamage = 2.0F;
        this.attackSpeed = -2.8F;
        this.setMaxDamage(160);
        this.setMaxStackSize(1);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("IronGlove");

    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (double) this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double) this.attackSpeed, 0));
        }

        return multimap;
    }

    @Override
    public int getItemEnchantability() {
        return 14;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.LOOTING) {
            return true;
        }

        if (enchantment == Enchantments.SHARPNESS) {
            return true;
        }

        return super.canApplyAtEnchantingTable(stack, enchantment);
    }

}
