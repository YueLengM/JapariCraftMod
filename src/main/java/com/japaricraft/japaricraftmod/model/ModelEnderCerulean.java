package com.japaricraft.japaricraftmod.model;

import com.japaricraft.japaricraftmod.mob.EntityEnderCerulean;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelEnderCerulean extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer handR;
    public ModelRenderer handL;
    public ModelRenderer legR;
    public ModelRenderer legL;

    public ModelEnderCerulean() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.legR = new ModelRenderer(this, 16, 16);
        this.legR.setRotationPoint(-1.2F, 11.0F, 0.0F);
        this.legR.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.handR = new ModelRenderer(this, 0, 19);
        this.handR.setRotationPoint(-3.5F, 0.0F, 0.0F);
        this.handR.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(handR, 0.0F, 0.0F, 0.40980330836826856F);
        this.handL = new ModelRenderer(this, 8, 19);
        this.handL.setRotationPoint(3.5F, 0.0F, 0.0F);
        this.handL.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(handL, 0.0F, 0.0F, -0.40980330836826856F);
        this.legL = new ModelRenderer(this, 24, 16);
        this.legL.setRotationPoint(1.2F, 11.0F, 0.0F);
        this.legL.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.head.addBox(-4.0F, -9.0F, -4.0F, 8, 8, 8, 0.0F);
        this.body = new ModelRenderer(this, 32, 0);
        this.body.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.body.addBox(-3.0F, 0.0F, -3.0F, 6, 11, 6, 0.0F);
        this.body.addChild(this.legR);
        this.body.addChild(this.handR);
        this.body.addChild(this.handL);
        this.body.addChild(this.legL);
        this.body.addChild(this.head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (!(entityIn instanceof EntityEnderCerulean)) {
            return;
        }

        this.head.rotateAngleY = netHeadYaw * 0.017453292F;

        {
            this.head.rotateAngleX = headPitch * 0.017453292F;
        }

        this.body.rotateAngleX = 0.0F;
        this.body.rotateAngleY = 0.0F;
        // wave arms more
        this.handR.rotateAngleX = 0.0F;//MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        this.handL.rotateAngleX = 0.0F;//MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.handR.rotateAngleZ = 0.0F;
        this.handL.rotateAngleZ = 0.0F;


        this.handR.rotateAngleZ += MathHelper.cos((ageInTicks + 10F) * 0.133F) * 0.3F + 0.3F;
        this.handL.rotateAngleZ -= MathHelper.cos((ageInTicks + 10F) * 0.133F) * 0.3F + 0.3F;
        this.handR.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.handL.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float partialTick) {
        float bounce = par1EntityLiving.ticksExisted + partialTick;

        // this is where we add the floating
        GL11.glTranslatef(0F, -0.125F - MathHelper.sin((bounce) * 0.133F) * 0.08F, 0F);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
