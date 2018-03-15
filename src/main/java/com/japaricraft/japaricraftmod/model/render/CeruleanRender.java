package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.EntityCerulean;
import com.japaricraft.japaricraftmod.model.ModelCerulean;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

@SideOnly(Side.CLIENT)
public class CeruleanRender extends RenderLiving<EntityCerulean>
{
    private static final ResourceLocation Cerulean_TEXTURES = new ResourceLocation(MODID, "textures/entity/cerulean/cerulean.png");
    public CeruleanRender(RenderManager renderManager)
    {
        super(renderManager, new ModelCerulean(), 0.9F);
    }


    public void doRender(EntityCerulean entity, double x, double y, double z, float entityYaw, float partialTicks) {
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
    protected ResourceLocation getEntityTexture(EntityCerulean entity)
    {
        return Cerulean_TEXTURES;
    }
}