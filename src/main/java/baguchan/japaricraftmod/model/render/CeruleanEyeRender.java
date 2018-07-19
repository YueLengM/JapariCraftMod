package baguchan.japaricraftmod.model.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.mob.EntityCeruleanEye;
import baguchan.japaricraftmod.model.ModelCeruleanEye;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class CeruleanEyeRender extends RenderLiving<EntityCeruleanEye> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/cerulean/ceruleaneye.png");

    public CeruleanEyeRender(RenderManager renderManager) {
        super(renderManager, new ModelCeruleanEye(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityCeruleanEye entity) {
        return TEXTURES;
    }
}