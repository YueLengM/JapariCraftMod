package baguchan.japaricraftmod.model.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.mob.EntityGuide;
import baguchan.japaricraftmod.model.ModelGuide;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuideRender extends RenderBiped<EntityGuide>
{
    private static final ResourceLocation Guide_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/guide.png");
    public GuideRender(RenderManager renderManager)
    {
        super(renderManager, new ModelGuide(), 0.6F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityGuide entity)
    {
        return Guide_TEXTURES;
    }
}