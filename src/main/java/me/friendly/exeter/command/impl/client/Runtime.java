/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.command.impl.client;

import me.friendly.exeter.command.Argument;
import me.friendly.exeter.command.Command;
import me.friendly.exeter.Exeter;

public final class Runtime
extends Command {
    public Runtime() {
        super(new String[]{"runtime", "time"}, new Argument("format"));
    }

    @Override
    public String dispatch() {
        String runtime;
        long second = (System.nanoTime() / 1000000L - Exeter.getInstance().startTime) / 1000L;
        long minute = second / 60L;
        long hour = minute / 60L;
        switch (this.getArgument("format").getValue()) {
            case "second": {
                runtime = String.format("%s seconds", second);
                break;
            }
            case "minute": {
                runtime = String.format("%s minutes", minute);
                break;
            }
            case "hour": {
                runtime = String.format("%s hours", hour);
                break;
            }
            default: {
                return "Invalid time format, use second, minute, hour.";
            }
        }
        return String.format("You've been playing for &e%s&7.", runtime);
    }
}

