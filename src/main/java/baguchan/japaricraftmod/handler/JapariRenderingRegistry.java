package baguchan.japaricraftmod.handler;

import baguchan.japaricraftmod.client.render.*;
import baguchan.japaricraftmod.mob.*;
import baguchan.japaricraftmod.mob.projectile.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class JapariRenderingRegistry {
    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityServal.class, ServalEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityWhiteOwl.class, WhiteOwlEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBrownOwl.class, BrownOwlEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAraisan.class, AraisanRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCerulean.class, CeruleanRender::new);
        RenderingRegistry.registerEntityRenderingHandler(PoisonEntityCerulean.class, PoisonCeruleanRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityShoebill.class, ShoebillEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGuide.class, GuideRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityKouteiPenguin.class, KouteiPenginEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFennec.class, FennecRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RoyalPenguinEntity.class, RoyalPenguinRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAlpaca.class, AlpacaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySquirre.class, SquirreRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTutinoko.class, TutinokoRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCeruleanEye.class, CeruleanEyeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderCerulean.class, EnderCeruleanRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySandCat.class, SandCatRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySandStarSlime.class, SandStarSlimeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkSandStarball.class, m -> new RenderSnowball<>(m, JapariItems.darksandstar, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityOtter.class, OtterRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBeluga.class, BelugaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTwilightKobold.class, TwilightKoboldRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityStarSorcerger.class, StarSorcergerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityScocel.class, ScocelRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPoisonBall.class, PoisonBallRender::new);
    }
}
