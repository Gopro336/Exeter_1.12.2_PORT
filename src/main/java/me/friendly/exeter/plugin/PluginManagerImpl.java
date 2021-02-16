/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.plugin;

import java.io.File;
import java.io.IOException;

public interface PluginManagerImpl<T> {
    public File getFile();

    public void onLoad() throws IOException;

    public boolean needsUpdate();

    default public boolean needsUpdate(T type) {
        return this.needsUpdate();
    }

    default public void onLoad(T type) throws IOException {
        this.onLoad();
    }
}

