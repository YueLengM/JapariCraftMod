package baguchan.japaricraftmod.client.model;

import baguchan.japaricraftmod.mob.EntityStarSorcerger;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

/**
 * ModelStarSorcerger - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelStarSorcerger extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Hat1;
    public ModelRenderer Hat2;
    public ModelRenderer Hat3;
    public ModelRenderer Body;
    public ModelRenderer Cloak;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Nose;
    public ModelRenderer Sandstar;

    public ModelStarSorcerger() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Hat2 = new ModelRenderer(this, 36, 18);
        this.Hat2.setRotationPoint(0.0F, -10.0F, -3.0F);
        this.Hat2.addBox(-3.5F, -4.0F, 0.0F, 7, 4, 7, 0.0F);
        this.setRotateAngle(Hat2, -0.2181661564992912F, 0.0F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 18);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Hat3 = new ModelRenderer(this, 16, 18);
        this.Hat3.setRotationPoint(0.0F, -13.0F, 0.0F);
        this.Hat3.addBox(-2.5F, -4.0F, 0.0F, 5, 4, 5, 0.0F);
        this.setRotateAngle(Hat3, -0.7853981633974483F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 28, 30);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 18);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Hat1 = new ModelRenderer(this, 32, 0);
        this.Hat1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat1.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.5F);
        this.Cloak = new ModelRenderer(this, 0, 36);
        this.Cloak.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloak.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.LeftArm = new ModelRenderer(this, 44, 48);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Sandstar = new ModelRenderer(this, 0, 0);
        this.Sandstar.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Sandstar.addBox(-4.0F, 7.0F, -1.0F, 1, 2, 2, 0.0F);
        this.Nose = new ModelRenderer(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -3.0F, -4.0F);
        this.Nose.addBox(-1.0F, 0.0F, -2.0F, 2, 4, 2, 0.0F);
        this.RightArm = new ModelRenderer(this, 28, 48);
        this.RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RightArm.addChild(this.Sandstar);
        this.Head.addChild(this.Nose);
        this.Head.addChild(this.Hat1);
        this.Hat1.addChild(this.Hat2);
        this.Hat3.addChild(this.Hat3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.LeftLeg.render(f5);
        this.Body.render(f5);
        this.Head.render(f5);
        this.RightLeg.render(f5);
        this.Cloak.render(f5);
        this.LeftArm.render(f5);
        this.RightArm.render(f5);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.Head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.Head.rotateAngleX = headPitch * 0.017453292F;
        this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.RightLeg.rotateAngleY = 0.0F;
        this.LeftLeg.rotateAngleY = 0.0F;

        EntityStarSorcerger.IllagerArmPose abstractillager$illagerarmpose = ((EntityStarSorcerger) entityIn).getArmPose();

        if (abstractillager$illagerarmpose == EntityStarSorcerger.IllagerArmPose.ATTACKING) {
            float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
            float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
            this.RightArm.rotateAngleZ = 0.0F;
            this.LeftArm.rotateAngleZ = 0.0F;
            this.RightArm.rotateAngleY = 0.15707964F;
            this.LeftArm.rotateAngleY = -0.15707964F;

            if (((EntityLivingBase) entityIn).getPrimaryHand() == EnumHandSide.RIGHT) {
                this.RightArm.rotateAngleX = -1.8849558F + MathHelper.cos(ageInTicks * 0.09F) * 0.15F;
                this.LeftArm.rotateAngleX = -0.0F + MathHelper.cos(ageInTicks * 0.19F) * 0.5F;
                this.RightArm.rotateAngleX += f * 2.2F - f1 * 0.4F;
                this.LeftArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
            } else {
                this.RightArm.rotateAngleX = -0.0F + MathHelper.cos(ageInTicks * 0.19F) * 0.5F;
                this.LeftArm.rotateAngleX = -1.8849558F + MathHelper.cos(ageInTicks * 0.09F) * 0.15F;
                this.RightArm.rotateAngleX += f * 1.2F - f1 * 0.4F;
                this.LeftArm.rotateAngleX += f * 2.2F - f1 * 0.4F;
            }

            this.RightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.LeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.RightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
            this.LeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        } else if (abstractillager$illagerarmpose == EntityStarSorcerger.IllagerArmPose.SPELLCASTING) {
            this.RightArm.rotationPointZ = 0.0F;
            this.RightArm.rotationPointX = -5.0F;
            this.LeftArm.rotationPointZ = 0.0F;
            this.LeftArm.rotationPointX = 5.0F;
            this.RightArm.rotateAngleX = MathHelper.cos(ageInTicks * 0.6662F) * 0.25F;
            this.LeftArm.rotateAngleX = MathHelper.cos(ageInTicks * 0.6662F) * 0.25F;
            this.RightArm.rotateAngleZ = 2.3561945F;
            this.LeftArm.rotateAngleZ = -2.3561945F;
            this.RightArm.rotateAngleY = 0.0F;
            this.LeftArm.rotateAngleY = 0.0F;
        } else if (abstractillager$illagerarmpose == EntityStarSorcerger.IllagerArmPose.BOW_AND_ARROW) {
            this.RightArm.rotateAngleY = -0.1F + this.Head.rotateAngleY;
            this.RightArm.rotateAngleX = -((float) Math.PI / 2F) + this.Head.rotateAngleX;
            this.LeftArm.rotateAngleX = -0.9424779F + this.Head.rotateAngleX;
            this.LeftArm.rotateAngleY = this.Head.rotateAngleY - 0.4F;
            this.LeftArm.rotateAngleZ = ((float) Math.PI / 2F);
        } else {
            this.RightArm.rotateAngleZ = 0.0F;
            this.LeftArm.rotateAngleZ = 0.0F;
            this.RightArm.rotateAngleY = 0.0F;
            this.LeftArm.rotateAngleY = 0.0F;
            this.RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
            this.LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        }
    }

    public ModelRenderer getArm(EnumHandSide p_191216_1_) {
        return p_191216_1_ == EnumHandSide.LEFT ? this.LeftArm : this.RightArm;
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
