package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelAraisan;
import baguchan.japaricraftmod.client.render.layer.LayerFriendHeldItem;
import baguchan.japaricraftmod.mob.EntityAraisan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AraisanRender extends RenderLiving<EntityAraisan>
{
    private static final ResourceLocation Arai_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/araisan/araisan.png");
    public AraisanRender(RenderManager renderManager)
    {
        super(renderManager, new ModelAraisan(), 0.5F);
        this.addLayer(new LayerFriendHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelAraisan) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });

    }


    @Override
    protected ResourceLocation getEntityTexture(EntityAraisan entity)
    {
        return Arai_TEXTURES;
    }
}