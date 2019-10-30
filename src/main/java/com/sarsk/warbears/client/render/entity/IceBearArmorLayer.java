package com.sarsk.warbears.client.render.entity;

import com.sarsk.warbears.client.render.models.IceBearModel;
import com.sarsk.warbears.entity.IceBearEntity;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class IceBearArmorLayer extends LayerRenderer<IceBearEntity, IceBearModel<IceBearEntity>> {
    private final IceBearModel<IceBearEntity> iceBearModel = new IceBearModel<>(0.1F);

    private ResourceLocation ICE_BEAR_LEATHER_ARMOR_TEXTURE = new ResourceLocation("textures/models/armor/leather_layer_1.png");
    private ResourceLocation ICE_BEAR_GOLD_ARMOR_TEXTURE = new ResourceLocation("textures/models/armor/gold_layer_1.png");
    private ResourceLocation ICE_BEAR_IRON_ARMOR_TEXTURE = new ResourceLocation("textures/models/armor/iron_layer_1.png");
    private ResourceLocation ICE_BEAR_DIAMOND_ARMOR_TEXTURE = new ResourceLocation("textures/models/armor/diamond_layer_1.png");
    private ResourceLocation ICE_BEAR_CHAINMAIL_ARMOR_TEXTURE = new ResourceLocation("textures/models/armor/chainmail_layer_1.png");

    // Leather Armor Models
    //TODO

    // Gold Armor Models
    private RendererModel goldHeadArmorModel;
    private RendererModel goldBodyArmorModel;
    //TODO

    // Iron Armor Models
    // TODO

    // Diamond Armor Models
    // TODO

    public IceBearArmorLayer(IEntityRenderer<IceBearEntity, IceBearModel<IceBearEntity>> entityIn) {
        super(entityIn);

            /*
            this.headModel = new RendererModel(this, 0, 0);
            this.headModel.addBox(-3.5F, -3.0F, -3.0F, 7, 7, 7, 0.0F); // Main head
            this.headModel.setRotationPoint(0.0F, 10.0F, -16.0F);
            this.headModel.setTextureOffset(0, 44).addBox(-2.5F, 1.0F, -6.0F, 5, 3, 3, 0.0F); // Snout
            this.headModel.setTextureOffset(26, 0).addBox(-4.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F); // Left Ear
            RendererModel lvt_1_1_ = this.headModel.setTextureOffset(26, 0);
            lvt_1_1_.mirror = true;
            lvt_1_1_.addBox(2.5F, -4.0F, -1.0F, 2, 2, 1, 0.0F); // Right Ear
            */

        // Depth is from front to back of head
        this.goldHeadArmorModel = new RendererModel(entityIn.getEntityModel(), 8, 0);
        this.goldHeadArmorModel.addBox(-1.5F, 1.5F, -4.0F, 3, 1, 2, 0.0F); // Snout
       // this.headModel.setTextureOffset(26, 0).addBox(0, -3.25F, -2.75F, 4, 8, 6, 0.0F); // Main head?
       // this.goldHeadArmorModel.setRotationPoint(0.0F, 10.0F, -16.0F);
       // this.goldHeadArmorModel.setTextureOffset(26, 0);
       // this.goldHeadArmorModel.addBox(-1.0F, -4.0F, -1.0F, 2, 5, 1, 0.0F); // test

        /*
        this.body = new RendererModel(this);
        this.body.setTextureOffset(0, 19).addBox(-5.0F, -13.0F, -7.0F, 14, 14, 11, 0.0F);
        this.body.setTextureOffset(39, 0).addBox(-4.0F, -25.0F, -7.0F, 12, 12, 10, 0.0F);
        this.body.setRotationPoint(-2.0F, 9.0F, 12.0F);
        */
        this.goldBodyArmorModel = new RendererModel(entityIn.getEntityModel(), 40, 16);
        this.goldBodyArmorModel.addBox(-5.5F, -13.5F, -7.0F, 8, 8, 8, 0.0F);
        this.goldBodyArmorModel.addBox(-4.5F, -25.5F, -7.0F, 4, 4, 4, 0.0F);

        /*
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
        */
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

    public void render(IceBearEntity entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        //ItemStack itemstack = entityIn.func_213803_dV();

        // Render the entity
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.iceBearModel.render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);

        // Render the Armor layer
        // TODO: Render based on armor type
        if(true){//if (!entityIn.getChestArmor().isEmpty()) {
            //HorseArmorItem horsearmoritem = (HorseArmorItem)itemstack.getItem();
            this.getEntityModel().setModelAttributes(this.iceBearModel);
            //TODO: Should the livingAnimations occur for both? Why is this here, and is it applied in the parent render already?
            this.iceBearModel.setLivingAnimations(entityIn, p_212842_2_, p_212842_3_, p_212842_4_);
            this.bindTexture(ICE_BEAR_GOLD_ARMOR_TEXTURE);
            this.goldHeadArmorModel = copyModelLocation(getEntityModel().getHeadModel(), this.goldHeadArmorModel);
            this.goldHeadArmorModel.render(p_212842_8_);
            this.goldBodyArmorModel = copyModelLocation(getEntityModel().getBodyModel(), this.goldBodyArmorModel);
            this.goldBodyArmorModel.render(p_212842_8_);

        }

    }

    public boolean shouldCombineTextures() {
        return false;
    }
}