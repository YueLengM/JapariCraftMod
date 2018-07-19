package baguchan.japaricraftmod.model.render;


import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.mob.EntityFennec;
import baguchan.japaricraftmod.model.ModelFennec;
import baguchan.japaricraftmod.model.render.layer.LayerFriendHeldItem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

public class FennecRender extends RenderLiving<EntityFennec> {
    private static final ResourceLocation Fennec_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/fennec.png");

    public FennecRender(RenderManager renderManager) {
        super(renderManager, new ModelFennec(), 0.5F);
        this.addLayer(new LayerFriendHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelFennec) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityFennec entity) {
        return Fennec_TEXTURES;
    }
}