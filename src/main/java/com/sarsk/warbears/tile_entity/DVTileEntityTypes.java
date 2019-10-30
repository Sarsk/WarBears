package com.sarsk.warbears.tile_entity;

import com.sarsk.warbears.DVRegistry;
import com.sarsk.warbears.WarBears;
import com.sarsk.warbears.blocks.BlocksDV;
import com.sarsk.warbears.blocks.tile.FishDryingRack;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(WarBears.MODID)
public class DVTileEntityTypes
{
	public static final TileEntityType<TileEntityFishDryingRack> DWARF_VILLAGE_HEART = null;
	
	public static void init(Register<TileEntityType<?>> event)
	{
		DVRegistry.register(event.getRegistry(), FishDryingRack.name, TileEntityType.Builder.create(TileEntityFishDryingRack::new, BlocksDV.fish_drying_rack).build(null));
	}
}
