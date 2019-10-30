package com.sarsk.warbears.client.render;

import com.sarsk.warbears.tile_entity.TileEntityFishDryingRack;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class DVTileEntityRendering
{
	public static void initialization()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFishDryingRack.class, new TileEntityFishDryingRackRenderer());
	}
}
