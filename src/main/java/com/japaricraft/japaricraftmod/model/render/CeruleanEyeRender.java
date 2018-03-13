package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.EntityCeruleanEye;
import com.japaricraft.japaricraftmod.model.ModelCeruleanEye;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class CeruleanEyeRender extends RenderLiving<EntityCeruleanEye> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(MODID, "textures/entity/cerulean/ceruleaneye.png");

    public CeruleanEyeRender(RenderManager renderManager) {
        super(renderManager, new ModelCeruleanEye(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityCeruleanEye entity) {
        return TEXTURES;
    }
}