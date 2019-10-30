package com.sarsk.warbears.village;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

/*
 * For generating the village
 */
public class VillageBuilder {
    public VillageBuilder() {

    }
    // TODO: Return the number of village states

    public static PlayerEntity player = null;
    public void RegisterPlayer(@Nullable LivingEntity placer) {
        SendMessageToPlayer(placer, null);
    }
    private void SendMessageToPlayer(@Nullable LivingEntity placer, String message) {
        if (placer instanceof PlayerEntity) {
            player = (PlayerEntity) placer;
        }
        if (null != player && null != message) {
            player.sendMessage(new TranslationTextComponent(message));
        }
    }


    // Pass in state counter, from 0 to n
    public BlockChangeList getChanges(BlockPos center, int bottomY) {

        return GetMainCavern(center, new BlockPos(10,10,10));
    }


    public BlockChangeList GetMainCavern(BlockPos center, BlockPos size) {
        BlockChangeFactory factory = new BlockChangeFactory();

        BlockChangeList cavern = new BlockChangeList("Main Cavern");

        float step = 0.0F;
        int y = center.getY();
        for (int s = 30; s > 0; s-=(int)step, y++) {
            BlockChangeList circle = factory.GetCircle(Blocks.AIR.getDefaultState(), new BlockPos(center.getX(), y, center.getZ()), 0, s);
            cavern.Add(circle);
            step = 0.1F + step*1.5F;
        }
        return cavern;
    }
}
