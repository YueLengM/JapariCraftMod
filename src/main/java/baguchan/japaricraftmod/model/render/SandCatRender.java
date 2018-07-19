package baguchan.japaricraftmod.model.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.mob.EntitySandCat;
import baguchan.japaricraftmod.model.ModelSandCat;
import baguchan.japaricraftmod.model.render.layer.LayerFriendHeldItem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

public class SandCatRender extends RenderLiving<EntitySandCat> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/sandcat.png");

    public SandCatRender(RenderManager renderManager) {
        super(renderManager, new ModelSandCat(), 0.5F);
        this.addLayer(new LayerFriendHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelSandCat) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }


    @Override
    protected ResourceLocation getEntityTexture(EntitySandCat entity) {
        return TEXTURES;
    }
}