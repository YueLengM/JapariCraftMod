package baguchan.japaricraftmod.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;

public class ModelKabanHat extends net.minecraft.client.model.ModelBiped {
    public ModelRenderer hat_m;
    public ModelRenderer hat_u;
    public ModelRenderer hat_u02;
    public ModelRenderer feather_gr;
    public ModelRenderer feather_red;

    public ModelKabanHat() {
        super(0.5F, 0, 128, 64);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.hat_u02 = new ModelRenderer(this, 0, 55);
        this.hat_u02.setRotationPoint(0.0F, -0.2F, 0.0F);
        this.hat_u02.addBox(-4.5F, -0.5F, -4.5F, 9, 1, 9, 0.0F);
        this.hat_m = new ModelRenderer(this, 48, 51);
        this.hat_m.setRotationPoint(0.0F, -6.2F, 0.0F);
        this.hat_m.addBox(-6.0F, 0.0F, -6.0F, 12, 1, 12, 0.0F);
        this.feather_red = new ModelRenderer(this, 84, 53);
        this.feather_red.setRotationPoint(4.0F, 0.0F, -0.0F);
        this.feather_red.addBox(0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(feather_red, -0.1832595714594046F, 0.0F, 0.0F);
        this.feather_gr = new ModelRenderer(this, 55, 53);
        this.feather_gr.setRotationPoint(-5.0F, 0.0F, -0.0F);
        this.feather_gr.addBox(-0.5F, -6.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(feather_gr, -0.1832595714594046F, 0.0F, 0.0F);
        this.hat_u = new ModelRenderer(this, 96, 0);
        this.hat_u.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hat_u.addBox(-4.0F, -3.0F, -4.0F, 8, 3, 8, 0.0F);
        this.hat_u.addChild(this.hat_u02);
        this.hat_u.addChild(this.feather_red);
        this.hat_u.addChild(this.feather_gr);
        this.hat_m.addChild(this.hat_u);
        this.bipedHead.addChild(hat_m);
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

        if (entityIn instanceof EntityArmorStand) {

            EntityArmorStand entityarmorstand = (EntityArmorStand) entityIn;

            this.bipedHead.rotateAngleX = 0.017453292F * entityarmorstand.getHeadRotation().getX();
            this.bipedHead.rotateAngleY = 0.017453292F * entityarmorstand.getHeadRotation().getY();
            this.bipedHead.rotateAngleZ = 0.017453292F * entityarmorstand.getHeadRotation().getZ();
            this.bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);

            this.bipedBody.rotateAngleX = 0.017453292F * entityarmorstand.getBodyRotation().getX();
            this.bipedBody.rotateAngleY = 0.017453292F * entityarmorstand.getBodyRotation().getY();
            this.bipedBody.rotateAngleZ = 0.017453292F * entityarmorstand.getBodyRotation().getZ();

            this.bipedLeftArm.rotateAngleX = 0.017453292F * entityarmorstand.getLeftArmRotation().getX();
            this.bipedLeftArm.rotateAngleY = 0.017453292F * entityarmorstand.getLeftArmRotation().getY();
            this.bipedLeftArm.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftArmRotation().getZ();

            this.bipedRightArm.rotateAngleX = 0.017453292F * entityarmorstand.getRightArmRotation().getX();
            this.bipedRightArm.rotateAngleY = 0.017453292F * entityarmorstand.getRightArmRotation().getY();
            this.bipedRightArm.rotateAngleZ = 0.017453292F * entityarmorstand.getRightArmRotation().getZ();

            this.bipedLeftLeg.rotateAngleX = 0.017453292F * entityarmorstand.getLeftLegRotation().getX();
            this.bipedLeftLeg.rotateAngleY = 0.017453292F * entityarmorstand.getLeftLegRotation().getY();
            this.bipedLeftLeg.rotateAngleZ = 0.017453292F * entityarmorstand.getLeftLegRotation().getZ();
            this.bipedLeftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);

            this.bipedRightLeg.rotateAngleX = 0.017453292F * entityarmorstand.getRightLegRotation().getX();
            this.bipedRightLeg.rotateAngleY = 0.017453292F * entityarmorstand.getRightLegRotation().getY();
            this.bipedRightLeg.rotateAngleZ = 0.017453292F * entityarmorstand.getRightLegRotation().getZ();
            this.bipedRightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
            copyModelAngles(this.bipedHead, this.bipedHeadwear);

        } else {
            super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
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
