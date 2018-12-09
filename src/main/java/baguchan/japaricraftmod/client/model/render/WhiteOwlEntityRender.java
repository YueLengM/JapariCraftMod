package baguchan.japaricraftmod.client.model.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.client.model.ModelWhiteOwl;
import baguchan.japaricraftmod.mob.EntityWhiteOwl;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WhiteOwlEntityRender extends RenderLiving<EntityWhiteOwl> {
    private static final ResourceLocation OWL_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/owl2.png");

    public WhiteOwlEntityRender(RenderManager renderManager) {
        super(renderManager, new ModelWhiteOwl(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityWhiteOwl entity) {
        return OWL_TEXTURES;
    }

    protected float handleRotationFloat(EntityWhiteOwl livingBase, float partialTicks) {
        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}