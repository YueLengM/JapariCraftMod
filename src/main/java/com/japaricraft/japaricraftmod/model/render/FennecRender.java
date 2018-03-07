package com.japaricraft.japaricraftmod.model.render;


import com.japaricraft.japaricraftmod.mob.EntityFennec;
import com.japaricraft.japaricraftmod.model.ModelFennec;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

public class FennecRender extends RenderLiving<EntityFennec>
{
    private static final ResourceLocation Fennec_TEXTURES = new ResourceLocation(MODID, "textures/entity/fennec.png");
    public FennecRender(RenderManager renderManager)
    {
        super(renderManager, new ModelFennec(), 0.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityFennec entity)
    {
        return Fennec_TEXTURES;
    }
}