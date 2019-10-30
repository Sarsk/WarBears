package com.sarsk.warbears.village;

import net.minecraft.block.BlockState;

public class BlockStateMap {
    public BlockState state;
    public char code;

    public BlockStateMap(char c, BlockState s) {
        state = s;
        code = c;
    }
}
