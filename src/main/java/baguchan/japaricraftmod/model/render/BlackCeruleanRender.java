package baguchan.japaricraftmod.model.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.mob.EntityBlackCerulean;
import baguchan.japaricraftmod.model.ModelBlackCerulean;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlackCeruleanRender extends RenderLiving<EntityBlackCerulean> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/blackcerulean.png");

    public BlackCeruleanRender(RenderManager renderManager) {
        super(renderManager, new ModelBlackCerulean(), 3.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityBlackCerulean entity) {
        return TEXTURES;
    }
}