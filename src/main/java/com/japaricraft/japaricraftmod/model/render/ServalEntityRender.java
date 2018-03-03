package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.Serval;
import com.japaricraft.japaricraftmod.model.ModelServal;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;


@SideOnly(Side.CLIENT)
public class ServalEntityRender extends RenderLiving<Serval>
{
    private static final ResourceLocation SERVAL_TEXTURES = new ResourceLocation(MODID, "textures/entity/serval/serval.png");
    private static final ResourceLocation BEG_TEXTURES = new ResourceLocation(MODID, "textures/entity/serval/serval_beg.png");
    public ServalEntityRender(RenderManager renderManager)
    {
            super(renderManager, new ModelServal(), 0.5F);
        this.addLayer(new LayerHeldItem(this) {
            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelServal) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }


    @Override
    protected ResourceLocation getEntityTexture(Serval entity)
    {
        if (entity.isBegging()) {
            return BEG_TEXTURES;
        } else {
            return SERVAL_TEXTURES;
        }
    }
}