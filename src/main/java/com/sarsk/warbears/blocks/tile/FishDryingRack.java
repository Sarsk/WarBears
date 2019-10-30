package com.sarsk.warbears.blocks.tile;

import com.sarsk.warbears.tile_entity.TileEntityFishDryingRack;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class FishDryingRack extends Block implements ITileEntityProvider
{
	public static String name = "fish_drying_rack";

	//public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
	static PlayerEntity player = null;

	protected static final VoxelShape field_220176_c = Block.makeCuboidShape(0.0D, 3.0D, 0.0D, 16.0D, 9.0D, 16.0D);

	protected static final VoxelShape field_220177_d = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 3.0D, 3.0D);

	protected static final VoxelShape field_220178_e = Block.makeCuboidShape(0.0D, 0.0D, 13.0D, 3.0D, 3.0D, 16.0D);

	protected static final VoxelShape field_220179_f = Block.makeCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 3.0D, 3.0D);

	protected static final VoxelShape field_220180_g = Block.makeCuboidShape(13.0D, 0.0D, 13.0D, 16.0D, 3.0D, 16.0D);

	protected static final VoxelShape field_220181_h = VoxelShapes.or(field_220176_c, field_220177_d, field_220179_f);

	protected static final VoxelShape field_220182_i = VoxelShapes.or(field_220176_c, field_220178_e, field_220180_g);

	protected static final VoxelShape field_220183_j = VoxelShapes.or(field_220176_c, field_220177_d, field_220178_e);

	protected static final VoxelShape field_220184_k = VoxelShapes.or(field_220176_c, field_220179_f, field_220180_g);


	public FishDryingRack(Properties properties)
	{
		super(properties);
		//this.setDefaultState(this.stateContainer.getBaseState().with(PART, BedPart.FOOT));
	}

	private void SendMessageToPlayer(@Nullable LivingEntity placer, String message) {
		if (placer instanceof PlayerEntity) {
			player = (PlayerEntity) placer;
		}
		if (null != player && null != message) {
			player.sendMessage(new TranslationTextComponent(message));
		}
	}

	public MaterialColor getMaterialColor(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return MaterialColor.WOOL;
	}

	@Override
	public boolean ticksRandomly(BlockState state) {
		return false;
	//this.ticksRandomly;
    }

    @Override
	public int tickRate(IWorldReader worldIn) {
		return 1;
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		super.tick(state, worldIn, pos, random);

	}

	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (worldIn.isRemote)
		{
				player.sendMessage(new TranslationTextComponent("This is a test"));

			return true;
		}
		else
		{
			return true;
		}
	}

	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
	{
		super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.5F);
	}

	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack)
	{
		System.out.println("harvestBlock " + pos.toString());
		super.harvestBlock(worldIn, player, pos, state, te, stack);
	}
/*
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
	{
		BedPart bedpart = state.get(PART);
		BlockPos blockpos = pos.offset(getDirectionToOther(bedpart, state.get(HORIZONTAL_FACING)));
		BlockState blockstate = worldIn.getBlockState(blockpos);
		if (blockstate.getBlock() == this && blockstate.get(PART) != bedpart)
		{
			worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
			worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
			if (!worldIn.isRemote && !player.isCreative())
			{
				ItemStack itemstack = player.getHeldItemMainhand();
				spawnDrops(state, worldIn, pos, (TileEntity) null, player, itemstack);
				spawnDrops(blockstate, worldIn, blockpos, (TileEntity) null, player, itemstack);
			}
			player.addStat(Stats.BLOCK_MINED.get(this));
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}*/

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		Direction direction = Direction.SOUTH;/*state.get(HORIZONTAL_FACING);
		Direction direction1 = state.get(PART) == BedPart.HEAD ? direction : direction.getOpposite();*/
		switch (direction)
		{
			case NORTH:
				return field_220181_h;
			case SOUTH:
				return field_220182_i;
			case WEST:
				return field_220183_j;
			default:
				return field_220184_k;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public boolean hasCustomBreakingProgress(BlockState state)
	{
		return true;
	}

	public PushReaction getPushReaction(BlockState state)
	{
		return PushReaction.DESTROY;
	}

	public BlockRenderLayer getRenderLayer()
	{
		// TODO:
		return BlockRenderLayer.CUTOUT;
	}

	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	public TileEntity createNewTileEntity(IBlockReader worldIn)
	{
		return new TileEntityFishDryingRack();
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
	{
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	/**
	 * Called after a player destroys this Block - the position pos may no longer hold the state indicated.
	 */
	public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
		System.out.println("onPlayerDestroy " + pos.toString());
		super.onPlayerDestroy(worldIn, pos, state);
	}

	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
	{
		return false;
	}
}