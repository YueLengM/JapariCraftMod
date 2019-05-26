package baguchan.japaricraftmod.client.model;

import baguchan.japaricraftmod.mob.EntityTwilightKobold;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * TwilightKobolt - bagu
 * Created using Tabula 7.0.0
 */
public class ModelTwilightKobold extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer breastR;
    public ModelRenderer breastL;
    public ModelRenderer handL;
    public ModelRenderer handR;
    public ModelRenderer head;
    public ModelRenderer hip;
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer bihind;

    public ModelTwilightKobold() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new ModelRenderer(this, 12, 8);
        this.head.setRotationPoint(0.0F, -6.0F, 0.0F);
        this.head.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6, 0.0F);
        this.legL = new ModelRenderer(this, 0, 16);
        this.legL.setRotationPoint(1.4F, 3.0F, 0.0F);
        this.legL.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.handL = new ModelRenderer(this, 0, 31);
        this.handL.setRotationPoint(3.0F, -5.8F, 0.0F);
        this.handL.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
        this.setRotateAngle(handL, 0.0F, 0.0F, -0.136659280431156F);
        this.bihind = new ModelRenderer(this, 16, 0);
        this.bihind.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bihind.addBox(-3.0F, 0.0F, -2.5F, 6, 4, 4, 0.0F);
        this.legR = new ModelRenderer(this, 0, 16);
        this.legR.setRotationPoint(-1.4F, 3.0F, 0.0F);
        this.legR.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.breastR = new ModelRenderer(this, 48, 4);
        this.breastR.setRotationPoint(1.0F, -3.6F, -1.6F);
        this.breastR.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(breastR, 0.8196066167365371F, 0.0F, 0.0F);
        this.earL = new ModelRenderer(this, 25, 15);
        this.earL.setRotationPoint(2.0F, -5.0F, 0.0F);
        this.earL.addBox(0.0F, -1.5F, -1.0F, 3, 3, 2, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.body.addBox(-2.5F, -6.0F, -1.5F, 5, 6, 3, 0.0F);
        this.breastL = new ModelRenderer(this, 48, 0);
        this.breastL.setRotationPoint(-1.0F, -3.6F, -1.6F);
        this.breastL.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(breastL, 0.8196066167365371F, 0.0F, 0.0F);
        this.handR = new ModelRenderer(this, 0, 31);
        this.handR.setRotationPoint(-3.0F, -5.8F, 0.0F);
        this.handR.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
        this.setRotateAngle(handR, 0.0F, 0.0F, 0.136659280431156F);
        this.earR = new ModelRenderer(this, 25, 15);
        this.earR.setRotationPoint(-2.0F, -5.0F, 0.0F);
        this.earR.addBox(-3.0F, -1.5F, -1.0F, 3, 3, 2, 0.0F);
        this.hip = new ModelRenderer(this, 36, 0);
        this.hip.setRotationPoint(0.0F, 2.0F, 0.6F);
        this.hip.addBox(-2.0F, -3.0F, -2.0F, 4, 3, 2, 0.0F);
        this.body.addChild(this.head);
        this.body.addChild(this.legL);
        this.body.addChild(this.handL);
        this.hip.addChild(this.bihind);
        this.body.addChild(this.legR);
        this.body.addChild(this.breastR);
        this.head.addChild(this.earL);
        this.body.addChild(this.breastL);
        this.body.addChild(this.handR);
        this.head.addChild(this.earR);
        this.body.addChild(this.hip);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.body.render(scale);
    }

    //下は特殊なモデルを動かすのに必須
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (!(entityIn instanceof EntityTwilightKobold)) {
            return;
        }

        EntityTwilightKobold entityKobold = (EntityTwilightKobold) entityIn;
        boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;


        this.body.rotateAngleY = 0.0F;
        this.body.rotateAngleX = 0.0F;
        float f = 1.0F;

        if (flag) {
            f = (float) (entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.handR.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f);
        this.handL.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f);
        this.handR.rotateAngleZ = 0.106659280431156F;
        this.handL.rotateAngleZ = -0.106659280431156F;
        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        this.legR.rotateAngleY = 0.0F;
        this.legL.rotateAngleY = 0.0F;
        this.legR.rotateAngleZ = 0.0F;
        this.legL.rotateAngleZ = 0.0F;

        if (entityKobold.isScared()) {
            this.handR.rotateAngleX = -1.95F;
            this.handL.rotateAngleX = -1.95F;
            this.handR.rotateAngleZ = -0.8F;
            this.handL.rotateAngleZ = 0.8F;
        }

        if (flag) {
            this.head.rotateAngleX = -((float) Math.PI / 4F);
        } else {
            this.head.rotateAngleX = headPitch * 0.017453292F;
        }

        if (entityKobold.isSitting() || this.isRiding) {
            this.legR.rotateAngleX = -1.4137167F;
            this.legR.rotateAngleY = ((float) Math.PI / 10F);
            this.legR.rotateAngleZ = 0.05853982F;
            this.legL.rotateAngleX = -1.4137167F;
            this.legL.rotateAngleY = -((float) Math.PI / 10F);
            this.legL.rotateAngleZ = -0.05853982F;

            this.handR.rotateAngleX = -0.296659280431156F;
            this.handL.rotateAngleX = -0.296659280431156F;
            this.handR.rotateAngleZ = -0.186659280431156F;
            this.handL.rotateAngleZ = 0.186659280431156F;
            GL11.glTranslatef(0F, 0.5F, 0F);
        }

        this.handR.rotateAngleY = 0.0F;
        this.handL.rotateAngleY = 0.0F;

        if (entityKobold.getEatingTick() > 1) {
            this.handR.rotateAngleZ = -0.6F + MathHelper.cos(ageInTicks * 0.5F) * 0.6F;
            this.handR.rotateAngleX = -0.9F;
        }


        if (this.swingProgress > 0.0F) {
            EnumHandSide enumhandside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
            float f1 = this.swingProgress;
            this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT) {
                this.body.rotateAngleY *= -1.0F;
            }
            this.handR.rotateAngleY += this.body.rotateAngleY;
            this.handL.rotateAngleY += this.body.rotateAngleY;
            float f2 = MathHelper.sin(f1 * (float) Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
            modelrenderer.rotateAngleY += this.body.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
        }

        if (entityKobold.isSwingingArms()) {
            float f2 = MathHelper.sin(this.swingProgress * (float) Math.PI);
            float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
            this.handR.rotateAngleZ = 0.0F;
            this.handL.rotateAngleZ = 0.0F;
            this.handR.rotateAngleY = -(0.1F - f2 * 0.6F);
            this.handL.rotateAngleY = 0.1F - f2 * 0.6F;
            this.handR.rotateAngleX = (-(float) Math.PI / 2F);
            this.handL.rotateAngleX = (-(float) Math.PI / 2F);
            this.handR.rotateAngleX -= f2 * 1.2F - f1 * 0.4F;
            this.handL.rotateAngleX -= f2 * 1.2F - f1 * 0.4F;
            this.handR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.handL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.handR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
            this.handL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        }

        this.handR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.handL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

    }

    public void postRenderArm(float scale, EnumHandSide side) {
        this.getArmForSide(side).postRender(scale);
    }

    public ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.handL : this.handR;
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
