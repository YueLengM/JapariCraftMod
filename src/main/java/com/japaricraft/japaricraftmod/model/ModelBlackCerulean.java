package com.japaricraft.japaricraftmod.model;

import com.japaricraft.japaricraftmod.mob.BlackCerulean;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBlackCerulean extends ModelBase {
    public ModelRenderer mainBody;
    public ModelRenderer sidebody;
    public ModelRenderer mainLegR1;
    public ModelRenderer mainLegL1;
    public ModelRenderer sideLegL;
    public ModelRenderer sideLegR;
    public ModelRenderer mainLegR2;
    public ModelRenderer mainLegL2;

    public ModelBlackCerulean() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.mainLegL2 = new ModelRenderer(this, 0, 0);
        this.mainLegL2.setRotationPoint(0.0F, 8.1F, 0.0F);
        this.mainLegL2.addBox(-1.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.setRotateAngle(mainLegL2, 1.3658946726107624F, 0.0F, 0.0F);
        this.sideLegR = new ModelRenderer(this, 0, 0);
        this.sideLegR.setRotationPoint(-5.0F, -4.0F, 31.0F);
        this.sideLegR.addBox(-2.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(sideLegR, 0.31869712141416456F, 0.0F, 0.0F);
        this.mainLegL1 = new ModelRenderer(this, 0, 0);
        this.mainLegL1.setRotationPoint(2.0F, -1.0F, -27.0F);
        this.mainLegL1.addBox(-1.0F, 0.0F, -2.0F, 3, 8, 3, 0.0F);
        this.setRotateAngle(mainLegL1, -0.5009094953223726F, 0.0F, 0.0F);
        this.mainLegR2 = new ModelRenderer(this, 0, 0);
        this.mainLegR2.setRotationPoint(0.0F, 8.1F, 0.0F);
        this.mainLegR2.addBox(-2.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.setRotateAngle(mainLegR2, 1.3658946726107624F, 0.0F, 0.0F);
        this.sidebody = new ModelRenderer(this, 43, 0);
        this.sidebody.setRotationPoint(0.0F, -5.0F, 0.0F);
        this.sidebody.addBox(-5.0F, 0.0F, -10.0F, 10, 8, 10, 0.0F);
        this.setRotateAngle(sidebody, -0.4553564018453205F, 0.0F, 0.0F);
        this.mainLegR1 = new ModelRenderer(this, 0, 0);
        this.mainLegR1.setRotationPoint(-2.0F, -1.0F, -27.0F);
        this.mainLegR1.addBox(-2.0F, 0.0F, -2.0F, 3, 8, 3, 0.0F);
        this.setRotateAngle(mainLegR1, -0.5009094953223726F, 0.0F, 0.0F);
        this.mainBody = new ModelRenderer(this, 0, 0);
        this.mainBody.setRotationPoint(0.0F, -3.0F, 49.0F);
        this.mainBody.addBox(-5.5F, -11.0F, -30.0F, 11, 11, 21, 0.0F);
        this.sideLegL = new ModelRenderer(this, 0, 0);
        this.sideLegL.setRotationPoint(2.0F, -4.0F, 31.0F);
        this.sideLegL.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(sideLegL, 0.31869712141416456F, 0.0F, 0.0F);
        this.mainLegL1.addChild(this.mainLegL2);
        this.mainBody.addChild(this.mainLegL1);
        this.mainLegR1.addChild(this.mainLegR2);
        this.mainBody.addChild(this.sidebody);
        this.mainBody.addChild(this.mainLegR1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.sideLegR.offsetX, this.sideLegR.offsetY, this.sideLegR.offsetZ);
        GlStateManager.translate(this.sideLegR.rotationPointX * f5, this.sideLegR.rotationPointY * f5, this.sideLegR.rotationPointZ * f5);
        GlStateManager.scale(3.0D, 3.0D, 3.0D);
        GlStateManager.translate(-this.sideLegR.offsetX, -this.sideLegR.offsetY, -this.sideLegR.offsetZ);
        GlStateManager.translate(-this.sideLegR.rotationPointX * f5, -this.sideLegR.rotationPointY * f5, -this.sideLegR.rotationPointZ * f5);
        this.sideLegR.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.mainBody.offsetX, this.mainBody.offsetY, this.mainBody.offsetZ);
        GlStateManager.translate(this.mainBody.rotationPointX * f5, this.mainBody.rotationPointY * f5, this.mainBody.rotationPointZ * f5);
        GlStateManager.scale(3.0D, 3.0D, 3.0D);
        GlStateManager.translate(-this.mainBody.offsetX, -this.mainBody.offsetY, -this.mainBody.offsetZ);
        GlStateManager.translate(-this.mainBody.rotationPointX * f5, -this.mainBody.rotationPointY * f5, -this.mainBody.rotationPointZ * f5);
        this.mainBody.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.sideLegL.offsetX, this.sideLegL.offsetY, this.sideLegL.offsetZ);
        GlStateManager.translate(this.sideLegL.rotationPointX * f5, this.sideLegL.rotationPointY * f5, this.sideLegL.rotationPointZ * f5);
        GlStateManager.scale(3.0D, 3.0D, 3.0D);
        GlStateManager.translate(-this.sideLegL.offsetX, -this.sideLegL.offsetY, -this.sideLegL.offsetZ);
        GlStateManager.translate(-this.sideLegL.rotationPointX * f5, -this.sideLegL.rotationPointY * f5, -this.sideLegL.rotationPointZ * f5);
        this.sideLegL.render(f5);
        GlStateManager.popMatrix();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        float f = ageInTicks - (float) entityIn.ticksExisted;
        float f1 = ((BlackCerulean) entityIn).getStandingAnimationScale(f);
        f1 = f1 * f1;
        this.mainBody.rotateAngleX = f1 * (float) Math.PI * -0.32F;
        this.mainLegL1.rotateAngleX -= f1 * (float) Math.PI * 0.45F;
        this.mainLegR1.rotateAngleX -= f1 * (float) Math.PI * 0.45F;

        this.mainLegL1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.mainLegR1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.sideLegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.sideLegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
