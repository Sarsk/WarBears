package com.sarsk.warbears.tile_entity;

import com.sarsk.warbears.village.BlockChangeList;
import com.sarsk.warbears.village.BlockChanges;
import com.sarsk.warbears.village.VillageBuilder;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileEntityFishDryingRack extends TileEntity
{
	public TileEntityFishDryingRack()
	{
		super(DVTileEntityTypes.DWARF_VILLAGE_HEART);
	}

	public int offset = 0;
	public BlockPos heartLoc;
	VillageBuilder builder = new VillageBuilder();
	BlockChangeList changes = null;
	//TODO: BAD! This cant be static, but looks like the TE gets deleted before the block destroy
	static BlockChangeList restore = null;

	public static PlayerEntity player = null;
	public void RegisterPlayer(@Nullable LivingEntity placer) {
		SendMessageToPlayer(placer, null);
		builder.RegisterPlayer(placer);
	}
	private void SendMessageToPlayer(@Nullable LivingEntity placer, String message) {
		if (placer instanceof PlayerEntity) {
			player = (PlayerEntity) placer;
		}
		if (null != player && null != message) {
			player.sendMessage(new TranslationTextComponent(message));
		}
	}

	public SUpdateTileEntityPacket getUpdatePacket()
	{
		// TODO: Type in was 11 for the beds
		return new SUpdateTileEntityPacket(this.pos, 12, this.getUpdateTag());

	}

	public int getTickRate() {
		//TODO: Defaulting to 10
		return 10;
	}


	public void CreateNewVillage(World worldIn, BlockPos pos) {
		System.out.println("Creating new Village at " + pos.toString());
		this.heartLoc = pos;
		changes = builder.getChanges(heartLoc, offset);

		// Get restore of the blocks
		restore = new BlockChangeList("Restore");
		for (BlockChanges c : changes.changes) {
			restore.Add(new BlockChanges(worldIn.getBlockState(c.pos), c.pos));
		}
	}

	public BlockChangeList getTickChanges() {
		//TODO: Slice the changes
		return changes;

	}
	public BlockChangeList getRestore() {

		System.out.println("getRestore: "+restore);
		return restore;
	}
}