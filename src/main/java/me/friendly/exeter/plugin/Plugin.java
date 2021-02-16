/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.plugin;

public abstract class Plugin {
    private final String name;
    private boolean running;

    public Plugin(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void setRunning(boolean state) {
        boolean update = this.running != state;
        this.running = state;
        if (update) {
            if (this.isRunning()) {
                this.startPlugin();
            } else {
                this.stopPlugin();
            }
        }
    }

    public abstract void startPlugin();

    public abstract void stopPlugin();
}

