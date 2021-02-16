/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.command.impl.player;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import me.friendly.exeter.command.Argument;
import me.friendly.exeter.command.Command;

public final class Grab
extends Command {
    public Grab() {
        super(new String[]{"grab", "grabip", "grabcoords"}, new Argument("ip|coords"));
    }

    @Override
    public String dispatch() {
        String type;
        switch (type = this.getArgument("ip|coords").getValue()) {
            case "ip": 
            case "i": {
                String address = this.minecraft.getCurrentServerData().serverIP;
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(address), null);
                break;
            }
            case "coords": 
            case "coord": 
            case "coordinates": 
            case "coordinate": 
            case "c": {
                String coords = String.format("X: %s, Y: %s, Z: %s", (int)this.minecraft.player.posX, (int)this.minecraft.player.posY, (int)this.minecraft.player.posZ);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(coords), null);
                break;
            }
            default: {
                return "Incorrect type.";
            }
        }
        return "Copied the selected type.";
    }
}

