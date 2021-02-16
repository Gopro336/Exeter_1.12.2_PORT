/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.command.impl.player;

import me.friendly.exeter.command.Argument;
import me.friendly.exeter.command.Command;

public final class HClip
extends Command {
    public HClip() {
        super(new String[]{"hclip", "hc", "h"}, new Argument("blocks"));
    }

    @Override
    public String dispatch() {
        double blocks = Double.parseDouble(this.getArgument("blocks").getValue());
        double x = Math.cos(Math.toRadians(this.minecraft.player.rotationYaw + 90.0f));
        double z = Math.sin(Math.toRadians(this.minecraft.player.rotationYaw + 90.0f));
        this.minecraft.player.setPosition(this.minecraft.player.posX + (1.0 * blocks * x + 0.0 * blocks * z), this.minecraft.player.posY, this.minecraft.player.posZ + (1.0 * blocks * z - 0.0 * blocks * x));
        return String.format("Teleported %s &e%s&7 block(s).", blocks < 0.0 ? "back" : "forward", blocks);
    }
}

