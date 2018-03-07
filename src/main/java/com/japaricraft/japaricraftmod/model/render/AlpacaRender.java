package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.EntityAlpaca;
import com.japaricraft.japaricraftmod.model.ModelAlpaca;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class AlpacaRender extends RenderLiving<EntityAlpaca> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(MODID, "textures/entity/a_suri.png");

    public AlpacaRender(RenderManager renderManager) {
        super(renderManager, new ModelAlpaca(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityAlpaca entity) {
        return TEXTURES;
    }
}