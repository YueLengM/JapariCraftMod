package baguchan.japaricraftmod.client.render.layer;

import baguchan.japaricraftmod.client.render.WhiteOwlEntityRender;
import baguchan.japaricraftmod.mob.EntityWhiteOwl;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LayerWhiteOwlBook implements LayerRenderer<EntityWhiteOwl> {
    private static final ResourceLocation TEXTURE_BOOK = new ResourceLocation("textures/entity/enchanting_table_book.png");
    private final ModelBook modelBook = new ModelBook();

    protected final WhiteOwlEntityRender livingEntityRenderer;

    public LayerWhiteOwlBook(WhiteOwlEntityRender brownOwlEntityRender) {
        livingEntityRenderer = brownOwlEntityRender;
    }

    public void doRenderLayer(EntityWhiteOwl entityWhiteOwl, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack = entityWhiteOwl.getHeldItemOffhand();

        if (itemstack.getItem() == Items.BOOK && entityWhiteOwl.isReading()) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0.4F, -0.4F);


            GlStateManager.rotate(90F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            this.livingEntityRenderer.bindTexture(TEXTURE_BOOK);

            float f5 = 1.0F;
            GlStateManager.enableCull();
            this.modelBook.render(null, 0.0F, 0.0F, 0.0F, f5, 0.0F, 0.0625F);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
