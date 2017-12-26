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

    @Config.LangKey(config + "ceruleanbird_wight")
    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 100)
    @Config.Comment("Changes spawn rate of Cerulean Bird. Increase value to spawn more Cerulean Bird.")
    public static int birdwight = 28;

    @Config.LangKey(config + "cerulean_wight")
    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 100)
    @Config.Comment("Changes spawn rate of Cerulean Bird. Increase value to spawn more Cerulean Bird.")
    public static int cerulean_wight = 32;

    @Config.LangKey(config + "poisoncerulean_wight")
    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 100)
    @Config.Comment("Changes spawn rate of Cerulean Bird. Increase value to spawn more Cerulean Bird.")
    public static int poisoncerulean_wight = 30;

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(JapariCraftMod.MODID)) {
            ConfigManager.sync(JapariCraftMod.MODID, Config.Type.INSTANCE);
        }
    }
}