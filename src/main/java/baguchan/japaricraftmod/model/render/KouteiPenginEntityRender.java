package baguchan.japaricraftmod.model.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.mob.EntityKouteiPenguin;
import baguchan.japaricraftmod.model.ModelKouteiPengin;
import baguchan.japaricraftmod.model.render.layer.LayerFriendHeldItem;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KouteiPenginEntityRender extends RenderLiving<EntityKouteiPenguin>
{
    private static final ResourceLocation Pengin_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/ppp1.png");
    public KouteiPenginEntityRender(RenderManager renderManager)
    {
        super(renderManager, new ModelKouteiPengin(), 0.5F);
        this.addLayer(new LayerBipedArmor(this) {
            protected void setModelSlotVisible(ModelBiped p_188359_1_, EntityEquipmentSlot slotIn) {
                this.setModelVisible(p_188359_1_);

                switch (slotIn) {
                    case HEAD:
                        p_188359_1_.bipedHead.showModel = true;
                        p_188359_1_.bipedHeadwear.showModel = true;
                        break;
                    case CHEST:
                        p_188359_1_.bipedBody.showModel = false;
                        p_188359_1_.bipedRightArm.showModel = false;
                        p_188359_1_.bipedLeftArm.showModel = false;
                        break;
                    case LEGS:
                        p_188359_1_.bipedBody.showModel = false;
                        p_188359_1_.bipedRightLeg.showModel = false;
                        p_188359_1_.bipedLeftLeg.showModel = false;
                        break;
                    case FEET:
                        p_188359_1_.bipedRightLeg.showModel = false;
                        p_188359_1_.bipedLeftLeg.showModel = false;
                }
            }

            @Override
            public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
                super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                p_188359_1_();
            }

            void p_188359_1_() {
                GlStateManager.translate(0.0F, 0.01F, 0.0F);
            }
        });
        this.addLayer(new LayerFriendHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelKouteiPengin) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityKouteiPenguin entity)
    {
        return Pengin_TEXTURES;
    }
}