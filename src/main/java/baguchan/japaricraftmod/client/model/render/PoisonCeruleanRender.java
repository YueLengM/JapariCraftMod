package baguchan.japaricraftmod.client.model.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelCerulean;
import baguchan.japaricraftmod.mob.PoisonEntityCerulean;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PoisonCeruleanRender extends RenderLiving<PoisonEntityCerulean>
{
    private static final ResourceLocation Cerulean_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/cerulean/poison_cerulean.png");
    public PoisonCeruleanRender(RenderManager renderManager)
    {
        super(renderManager, new ModelCerulean(), 0.9F);
    }

    public void doRender(PoisonEntityCerulean entity, double x, double y, double z, float entityYaw, float partialTicks) {
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
    @Override
    protected ResourceLocation getEntityTexture(PoisonEntityCerulean entity)
    {
        return Cerulean_TEXTURES;
    }
}