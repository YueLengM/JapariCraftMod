package com.japaricraft.japaricraftmod.item;

import com.japaricraft.japaricraftmod.JapariCraftMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static sun.audio.AudioPlayer.player;


public class Japariman extends ItemFood {

    public Japariman(){
        super(4,3,false);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("Japariman");
        this.setMaxStackSize(64);


    }
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn){
        if (!worldIn.isRemote)
        {
            playerIn.addStat(JapariCraftMod.achievement_japariman);
        }
    }

}