package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.Delphinus;
import com.japaricraft.japaricraftmod.model.ModelDelphinus;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class DelphinusRender extends RenderLiving<Delphinus> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(MODID, "textures/entity/delphinus.png");

    public DelphinusRender(RenderManager renderManager) {
        super(renderManager, new ModelDelphinus(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(Delphinus entity) {
        return TEXTURES;
    }
}