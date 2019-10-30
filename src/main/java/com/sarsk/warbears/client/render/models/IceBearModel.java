package com.sarsk.warbears.client.render.models;

import com.sarsk.warbears.entity.IceBearEntity;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IceBearModel<T extends IceBearEntity> extends HorseModel<T>
{
    private static final ResourceLocation POLAR_BEAR_TEXTURE = new ResourceLocation("textures/entity/bear/polarbear.png");

    public IceBearModel(float scale)
    {
        super(scale);
        PolarBearConstructor(scale);
    }

    public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
    	//super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        polarBearRender(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        /*

        AbstractHorseEntity abstracthorse = (AbstractHorseEntity)entityIn;
        boolean flag = abstracthorse.isChild();
        float f1 = 0.5F;
        if (flag)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scalef(f1, f1, f1);
            GlStateManager.translatef(0.0F, 2F * (1.0F - f1), 0.0F);
            this.horn.render(scale);
            GlStateManager.popMatrix();

        }
        else
        {
            this.horn.render(scale);

        }

        //GlStateManager.popMatrix();
        */
    }

    private float updateHorseRotation(float p_110683_1_, float p_110683_2_, float p_110683_3_)
    {
        float f;

        for (f = p_110683_2_ - p_110683_1_; f < -180.0F; f += 360.0F)
        {
            ;
        }

        while (f >= 180.0F)
        {
            f -= 360.0F;
        }

        return p_110683_1_ + p_110683_3_ * f;
    }

    @Override
	public void setLivingAnimations(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        super.setLivingAnimations((T) entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
        float f = this.updateHorseRotation(entitylivingbaseIn.prevRenderYawOffset, entitylivingbaseIn.renderYawOffset, partialTickTime);
        float f1 = this.updateHorseRotation(entitylivingbaseIn.prevRotationYawHead, entitylivingbaseIn.rotationYawHead, partialTickTime);
        //float f2 = entitylivingbaseIn.prevRotationPitch + (entitylivingbaseIn.rotationPitch - entitylivingbaseIn.prevRotationPitch) * partialTickTime;
        float f3 = f1 - f;
        //float f4 = f2 * 0.017453292F;

        if (f3 > 20.0F)
        {
            f3 = 20.0F;
        }

        if (f3 < -20.0F)
        {
            f3 = -20.0F;
        }

        if (limbSwingAmount > 0.2F)
        {
            //f4 += MathHelper.cos(limbSwing * 0.4F) * 0.15F * limbSwingAmount;
        }

        AbstractHorseEntity abstracthorse = (AbstractHorseEntity)entitylivingbaseIn;
        boolean flag2 = abstracthorse.isBeingRidden();
        
        if (flag2)
        {
        	limbSwing = limbSwing / 2;
        }

    }


    public RendererModel copyModelLocation(RendererModel from, RendererModel target) {

        target.rotationPointX = from.rotationPointX;
        target.rotationPointY = from.rotationPointY;
        target.rotationPointZ = from.rotationPointZ;
        target.rotateAngleX = from.rotateAngleX;
        target.rotateAngleY = from.rotateAngleY;
        target.rotateAngleZ = from.rotateAngleZ;
        return target;
    }

    public RendererModel getHeadModel() {
        return this.headModel;
    }
    public RendererModel getBodyModel() {
        return this.body;
    }
    /*
     * POLAR BEAR
     */

    public void PolarBearConstructor(float scale) {
        quadrupedModelConstructor(12, 0.0F);
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.headModel = new RendererModel(this, 0, 0);
        this.headModel.addBox(-3.5F, -3.0F, -3.0F, 7, 7, 7, 0.0F); // Main head?
        this.headModel.setRotationPoint(0.0F, 10.0F, -16.0F);
        this.headModel.setTextureOffset(0, 44).addBox(-2.5F, 1.0F, -6.0F, 5, 3, 3, 0.0F); // Snout
        this.headModel.setTextureOffset(26, 0).addBox(-4.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F); // Left Ear
        RendererModel lvt_1_1_ = this.headModel.setTextureOffset(26, 0);
        lvt_1_1_.mirror = true;
        lvt_1_1_.addBox(2.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F); // Right Ear
        this.body = new RendererModel(this);
        this.body.setTextureOffset(0, 19).addBox(-5.0F, -13.0F, -7.0F, 14, 14, 11, 0.0F);
        this.body.setTextureOffset(39, 0).addBox(-4.0F, -25.0F, -7.0F, 12, 12, 10, 0.0F);
        this.body.setRotationPoint(-2.0F, 9.0F, 12.0F);
        //int lvt_2_1_ = true;
        this.legBackRight = new RendererModel(this, 50, 22);
        this.legBackRight.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.legBackRight.setRotationPoint(-3.5F, 14.0F, 6.0F);
        this.legBackLeft = new RendererModel(this, 50, 22);
        this.legBackLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.legBackLeft.setRotationPoint(3.5F, 14.0F, 6.0F);
        this.legFrontRight = new RendererModel(this, 50, 40);
        this.legFrontRight.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.legFrontRight.setRotationPoint(-2.5F, 14.0F, -7.0F);
        this.legFrontLeft = new RendererModel(this, 50, 40);
        this.legFrontLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.legFrontLeft.setRotationPoint(2.5F, 14.0F, -7.0F);
        --this.legBackRight.rotationPointX;
        ++this.legBackLeft.rotationPointX;
        RendererModel var10000 = this.legBackRight;
        var10000.rotationPointZ += 0.0F;
        var10000 = this.legBackLeft;
        var10000.rotationPointZ += 0.0F;
        --this.legFrontRight.rotationPointX;
        ++this.legFrontLeft.rotationPointX;
        --this.legFrontRight.rotationPointZ;
        --this.legFrontLeft.rotationPointZ;
        this.childZOffset += 2.0F;
    }

    public void polarBearRender(T entityIn, float limbSwing, float limbSwingAmount, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        polarBearSetRotationAngles(entityIn, limbSwing, limbSwingAmount, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);

        if (this.isChild) {
            float lvt_8_1_ = 2.0F;
            this.childYOffset = 16.0F;
            this.childZOffset = 4.0F;
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.6666667F, 0.6666667F, 0.6666667F);
            GlStateManager.translatef(0.0F, this.childYOffset * p_78088_7_, this.childZOffset * p_78088_7_);
            this.headModel.render(p_78088_7_);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.body.render(p_78088_7_);
            this.legBackRight.render(p_78088_7_);
            this.legBackLeft.render(p_78088_7_);
            this.legFrontRight.render(p_78088_7_);
            this.legFrontLeft.render(p_78088_7_);
            GlStateManager.popMatrix();
        } else {
            //TODO: Perform a bindTexture for the different armor types
            this.headModel.render(p_78088_7_);
            this.body.render(p_78088_7_);
            this.legBackRight.render(p_78088_7_);
            this.legBackLeft.render(p_78088_7_);
            this.legFrontRight.render(p_78088_7_);
            this.legFrontLeft.render(p_78088_7_);
/*
            //if (!entityIn.getChestArmor().isEmpty())
            {

                // Copy the rotation angles from the head model
                copyModelLocation(this.headModel, this.headArmorModel);
                GlStateManager.bindTexture(1);
                this.headArmorModel.render(p_78088_7_);
            }*/
        }

    }

    public void polarBearSetRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float p_212844_4_, float p_212844_5_, float p_212844_6_, float p_212844_7_) {
        quadropedSetRotationAngles(entityIn, limbSwing, limbSwingAmount, p_212844_4_, p_212844_5_, p_212844_6_, p_212844_7_);
        float lvt_8_1_ = p_212844_4_ - (float)entityIn.ticksExisted;
        float lvt_9_1_ = entityIn.getStandingAnimationScale(lvt_8_1_);
        lvt_9_1_ *= lvt_9_1_;
        float lvt_10_1_ = 1.0F - lvt_9_1_;
        this.body.rotateAngleX = 1.5707964F - lvt_9_1_ * 3.1415927F * 0.35F;
        this.body.rotationPointY = 9.0F * lvt_10_1_ + 11.0F * lvt_9_1_;
        this.legFrontRight.rotationPointY = 14.0F * lvt_10_1_ - 6.0F * lvt_9_1_;
        this.legFrontRight.rotationPointZ = -8.0F * lvt_10_1_ - 4.0F * lvt_9_1_;
        RendererModel model = this.legFrontRight;
        model.rotateAngleX -= lvt_9_1_ * 3.1415927F * 0.45F;
        this.legFrontLeft.rotationPointY = this.legFrontRight.rotationPointY;
        this.legFrontLeft.rotationPointZ = this.legFrontRight.rotationPointZ;
        model = this.legFrontLeft;
        model.rotateAngleX -= lvt_9_1_ * 3.1415927F * 0.45F;
        if (this.isChild) {
            this.headModel.rotationPointY = 10.0F * lvt_10_1_ - 9.0F * lvt_9_1_;
            this.headModel.rotationPointZ = -16.0F * lvt_10_1_ - 7.0F * lvt_9_1_;
        } else {
            this.headModel.rotationPointY = 10.0F * lvt_10_1_ - 14.0F * lvt_9_1_;
            this.headModel.rotationPointZ = -16.0F * lvt_10_1_ - 3.0F * lvt_9_1_;
 //           this.headArmorModel.rotationPointY = 10.0F * lvt_10_1_ - 14.0F * lvt_9_1_;
  //          this.headArmorModel.rotationPointZ = -16.0F * lvt_10_1_ - 3.0F * lvt_9_1_;
        }

        model = this.headModel;
        model.rotateAngleX += lvt_9_1_ * 3.1415927F * 0.15F;
        //this.headArmorModel.rotateAngleX += lvt_9_1_ * 3.1415927F * 0.15F;
    }

    /*
     * Quadroped
     */
//TODO: Once the armor is working easily, try copying the rotation angles straight from the body part.. maybe we can
// extend the PolarBearModel with a little reflection
    protected RendererModel headModel = new RendererModel(this, 0, 0);
    protected RendererModel body;
    protected RendererModel legBackRight;
    protected RendererModel legBackLeft;
    protected RendererModel legFrontRight;
    protected RendererModel legFrontLeft;
    protected float childYOffset = 8.0F;
    protected float childZOffset = 4.0F;
    // Armor Models
    //protected RendererModel headArmorModel = new RendererModel(this, 0, 0);

    public void quadrupedModelConstructor(int p_i1154_1_, float p_i1154_2_) {
        this.headModel.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, p_i1154_2_);
        this.headModel.setRotationPoint(0.0F, (float)(18 - p_i1154_1_), -6.0F);
        this.body = new RendererModel(this, 28, 8);
        this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8, p_i1154_2_);
        this.body.setRotationPoint(0.0F, (float)(17 - p_i1154_1_), 2.0F);
        this.legBackRight = new RendererModel(this, 0, 16);
        this.legBackRight.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
        this.legBackRight.setRotationPoint(-3.0F, (float)(24 - p_i1154_1_), 7.0F);
        this.legBackLeft = new RendererModel(this, 0, 16);
        this.legBackLeft.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
        this.legBackLeft.setRotationPoint(3.0F, (float)(24 - p_i1154_1_), 7.0F);
        this.legFrontRight = new RendererModel(this, 0, 16);
        this.legFrontRight.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
        this.legFrontRight.setRotationPoint(-3.0F, (float)(24 - p_i1154_1_), -5.0F);
        this.legFrontLeft = new RendererModel(this, 0, 16);
        this.legFrontLeft.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
        this.legFrontLeft.setRotationPoint(3.0F, (float)(24 - p_i1154_1_), -5.0F);
    }

    public void quadropedRender(T p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        quadropedSetRotationAngles(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        if (this.isChild) {
            float lvt_8_1_ = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, this.childYOffset * p_78088_7_, this.childZOffset * p_78088_7_);
            this.headModel.render(p_78088_7_);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.body.render(p_78088_7_);
            this.legBackRight.render(p_78088_7_);
            this.legBackLeft.render(p_78088_7_);
            this.legFrontRight.render(p_78088_7_);
            this.legFrontLeft.render(p_78088_7_);
            GlStateManager.popMatrix();
        } else {
            this.headModel.render(p_78088_7_);
            this.body.render(p_78088_7_);
            this.legBackRight.render(p_78088_7_);
            this.legBackLeft.render(p_78088_7_);
            this.legFrontRight.render(p_78088_7_);
            this.legFrontLeft.render(p_78088_7_);
        }

    }

    public void quadropedSetRotationAngles(T p_212844_1_, float p_212844_2_, float p_212844_3_, float p_212844_4_, float p_212844_5_, float p_212844_6_, float p_212844_7_) {
        this.headModel.rotateAngleX = p_212844_6_ * 0.017453292F;
        this.headModel.rotateAngleY = p_212844_5_ * 0.017453292F;
        //this.headArmorModel.rotateAngleX = p_212844_6_ * 0.017453292F;
       // this.headArmorModel.rotateAngleY = p_212844_5_ * 0.017453292F;
        this.body.rotateAngleX = 1.5707964F;
        this.legBackRight.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F) * 1.4F * p_212844_3_;
        this.legBackLeft.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F + 3.1415927F) * 1.4F * p_212844_3_;
        this.legFrontRight.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F + 3.1415927F) * 1.4F * p_212844_3_;
        this.legFrontLeft.rotateAngleX = MathHelper.cos(p_212844_2_ * 0.6662F) * 1.4F * p_212844_3_;
    }
}
