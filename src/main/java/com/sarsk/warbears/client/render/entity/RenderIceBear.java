package com.sarsk.warbears.client.render.entity;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.platform.GlStateManager;
import com.sarsk.warbears.WarBears;
import com.sarsk.warbears.client.render.models.IceBearModel;
import com.sarsk.warbears.entity.IceBearEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class RenderIceBear extends MobRenderer<IceBearEntity, IceBearModel<IceBearEntity>>
{
	private static final ResourceLocation POLAR_BEAR_TEXTURE = new ResourceLocation("textures/entity/bear/polarbear.png");

	private static final ResourceLocation POLAR_BEAR_LEATHER_TEXTURE = new ResourceLocation(WarBears.MODID, "textures/entities/icebear/polarbear_leather.png");
	private static final ResourceLocation POLAR_BEAR_GOLD_TEXTURE = new ResourceLocation(WarBears.MODID, "textures/entities/icebear/polarbear_gold.png");
	private static final ResourceLocation POLAR_BEAR_IRON_TEXTURE = new ResourceLocation(WarBears.MODID, "textures/entities/icebear/polarbear_iron.png");
	private static final ResourceLocation POLAR_BEAR_DIAMOND_TEXTURE = new ResourceLocation(WarBears.MODID, "textures/entities/icebear/polarbear_diamond.png");

	private static final ResourceLocation[] POLAR_BEAR_ARMOR_TEXTURE = new ResourceLocation[]{
			new ResourceLocation("textures/entity/horse/armor/horse_armor_leather.png"),
			new ResourceLocation("textures/entity/horse/armor/horse_armor_gold.png"),
			new ResourceLocation("textures/entity/horse/armor/horse_armor_iron.png"),
			new ResourceLocation("textures/entity/horse/armor/horse_armor_diamond.png")};

	private static final Map<String, ResourceLocation> LAYERED_LOCATION_CACHE = Maps.newHashMap();

	public RenderIceBear(EntityRendererManager manager)
    {
        super(manager, new IceBearModel<>(2.0F), 1.25F);
		//this.addLayer(new IceBearArmorLayer(this));
    }

	protected ResourceLocation getEntityTexture(IceBearEntity entity)
	{
		/*if (entity.getChestArmor().isEmpty()){
			return POLAR_BEAR_TEXTURE;
		}
		else*/ {
			return POLAR_BEAR_GOLD_TEXTURE;
		}
		/*
		String type = entity.getUnicornType() == 1 ? "green" : entity.getUnicornType() == 2 ? "yellow" : entity.getUnicornType() == 3 ? "blue" : "pink";
		//return new ResourceLocation("goodnightsleep", "textures/entities/unicorn/unicorn_" + type + ".png");
		if (!entity.getChestArmor().isEmpty()){
			return LLAMA_DECOR_TEXTURES[1];
		}*/
		//return POLAR_BEAR_TEXTURE;
/*
		String s = "textures/entity/bear/polarbear.png";//""IceBear/gold";//entity.getHorseTexture();
		ResourceLocation resourcelocation = LAYERED_LOCATION_CACHE.get(s);
		String[] texturePaths = new String[2];
		texturePaths[0] = "textures/entity/bear/polarbear.png";
		texturePaths[1] = "textures/entity/horse/armor/horse_armor_gold.png";
		if (resourcelocation == null) {
			resourcelocation = new ResourceLocation(s);
			Minecraft.getInstance().getTextureManager().loadTexture(resourcelocation, new LayeredTexture(texturePaths));
			LAYERED_LOCATION_CACHE.put(s, resourcelocation);
		}

		return resourcelocation;*/
	}

	protected void preRenderCallback(IceBearEntity p_77041_1_, float p_77041_2_) {
		GlStateManager.scalef(1.4F, 1.4F, 1.4F);
		super.preRenderCallback(p_77041_1_, p_77041_2_);
	}
}