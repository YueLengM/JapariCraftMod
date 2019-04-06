package baguchan.japaricraftmod;

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
    public static int cerulean_wight = 46;

    @Config.LangKey(config + "poisoncerulean_wight")
    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 100)
    @Config.Comment("Changes spawn rate of PoisonCerulean. Increase value to spawn more PoisonCerulean.")
    public static int poisoncerulean_wight = 30;

    @Config.LangKey(config + "plain_friends_wight")
    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 20)
    @Config.Comment("Changes spawn rate of PlainAndForests'Friend. Increase value to spawn more Friends.")
    public static int plainsfriends_wight = 5;
    @Config.LangKey(config + "sand_friends_wight")
    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 20)
    @Config.Comment("Changes spawn rate of SandyBiome's Friend. Increase value to spawn more Friends.")
    public static int sandfriends_wight = 2;
    @Config.LangKey(config + "snow_friends_wight")
    @Config.RequiresMcRestart
    @Config.RangeInt(min = 0, max = 20)
    @Config.Comment("Changes spawn rate of SnownyBiome's Friend. Increase value to spawn more Friends.")
    public static int snowfriends_wight = 2;

    @Config.LangKey(config + "sandstarlab_gen")
    @Config.RequiresMcRestart
    @Config.RangeInt(min = 600, max = 1500)
    @Config.Comment("SandStar Lab Rarity Given this value as X, 1 lab will spawn in X plain biome and wasteland biome chunks")
    public static int sandstarlabGen = 900;


    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(JapariCraftMod.MODID)) {
            ConfigManager.sync(JapariCraftMod.MODID, Config.Type.INSTANCE);
        }
    }
}
