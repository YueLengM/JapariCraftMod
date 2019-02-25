package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelAlpaca;
import baguchan.japaricraftmod.client.render.layer.LayerFriendHeldItem;
import baguchan.japaricraftmod.mob.EntityAlpaca;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

public class AlpacaRender extends RenderLiving<EntityAlpaca> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/a_suri.png");

    public AlpacaRender(RenderManager renderManager) {
        super(renderManager, new ModelAlpaca(), 0.5F);
        this.addLayer(new LayerFriendHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelAlpaca) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityAlpaca entity) {
        return TEXTURES;
    }
}