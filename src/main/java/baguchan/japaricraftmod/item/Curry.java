package baguchan.japaricraftmod.item;

import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class Curry extends ItemFood {
    public Curry (){
        super(9,4,false);
        this.setCreativeTab(JapariCraftMod.tabJapariCraft);
        this.setUnlocalizedName("Curry");
        this.setMaxStackSize(8);
    }
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(Items.BOWL);
    }
}