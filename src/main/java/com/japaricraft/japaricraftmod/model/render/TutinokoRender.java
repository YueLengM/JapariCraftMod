package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.EntityTutinoko;
import com.japaricraft.japaricraftmod.model.ModelTutinoko;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class TutinokoRender extends RenderLiving<EntityTutinoko> {
    private static final ResourceLocation Tutinoko_TEXTURES = new ResourceLocation(MODID, "textures/entity/tutinoko.png");

    public TutinokoRender(RenderManager renderManager) {
        super(renderManager, new ModelTutinoko(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityTutinoko entity) {
        return Tutinoko_TEXTURES;
    }

}