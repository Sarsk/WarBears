package com.sarsk.warbears.client.render.item;

import com.sarsk.warbears.tile_entity.TileEntityFishDryingRack;

import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FishDryingRackItemRenderer extends ItemStackTileEntityRenderer
{
	private final TileEntityFishDryingRack rack = new TileEntityFishDryingRack();

	public FishDryingRackItemRenderer()
	{
	}

	@Override
	public void renderByItem(ItemStack stack)
	{
		TileEntityRendererDispatcher.instance.renderAsItem(this.rack);
	}
}
