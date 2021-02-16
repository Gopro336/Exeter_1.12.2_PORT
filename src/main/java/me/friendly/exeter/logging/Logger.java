/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.logging;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public final class Logger {
    private static Logger logger = null;

    public void print(String message) {
        System.out.println(String.format("[%s] %s", "Exeter", message));
    }

    public void printToChat(String message) {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString(String.format("\u00a7c[%s] \u00a77%s", "Exeter", message.replace("&", "\u00a7"))).setStyle(new Style().setColor(TextFormatting.GRAY)));
    }

    public static Logger getLogger() {
        return logger == null ? (logger = new Logger()) : logger;
    }
}

