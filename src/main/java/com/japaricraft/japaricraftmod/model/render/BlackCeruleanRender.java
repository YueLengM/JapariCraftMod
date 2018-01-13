package com.japaricraft.japaricraftmod.model.render;

import com.japaricraft.japaricraftmod.mob.BlackCerulean;
import com.japaricraft.japaricraftmod.model.ModelBlackCerulean;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.japaricraft.japaricraftmod.JapariCraftMod.MODID;

@SideOnly(Side.CLIENT)
public class BlackCeruleanRender extends RenderLiving<BlackCerulean> {
    private static final ResourceLocation TEXTURES = new ResourceLocation(MODID, "textures/entity/blackcerulean.png");

    public BlackCeruleanRender(RenderManager renderManager) {
        super(renderManager, new ModelBlackCerulean(), 3.5F);
    }


    @Override
    protected ResourceLocation getEntityTexture(BlackCerulean entity) {
        return TEXTURES;
    }
}