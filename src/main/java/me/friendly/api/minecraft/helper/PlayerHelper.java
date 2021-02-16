/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.api.minecraft.helper;

import me.friendly.api.minecraft.helper.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;

public final class PlayerHelper {
    private static Minecraft minecraft = Minecraft.getMinecraft();

    public static Block getBlockBelowPlayer(double height) {
        return WorldHelper.getBlock(PlayerHelper.minecraft.player.posX, PlayerHelper.minecraft.player.posY - height, PlayerHelper.minecraft.player.posZ);
    }

    public static Block getBlockAbovePlayer(double height) {
        return WorldHelper.getBlock(PlayerHelper.minecraft.player.posX, PlayerHelper.minecraft.player.posY + height, PlayerHelper.minecraft.player.posZ);
    }

    public static boolean isInLiquid(double offset) {
        return PlayerHelper.getBlockBelowPlayer(-offset) instanceof BlockLiquid;
    }

    public static boolean isAiming(float yaw, float pitch, int fov) {
        float pitchDiff;
        yaw = PlayerHelper.wrapAngleTo180(yaw);
        pitch = PlayerHelper.wrapAngleTo180(pitch);
        float curYaw = PlayerHelper.wrapAngleTo180(PlayerHelper.minecraft.player.rotationYaw);
        float curPitch = PlayerHelper.wrapAngleTo180(PlayerHelper.minecraft.player.rotationPitch);
        float yawDiff = Math.abs(yaw - curYaw);
        return yawDiff + (pitchDiff = Math.abs(pitch - curPitch)) <= (float)fov;
    }

    public static float getFOV(float[] rotations) {
        float yaw = rotations[0];
        float pitch = rotations[1];
        yaw = PlayerHelper.wrapAngleTo180(yaw);
        pitch = PlayerHelper.wrapAngleTo180(pitch);
        float curYaw = PlayerHelper.wrapAngleTo180(PlayerHelper.minecraft.player.rotationYaw);
        float curPitch = PlayerHelper.wrapAngleTo180(PlayerHelper.minecraft.player.rotationPitch);
        float yawDiff = Math.abs(yaw - curYaw);
        float pitchDiff = Math.abs(pitch - curPitch);
        return yawDiff + pitchDiff;
    }

    public static float wrapAngleTo180(float angle) {
        if ((angle %= 360.0f) >= 180.0f) {
            angle -= 360.0f;
        }
        if (angle < -180.0f) {
            angle += 360.0f;
        }
        return angle;
    }

    public static boolean isMoving() {
        return (double)PlayerHelper.minecraft.player.moveForward != 0.0 || (double)PlayerHelper.minecraft.player.moveStrafing != 0.0;
    }

    /*public static String getFacingWithProperCapitals() {
        String directionLabel;
        switch (directionLabel = minecraft.getRenderViewEntity().getDirectionFacing().getName()) {
            case "north": {
                directionLabel = "North";
                break;
            }
            case "south": {
                directionLabel = "South";
                break;
            }
            case "west": {
                directionLabel = "West";
                break;
            }
            case "east": {
                directionLabel = "East";
            }
        }
        return directionLabel;
    }*/
    public static String getFacingWithProperCapitals()
    {
        switch (MathHelper.floor((double) (Minecraft.getMinecraft().player.rotationYaw * 8.0F / 360.0F) + 0.5D) & 7)
        {
            case 0:
                return "South";
            case 1:
                return "South West";
            case 2:
                return "West";
            case 3:
                return "North West";
            case 4:
                return "North";
            case 5:
                return "North East";
            case 6:
                return "East";
            case 7:
                return "South East";
        }
        return "Invalid";
    }
}

