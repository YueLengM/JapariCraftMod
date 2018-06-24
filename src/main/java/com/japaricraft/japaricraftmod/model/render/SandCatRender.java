package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.EntitySandCat;
import com.japaricraft.japaricraftmod.model.ModelSandCat;
import com.japaricraft.japaricraftmod.model.render.layer.LayerFriendHeldItem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class SandCatRender extends RenderLiving<EntitySandCat> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(MODID, "textures/entity/sandcat.png");

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