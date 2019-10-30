package com.sarsk.warbears.village;

import net.minecraft.util.math.BlockPos;

public class BlockChangeFactory {

    public BlockChangeList GetCircle(net.minecraft.block.BlockState blockState, BlockPos center, int radius, int thickness) {

        BlockChangeList circle = new BlockChangeList("Circle");
        for (int x = 0; x < radius + thickness; x++) {
            for (int z= 0; z < radius + thickness; z++) {
                double len = Math.sqrt(x*x + z*z);
                if (len >= radius - 0.25 && len <= radius + thickness) {
                    circle.Add(blockState, center, x, 0, z);
                    circle.Add(blockState, center, -x, 0, z);
                    circle.Add(blockState, center, x, 0, -z);
                    circle.Add(blockState, center, -x, 0, -z);
                }
            }
        }
        return circle;
    }

    public BlockPos GetMin(BlockPos a, BlockPos b) {
        return new BlockPos(Math.min(a.getX(), b.getX()), Math.min(a.getY(), b.getY()), Math.min(a.getZ(), b.getZ()));
    }
    public BlockPos GetMax(BlockPos a, BlockPos b) {
        return new BlockPos(Math.max(a.getX(), b.getX()), Math.max(a.getY(), b.getY()), Math.max(a.getZ(), b.getZ()));
    }

    public BlockPos[] GetLine(BlockPos a, BlockPos b){
        // TODO: Generate line of positions from a to b
        if (a.getX() != b.getX() || a.getZ() != b.getZ()) {
            System.out.println("ERROR: Now you have to implement 3D getline!");
            return null;
        }
        BlockPos[] positions = new BlockPos[Math.abs(a.getY() - b.getY()) + 1];
        int dir = 1;
        if (a.getY() > b.getY()) {
            dir = -1;
        }
        for (int i = 0; i < positions.length; i++)
        {
            positions[i] = new BlockPos(a.getX(), a.getY() + (dir*i), a.getZ());
        }
        //TODO: Validate algo
        return positions;
    }


    public BlockChangeList GetCylinder(net.minecraft.block.BlockState blockState, BlockPos a, BlockPos b, int radius, int thickness) {

        BlockChangeList cylinder = new BlockChangeList("Cylinder");
        BlockPos[] line = GetLine(a,b);
        for (BlockPos p : line) {
            cylinder.Add(GetCircle(blockState, p, radius, thickness));
        }
        return cylinder;
    }

    public BlockChangeList GetSolidRectangle(net.minecraft.block.BlockState blockState, BlockPos a, BlockPos b) {
        BlockChangeList rectangle = new BlockChangeList("SolidRectangle");
        BlockPos min = GetMin(a,b);
        BlockPos max = GetMax(a,b);
        System.out.println("GetSolidRectangle " + min.toString() + "," + max.toString());
        for (int x = min.getX(); x <= max.getX(); x++) {
            for (int y = min.getY(); y <= max.getY(); y++) {
                for (int z = min.getZ(); z <= max.getZ(); z++) {
                    rectangle.Add(new BlockChanges(blockState, x , y, z));
                }
            }
        }
        System.out.println("GetSolidRectangle added " + rectangle.changes.size() + " blocks");
        rectangle.PrintSlice();
        return rectangle;
    }

}
