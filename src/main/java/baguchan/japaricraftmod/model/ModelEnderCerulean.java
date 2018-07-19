package baguchan.japaricraftmod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEnderCerulean extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer core;
    public final ModelRenderer[] thorn = new ModelRenderer[8];

    public ModelEnderCerulean() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        for (int i = 0; i < this.thorn.length; ++i) {
            this.thorn[i] = new ModelRenderer(this, 0, 0);
            this.thorn[i].addBox(0.0F, 0.0F, 0.0F, 2, 7, 2);
        }
        this.core = new ModelRenderer(this, 0, 24);
        this.core.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.core.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.body.addBox(-6.0F, -6.0F, -6.0F, 12, 12, 12, 0.0F);
        this.body.addChild(this.core);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.body.render(scale);
        for (ModelRenderer modelrenderer : this.thorn) {
            modelrenderer.render(scale);
        }
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        float f = ageInTicks * (float) Math.PI * -0.1F;

        for (int i = 0; i < 4; ++i) {
            this.thorn[i].rotationPointY = 8.0F + MathHelper.cos(((float) (i * 2) + ageInTicks) * 0.25F);
            this.thorn[i].rotationPointX = MathHelper.cos(f) * 9.0F;
            this.thorn[i].rotationPointZ = MathHelper.sin(f) * 9.0F;
            ++f;
        }

        f = ((float) Math.PI / 4F) + ageInTicks * (float) Math.PI * 0.03F;

        for (int j = 4; j < 8; ++j) {
            this.thorn[j].rotationPointY = 12.0F + MathHelper.cos(((float) (j * 2) + ageInTicks) * 0.25F);
            this.thorn[j].rotationPointX = MathHelper.cos(f) * 7.0F;
            this.thorn[j].rotationPointZ = MathHelper.sin(f) * 7.0F;
            ++f;
        }

        this.body.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleX = headPitch * 0.017453292F;
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
