package com.japaricraft.japaricraftmod;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("WeakerAccess")
@Config(modid = JapariCraftMod.MODID)
@Mod.EventBusSubscriber(modid = JapariCraftMod.MODID)
public class JapariConfig {
    private final static String config = JapariCraftMod.MODID + ".config.";

    @Config.LangKey(config + "cerulean_wight")
    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 100)
    @Config.Comment("Changes spawn rate of Cerulean. Increase value to spawn more Cerulean.")
    public static int cerulean_wight = 32;

    @Config.LangKey(config + "poisoncerulean_wight")
    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 100)
    @Config.Comment("Changes spawn rate of PoisonCerulean. Increase value to spawn more PoisonCerulean.")
    public static int poisoncerulean_wight = 30;

    @Config.LangKey(config + "blackcerulean_wight")
    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 30)
    @Config.Comment("Changes spawn rate of BlackCerulean. Increase value to spawn more BlackCerulean.")
    public static int blackcerulean_wight = 1;

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(JapariCraftMod.MODID)) {
            ConfigManager.sync(JapariCraftMod.MODID, Config.Type.INSTANCE);
        }
    }
}
