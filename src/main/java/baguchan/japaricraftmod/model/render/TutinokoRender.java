package baguchan.japaricraftmod.model.render;

import baguchan.japaricraftmod.JapariCraftMod;
import baguchan.japaricraftmod.mob.EntityTutinoko;
import baguchan.japaricraftmod.model.ModelTutinoko;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class TutinokoRender extends RenderLiving<EntityTutinoko> {
    private static final ResourceLocation Tutinoko_TEXTURES = new ResourceLocation(JapariCraftMod.MODID, "textures/entity/tutinoko.png");

    public TutinokoRender(RenderManager renderManager) {
        super(renderManager, new ModelTutinoko(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityTutinoko entity) {
        return Tutinoko_TEXTURES;
    }

}