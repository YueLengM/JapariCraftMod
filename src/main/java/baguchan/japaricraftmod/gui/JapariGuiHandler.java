package baguchan.japaricraftmod.gui;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.mob.EntityFriend;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class JapariGuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == JapariCraftMod.ID_JAPARI_INVENTORY) {
            Entity entity = world.getEntityByID(x);

            if (entity instanceof EntityFriend) {
                return new ContainerFriendInventory((EntityFriend) entity, player);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == JapariCraftMod.ID_JAPARI_INVENTORY) {
            Entity entity = world.getEntityByID(x);

            if (entity instanceof EntityFriend) {
                return new GuiFriendInventory((EntityFriend) entity, player);
            }
        }
        if (ID == JapariCraftMod.ID_Tutorial_Gui) {

            return new GuiTutorialPaper(player);

        }
        return null;
    }
}