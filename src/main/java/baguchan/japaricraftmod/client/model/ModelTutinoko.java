package baguchan.japaricraftmod.client.model;

import baguchan.japaricraftmod.mob.EntityTutinoko;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelTutinoko extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer handR;
    public ModelRenderer handL;
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer tail;
    public ModelRenderer hood1;
    public ModelRenderer hood2;
    public ModelRenderer hood3;
    public ModelRenderer hood4;
    public ModelRenderer tail2;

    public ModelTutinoko() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.tail2 = new ModelRenderer(this, 16, 24);
        this.tail2.setRotationPoint(0.0F, 0.0F, 3.3F);
        this.tail2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tail2, -0.40980330836826856F, 0.0F, 0.0F);
        this.hood3 = new ModelRenderer(this, 77, 27);
        this.hood3.setRotationPoint(-4.5F, -2.0F, 3.5F);
        this.hood3.addBox(0.0F, 0.0F, 0.0F, 9, 6, 1, 0.0F);
        this.handR = new ModelRenderer(this, 0, 17);
        this.handR.setRotationPoint(-4.4F, 0.3F, 0.0F);
        this.handR.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.hood1 = new ModelRenderer(this, 56, 0);
        this.hood1.setRotationPoint(-4.5F, -5.0F, -4.5F);
        this.hood1.addBox(0.0F, 0.0F, 0.0F, 9, 3, 9, 0.0F);
        this.legR = new ModelRenderer(this, 8, 17);
        this.legR.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.legR.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
        this.hood2 = new ModelRenderer(this, 56, 12);
        this.hood2.setRotationPoint(-4.5F, -2.0F, -4.5F);
        this.hood2.addBox(0.0F, 0.0F, 0.0F, 1, 6, 9, 0.0F);
        this.handL = new ModelRenderer(this, 0, 17);
        this.handL.setRotationPoint(4.6F, 0.3F, 0.0F);
        this.handL.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.tail = new ModelRenderer(this, 16, 17);
        this.tail.setRotationPoint(0.0F, 9.0F, 2.0F);
        this.tail.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(tail, -0.40980330836826856F, 0.0F, 0.0F);
        this.hood4 = new ModelRenderer(this, 77, 12);
        this.hood4.setRotationPoint(3.5F, -2.0F, -4.5F);
        this.hood4.addBox(0.0F, 0.0F, 0.0F, 1, 6, 9, 0.0F);
        this.head = new ModelRenderer(this, 24, 0);
        this.head.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.body.addBox(-3.5F, 0.0F, -2.5F, 7, 12, 5, 0.0F);
        this.legL = new ModelRenderer(this, 8, 17);
        this.legL.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.legL.addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F);
        this.tail.addChild(this.tail2);
        this.head.addChild(this.hood3);
        this.body.addChild(this.handR);
        this.head.addChild(this.hood1);
        this.body.addChild(this.legR);
        this.head.addChild(this.hood2);
        this.body.addChild(this.handL);
        this.body.addChild(this.tail);
        this.head.addChild(this.hood4);
        this.body.addChild(this.head);
        this.body.addChild(this.legL);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.body.render(scale);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (!(entityIn instanceof EntityTutinoko)) {
            return;
        }

        EntityTutinoko entity = (EntityTutinoko) entityIn;
        boolean flag = ((EntityLivingBase) entityIn).getTicksElytraFlying() > 4;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;

        this.head.rotateAngleX = headPitch * 0.017453292F;

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

        float f4 = entity.getSittingAnimationScale(f);

        this.handR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.handL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.handR.rotateAngleZ = 0.0F;
        this.handL.rotateAngleZ = 0.0F;
        this.legR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f - 1.4137167F * f4;
        this.legL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f - 1.4137167F * f4;
        this.legR.rotateAngleY = ((float) Math.PI / 10F) * f4;
        this.legL.rotateAngleY = -((float) Math.PI / 10F) * f4;
        this.legR.rotateAngleZ = 0.07853982F * f4;
        this.legL.rotateAngleZ = -0.07853982F * f4;

        GL11.glTranslatef(0F, 0.4F * f4, 0F);


        this.handR.rotateAngleY = 0.0F;
        this.handR.rotateAngleZ = 0.0F;
        this.handL.rotateAngleY = 0.0F;

        if (entity.getEatingTick() > 1) {
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

        this.handR.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handL.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.handR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.handL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.tail.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount - 0.3F;
        this.tail2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.9F * limbSwingAmount - 0.4F;
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
