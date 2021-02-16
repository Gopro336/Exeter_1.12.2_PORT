/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.config;

import java.io.File;
import me.friendly.api.interfaces.Labeled;
import me.friendly.exeter.Exeter;

public abstract class Config
implements Labeled {
    private final String label;
    private final File file;
    private final File directory;

    public Config(String label) {
        this.label = label;
        this.directory = Exeter.getInstance().getDirectory();
        this.file = new File(this.directory, label);
        Exeter.getInstance().getConfigManager().register(this);
    }

    public Config(String label, File directory) {
        this.label = label;
        this.directory = directory;
        this.file = new File(directory, label);
        Exeter.getInstance().getConfigManager().register(this);
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    public File getDirectory() {
        return this.directory;
    }

    public File getFile() {
        return this.file;
    }

    public abstract void load(Object ... var1);

    public abstract void save(Object ... var1);
}

