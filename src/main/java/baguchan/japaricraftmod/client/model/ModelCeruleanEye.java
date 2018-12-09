package baguchan.japaricraftmod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelCeruleanEye extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer wingR;
    public ModelRenderer wingL;

    public ModelCeruleanEye() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.wingL = new ModelRenderer(this, 28, 8);
        this.wingL.setRotationPoint(-3.0F, -2.0F, -0.5F);
        this.wingL.addBox(-6.5F, 0.0F, -2.5F, 7, 1, 5, 0.0F);
        this.wingR = new ModelRenderer(this, 28, 0);
        this.wingR.setRotationPoint(3.0F, -2.0F, 0.5F);
        this.wingR.addBox(-0.5F, 0.0F, -2.5F, 7, 1, 5, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7, 0.0F);
        this.body.addChild(this.wingL);
        this.body.addChild(this.wingR);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.body.offsetX, this.body.offsetY, this.body.offsetZ);
        GlStateManager.translate(this.body.rotationPointX * scale, this.body.rotationPointY * scale, this.body.rotationPointZ * scale);
        GlStateManager.scale(1.4D, 1.4D, 1.4D);
        GlStateManager.translate(-this.body.offsetX, -this.body.offsetY, -this.body.offsetZ);
        GlStateManager.translate(-this.body.rotationPointX * scale, -this.body.rotationPointY * scale, -this.body.rotationPointZ * scale);
        this.body.render(scale);
        GlStateManager.popMatrix();
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.wingR.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.6662F) * 0.8F;
        this.wingL.rotateAngleZ = MathHelper.cos(ageInTicks * 0.6662F) * 0.8F;
        GL11.glTranslatef(0F, 0.5F, 0F);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
