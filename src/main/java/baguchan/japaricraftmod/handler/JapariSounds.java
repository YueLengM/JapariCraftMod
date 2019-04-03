package baguchan.japaricraftmod.handler;


import baguchan.japaricraftmod.JapariCraftMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = JapariCraftMod.MODID)
public final class JapariSounds {
    public static final SoundEvent SQUIRRE_SLEEP = createEvent("mob.squirre.sleep");
    public static final SoundEvent SANDSTAR_ACTIVE = createEvent("sandstar.active");

    private static SoundEvent createEvent(String sound) {

        ResourceLocation name = new ResourceLocation(JapariCraftMod.MODID, sound);

        return new SoundEvent(name).setRegistryName(name);

    }


    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> evt) {

        evt.getRegistry().register(SQUIRRE_SLEEP);
        evt.getRegistry().register(SANDSTAR_ACTIVE);
    }

    private JapariSounds() {

    }
}
