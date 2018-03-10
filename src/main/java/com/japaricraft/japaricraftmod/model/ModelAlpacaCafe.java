package com.japaricraft.japaricraftmod.model;

import com.japaricraft.japaricraftmod.mob.EntityAlpacaCafe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelAlpacaCafe extends ModelAlpaca {
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (!(entityIn instanceof EntityAlpacaCafe)) {
            return;
        }

        EntityAlpacaCafe entityarai = (EntityAlpacaCafe) entityIn;
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
        this.leg_r.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leg_l.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        this.leg_r.rotateAngleY = 0.0F;
        this.leg_l.rotateAngleY = 0.0F;
        this.leg_r.rotateAngleZ = 0.0F;
        this.leg_l.rotateAngleZ = 0.0F;

        if (this.isRiding) {
            this.leg_r.rotateAngleX = -1.4137167F;
            this.leg_r.rotateAngleY = ((float) Math.PI / 10F);
            this.leg_r.rotateAngleZ = 0.07853982F;
            this.leg_l.rotateAngleX = -1.4137167F;
            this.leg_l.rotateAngleY = -((float) Math.PI / 10F);
            this.leg_l.rotateAngleZ = -0.07853982F;
        } else {
            this.leg_r.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
            this.leg_l.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
            this.leg_r.rotateAngleY = 0.0F;
            this.leg_l.rotateAngleY = 0.0F;
            this.leg_r.rotateAngleZ = 0.0F;
            this.leg_l.rotateAngleZ = 0.0F;
        }

        this.hand_r.rotateAngleY = 0.0F;
        this.hand_r.rotateAngleZ = 0.0F;


        this.hand_r.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.hand_l.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.hand_r.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.hand_l.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        GL11.glTranslatef(0F, 0.4F, 0F);
    }
}
