package baguchan.japaricraftmod.item;

import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class ItemSandStarFragment extends Item {
    public ItemSandStarFragment() {
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("SandStarFragment");
    }
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }
}

