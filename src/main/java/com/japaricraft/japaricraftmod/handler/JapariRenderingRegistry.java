package com.japaricraft.japaricraftmod.handler;

import com.japaricraft.japaricraftmod.mob.*;
import com.japaricraft.japaricraftmod.model.render.*;
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
        RenderingRegistry.registerEntityRenderingHandler(EntitySandStarHandler.class, SandStarHandlerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(RoyalPenguinEntity.class, RoyalPenguinRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAlpaca.class, AlpacaRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySquirre.class, SquirreRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCeruleanBird.class, CeruleanBirdRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBlackCerulean.class, BlackCeruleanRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTutinoko.class, TutinokoRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAlpacaCafe.class, AlpacaCafeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCeruleanEye.class, CeruleanEyeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderCerulean.class, EnderCeruleanRender::new);
    }
}
