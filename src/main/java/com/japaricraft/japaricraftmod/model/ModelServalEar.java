package com.japaricraft.japaricraftmod.model;

import net.minecraft.client.model.ModelRenderer;

public class ModelServalEar extends net.minecraft.client.model.ModelBiped {
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer earChildR;
    public ModelRenderer earChildL;

    public ModelServalEar() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.earR = new ModelRenderer(this, 0, 121);
        this.earR.setRotationPoint(-2.0F, -8.0F, 0.0F);
        this.earR.addBox(-2.0F, -5.0F, -1.0F, 4, 5, 2, 0.0F);
        this.setRotateAngle(earR, 0.0F, 0.3490658503988659F, 0.0F);
        this.earChildR = new ModelRenderer(this, 39, 0);
        this.earChildR.setRotationPoint(0.0F, -5.0F, 0.0F);
        this.earChildR.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
        this.earChildL = new ModelRenderer(this, 27, 0);
        this.earChildL.setRotationPoint(0.0F, -5.0F, 0.0F);
        this.earChildL.addBox(-1.0F, -2.0F, -1.0F, 2, 2, 1, 0.0F);
        this.earL = new ModelRenderer(this, 0, 107);
        this.earL.setRotationPoint(2.0F, -8.0F, -0.1F);
        this.earL.addBox(-2.0F, -5.0F, -1.0F, 4, 5, 2, 0.0F);
        this.setRotateAngle(earL, 0.0F, -0.3490658503988659F, 0.0F);
        this.earR.addChild(this.earChildR);
        this.earL.addChild(this.earChildL);
        this.bipedHead.addChild(earR);
        this.bipedHead.addChild(earL);
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