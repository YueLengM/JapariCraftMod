package baguchan.japaricraftmod.client.model;

import baguchan.japaricraftmod.mob.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class ModelScocel extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer leg5;
    public ModelRenderer leg6;
    public ModelRenderer tail1;
    public ModelRenderer tail2;
    public ModelRenderer spike;

    public ModelScocel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer(this, 30, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.0F, -1.5F, -3.0F, 4, 3, 3, 0.0F);
        this.spike = new ModelRenderer(this, 0, 0);
        this.spike.setRotationPoint(0.0F, -5.6F, 1.0F);
        this.spike.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 19);
        this.leg1.setRotationPoint(-3.0F, -1.0F, 2.0F);
        this.leg1.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(leg1, 0.0F, -0.31869712141416456F, -0.5009094953223726F);
        this.leg6 = new ModelRenderer(this, 0, 19);
        this.leg6.setRotationPoint(3.0F, -1.0F, 2.0F);
        this.leg6.addBox(0.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(leg6, 0.0F, 0.31869712141416456F, 0.5009094953223726F);
        this.leg2 = new ModelRenderer(this, 0, 19);
        this.leg2.setRotationPoint(-3.0F, -1.0F, 4.5F);
        this.leg2.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(leg2, 0.0F, 0.0F, -0.5009094953223726F);
        this.leg4 = new ModelRenderer(this, 0, 19);
        this.leg4.setRotationPoint(3.0F, -1.0F, 7.0F);
        this.leg4.addBox(0.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(leg4, 0.0F, -0.36425021489121656F, 0.5009094953223726F);
        this.leg3 = new ModelRenderer(this, 0, 19);
        this.leg3.setRotationPoint(-3.0F, -1.0F, 7.0F);
        this.leg3.addBox(-5.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(leg3, 0.0F, 0.36425021489121656F, -0.5009094953223726F);
        this.tail1 = new ModelRenderer(this, 0, 12);
        this.tail1.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tail1.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 5, 0.0F);
        this.setRotateAngle(tail1, 0.7285004297824331F, 0.0F, 0.0F);
        this.tail2 = new ModelRenderer(this, 14, 12);
        this.tail2.setRotationPoint(0.0F, 0.0F, 3.0F);
        this.tail2.addBox(-1.0F, -4.0F, 0.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(tail2, -0.31869712141416456F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 22.0F, -4.0F);
        this.body.addBox(-3.0F, -1.5F, 0.0F, 6, 3, 9, 0.0F);
        this.leg5 = new ModelRenderer(this, 0, 19);
        this.leg5.setRotationPoint(3.0F, -1.0F, 4.5F);
        this.leg5.addBox(0.0F, -1.0F, -1.0F, 5, 2, 2, 0.0F);
        this.setRotateAngle(leg5, 0.0F, 0.0F, 0.5009094953223726F);
        this.body.addChild(this.head);
        this.tail2.addChild(this.spike);
        this.body.addChild(this.leg1);
        this.body.addChild(this.leg6);
        this.body.addChild(this.leg2);
        this.body.addChild(this.leg4);
        this.body.addChild(this.leg3);
        this.body.addChild(this.tail1);
        this.tail1.addChild(this.tail2);
        this.body.addChild(this.leg5);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float scale) {
        this.body.render(scale);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float scaleFactor, Entity entityIn) {

        EntityScocel entityScocel = (EntityScocel) entityIn;
        boolean flag = ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
        this.head.rotateAngleY = netheadYaw * 0.017453292F;

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


        this.leg1.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount - 0.31869712141416456F;
        this.leg2.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.5F * limbSwingAmount;
        this.leg3.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount + 0.31869712141416456F;
        this.leg4.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount - 0.31869712141416456F;
        this.leg5.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.6F * limbSwingAmount;
        this.leg6.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount + 0.31869712141416456F;

        this.tail1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount + 0.7285004297824331F;
        this.tail2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount - 0.31869712141416456F;

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
