/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.module.impl.toggle.render;

import me.friendly.exeter.module.ModuleType;
import me.friendly.exeter.module.ToggleableModule;

public final class ClickGui
extends ToggleableModule {
    public ClickGui() {
        super("Click Gui", new String[]{"clickgui"}, ModuleType.RENDER);
    }

    @Override
    protected void onEnable() {
        super.onEnable();
        this.minecraft.displayGuiScreen(me.friendly.exeter.module.impl.toggle.render.clickgui.ClickGui.getClickGui());
        this.setRunning(false);
    }
}

