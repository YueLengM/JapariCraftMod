package baguchan.japaricraftmod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Cerulean - bagu_chan
 * Created using Tabula 7.0.0
 */
public class ModelCerulean extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer leg;
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer leg2;

    public ModelCerulean() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leg2 = new ModelRenderer(this, 28, 0);
        this.leg2.setRotationPoint(0.0F, 2.4F, 0.0F);
        this.leg2.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(leg2, 0.22759093446006054F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 16.4F, 0.0F);
        this.body.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7, 0.0F);
        this.leg = new ModelRenderer(this, 28, 0);
        this.leg.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.leg.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, 0.0F);
        this.earL = new ModelRenderer(this, 28, 6);
        this.earL.setRotationPoint(2.0F, -3.5F, 0.0F);
        this.earL.addBox(-0.4F, -2.0F, -1.0F, 3, 3, 3, 0.0F);
        this.earR = new ModelRenderer(this, 28, 6);
        this.earR.setRotationPoint(-2.0F, -3.5F, 0.0F);
        this.earR.addBox(-2.6F, -2.0F, -1.0F, 3, 3, 3, 0.0F);
        this.leg.addChild(this.leg2);
        this.body.addChild(this.leg);
        this.body.addChild(this.earL);
        this.body.addChild(this.earR);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
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
