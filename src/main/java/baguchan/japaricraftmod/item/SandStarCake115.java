package baguchan.japaricraftmod.item;

import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class SandStarCake115 extends ItemFood {

    public SandStarCake115() {
        super(4, 1F, false);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("SandStarCake115");
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
                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1200, 0));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flags) {
        super.addInformation(stack, world, tooltip, flags);
        tooltip.add(I18n.format(getUnlocalizedName() + ".tooltip"));
    }
}