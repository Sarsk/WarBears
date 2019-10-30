package com.sarsk.warbears;

import com.sarsk.warbears.client.WarBearsClient;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(WarBears.MODID)
public class WarBears
{
	
	public static final String NAME = "War Bears";

	public static final String MODID = "warbears";

	public WarBears()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initialization);
		//FMLJavaModLoadingContext.get().getModEventBus().addListener(WarBearsClient::initialization);
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new DVEvents());
		//MinecraftForge.EVENT_BUS.register(new WarBearsClient());
		
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () ->
		{
			FMLJavaModLoadingContext.get().getModEventBus().addListener(WarBearsClient::initialization);
			MinecraftForge.EVENT_BUS.register(new WarBearsClient());
		});
	}
	
	private void initialization(final FMLCommonSetupEvent event)
    {		
		for (Biome biome : ForgeRegistries.BIOMES.getValues())
		{
			//TODO: Place War Bear Village in tundra biome
			if (biome != Biomes.NETHER && !biome.getRegistryName().toString().contains("end"))
			{
				//biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(BlocksDV.despair_mushroom.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(4)));
				//biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, new BushConfig(BlocksDV.hope_mushroom.getDefaultState()), Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(8)));
			}
		}
    }

	public static ResourceLocation locate(String name)
	{
		return new ResourceLocation(MODID, name);
	}
	
	public static String find(String name)
	{
		return MODID + ":" + name;
	}
}
