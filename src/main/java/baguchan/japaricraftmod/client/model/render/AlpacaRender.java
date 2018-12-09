package baguchan.japaricraftmod.client.model.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelAlpaca;
import baguchan.japaricraftmod.mob.EntityAlpaca;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class AlpacaRender extends RenderLiving<EntityAlpaca> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/a_suri.png");

    public AlpacaRender(RenderManager renderManager) {
        super(renderManager, new ModelAlpaca(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityAlpaca entity) {
        return TEXTURES;
    }
}