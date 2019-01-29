package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelBeluga;
import baguchan.japaricraftmod.client.render.layer.LayerFriendHeldItem;
import baguchan.japaricraftmod.mob.EntityBeluga;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

public class BelugaRender extends RenderLiving<EntityBeluga> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/beluga.png");

    public BelugaRender(RenderManager renderManager) {
        super(renderManager, new ModelBeluga(), 0.5F);
        this.addLayer(new LayerFriendHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelBeluga) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityBeluga entity) {
        return TEXTURES;
    }
}