package com.sarsk.warbears.item;

import com.sarsk.warbears.WarBears;
import com.sarsk.warbears.blocks.BlocksDV;
import com.sarsk.warbears.entity.DVEntityTypes;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemsDV
{
	private static IForgeRegistry<Item> iItemRegistry;

	public static Item fish_drying_rack_item;
	
	public static Item ice_bear_spawn_egg;

	public static void initialization()
	{
		// Block Items
		fish_drying_rack_item = register("fish_drying_rack_item", new BlockItem(BlocksDV.fish_drying_rack, new Item.Properties().group(DVCreativeTabs.blocks)));

		// Spawn Eggs
		ice_bear_spawn_egg = register("ice_bear_spawn_egg", new SpawnEggItem(DVEntityTypes.ICEBEAR, 0xffffff, 0xa9f5f5, new Item.Properties().group(DVCreativeTabs.items)));
	}

	private static Item register(String unlocalizedName, Item item)
	{
		item.setRegistryName(WarBears.locate(unlocalizedName));
		iItemRegistry.register(item);
		return item;
	}
	
	public static void setItemRegistry(IForgeRegistry<Item> iItemRegistry)
	{
		ItemsDV.iItemRegistry = iItemRegistry;
	}
}