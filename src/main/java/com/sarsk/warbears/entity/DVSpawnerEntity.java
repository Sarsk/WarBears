package com.sarsk.warbears.entity;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap.Type;

public class DVSpawnerEntity extends AnimalEntity
{
	private boolean spawnedMobs;

    public DVSpawnerEntity(EntityType<? extends DVSpawnerEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.setInvisible(true);
        //this.setSize(0.0F, 0.0F);
    }
    
    @Override
    protected void registerGoals()
    {
    }

    //@Override
    //public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
    public void spawnMobs()
    {
    	World worldIn = this.world;
    	
    	DifficultyInstance difficultyIn = this.world.getDifficultyForLocation(this.getPosition());
    	int type = rand.nextInt(4);
    	int chance =  rand.nextInt(4) + 1;
    	
    	BlockPos blockpos = this.world.getHeight(Type.MOTION_BLOCKING_NO_LEAVES, this.getPosition()).add(-2 + this.rand.nextInt(4), 0, -2 + this.rand.nextInt(4));//(new BlockPos(this)).add(-1 + this.rand.nextInt(1), 1, -1 + this.rand.nextInt(1));

		// TODO: Refer to the GoodNightSleep spawn logic
    	//BlockPos blockpos = (new BlockPos(this));
    	/*
    	if (worldIn.getDimension().getType() != GNSDimensions.dimensionType(false))
    	{
	    	if (type == 0)
	    	{
		    	for (int i = 0; i < chance; ++i)
		        {
		    		PigEntity pig = new PigEntity(EntityType.PIG, this.world);
		            pig.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
		            pig.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData)null, (CompoundNBT)null);
		            this.world.addEntity(pig);
		        }
	    	}
	    	
	    	if (type == 1)
	    	{
		    	for (int i = 0; i < chance; ++i)
		        {
		            CowEntity cow = new CowEntity(EntityType.COW, this.world);
		            cow.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
		            cow.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData)null, (CompoundNBT)null);
		            this.world.addEntity(cow);
		        }
	    	}
	    	
	    	if (type == 2)
	    	{
		    	for (int i = 0; i < chance; ++i)
		        {
		            SheepEntity sheep = new SheepEntity(EntityType.SHEEP, this.world);
		            sheep.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
		            sheep.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData)null, (CompoundNBT)null);
		            
		            sheep.setFleeceColor(DyeColor.byId(rand.nextInt(15)));
		            this.world.addEntity(sheep);
		        }
	    	}
	    	
	    	if (type == 3)
	    	{
		    	for (int i = 0; i < chance; ++i)
		        {
		            ChickenEntity chicken = new ChickenEntity(EntityType.CHICKEN, this.world);
		            chicken.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
		            chicken.onInitialSpawn(worldIn, difficultyIn, SpawnReason.NATURAL, (ILivingEntityData)null, (CompoundNBT)null);
		            this.world.addEntity(chicken);
		        }
	    	}
    	}*/
    }
    
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
    
    @Override
    public void tick()
    {
    	super.tick();
    	if (this.ticksExisted > 50 || this.spawnedMobs)
    	{
    		this.remove();
    	}

    	if (this.ticksExisted == 3)
    	{
    		this.spawnMobs();
    		this.spawnedMobs = true;
    	}
    }

	@Override
	public AgeableEntity createChild(AgeableEntity ageable)
	{
		return null;
	}
}