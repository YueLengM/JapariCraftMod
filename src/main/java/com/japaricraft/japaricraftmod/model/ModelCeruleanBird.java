package com.japaricraft.japaricraftmod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCeruleanBird extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer WingR;
    public ModelRenderer WingL;

    public ModelCeruleanBird() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.WingR = new ModelRenderer(this, 0, 8);
        this.WingR.setRotationPoint(1.7F, 0.0F, -4.0F);
        this.WingR.addBox(0.0F, 0.0F, 0.0F, 0, 3, 5, 0.0F);
        this.legR = new ModelRenderer(this, 0, 9);
        this.legR.setRotationPoint(0.0F, 3.2F, -1.0F);
        this.legR.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.body.addBox(-2.4F, 0.0F, -4.0F, 4, 3, 6, 0.0F);
        this.head = new ModelRenderer(this, 20, 0);
        this.head.setRotationPoint(0.0F, 1.0F, -3.0F);
        this.head.addBox(-2.0F, -3.0F, -3.0F, 3, 3, 3, 0.0F);
        this.WingL = new ModelRenderer(this, 10, 8);
        this.WingL.setRotationPoint(-2.4F, 0.0F, -4.0F);
        this.WingL.addBox(0.0F, 0.0F, 0.0F, 0, 3, 5, 0.0F);
        this.legL = new ModelRenderer(this, 2, 9);
        this.legL.setRotationPoint(-2.0F, 3.1F, -1.0F);
        this.legL.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.body.addChild(this.WingR);
        this.body.addChild(this.legR);
        this.body.addChild(this.head);
        this.body.addChild(this.WingL);
        this.body.addChild(this.legL);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        WingR.rotateAngleZ = MathHelper.cos(f2 * 1.7F) * (float) Math.PI * 0.25F;
        WingL.rotateAngleZ = -WingR.rotateAngleZ;
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
