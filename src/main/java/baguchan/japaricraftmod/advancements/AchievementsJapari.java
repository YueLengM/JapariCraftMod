package baguchan.japaricraftmod.advancements;

import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class AchievementsJapari {
    //1.12.2のメイドの進捗を参考にした
    public static void grantAdvancement(EntityPlayer player, String advancementName) {
        if (!(player instanceof EntityPlayerMP))
            return;

        AdvancementManager manager = player.world.getMinecraftServer().getAdvancementManager();
        Advancement advancement = manager.getAdvancement(new ResourceLocation(JapariCraftMod.MODID, "japaricraftmod/" + advancementName));
        if (advancement == null)
            return;

        ((EntityPlayerMP) player).getAdvancements().grantCriterion(advancement, "done");
    }
}
