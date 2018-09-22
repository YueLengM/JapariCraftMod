package baguchan.japaricraftmod.model;

import baguchan.japaricraftmod.mob.EntitySandCat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelSandCat extends ModelBase {
    public ModelRenderer skirt_1;
    public ModelRenderer body;
    public ModelRenderer skirt_1Child;
    public ModelRenderer bodyChild1;
    public ModelRenderer headMain;
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer handL;
    public ModelRenderer tail;
    public ModelRenderer ribbon;
    public ModelRenderer handR;
    public ModelRenderer hair1;
    public ModelRenderer hair2;
    public ModelRenderer hair3;
    public ModelRenderer hair4;
    public ModelRenderer earL;
    public ModelRenderer hair5;
    public ModelRenderer hair6;
    public ModelRenderer hair7;
    public ModelRenderer hair8;
    public ModelRenderer earR;
    public ModelRenderer unknowpart;
    public ModelRenderer hair9;
    public ModelRenderer hair11;
    public ModelRenderer neck;
    public ModelRenderer ribbonBackL;
    public ModelRenderer ribbonBackR;
    public ModelRenderer tailChild;
    public ModelRenderer tailChild2;
    public ModelRenderer ribbonChild;
    public ModelRenderer ribbonChild2;

    public ModelSandCat() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.earR = new ModelRenderer(this, 0, 121);
        this.earR.setRotationPoint(-5.0F, -5.1F, 0.0F);
        this.earR.addBox(-4.0F, -5.0F, -1.0F, 5, 4, 1, 0.0F);
        this.setRotateAngle(earR, 0.0F, 0.0F, 0.8196066167365371F);
        this.handL = new ModelRenderer(this, 28, 20);
        this.handL.setRotationPoint(3.5F, -7.4F, -1.0F);
        this.handL.addBox(-1.0F, -0.5F, 0.0F, 2, 10, 2, 0.0F);
        this.hair6 = new ModelRenderer(this, 0, 61);
        this.hair6.setRotationPoint(0.0F, -7.0F, -4.0F);
        this.hair6.addBox(-4.0F, -1.0F, -1.0F, 8, 2, 1, 0.0F);
        this.hair4 = new ModelRenderer(this, 28, 46);
        this.hair4.setRotationPoint(0.0F, -4.0F, 4.0F);
        this.hair4.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 1, 0.0F);
        this.setRotateAngle(hair4, 0.08726646259971647F, 0.0F, 0.0F);
        this.ribbonChild = new ModelRenderer(this, 60, 19);
        this.ribbonChild.setRotationPoint(-0.5F, -2.0F, 0.0F);
        this.ribbonChild.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(ribbonChild, 0.0F, 0.0F, 1.5707963267948966F);
        this.ribbon = new ModelRenderer(this, 59, 15);
        this.ribbon.setRotationPoint(-0.5F, -7.0F, -2.3F);
        this.ribbon.addBox(-1.0F, -1.0F, -1.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(ribbon, 0.0F, 0.0F, 1.5707963267948966F);
        this.legR = new ModelRenderer(this, 66, 0);
        this.legR.setRotationPoint(-1.5F, 1.0F, 0.0F);
        this.legR.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.ribbonChild2 = new ModelRenderer(this, 65, 14);
        this.ribbonChild2.setRotationPoint(-0.5F, 1.0F, 0.0F);
        this.ribbonChild2.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(ribbonChild2, 0.0F, -0.010471975511965976F, 1.5707963267948966F);
        this.earL = new ModelRenderer(this, 0, 107);
        this.earL.setRotationPoint(5.0F, -5.1F, 0.0F);
        this.earL.addBox(-1.0F, -5.0F, -1.0F, 5, 4, 1, 0.0F);
        this.setRotateAngle(earL, 0.0F, 0.0F, -0.8196066167365371F);
        this.hair5 = new ModelRenderer(this, 0, 55);
        this.hair5.setRotationPoint(1.0F, -3.1F, -4.0F);
        this.hair5.addBox(-2.0F, -1.0F, -1.0F, 2, 1, 1, 0.0F);
        this.tailChild = new ModelRenderer(this, 217, 10);
        this.tailChild.setRotationPoint(0.0F, -0.5F, 2.7F);
        this.tailChild.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tailChild, -0.407185314490277F, 0.0F, 0.0F);
        this.hair3 = new ModelRenderer(this, 188, 0);
        this.hair3.mirror = true;
        this.hair3.setRotationPoint(3.9F, -7.58F, -3.8F);
        this.hair3.addBox(-1.5F, -0.4F, -1.2F, 2, 6, 2, 0.0F);
        this.setRotateAngle(hair3, 0.0F, 0.0F, -0.091106186954104F);
        this.headMain = new ModelRenderer(this, 96, 39);
        this.headMain.setRotationPoint(0.0F, -9.0F, 0.0F);
        this.headMain.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.hair7 = new ModelRenderer(this, 205, 45);
        this.hair7.setRotationPoint(3.0F, -5.9F, -3.0F);
        this.hair7.addBox(-1.0F, -0.5F, -2.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(hair7, 0.0F, 0.0F, -0.08726646259971647F);
        this.skirt_1Child = new ModelRenderer(this, 0, 90);
        this.skirt_1Child.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.skirt_1Child.addBox(-3.5F, 1.0F, -3.5F, 7, 2, 7, 0.0F);
        this.unknowpart = new ModelRenderer(this, 96, 5);
        this.unknowpart.setRotationPoint(-0.09F, 0.0F, 0.0F);
        this.unknowpart.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2, 0.0F);
        this.hair2 = new ModelRenderer(this, 28, 55);
        this.hair2.setRotationPoint(-4.0F, -3.95F, 0.0F);
        this.hair2.addBox(-4.0F, -4.0F, -1.0F, 8, 8, 1, 0.0F);
        this.setRotateAngle(hair2, 0.0F, 1.5707963267948966F, 0.091106186954104F);
        this.skirt_1 = new ModelRenderer(this, 0, 73);
        this.skirt_1.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.skirt_1.addBox(-3.0F, 0.0F, -3.0F, 6, 4, 6, 0.0F);
        this.ribbonBackL = new ModelRenderer(this, 0, 0);
        this.ribbonBackL.setRotationPoint(0.5F, 1.0F, 3.0F);
        this.ribbonBackL.addBox(-0.5F, -0.5F, 0.0F, 1, 4, 0, 0.0F);
        this.setRotateAngle(ribbonBackL, 0.0F, 0.0F, -0.3490658503988659F);
        this.tailChild2 = new ModelRenderer(this, 217, 22);
        this.tailChild2.setRotationPoint(0.0F, 0.3F, 3.3F);
        this.tailChild2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(tailChild2, -0.2617993877991494F, 0.0F, 0.0F);
        this.hair9 = new ModelRenderer(this, 205, 50);
        this.hair9.setRotationPoint(-2.0F, -5.9F, -3.0F);
        this.hair9.addBox(-1.0F, -0.5F, -2.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(hair9, 0.0F, 0.0F, 0.08726646259971647F);
        this.hair8 = new ModelRenderer(this, 0, 58);
        this.hair8.setRotationPoint(0.0F, -5.0F, -4.0F);
        this.hair8.addBox(-2.0F, -1.0F, -1.0F, 4, 2, 1, 0.0F);
        this.neck = new ModelRenderer(this, 74, 24);
        this.neck.setRotationPoint(0.1F, -1.0F, 0.0F);
        this.neck.addBox(-3.0F, 1.0F, -3.0F, 6, 1, 6, 0.0F);
        this.legL = new ModelRenderer(this, 57, 0);
        this.legL.setRotationPoint(1.5F, 1.0F, 0.0F);
        this.legL.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.hair11 = new ModelRenderer(this, 0, 34);
        this.hair11.setRotationPoint(4.0F, -3.95F, 0.0F);
        this.hair11.addBox(-4.0F, -4.0F, -1.0F, 8, 8, 1, 0.0F);
        this.setRotateAngle(hair11, 0.0F, -1.5707963267948966F, -0.091106186954104F);
        this.handR = new ModelRenderer(this, 38, 20);
        this.handR.setRotationPoint(-3.5F, -7.5F, -1.0F);
        this.handR.addBox(-1.0F, -0.5F, 0.0F, 2, 10, 2, 0.0F);
        this.tail = new ModelRenderer(this, 217, 0);
        this.tail.setRotationPoint(0.0F, 1.2F, 0.7F);
        this.tail.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tail, -0.30927234345339516F, 0.0F, 0.0F);
        this.bodyChild1 = new ModelRenderer(this, 60, 33);
        this.bodyChild1.setRotationPoint(0.0F, -8.0F, 1.2F);
        this.bodyChild1.addBox(-2.5F, -1.0F, -5.0F, 5, 3, 3, 0.0F);
        this.setRotateAngle(bodyChild1, 0.5410520681182421F, 0.0F, 0.0F);
        this.ribbonBackR = new ModelRenderer(this, 0, 0);
        this.ribbonBackR.setRotationPoint(-0.5F, 1.0F, 3.0F);
        this.ribbonBackR.addBox(-0.5F, -0.5F, 0.0F, 1, 4, 0, 0.0F);
        this.setRotateAngle(ribbonBackR, 0.0F, 0.0F, 0.3490658503988659F);
        this.body = new ModelRenderer(this, 108, 16);
        this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.body.addBox(-2.5F, -8.0F, -2.5F, 5, 8, 5, 0.0F);
        this.hair1 = new ModelRenderer(this, 203, 0);
        this.hair1.mirror = true;
        this.hair1.setRotationPoint(-2.9F, -7.41F, -3.8F);
        this.hair1.addBox(-1.5F, -0.4F, -1.2F, 2, 6, 2, 0.0F);
        this.setRotateAngle(hair1, 0.0F, 0.0F, 0.091106186954104F);
        this.headMain.addChild(this.earR);
        this.body.addChild(this.handL);
        this.headMain.addChild(this.hair6);
        this.headMain.addChild(this.hair4);
        this.ribbon.addChild(this.ribbonChild);
        this.body.addChild(this.ribbon);
        this.body.addChild(this.legR);
        this.ribbon.addChild(this.ribbonChild2);
        this.headMain.addChild(this.earL);
        this.headMain.addChild(this.hair5);
        this.tail.addChild(this.tailChild);
        this.headMain.addChild(this.hair3);
        this.body.addChild(this.headMain);
        this.headMain.addChild(this.hair7);
        this.skirt_1.addChild(this.skirt_1Child);
        this.headMain.addChild(this.unknowpart);
        this.headMain.addChild(this.hair2);
        this.neck.addChild(this.ribbonBackL);
        this.tailChild.addChild(this.tailChild2);
        this.headMain.addChild(this.hair9);
        this.headMain.addChild(this.hair8);
        this.unknowpart.addChild(this.neck);
        this.body.addChild(this.legL);
        this.headMain.addChild(this.hair11);
        this.body.addChild(this.handR);
        this.body.addChild(this.tail);
        this.body.addChild(this.bodyChild1);
        this.neck.addChild(this.ribbonBackR);
        this.headMain.addChild(this.hair1);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.skirt_1.render(scale);
        this.body.render(scale);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (!(entityIn instanceof EntitySandCat)) {
            return;
        }

        EntitySandCat entitySandcat = (EntitySandCat) entityIn;
        boolean flag = ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
        this.headMain.rotateAngleY = netHeadYaw * 0.017453292F;

        if (flag) {
            this.headMain.rotateAngleX = -((float) Math.PI / 4F);
        } else {
            this.headMain.rotateAngleX = headPitch * 0.017453292F;
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


        this.handR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.handL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.handR.rotateAngleZ = 0.0F;
        this.handL.rotateAngleZ = 0.0F;
        this.handR.rotateAngleY = 0.0F;
        this.handL.rotateAngleY = 0.0F;

        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
        this.legR.rotateAngleY = 0.0F;
        this.legL.rotateAngleY = 0.0F;
        this.legR.rotateAngleZ = 0.0F;
        this.legL.rotateAngleZ = 0.0F;

        if (entitySandcat.isSitting() || this.isRiding) {
            this.legR.rotateAngleX = -1.4137167F;
            this.legR.rotateAngleY = ((float) Math.PI / 10F);
            this.legR.rotateAngleZ = 0.07853982F;
            this.legL.rotateAngleX = -1.4137167F;
            this.legL.rotateAngleY = -((float) Math.PI / 10F);
            this.legL.rotateAngleZ = -0.07853982F;
            GL11.glTranslatef(0F, 0.4F, 0F);
        }

        if (entitySandcat.getEatingTick() > 1) {
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
            float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.headMain.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
            modelrenderer.rotateAngleY += this.body.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
        }

        this.handR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.handL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        this.tailChild.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
        this.tailChild2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;

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