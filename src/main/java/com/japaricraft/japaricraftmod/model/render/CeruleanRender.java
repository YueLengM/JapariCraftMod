package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.EntityCerulean;
import com.japaricraft.japaricraftmod.model.ModelCerulean;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

@SideOnly(Side.CLIENT)
public class CeruleanRender extends RenderLiving<EntityCerulean>
{
    private static final ResourceLocation Cerulean_TEXTURES = new ResourceLocation(MODID, "textures/entity/cerulean/cerulean.png");
    public CeruleanRender(RenderManager renderManager)
    {
        super(renderManager, new ModelCerulean(), 0.9F);
    }


    @Override
    protected ResourceLocation getEntityTexture(EntityCerulean entity)
    {
        return Cerulean_TEXTURES;
    }
}