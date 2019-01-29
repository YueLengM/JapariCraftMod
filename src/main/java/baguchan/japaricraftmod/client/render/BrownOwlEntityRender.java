package baguchan.japaricraftmod.client.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelBrownOwl;
import baguchan.japaricraftmod.mob.EntityBrownOwl;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BrownOwlEntityRender extends RenderLiving<EntityBrownOwl>
{
    private static final ResourceLocation OWL_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/owl1.png");
    public BrownOwlEntityRender(RenderManager renderManager)
    {
        super(renderManager, new ModelBrownOwl(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBrownOwl entity)
    {
        return OWL_TEXTURES;
    }
    protected float handleRotationFloat(EntityBrownOwl livingBase, float partialTicks)
    {
        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}