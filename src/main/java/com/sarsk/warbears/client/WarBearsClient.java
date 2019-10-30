package com.sarsk.warbears.client;

import com.sarsk.warbears.client.render.DVEntityRendering;
import com.sarsk.warbears.client.render.DVTileEntityRendering;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class WarBearsClient
{
	public static void initialization(FMLClientSetupEvent event)
	{
		DVTileEntityRendering.initialization();
		DVEntityRendering.initialization();
		//MinecraftForge.EVENT_BUS.register(new DVMusicHandler());
		
	}
}