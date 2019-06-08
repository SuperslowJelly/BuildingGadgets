package com.direwolf20.buildinggadgets.test.util;

import com.direwolf20.buildinggadgets.api.building.Region;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public final class BlockTestUtils {
    private static final Random RND = new Random();

    private BlockTestUtils() { }

    public static BlockPos randomBlockPosIn(Region r) {
        int x = r.getMinX() + RND.nextInt(Math.abs(r.getMaxX() - r.getMinX()));
        int y = r.getMinZ() + RND.nextInt(Math.abs(r.getMaxY() - r.getMinY()));
        int z = r.getMinZ() + RND.nextInt(Math.abs(r.getMaxZ() - r.getMinZ()));
        return new BlockPos(x, y, z);
    }

    public static Region randomRegion() {
        return new Region(RND.nextInt(), RND.nextInt(), RND.nextInt(), RND.nextInt(), RND.nextInt(), RND.nextInt());
    }

    public static Region randomRegionAroundOrigin() {
        return new Region(- RND.nextInt(Integer.MAX_VALUE), - RND.nextInt(Integer.MAX_VALUE), - RND.nextInt(Integer.MAX_VALUE), RND.nextInt(Integer.MAX_VALUE), RND.nextInt(Integer.MAX_VALUE), RND.nextInt(Integer.MAX_VALUE));
    }
}