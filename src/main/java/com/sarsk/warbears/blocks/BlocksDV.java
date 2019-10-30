
package com.sarsk.warbears.blocks;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.sarsk.warbears.WarBears;
import com.sarsk.warbears.blocks.tile.FishDryingRack;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.registries.IForgeRegistry;

public class BlocksDV
{
	public static Block fish_drying_rack;

	private static IForgeRegistry<Block> iBlockRegistry;

	public static ArrayList<Block> dvBlockList = Lists.newArrayList();

	public static void initialization()
	{		
		if (iBlockRegistry == null)
		{
			return;
		}

		fish_drying_rack = registerWithoutItem( FishDryingRack.name, new FishDryingRack(Block.Properties.from(Blocks.OBSIDIAN)));
	}

	public static void setBlockRegistry(IForgeRegistry<Block> iBlockRegistry)
	{
		BlocksDV.iBlockRegistry = iBlockRegistry;
	}
	
	public static Block register(String name, Block block)
	{
		if (iBlockRegistry != null) 
		{
			block.setRegistryName(WarBears.locate(name));
			dvBlockList.add(block);
			iBlockRegistry.register(block);
		}

		return block;
	}
	
	public static Block registerWithoutItem(String name, Block block)
	{
		if (iBlockRegistry != null) 
		{
			block.setRegistryName(WarBears.locate(name));
			iBlockRegistry.register(block);
		}

		return block;
	}
}