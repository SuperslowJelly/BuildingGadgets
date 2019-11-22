package com.direwolf20.buildinggadgets.common.gadgets.building;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class HorizontalColumnMode extends AbstractMode {
    public HorizontalColumnMode(boolean isExchanging) {
        super(isExchanging);
    }

    @Override
    List<BlockPos> collect(EntityPlayer player, BlockPos playerPos, EnumFacing side, int range, BlockPos start) {
        List<BlockPos> coordinates = new ArrayList<>();

        side = XYZ.isAxisY(side) ? player.getHorizontalFacing() : side.getOpposite();
        for (int i = 0; i < range; i++)
            coordinates.add(XYZ.extendPosSingle(i, start, side, XYZ.fromFacing(side)));

        return coordinates;
    }
}
