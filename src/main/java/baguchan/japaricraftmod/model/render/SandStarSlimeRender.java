package baguchan.japaricraftmod.model.render;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SandStarSlimeRender extends RenderSlime {
    private static final ResourceLocation SANDSTARSLIME_TEXTURES = new ResourceLocation("japaricraftmod:textures/entity/sandstarslime.png");

    public SandStarSlimeRender(RenderManager p_i47193_1_) {
        super(p_i47193_1_);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySlime entity) {
        return SANDSTARSLIME_TEXTURES;
    }
}
