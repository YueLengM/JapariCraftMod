package baguchan.japaricraftmod.item;

import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.item.ItemFood;


public class Japariman extends ItemFood {

    public Japariman(){
        super(4, 3, false);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("Japariman");
    }

}