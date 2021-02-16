/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.api.minecraft.helper;

import java.util.UUID;
import net.minecraft.client.Minecraft;
//import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;

public final class EntityHelper {
    private static Minecraft minecraft = Minecraft.getMinecraft();

    public static Entity getClosestEntity(double x, double y, double z, float distance) {
        double var9 = -1.0;
        Entity closestEntity = null;
        for (int loadedEntity = 0; loadedEntity < EntityHelper.minecraft.world.loadedEntityList.size(); ++loadedEntity) {
            Entity entity = (Entity)EntityHelper.minecraft.world.loadedEntityList.get(loadedEntity);
            //if (!IEntitySelector.NOT_SPECTATING.apply((Object)entity)) continue;
            double distanceSq = entity.getDistanceSq(x, y, z);
            if (!((double)distance < 0.0) && !(distanceSq < (double)(distance * distance)) || var9 != -1.0 && !(distanceSq < var9)) continue;
            var9 = distanceSq;
            closestEntity = entity;
        }
        return closestEntity;
    }

    public static float[] getRotations(Entity entity) {
        double positionX = entity.posX - EntityHelper.minecraft.player.posX;
        double positionZ = entity.posZ - EntityHelper.minecraft.player.posZ;
        double positionY = entity.posY + (double)entity.getEyeHeight() / 1.3 - (EntityHelper.minecraft.player.posY + (double)EntityHelper.minecraft.player.getEyeHeight());
        double positions = MathHelper.sqrt(positionX * positionX + positionZ * positionZ);
        float yaw = (float)(Math.atan2(positionZ, positionX) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float)(-(Math.atan2(positionY, positions) * 180.0 / Math.PI));
        return new float[]{yaw, pitch};
    }

    public static float[] getRotationsAtLocation(Location location, Entity entity) {
        double locationMath;
        double positionX = entity.posX - EntityHelper.minecraft.player.posX;
        double positionZ = entity.posZ - EntityHelper.minecraft.player.posZ;
        switch (location) {
            case HEAD: {
                locationMath = 1.0;
                break;
            }
            case BODY: {
                locationMath = 1.3;
                break;
            }
            case LEGS: {
                locationMath = 2.9;
                break;
            }
            case FEET: {
                locationMath = 4.0;
                break;
            }
            default: {
                locationMath = 1.3;
            }
        }
        double positionY = entity.posY + (double)entity.getEyeHeight() / locationMath - (EntityHelper.minecraft.player.posY + (double)EntityHelper.minecraft.player.getEyeHeight());
        double positions = MathHelper.sqrt(positionX * positionX + positionZ * positionZ);
        float yaw = (float)(Math.atan2(positionZ, positionX) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float)(-(Math.atan2(positionY, positions) * 180.0 / Math.PI));
        return new float[]{yaw, pitch};
    }

    public static UUID getUUID(EntityPlayer entityPlayer) {
        return entityPlayer.getUniqueID();
    }

    public static enum Location {
        HEAD,
        BODY,
        LEGS,
        FEET;

    }
}

