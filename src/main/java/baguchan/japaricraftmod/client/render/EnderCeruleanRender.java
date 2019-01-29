package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelEnderCerulean;
import baguchan.japaricraftmod.mob.EntityEnderCerulean;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class EnderCeruleanRender extends RenderLiving<EntityEnderCerulean> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/cerulean/endercerulean.png");
    private static final ResourceLocation INVULNERABLE_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/cerulean/endercerulean_invulnerable.png");

    public EnderCeruleanRender(RenderManager renderManager) {
        super(renderManager, new ModelEnderCerulean(), 0.5F);
    }

    public void doRender(EntityEnderCerulean entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (!entity.isInvisible()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
        }
    }

    protected ResourceLocation getEntityTexture(EntityEnderCerulean entity) {
        int i = entity.getInvulTime();
        return i > 0 && (i > 80 || i / 5 % 2 != 1) ? INVULNERABLE_TEXTURES : TEXTURES;
    }
}