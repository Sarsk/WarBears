package com.sarsk.warbears.item;

import com.sarsk.warbears.WarBears;
import com.sarsk.warbears.blocks.BlocksDV;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public abstract class DVCreativeTabs extends ItemGroup
{
	public DVCreativeTabs(String label)
	{
		super(WarBears.MODID + "." + label);
	}
	
	public static final ItemGroup blocks = new ItemGroup("dv_blocks")
	{
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(BlocksDV.fish_drying_rack);
		}
	};

	public static final ItemGroup items = new ItemGroup("dv_items")
	{
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(ItemsDV.ice_bear_spawn_egg);
		}
	};
}