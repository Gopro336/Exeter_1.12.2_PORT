/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.command.impl.client;

import me.friendly.api.interfaces.Toggleable;
import me.friendly.exeter.command.Argument;
import me.friendly.exeter.command.Command;
import me.friendly.exeter.Exeter;
import me.friendly.exeter.module.Module;
import me.friendly.exeter.module.ToggleableModule;

public final class Toggle
extends Command {
    public Toggle() {
        super(new String[]{"toggle", "t"}, new Argument("module"));
    }

    @Override
    public String dispatch() {
        Module module = Exeter.getInstance().getModuleManager().getModuleByAlias(this.getArgument("module").getValue());
        if (module == null) {
            return "No such module exists.";
        }
        if (!(module instanceof Toggleable)) {
            return "That module is not toggleable.";
        }
        ToggleableModule toggleableModule = (ToggleableModule)module;
        toggleableModule.toggle();
        return String.format("&e%s&7 has been %s&7.", toggleableModule.getLabel(), toggleableModule.isRunning() ? "&aenabled" : "&cdisabled");
    }
}

