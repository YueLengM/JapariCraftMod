
package baguchan.japaricraftmod.item;


import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.item.ItemFood;


public class JaparimanCocoa extends ItemFood {
    public JaparimanCocoa() {
        super(5, 0.65F, false);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("JaparimanCocoa");
    }

}