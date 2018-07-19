package baguchan.japaricraftmod.model.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.mob.EntityServal;
import baguchan.japaricraftmod.model.ModelServal;
import baguchan.japaricraftmod.model.render.layer.LayerFriendHeldItem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ServalEntityRender extends RenderLiving<EntityServal>
{
    private static final ResourceLocation SERVAL_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/serval/serval.png");
    private static final ResourceLocation BEG_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/serval/serval_beg.png");
    public ServalEntityRender(RenderManager renderManager)
    {
            super(renderManager, new ModelServal(), 0.5F);
        this.addLayer(new LayerFriendHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelServal) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityServal entity)
    {
        if (entity.isBegging()) {
            return BEG_TEXTURES;
        } else {
            return SERVAL_TEXTURES;
        }
    }
}