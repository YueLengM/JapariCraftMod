package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.EntityAlpacaCafe;
import com.japaricraft.japaricraftmod.model.ModelAlpacaCafe;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class AlpacaCafeRender extends RenderLiving<EntityAlpacaCafe> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(MODID, "textures/entity/a_suri.png");

    public AlpacaCafeRender(RenderManager renderManager) {
        super(renderManager, new ModelAlpacaCafe(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityAlpacaCafe entity) {
        return TEXTURES;
    }
}