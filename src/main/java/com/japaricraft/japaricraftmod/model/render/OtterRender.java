package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.EntityOtter;
import com.japaricraft.japaricraftmod.model.ModelOtter;
import com.japaricraft.japaricraftmod.model.render.layer.LayerFriendHeldItem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class OtterRender extends RenderLiving<EntityOtter> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(MODID, "textures/entity/otter.png");

    public OtterRender(RenderManager renderManager) {
        super(renderManager, new ModelOtter(), 0.5F);
        this.addLayer(new LayerFriendHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelOtter) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityOtter entity) {
        return TEXTURES;
    }
}