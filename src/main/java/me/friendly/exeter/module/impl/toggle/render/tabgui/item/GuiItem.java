/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.module.impl.toggle.render.tabgui.item;

import me.friendly.exeter.module.ToggleableModule;

public class GuiItem {
    private final ToggleableModule toggleableModule;

    public GuiItem(ToggleableModule toggleableModule) {
        this.toggleableModule = toggleableModule;
    }

    public ToggleableModule getToggleableModule() {
        return this.toggleableModule;
    }
}

