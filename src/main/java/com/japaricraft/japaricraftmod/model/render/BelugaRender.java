package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.EntityBeluga;
import com.japaricraft.japaricraftmod.model.ModelBeluga;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class BelugaRender extends RenderLiving<EntityBeluga> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(MODID, "textures/entity/beluga.png");

    public BelugaRender(RenderManager renderManager) {
        super(renderManager, new ModelBeluga(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityBeluga entity) {
        return TEXTURES;
    }
}