package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelTwilightKobold;
import baguchan.japaricraftmod.mob.EntityTwilightKobold;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class TwilightKoboldRender extends RenderLiving<EntityTwilightKobold> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/twilightkobolt/twilightkobolt.png");
    private static final ResourceLocation SCARED_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/twilightkobolt/twilightkobolt_scared.png");

    private final Random rnd = new Random();

    public TwilightKoboldRender(RenderManager renderManager) {
        super(renderManager, new ModelTwilightKobold(), 0.5F);
        this.addLayer(new LayerHeldItem(this) {

            public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
                GlStateManager.translate(0.0F, 0.4F, 0.0F);
                super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
            }

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelTwilightKobold) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0425F);
            }
        });
    }

    public void doRender(EntityTwilightKobold entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (entity.isScared()) {
            double d0 = 0.02D;
            x += this.rnd.nextGaussian() * 0.008D;
            z += this.rnd.nextGaussian() * 0.008D;
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTwilightKobold entity) {
        if (entity.isScared()) {
            return SCARED_TEXTURES;
        } else {
            return TEXTURES;
        }
    }
}