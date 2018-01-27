package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.Tutinoko;
import com.japaricraft.japaricraftmod.model.ModelTutinoko;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class TutinokoRender extends RenderLiving<Tutinoko> {
    private static final ResourceLocation Tutinoko_TEXTURES = new ResourceLocation(MODID, "textures/entity/tutinoko.png");

    public TutinokoRender(RenderManager renderManager) {
        super(renderManager, new ModelTutinoko(0.0F), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(Tutinoko entity) {
        return Tutinoko_TEXTURES;
    }

    protected void preRenderCallback(Tutinoko entitylivingbaseIn, float partialTickTime) {
        float f = 0.9375F;

        if (entitylivingbaseIn.getGrowingAge() < 0) {
            f = (float) ((double) f * 0.5D);
            this.shadowSize = 0.25F;
        } else {
            this.shadowSize = 0.5F;
        }

        GlStateManager.scale(f, f, f);
    }
}