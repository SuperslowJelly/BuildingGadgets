package com.direwolf20.buildinggadgets.api.building.placement;

import com.direwolf20.buildinggadgets.api.building.IPositionPlacementSequence;
import com.direwolf20.buildinggadgets.api.building.Region;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.AxisDirection;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Spliterator;

/**
 * A wall is a plane of blocks described with a starting and an ending position. The positions will and must have 1
 * coordinate that is the same.
 * <p>
 * See the static factory methods for more information.
 */
public final class Wall implements IPositionPlacementSequence {

    /**
     * Creates a wall centered at the given position.
     *
     * @param center the center of the wall
     * @param side   front face of the wall
     * @param radius radius of the wall
     */
    public static Wall clickedSide(BlockPos center, Direction side, int radius) {
        return new Wall(center, side, radius, null, 0);
    }

    /**
     * Creates a wall extending to some direction with the given position as its bottom.
     *
     * @param posHit    bottom of the wall
     * @param extension top side (growing direction) of the wall
     * @param flatSide  front face of the wall
     * @param radius    radius of the wall.
     * @param extra     amount of blocks to add beyond the radius
     */
    public static Wall extendingFrom(BlockPos posHit, Direction extension, Direction flatSide, int radius, int extra) {
        Preconditions.checkArgument(extension != flatSide, "Cannot have a wall extending to " + extension + " and flat at " + flatSide);
        return new Wall(posHit.offset(extension, radius + 1), flatSide, radius, extension, extra);
    }

    private final Region region;

    @VisibleForTesting
    private Wall(BlockPos posHit, Direction side, int radius, @Nullable Direction extendingSide, int extendingSize) {
        Region createdRegion = new Region(posHit).expand(
                radius * (1 - Math.abs(side.getXOffset())),
                radius * (1 - Math.abs(side.getYOffset())),
                radius * (1 - Math.abs(side.getZOffset())));

        if (extendingSize != 0 && extendingSide != null) {
            if (extendingSide.getAxisDirection() == AxisDirection.POSITIVE)
                this.region = new Region(createdRegion.getMin(), createdRegion.getMax().offset(extendingSide, extendingSize));
            else
                this.region = new Region(createdRegion.getMin().offset(extendingSide, extendingSize), createdRegion.getMax());
        }
        else
            this.region = createdRegion;
    }

    /**
     * For {@link #copy()}
     */
    @VisibleForTesting
    private Wall(Region region) {
        this.region = region;
    }

    @Override
    public Region getBoundingBox() {
        return region;
    }

    @Override
    public boolean mayContain(int x, int y, int z) {
        return region.mayContain(x, y, z);
    }

    @Override
    public IPositionPlacementSequence copy() {
        return new Wall(region);
    }

    @Override
    @Nonnull
    public Iterator<BlockPos> iterator() {
        return region.iterator();
    }

    @Override
    public Spliterator<BlockPos> spliterator() {
        return region.spliterator();
    }

}