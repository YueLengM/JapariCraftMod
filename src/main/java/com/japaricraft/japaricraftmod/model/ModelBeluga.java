package com.japaricraft.japaricraftmod.model;

import com.japaricraft.japaricraftmod.mob.EntityBeluga;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelBeluga extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer tail_1;
    public ModelRenderer neck_a;
    public ModelRenderer asc_1;
    public ModelRenderer head;
    public ModelRenderer hand_l;
    public ModelRenderer hand_r;
    public ModelRenderer skirt_1;
    public ModelRenderer leg_l;
    public ModelRenderer leg_r;
    public ModelRenderer op_l;
    public ModelRenderer neck;
    public ModelRenderer hair_fh;
    public ModelRenderer hair_fh_2;
    public ModelRenderer hair_fh_4;
    public ModelRenderer hair_fh_5;
    public ModelRenderer main_hair_l;
    public ModelRenderer main_hair_back;
    public ModelRenderer main_hair_r;
    public ModelRenderer main_hair_l2;
    public ModelRenderer ear_l;
    public ModelRenderer main_hair_r2;
    public ModelRenderer ear_r;
    public ModelRenderer cl_l;
    public ModelRenderer cl_r;
    public ModelRenderer skirt_2;
    public ModelRenderer tail_2;
    public ModelRenderer tail_3;

    public ModelBeluga() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.body = new ModelRenderer(this, 108, 16);
        this.body.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.body.addBox(-2.5F, -8.0F, -2.5F, 5, 8, 5, 0.0F);
        this.cl_l = new ModelRenderer(this, 28, 0);
        this.cl_l.setRotationPoint(0.0F, 0.0F, -0.5F);
        this.cl_l.addBox(-1.5F, -1.0F, 0.0F, 3, 9, 3, 0.0F);
        this.setRotateAngle(cl_l, -0.020943951023931952F, 0.0F, 0.0F);
        this.tail_1 = new ModelRenderer(this, 57, 55);
        this.tail_1.setRotationPoint(0.0F, 11.5F, 1.1F);
        this.tail_1.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(tail_1, -0.7853981633974483F, 0.0F, 0.0F);
        this.hair_fh_4 = new ModelRenderer(this, 0, 50);
        this.hair_fh_4.setRotationPoint(-3.0F, -5.9F, -3.0F);
        this.hair_fh_4.addBox(-1.0F, -0.5F, -2.0F, 2, 3, 1, 0.0F);
        this.setRotateAngle(hair_fh_4, 0.0F, 0.0F, 0.08726646259971647F);
        this.cl_r = new ModelRenderer(this, 40, 0);
        this.cl_r.setRotationPoint(0.0F, 0.0F, 0.5F);
        this.cl_r.addBox(-1.5F, -1.0F, -3.0F, 3, 9, 3, 0.0F);
        this.setRotateAngle(cl_r, -0.020943951023931952F, 0.0F, 0.0F);
        this.ear_l = new ModelRenderer(this, 58, 9);
        this.ear_l.setRotationPoint(0.5F, -3.2F, -0.5F);
        this.ear_l.addBox(-1.5F, 0.0F, -0.5F, 3, 5, 1, 0.0F);
        this.setRotateAngle(ear_l, -0.3141592653589793F, 0.0F, 0.0F);
        this.main_hair_l2 = new ModelRenderer(this, 0, 0);
        this.main_hair_l2.setRotationPoint(-4.0F, 1.0F, -1.0F);
        this.main_hair_l2.addBox(0.0F, 0.0F, 0.0F, 9, 4, 2, 0.0F);
        this.setRotateAngle(main_hair_l2, 0.4363323129985824F, 0.0F, 0.0F);
        this.asc_1 = new ModelRenderer(this, 68, 24);
        this.asc_1.setRotationPoint(0.0F, 1.5F, 0.2F);
        this.asc_1.addBox(-2.5F, 1.0F, -2.5F, 5, 2, 0, 0.0F);
        this.setRotateAngle(asc_1, -0.20943951023931953F, 0.0F, 0.0F);
        this.hair_fh_2 = new ModelRenderer(this, 0, 58);
        this.hair_fh_2.setRotationPoint(0.0F, -5.0F, -4.0F);
        this.hair_fh_2.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 1, 0.0F);
        this.leg_r = new ModelRenderer(this, 66, 0);
        this.leg_r.setRotationPoint(-1.5F, 2.0F, 0.0F);
        this.leg_r.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.main_hair_l = new ModelRenderer(this, 28, 56);
        this.main_hair_l.setRotationPoint(-4.0F, -3.95F, 0.0F);
        this.main_hair_l.addBox(-4.0F, -4.0F, -1.0F, 9, 5, 2, 0.0F);
        this.setRotateAngle(main_hair_l, 0.0F, 1.5707963267948966F, 0.17453292519943295F);
        this.hand_l = new ModelRenderer(this, 28, 20);
        this.hand_l.setRotationPoint(4.0F, -7.4F, -1.0F);
        this.hand_l.addBox(-1.0F, -0.5F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(hand_l, 0.0F, 0.0F, -0.08726646259971647F);
        this.neck_a = new ModelRenderer(this, 75, 24);
        this.neck_a.setRotationPoint(0.1F, -1.0F, 0.0F);
        this.neck_a.addBox(-2.5F, 1.0F, -2.5F, 5, 2, 5, 0.0F);
        this.skirt_1 = new ModelRenderer(this, 0, 26);
        this.skirt_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.skirt_1.addBox(-3.0F, 0.0F, -3.0F, 6, 1, 6, 0.0F);
        this.tail_3 = new ModelRenderer(this, 57, 49);
        this.tail_3.setRotationPoint(0.4F, -0.1F, 0.4F);
        this.tail_3.addBox(-3.5F, 0.0F, 0.0F, 6, 1, 4, 0.0F);
        this.setRotateAngle(tail_3, 0.6421066318087139F, 0.0F, 0.0F);
        this.main_hair_r = new ModelRenderer(this, 106, 57);
        this.main_hair_r.setRotationPoint(4.0F, -3.95F, -1.0F);
        this.main_hair_r.addBox(-4.0F, -4.0F, -1.0F, 9, 5, 2, 0.0F);
        this.setRotateAngle(main_hair_r, 0.0F, -1.5707963267948966F, -0.17453292519943295F);
        this.head = new ModelRenderer(this, 96, 39);
        this.head.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.hair_fh = new ModelRenderer(this, 0, 61);
        this.hair_fh.setRotationPoint(0.0F, -7.0F, -4.0F);
        this.hair_fh.addBox(-4.0F, -1.0F, -1.0F, 8, 2, 1, 0.0F);
        this.ear_r = new ModelRenderer(this, 67, 9);
        this.ear_r.setRotationPoint(0.5F, -3.2F, -0.5F);
        this.ear_r.addBox(-1.5F, 0.0F, -0.5F, 3, 5, 1, 0.0F);
        this.setRotateAngle(ear_r, -0.3141592653589793F, 0.0F, 0.0F);
        this.op_l = new ModelRenderer(this, 60, 33);
        this.op_l.setRotationPoint(0.0F, -8.0F, 1.2F);
        this.op_l.addBox(-2.5F, -1.0F, -5.0F, 5, 3, 3, 0.0F);
        this.setRotateAngle(op_l, 0.5410520681182421F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 96, 5);
        this.neck.setRotationPoint(-0.09F, 0.0F, 0.0F);
        this.neck.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.hair_fh_5 = new ModelRenderer(this, 0, 45);
        this.hair_fh_5.setRotationPoint(3.0F, -5.9F, -3.0F);
        this.hair_fh_5.addBox(-1.0F, -0.5F, -2.0F, 2, 3, 1, 0.0F);
        this.setRotateAngle(hair_fh_5, 0.0F, 0.0F, -0.08726646259971647F);
        this.main_hair_back = new ModelRenderer(this, 28, 46);
        this.main_hair_back.setRotationPoint(0.0F, -4.0F, 4.0F);
        this.main_hair_back.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 1, 0.0F);
        this.setRotateAngle(main_hair_back, 0.08726646259971647F, 0.0F, 0.0F);
        this.main_hair_r2 = new ModelRenderer(this, 28, 33);
        this.main_hair_r2.setRotationPoint(-4.0F, 1.0F, -1.0F);
        this.main_hair_r2.addBox(0.0F, 0.0F, 0.0F, 9, 4, 2, 0.0F);
        this.setRotateAngle(main_hair_r2, 0.4363323129985824F, 0.0F, 0.0F);
        this.tail_2 = new ModelRenderer(this, 72, 55);
        this.tail_2.setRotationPoint(0.0F, -2.1F, 4.62F);
        this.tail_2.addBox(-1.5F, -1.0F, -3.0F, 3, 2, 4, 0.0F);
        this.setRotateAngle(tail_2, 1.0471975511965976F, 0.0F, 0.0F);
        this.hand_r = new ModelRenderer(this, 38, 20);
        this.hand_r.setRotationPoint(-4.0F, -7.5F, 1.0F);
        this.hand_r.addBox(-1.0F, -0.5F, -2.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(hand_r, 0.0F, 0.0F, 0.08726646259971647F);
        this.leg_l = new ModelRenderer(this, 57, 0);
        this.leg_l.setRotationPoint(1.5F, 2.0F, 0.0F);
        this.leg_l.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.skirt_2 = new ModelRenderer(this, 0, 13);
        this.skirt_2.setRotationPoint(-0.5F, 1.0F, -0.5F);
        this.skirt_2.addBox(-3.0F, 0.0F, -3.0F, 7, 1, 7, 0.0F);
        this.hand_l.addChild(this.cl_l);
        this.head.addChild(this.hair_fh_4);
        this.hand_r.addChild(this.cl_r);
        this.main_hair_l.addChild(this.ear_l);
        this.main_hair_l.addChild(this.main_hair_l2);
        this.head.addChild(this.hair_fh_2);
        this.body.addChild(this.leg_r);
        this.head.addChild(this.main_hair_l);
        this.body.addChild(this.hand_l);
        this.body.addChild(this.skirt_1);
        this.tail_2.addChild(this.tail_3);
        this.head.addChild(this.main_hair_r);
        this.body.addChild(this.head);
        this.head.addChild(this.hair_fh);
        this.main_hair_r.addChild(this.ear_r);
        this.body.addChild(this.op_l);
        this.head.addChild(this.neck);
        this.head.addChild(this.hair_fh_5);
        this.head.addChild(this.main_hair_back);
        this.main_hair_r.addChild(this.main_hair_r2);
        this.tail_1.addChild(this.tail_2);
        this.body.addChild(this.hand_r);
        this.body.addChild(this.leg_l);
        this.skirt_1.addChild(this.skirt_2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
        this.tail_1.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.asc_1.offsetX, this.asc_1.offsetY, this.asc_1.offsetZ);
        GlStateManager.translate(this.asc_1.rotationPointX * f5, this.asc_1.rotationPointY * f5, this.asc_1.rotationPointZ * f5);
        GlStateManager.scale(1.1D, 1.1D, 1.1D);
        GlStateManager.translate(-this.asc_1.offsetX, -this.asc_1.offsetY, -this.asc_1.offsetZ);
        GlStateManager.translate(-this.asc_1.rotationPointX * f5, -this.asc_1.rotationPointY * f5, -this.asc_1.rotationPointZ * f5);
        this.asc_1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.neck_a.offsetX, this.neck_a.offsetY, this.neck_a.offsetZ);
        GlStateManager.translate(this.neck_a.rotationPointX * f5, this.neck_a.rotationPointY * f5, this.neck_a.rotationPointZ * f5);
        GlStateManager.scale(1.1D, 1.1D, 1.1D);
        GlStateManager.translate(-this.neck_a.offsetX, -this.neck_a.offsetY, -this.neck_a.offsetZ);
        GlStateManager.translate(-this.neck_a.rotationPointX * f5, -this.neck_a.rotationPointY * f5, -this.neck_a.rotationPointZ * f5);
        this.neck_a.render(f5);
        GlStateManager.popMatrix();
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (!(entityIn instanceof EntityBeluga)) {
            return;
        }

        EntityBeluga entityBeluga = (EntityBeluga) entityIn;
        boolean flag = ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;

        if (flag) {
            this.head.rotateAngleX = -((float) Math.PI / 4F);
        } else {
            this.head.rotateAngleX = headPitch * 0.017453292F;
        }


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


        this.hand_r.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.hand_l.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.hand_r.rotateAngleZ = 0.0F;
        this.hand_l.rotateAngleZ = 0.0F;
        this.hand_r.rotateAngleY = 0.0F;
        this.hand_l.rotateAngleY = 0.0F;

        this.leg_r.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leg_l.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        this.leg_r.rotateAngleY = 0.0F;
        this.leg_l.rotateAngleY = 0.0F;
        this.leg_r.rotateAngleZ = 0.0F;
        this.leg_l.rotateAngleZ = 0.0F;

        if (entityBeluga.isSitting() || this.isRiding) {
            this.leg_r.rotateAngleX = -1.4137167F;
            this.leg_r.rotateAngleY = ((float) Math.PI / 10F);
            this.leg_r.rotateAngleZ = 0.07853982F;
            this.leg_l.rotateAngleX = -1.4137167F;
            this.leg_l.rotateAngleY = -((float) Math.PI / 10F);
            this.leg_l.rotateAngleZ = -0.07853982F;
            GL11.glTranslatef(0F, 0.2F, 0F);
        }

        if (this.swingProgress > 0.0F) {
            EnumHandSide enumhandside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
            float f1 = this.swingProgress;
            this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT) {
                this.body.rotateAngleY *= -1.0F;
            }
            this.hand_r.rotateAngleY += this.body.rotateAngleY;
            this.hand_l.rotateAngleY += this.body.rotateAngleY;
            float f2 = MathHelper.sin(f1 * (float) Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
            modelrenderer.rotateAngleY += this.body.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
        }

        this.hand_r.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.hand_l.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.hand_r.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.hand_l.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        this.tail_1.rotateAngleX = MathHelper.cos(limbSwing * 0.6362F) * 1.0F * limbSwingAmount + -0.78F;
        this.tail_2.rotateAngleX = MathHelper.cos(limbSwing * 0.582F) * 1.0F * limbSwingAmount + 1.0F;

        GL11.glTranslatef(0F, 0.4F, 0F);
    }

    public void postRenderArm(float scale, EnumHandSide side) {
        this.getArmForSide(side).postRender(scale);
    }

    protected ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.hand_l : this.hand_r;
    }

    protected EnumHandSide getMainHand(Entity entityIn) {
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase) entityIn;
            EnumHandSide enumhandside = entitylivingbase.getPrimaryHand();
            return entitylivingbase.swingingHand == EnumHand.MAIN_HAND ? enumhandside : enumhandside.opposite();
        } else {
            return EnumHandSide.RIGHT;
        }
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
