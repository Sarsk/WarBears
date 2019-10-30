package com.sarsk.warbears.entity;

import java.util.Random;

import com.sarsk.warbears.DVRegistry;
import com.sarsk.warbears.WarBears;
import com.sarsk.warbears.blocks.BlocksDV;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("goodnightsleep")
public class DVEntityTypes
{
	public static final EntityType<IceBearEntity> ICEBEAR = buildEntity("ice_bear", EntityType.Builder.<IceBearEntity>create(IceBearEntity::new, EntityClassification.CREATURE).size(1.3964844F, 1.6F));

	public static final EntityType<DVSpawnerEntity> SPAWNER_ENTITY = buildEntity("dv_spawner", EntityType.Builder.create(DVSpawnerEntity::new, EntityClassification.CREATURE).size(1.0F, 1.0F));

	public static void init(Register<EntityType<?>> event)
	{
		DVRegistry.register(event.getRegistry(), "ice_bear", DVEntityTypes.ICEBEAR);
		DVRegistry.register(event.getRegistry(), "dv_spawner", DVEntityTypes.SPAWNER_ENTITY);

		EntitySpawnPlacementRegistry.register(DVEntityTypes.ICEBEAR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DVEntityTypes::dreamAnimalSpawnConditions);
	  	EntitySpawnPlacementRegistry.register(DVEntityTypes.SPAWNER_ENTITY, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DVEntityTypes::dreamAnimalSpawnConditions);
	}

	// TODO: Can put bear logic to only spawn on ice or snow blocks
	public static boolean dreamAnimalSpawnConditions(EntityType<? extends AnimalEntity> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_)
	{
		return p_223316_1_.getBlockState(p_223316_3_.down()).getBlock() == BlocksDV.fish_drying_rack && p_223316_1_.getLightSubtracted(p_223316_3_, 0) > 8;
	}
	/*
	public static boolean nightmareAnimalSpawnConditions(EntityType<? extends AnimalEntity> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_)
	{
		return p_223316_1_.getBlockState(p_223316_3_.down()).getBlock() == BlocksDV.nightmare_grass_block && p_223316_1_.isSkyLightMax(p_223316_3_);
	}
	
	public static boolean otherSpawnConditions(EntityType<? extends MobEntity> p_223316_0_, IWorld p_223316_1_, SpawnReason p_223316_2_, BlockPos p_223316_3_, Random p_223316_4_)
	{
		return (p_223316_1_.getBlockState(p_223316_3_.down()).getBlock() == BlocksDV.nightmare_grass_block || p_223316_1_.getBlockState(p_223316_3_.down()).getBlock() == BlocksDV.dream_grass_block) && p_223316_1_.isSkyLightMax(p_223316_3_);
	}*/
	
	private static <T extends Entity> EntityType<T> buildEntity(String key, EntityType.Builder<T> builder)
	{
		return builder.build(WarBears.find(key));
	}
}