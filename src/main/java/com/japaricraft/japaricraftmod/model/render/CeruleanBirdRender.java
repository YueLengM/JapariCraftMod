package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.EntityCeruleanBird;
import com.japaricraft.japaricraftmod.model.ModelCeruleanBird;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class CeruleanBirdRender extends RenderLiving<EntityCeruleanBird> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(MODID, "textures/entity/cerulean/ceruleanbird.png");

    public CeruleanBirdRender(RenderManager renderManager) {
        super(renderManager, new ModelCeruleanBird(), 0.3F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityCeruleanBird entity) {
        return TEXTURES;
    }
}