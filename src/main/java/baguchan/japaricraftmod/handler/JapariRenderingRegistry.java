package baguchan.japaricraftmod.handler;

import baguchan.japaricraftmod.mob.*;
import baguchan.japaricraftmod.mob.projectile.EntityDarkSandStarball;
import baguchan.japaricraftmod.model.render.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    }
}
