/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.command.impl.server;

import me.friendly.exeter.command.Argument;
import me.friendly.exeter.command.Command;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;

public final class Connect
extends Command {
    public Connect() {
        super(new String[]{"connect", "c"}, new Argument("ip"));
    }

    @Override
    public String dispatch() {
        ServerData serverData = new ServerData("", this.getArgument("ip").getValue(), false);
        this.minecraft.world.sendQuittingDisconnectingPacket();
        this.minecraft.loadWorld(null);
        this.minecraft.displayGuiScreen(new GuiConnecting(null, this.minecraft, serverData));
        return "Connecting...";
    }
}

