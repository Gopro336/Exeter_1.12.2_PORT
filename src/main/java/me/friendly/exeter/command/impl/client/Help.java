/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.command.impl.client;

import java.util.StringJoiner;
import me.friendly.exeter.command.Argument;
import me.friendly.exeter.command.Command;
import me.friendly.exeter.Exeter;

public final class Help
extends Command {
    public Help() {
        super(new String[]{"help", "halp", "autism", "how"}, new Argument[0]);
    }

    @Override
    public String dispatch() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        Exeter.getInstance().getCommandManager().getRegistry().forEach(command -> stringJoiner.add(command.getAliases()[0]));
        return String.format("Commands (%s) %s", Exeter.getInstance().getCommandManager().getRegistry().size(), stringJoiner.toString());
    }
}

