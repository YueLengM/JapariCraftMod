package baguchan.japaricraftmod.client.model;

import net.minecraft.client.model.ModelRenderer;

public class ModelKabanHat extends net.minecraft.client.model.ModelBiped {
    public ModelRenderer hat_m;
    public ModelRenderer hat_u;
    public ModelRenderer hat_u02;
    public ModelRenderer feather_gr;
    public ModelRenderer feather_red;

    public ModelKabanHat() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.hat_u02 = new ModelRenderer(this, 22, 7);
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

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
