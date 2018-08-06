package baguchan.japaricraftmod.item;

import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTutorialPaper extends Item {

    public ItemTutorialPaper() {

        this.setCreativeTab(JapariCraftMod.tabJapariCraft);

        this.setUnlocalizedName("tutorialpaper");

    }


    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        ItemStack itemstack = playerIn.getHeldItem(handIn);

        playerIn.openGui(JapariCraftMod.instance, JapariCraftMod.ID_Tutorial_Gui, worldIn, ((int) playerIn.posX), ((int) playerIn.posY), ((int) playerIn.posZ));

        playerIn.addStat(StatList.getObjectUseStats(this));

        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);

    }

}