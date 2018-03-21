package com.japaricraft.japaricraftmod.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.japaricraft.japaricraftmod.JapariCraftMod.tabJapariCraft;


public class WildLiberationPotion extends ItemFood
{

    public WildLiberationPotion (){
        super(0,1,false);
        this.setCreativeTab(tabJapariCraft);
        this.setUnlocalizedName("WildLiberationPotion");
        this.setMaxStackSize(1);
        this.setAlwaysEdible();
    }


    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(Items.GLASS_BOTTLE);
    }

 //飲み物の動作
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.DRINK;
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {

            {
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 3600, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2400, 1));

            }
        }
    }
    public boolean hasEffect(ItemStack stack) {
        return true;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltip, flags);
        tooltip.add(I18n.format(getUnlocalizedName() + ".tooltip"));
    }
}
