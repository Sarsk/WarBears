package com.sarsk.warbears.village;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class BlockChangeList {

    public List<BlockChanges> changes;
    public String name;

    public BlockChangeList(String n) {
        this.name = n;
        this.changes = new ArrayList<>();
    }
    public BlockChangeList() {
        this.name = "";
        this.changes = new ArrayList<>();
    }

    public void Add(BlockChanges c) {
        changes.add(new BlockChanges(c));
    }
    public void Add(net.minecraft.block.BlockState s, BlockPos c, int x, int y, int z) {
        Add(new BlockChanges(s, c, x, y, z));
    }
    public void Add(net.minecraft.block.Block b, BlockPos c, int x, int y, int z) {
        Add(new BlockChanges(b, c, x, y, z));
    }
    public void Add(BlockChangeList b) {
        // Note: Cant directly add the list, as it will be by reference
        for (BlockChanges c : b.changes) {
            Add(c);
        }
    }

    public BlockChangeList SetName(String n) {
        name = n;
        return this;
    }

    /*
     * Rotate the blocks around the Y axis
     * Turn of 90, 180, 270
     */
    public BlockChangeList Rotate(int turn) {
        //  TODO: Replace a percentage of the list blocks with the new block
        for (int i = 0; i < changes.size(); i++) {
            BlockChanges a = changes.get(i);
            if (turn == 90) {
                a.pos = new BlockPos(-a.pos.getZ(), a.pos.getY(), a.pos.getX());
            } else if (turn == 180) {
                a.pos = new BlockPos(-a.pos.getX(), a.pos.getY(),- a.pos.getZ());
            } else if (turn == 270) {
                a.pos = new BlockPos( a.pos.getZ(), a.pos.getY(), -a.pos.getX());
            }

            changes.set(i, a);
        }
        return this;
    }

    /*
     * Merge all of blocks b with blocks a, overwriting any a
     */
    public BlockChangeList MergeChanges(BlockChangeList newList) {
        List<BlockChanges> b = newList.changes;
        System.out.println("Merging " + newList.changes.size() + " blocks from " + newList.name + " into " + changes.size() + " for " + name);

        for (int i = 0; i < b.size(); i++) {
            BlockChanges _b = b.get(i);
            boolean found = false;

            for (int j = 0; j < changes.size(); j++) {
                BlockChanges _a = changes.get(j);
                if (_a.SamePosition(_b)) {
                    System.out.println("Replacing " + _a.blockState.toString() + " with " + _b.blockState.toString() + " at " + _a.pos.toString() + " = " + _b.pos.toString());
                    changes.set(j, _b);
                    found = true;
                }
            }

            if (found == false) {
                System.out.println("Merge not found. Adding " + _b.blockState.toString() + " at " + _b.pos.toString());
                Add(_b);
            }
        }
        return this;
    }


    /*
     * Replace a percentage of the list blocks with the new block
     */
    public BlockChangeList RandomReplace(BlockState newBlockState, int percentage) {
        for (int i = 0; i < changes.size(); i++) {
            if (Math.random()*100 < percentage) {
                BlockChanges a = changes.get(i);
                a.blockState = newBlockState;
                changes.set(i, a);
            }
        }
        return this;
    }


    public BlockChangeList RemovePosition(BlockPos pos) {
        for (int i = 0; i < changes.size(); i++) {
            if (changes.get(i).SamePosition(pos)) {
                System.out.println("Removing " + pos.toString());
                changes.remove(i);
                i--;
            }
        }
        return this;
    }

    public BlockChangeList Translate(BlockPos p) {
        return Translate(p.getX(), p.getY(), p.getZ());
    }

    public BlockChangeList Translate(final int x , final int y, final int z) {
        for (int i = 0; i < changes.size(); i++) {
            BlockChanges c = changes.get(i);
            c.pos = c.pos.add(x,y,z);
            changes.set(i, c);
        }
        return this;
    }
    /*
     * Get min and max
     */
    public BlockPos[] GetRange() {
        BlockPos min = new BlockPos(changes.get(0).pos);
        BlockPos max = new BlockPos(changes.get(0).pos);

        for (int i = 0; i < changes.size(); i++) {
            BlockPos p = changes.get(i).pos;
            if (p.getX() < min.getX()) min = new BlockPos(p.getX(), min.getY(), min.getZ());
            if (p.getY() < min.getY()) min = new BlockPos(min.getX(), p.getY(), min.getZ());
            if (p.getZ() < min.getZ()) min = new BlockPos(min.getX(), min.getY(), p.getZ());
            if (p.getX() > max.getX()) max = new BlockPos(p.getX(), max.getY(), max.getZ());
            if (p.getY() > max.getY()) max = new BlockPos(max.getX(), p.getY(), max.getZ());
            if (p.getZ() > max.getZ()) max = new BlockPos(max.getX(), max.getY(), p.getZ());
        }
        return new BlockPos[] {min, max};
    }

    public BlockChangeList GetCopy() {
        BlockChangeList b = new BlockChangeList(this.name + ".");
        b.Add(this);
        return b;
    }


    public BlockChangeList AddSlice(String changes, BlockStateMap[] map) {
        //System.out.println("AddSlice");
        String rows[] = changes.split("\n");
        //System.out.println("Split into " + rows.length + " rows");

        for (int z = 0; z < rows.length; z++) {
            String row = rows[z];
            //System.out.println("Row " + z + " = (" + row + ")");
            for (int x = 0; x < row.length(); x++) {
                final char c = row.charAt(x);
                for (BlockStateMap m : map) {
                    if (c == m.code) {
                        Add(new BlockChanges(m.state, x,0, z));
                        break;
                    }
                }
            }
        }
        return this;
    }

    /*
     * Return a slice of the total block change list
     */
    public BlockChangeList GetSlice(int y) {
        BlockChangeList slice = new BlockChangeList("Slice Y=" + y + " of " + name);
        for (int i = 0; i < changes.size(); i++) {
            if (changes.get(i).pos.getY() == y) {
                slice.Add(changes.get(i));
            }
        }
        return slice;
    }

    public void PrintList(PlayerEntity player) {
        player.sendMessage(new TranslationTextComponent("Printing list of " + changes.size() + " blocks."));
        for (int i = 0; i < changes.size(); i++) {
            BlockChanges c = changes.get(i);

            player.sendMessage(new TranslationTextComponent("Block " + i + ": " + c.pos.toString() + ", " + c.blockState.toString()));
        }
    }

    public void Print() {
        System.out.println("Printing entire stack of " + name);
        //Get the slice range
        BlockPos range[] = GetRange();
        for (int y = range[1].getY(); y >= range[0].getY(); y--) {
            BlockChangeList slice = GetSlice(y);
            slice.PrintSlice();
        }
    }

    public void PrintSlice() {
        BlockPos[] range = GetRange();
        System.out.println(name + " slice from " + range[0].toString() + " to " + range[1].toString());

        for (int z = range[0].getZ(); z <= range[1].getZ(); z++) {
            String row = new String();
            for (int x = range[0].getX(); x <= range[1].getX(); x++) {
                BlockPos p = new BlockPos(x,range[0].getY(), z);

                if (HasBlock(p)) {
                    BlockChanges b = GetBlock(p);

                    if (b.blockState.getBlock() == Blocks.AIR) {
                        row = row.concat("A");
                    }
                    else if (b.blockState.getBlock() == Blocks.COBBLESTONE) {
                        row = row.concat("C");
                    }
                    else if (b.blockState.getBlock() == Blocks.MOSSY_COBBLESTONE) {
                        row = row.concat("M");
                    }
                    else if (b.blockState.getBlock() == Blocks.COBBLESTONE_STAIRS) {
                        row = row.concat("S");
                    }
                    else {
                        row = row.concat("?");
                    }
                }else {
                    row = row.concat(".");
                }
            }
            System.out.println("Row" + z + ": " + row);
        }
    }

    boolean SamePosition(BlockPos a, BlockPos b) {
        return (a.getX() == b.getX()) && (a.getY() == b.getY()) && (a.getZ() == b.getZ());
    }

    boolean HasBlock(BlockPos pos) {
        for (int i = 0; i < changes.size(); i++) {
            if (changes.get(i).SamePosition(pos)) {
                return true;
            }
        }
        return false;
    }

    BlockChanges GetBlock(BlockPos pos) {
        for (int i = 0; i < changes.size(); i++) {
            if (changes.get(i).SamePosition(pos)) {
                return changes.get(i);
            }
        }
        return null;
    }

}
