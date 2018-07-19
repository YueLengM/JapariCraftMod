package baguchan.japaricraftmod.item;

import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.item.ItemFood;

public class JaparimanApple extends ItemFood {
    public JaparimanApple() {
        super(7, 4, false);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("JaparimanApple");
    }

}