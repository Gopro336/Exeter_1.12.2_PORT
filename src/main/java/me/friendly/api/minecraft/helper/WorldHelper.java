/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.api.minecraft.helper;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

public final class WorldHelper {
    private static Minecraft minecraft = Minecraft.getMinecraft();

    public static BlockPos getSpawnPoint() {
        return WorldHelper.minecraft.world.getSpawnPoint();
    }

    public static Block getBlock(double x, double y, double z) {
        return WorldHelper.minecraft.world.getBlockState(new BlockPos(x, y, z)).getBlock();
    }
}

