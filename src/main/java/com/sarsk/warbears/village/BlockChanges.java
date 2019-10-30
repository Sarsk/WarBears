package com.sarsk.warbears.village;


import net.minecraft.util.math.BlockPos;

public class BlockChanges {
    public net.minecraft.block.BlockState blockState;
    public BlockPos pos;

    public BlockChanges(BlockChanges b) {
        this.blockState = b.blockState;
        this.pos = new BlockPos(b.pos);
    }
    public BlockChanges(net.minecraft.block.BlockState s, BlockPos p) {
        this.blockState = s;
        this.pos = p;
    }
    public BlockChanges(net.minecraft.block.BlockState s, int x, int y, int z) {
        this.blockState = s;
        this.pos = new BlockPos(x,y,z);
    }
    public BlockChanges(net.minecraft.block.Block b, int x, int y, int z) {
        this.blockState = b.getDefaultState();
        this.pos = new BlockPos(x,y,z);
    }
    public BlockChanges(net.minecraft.block.BlockState s, BlockPos p, int x, int y, int z) {
        this.blockState = s;
        this.pos = new BlockPos(p.getX() + x,p.getY() + y,p.getZ() + z);
    }
    public BlockChanges(net.minecraft.block.Block b, BlockPos p, int x, int y, int z) {
        this.blockState = b.getDefaultState();
        this.pos = new BlockPos(p.getX() + x,p.getY() + y,p.getZ() + z);
    }

    public boolean SamePosition(final BlockChanges b) {
        return (this.pos.getX() == b.pos.getX()) && (this.pos.getY() == b.pos.getY()) && (this.pos.getZ() == b.pos.getZ());
    }
    public boolean SamePosition(final BlockPos b) {
        return (this.pos.getX() == b.getX()) && (this.pos.getY() == b.getY()) && (this.pos.getZ() == b.getZ());
    }
}