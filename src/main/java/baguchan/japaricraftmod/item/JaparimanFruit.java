package baguchan.japaricraftmod.item;


import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.item.ItemFood;

public class JaparimanFruit extends ItemFood {
    public JaparimanFruit() {
        super(7, 5, false);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("JaparimanFruit");
    }

}