package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelWhiteOwl;
import baguchan.japaricraftmod.client.render.layer.LayerWhiteOwlBook;
import baguchan.japaricraftmod.mob.EntityWhiteOwl;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WhiteOwlEntityRender extends RenderLiving<EntityWhiteOwl> {
    private static final ResourceLocation OWL_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/owl2.png");

    public WhiteOwlEntityRender(RenderManager renderManager) {
        super(renderManager, new ModelWhiteOwl(), 0.5F);
        this.addLayer(new LayerHeldItem(this) {
            @Override
            public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
                if (!((EntityWhiteOwl) entitylivingbaseIn).isReading()) {
                    super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                }
            }

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelWhiteOwl) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0425F);
            }
        });
        this.addLayer(new LayerWhiteOwlBook(this));
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityWhiteOwl entity) {
        return OWL_TEXTURES;
    }

    protected float handleRotationFloat(EntityWhiteOwl livingBase, float partialTicks) {
        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}