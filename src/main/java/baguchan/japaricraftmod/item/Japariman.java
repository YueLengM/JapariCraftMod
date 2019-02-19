package baguchan.japaricraftmod.item;

import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.item.ItemFood;


public class Japariman extends ItemFood {

    public Japariman(){
        super(4, 0.62F, false);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("Japariman");
    }

}