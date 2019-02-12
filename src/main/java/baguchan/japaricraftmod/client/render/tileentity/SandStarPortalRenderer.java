package baguchan.japaricraftmod.client.render.tileentity;

import baguchan.japaricraftmod.client.model.tileentity.ModelSandStarPortal;
import baguchan.japaricraftmod.handler.JapariBlocks;
import baguchan.japaricraftmod.tileentity.TileEntitySandStarPortal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SandStarPortalRenderer extends TileEntitySpecialRenderer<TileEntitySandStarPortal> {

    protected static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation("japaricraftmod:textures/models/sandstar_portal.png");
    protected final ModelSandStarPortal simpleCabinet = new ModelSandStarPortal();

    @Override
    public void render(TileEntitySandStarPortal te, double x, double y, double z, float partialTicks,
                       int destroyStage, float alpha) {
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);

        if (te.hasWorld() && te.getWorld().getBlockState(te.getPos()).getBlock() == JapariBlocks.SANDSTAR_PORTAL) {

            ModelBase modelSandStarPortal = simpleCabinet;


            this.bindTexture(TEXTURE_NORMAL);


            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            GlStateManager.color(1F, 1F, 1F, alpha);

            GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
            GlStateManager.scale(1.0F, -1.0F, -1.0F);

            int rotation = 0;


            GlStateManager.translate(0.5F, 0.5F, 0.5F);
            GlStateManager.rotate((float) rotation, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            float f = (float) te.tickCount + partialTicks;

            ((ModelSandStarPortal) modelSandStarPortal).setRotateAngle(((ModelSandStarPortal) modelSandStarPortal).portal, 0, f * 0.05F, 0);

            GlStateManager.translate(0.5F, -0.5F, 0.5F);

            ((ModelSandStarPortal) modelSandStarPortal).renderAll();

            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        }
    }

    public static class SandStarTEISR extends TileEntityItemStackRenderer {

        private static ModelSandStarPortal model = new ModelSandStarPortal();

        public void renderByItem(ItemStack stack, float partialTicks) {
            GlStateManager.enableDepth();
            GlStateManager.depthFunc(515);
            GlStateManager.depthMask(true);

            ItemStack material = ItemStack.EMPTY;

            boolean useColoredTexture = material.isEmpty();

            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_NORMAL);

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            GlStateManager.color(1F, 1F, 1F, 1F);

            GlStateManager.translate(0F, 2F, 1F);
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            GlStateManager.translate(0.5F, 0.5F, 0.5F);
            model.renderAll();

            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }

    }

}