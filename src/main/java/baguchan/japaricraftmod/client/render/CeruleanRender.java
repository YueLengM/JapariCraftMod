package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelCerulean;
import baguchan.japaricraftmod.mob.EntityCerulean;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CeruleanRender extends RenderLiving<EntityCerulean>
{
    private static final ResourceLocation Cerulean_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/cerulean/cerulean.png");
    public CeruleanRender(RenderManager renderManager)
    {
        super(renderManager, new ModelCerulean(), 0.3F);
    }


    public void doRender(EntityCerulean entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.shadowSize = 0.2F * (float) entity.getCeruleanSize();
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

    protected void preRenderCallback(EntityCerulean entitylivingbaseIn, float partialTickTime) {
        float f = 0.999F;
        GlStateManager.scale(0.999F, 0.999F, 0.999F);
        float f1 = (float) entitylivingbaseIn.getCeruleanSize();
        float f2 = 1.0F + 0.2F * f1;
        GlStateManager.scale(f2, f2, f2);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCerulean entity)
    {
        return Cerulean_TEXTURES;
    }
}