package baguchan.japaricraftmod.model.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.mob.EntityTwilightKobolt;
import baguchan.japaricraftmod.model.ModelTwilightKobolt;
import baguchan.japaricraftmod.model.render.layer.LayerFriendHeldItem;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class TwilightKoboldRender extends RenderLiving<EntityTwilightKobolt> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/twilightkobolt/twilightkobolt.png");
    private static final ResourceLocation SCARED_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/twilightkobolt/twilightkobolt_scared.png");

    private final Random rnd = new Random();

    public TwilightKoboldRender(RenderManager renderManager) {
        super(renderManager, new ModelTwilightKobolt(), 0.5F);
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
        });

        this.addLayer(new LayerFriendHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelTwilightKobolt) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }

    public void doRender(EntityTwilightKobolt entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (entity.isScared()) {
            double d0 = 0.02D;
            x += this.rnd.nextGaussian() * 0.008D;
            z += this.rnd.nextGaussian() * 0.008D;
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTwilightKobolt entity) {
        if (entity.isScared()) {
            return SCARED_TEXTURES;
        } else {
            return TEXTURES;
        }
    }
}