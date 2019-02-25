package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelServal;
import baguchan.japaricraftmod.mob.EntityServal;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ServalEntityRender extends RenderLiving<EntityServal>
{
    private static final ResourceLocation SERVAL_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/serval/serval.png");
    private static final ResourceLocation BEG_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/serval/serval_beg.png");
    public ServalEntityRender(RenderManager renderManager)
    {
        super(renderManager, new ModelServal(), 0.5F);
        this.addLayer(new LayerHeldItem(this) {

            public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
                GlStateManager.translate(0.0F, 0.4F, 0.0F);
                super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);

            }

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelServal) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0425F);
            }
        });
    }

    @Override
    protected void applyRotations(EntityServal entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
        if (entityLiving.isStretching()) {
            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityServal entity)
    {
        if (entity.isBegging()) {
            return BEG_TEXTURES;
        } else {
            return SERVAL_TEXTURES;
        }
    }
}