package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelKouteiPengin;
import baguchan.japaricraftmod.client.render.layer.LayerFriendHeldItem;
import baguchan.japaricraftmod.mob.EntityKouteiPenguin;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KouteiPenginEntityRender extends RenderLiving<EntityKouteiPenguin>
{
    private static final ResourceLocation Pengin_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/ppp1.png");
    public KouteiPenginEntityRender(RenderManager renderManager)
    {
        super(renderManager, new ModelKouteiPengin(), 0.5F);
        this.addLayer(new LayerFriendHeldItem(this) {

            protected void translateToHand(EnumHandSide p_191361_1_) {
                ((ModelKouteiPengin) this.livingEntityRenderer.getMainModel()).getArmForSide(p_191361_1_).postRender(0.0625F);
            }
        });
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityKouteiPenguin entity)
    {
        return Pengin_TEXTURES;
    }
}