package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.EntityShoebill;
import com.japaricraft.japaricraftmod.model.ModelShoebill;
import com.japaricraft.japaricraftmod.model.render.layer.LayerFriendHeldItem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

@SideOnly(Side.CLIENT)
public class ShoebillEntityRender extends RenderLiving<EntityShoebill>
{
    private static final ResourceLocation Shobill_TEXTURES = new ResourceLocation(MODID, "textures/entity/shoebill.png");
    public ShoebillEntityRender(RenderManager renderManager)
    {
        super(renderManager, new ModelShoebill(), 0.5F);
        this.addLayer(new LayerFriendHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelShoebill) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityShoebill entity)
    {
        return Shobill_TEXTURES;
    }
}