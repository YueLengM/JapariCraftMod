package baguchan.japaricraftmod.item;

import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;



public class StarJapariman extends ItemFood {

    public StarJapariman() {
        super(4, 0.62F, false);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("StarJapariman");
        this.setAlwaysEdible();
    }
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {

            {
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 600, 0));
            }
        }
    }
}