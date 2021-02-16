/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.presets;

import me.friendly.api.interfaces.Labeled;

public abstract class Preset
implements Labeled {
    private final String label;

    protected Preset(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    public abstract void onSet();
}

