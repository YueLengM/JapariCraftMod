package com.japaricraft.japaricraftmod.model;

import com.japaricraft.japaricraftmod.mob.EntityTutinoko;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelTutinoko extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer handR;
    public ModelRenderer handL;
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer headhood;
    public ModelRenderer headhood2;
    public ModelRenderer headhood3;
    public ModelRenderer headhood4;
    public ModelRenderer headhood5;

    public ModelTutinoko(float scale) {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.legL = new ModelRenderer(this, 24, 16);
        this.legL.setRotationPoint(1.2F, 8.0F, 0.0F);
        this.legL.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, scale);
        this.handR = new ModelRenderer(this, 0, 19);
        this.handR.setRotationPoint(-3.5F, 0.0F, scale);
        this.handR.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, scale);
        this.setRotateAngle(handR, 0.0F, 0.0F, 0.091106186954104F);
        this.body = new ModelRenderer(this, 32, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-2.5F, 0.0F, -2.5F, 5, 8, 5, scale);
        this.headhood4 = new ModelRenderer(this, 48, 13);
        this.headhood4.setRotationPoint(-4.0F, -9.3F, 4.1F);
        this.headhood4.addBox(0.0F, 0.0F, 0.0F, 8, 8, 0, scale);
        this.headhood5 = new ModelRenderer(this, 40, 22);
        this.headhood5.setRotationPoint(-4.0F, -9.3F, -4.0F);
        this.headhood5.addBox(0.0F, 0.0F, 0.0F, 8, 0, 8, scale);
        this.headhood2 = new ModelRenderer(this, 32, 22);
        this.headhood2.setRotationPoint(-1.0F, -7.3F, -4.1F);
        this.headhood2.addBox(-3.0F, -2.0F, 0.0F, 8, 2, 0, scale);
        this.legR = new ModelRenderer(this, 16, 16);
        this.legR.setRotationPoint(-1.2F, 8.0F, 0.0F);
        this.legR.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, scale);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.head.addBox(-4.0F, -9.0F, -4.0F, 8, 8, 8, scale);
        this.handL = new ModelRenderer(this, 8, 19);
        this.handL.setRotationPoint(3.5F, 0.0F, 0.0F);
        this.handL.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, scale);
        this.setRotateAngle(handL, 0.0F, 0.0F, -0.091106186954104F);
        this.headhood3 = new ModelRenderer(this, 32, 16);
        this.headhood3.setRotationPoint(-4.1F, -9.4F, -4.0F);
        this.headhood3.addBox(0.0F, 0.0F, 0.0F, 0, 8, 8, scale);
        this.headhood = new ModelRenderer(this, 32, 5);
        this.headhood.mirror = true;
        this.headhood.setRotationPoint(4.1F, -7.3F, -4.0F);
        this.headhood.addBox(0.0F, -2.0F, 0.0F, 0, 8, 8, scale);
        this.body.addChild(this.legL);
        this.body.addChild(this.handR);
        this.head.addChild(this.headhood4);
        this.head.addChild(this.headhood5);
        this.head.addChild(this.headhood2);
        this.body.addChild(this.legR);
        this.body.addChild(this.head);
        this.body.addChild(this.handL);
        this.head.addChild(this.headhood3);
        this.head.addChild(this.headhood);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.body.render(scale);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (!(entityIn instanceof EntityTutinoko)) {
            return;
        }

        boolean flag = ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;


        this.body.rotateAngleY = 0.0F;
        float f = 1.0F;

        if (flag) {
            f = (float) (entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.handR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.handL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.handR.rotateAngleZ = 0.0F;
        this.handL.rotateAngleZ = 0.0F;
        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        this.legR.rotateAngleY = 0.0F;
        this.legL.rotateAngleY = 0.0F;
        this.legR.rotateAngleZ = 0.0F;
        this.legL.rotateAngleZ = 0.0F;

        if (this.isRiding) {
            this.legR.rotateAngleX = -1.4137167F;
            this.legR.rotateAngleY = ((float) Math.PI / 10F);
            this.legR.rotateAngleZ = 0.07853982F;
            this.legL.rotateAngleX = -1.4137167F;
            this.legL.rotateAngleY = -((float) Math.PI / 10F);
            this.legL.rotateAngleZ = -0.07853982F;
        } else {
            this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
            this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
            this.legR.rotateAngleY = 0.0F;
            this.legL.rotateAngleY = 0.0F;
            this.legR.rotateAngleZ = 0.0F;
            this.legL.rotateAngleZ = 0.0F;
        }

        this.handR.rotateAngleY = 0.0F;
        this.handR.rotateAngleZ = 0.0F;


        this.handR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.handL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

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
