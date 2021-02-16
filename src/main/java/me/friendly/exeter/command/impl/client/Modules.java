/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.command.impl.client;

import java.util.List;
import java.util.StringJoiner;
import me.friendly.api.interfaces.Toggleable;
import me.friendly.exeter.command.Argument;
import me.friendly.exeter.command.Command;
import me.friendly.exeter.Exeter;
import me.friendly.exeter.module.Module;
import me.friendly.exeter.module.ToggleableModule;

public final class Modules
extends Command {
    public Modules() {
        super(new String[]{"modules", "mods", "ms", "ml", "lm"}, new Argument[0]);
    }

    @Override
    public String dispatch() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        List<Module> modules = Exeter.getInstance().getModuleManager().getRegistry();
        modules.sort((mod1, mod2) -> mod1.getLabel().compareTo(mod2.getLabel()));
        modules.forEach(module -> {
            if (module instanceof Toggleable) {
                ToggleableModule toggleableModule = (ToggleableModule)module;
                stringJoiner.add(String.format("%s%s&7", toggleableModule.isRunning() ? "&a" : "&c", toggleableModule.getLabel()));
            }
        });
        return String.format("Modules (%s) %s", Exeter.getInstance().getModuleManager().getRegistry().size(), stringJoiner.toString());
    }
}

