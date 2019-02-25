package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelOtter;
import baguchan.japaricraftmod.mob.EntityOtter;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class OtterRender extends RenderLiving<EntityOtter> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/otter.png");

    public OtterRender(RenderManager renderManager) {
        super(renderManager, new ModelOtter(), 0.5F);
        this.addLayer(new LayerHeldItem(this) {

            @Override
            public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
                p_188359_1_();
                super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);

            }

            void p_188359_1_() {
                GL11.glTranslatef(0.0F, 0.1F, 0.0F);
            }

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelOtter) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0425F);
            }
        });
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityOtter entity) {
        return TEXTURES;
    }
}