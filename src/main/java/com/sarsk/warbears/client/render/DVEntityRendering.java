package com.sarsk.warbears.client.render;

import com.sarsk.warbears.client.render.entity.RenderIceBear;
import com.sarsk.warbears.entity.IceBearEntity;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class DVEntityRendering
{

	public static void initialization()
	{
		register(IceBearEntity.class, RenderIceBear::new);
	}

	private static <T extends Entity> void register(Class<T> entityClass, IRenderFactory<? super T> renderFactory)
	{
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

}