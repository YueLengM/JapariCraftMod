package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.*;
import baguchan.japaricraftmod.client.model.*;
import baguchan.japaricraftmod.mob.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class ScocelRender extends RenderLiving<EntityScocel> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/scocel/scocel.png");

    public ScocelRender(RenderManager renderManager) {
        super(renderManager, new ModelScocel(), 0.6F);
    }

    public void doRender(EntityScocel entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.87F);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

    }

    @Override
    protected ResourceLocation getEntityTexture(EntityScocel entity) {
        return TEXTURES;
    }

    protected void preRenderCallback(EntityScocel entitylivingbaseIn, float partialTickTime) {
        float f = 1.9F;

        GlStateManager.scale(f, f, f);
    }
}