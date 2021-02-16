/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.api.stopwatch;

public class Stopwatch {
    private long previousMS;

    public Stopwatch() {
        this.reset();
    }

    public boolean hasCompleted(long milliseconds) {
        return this.getCurrentMS() - this.previousMS >= milliseconds;
    }

    public void reset() {
        this.previousMS = this.getCurrentMS();
    }

    public long getPreviousMS() {
        return this.previousMS;
    }

    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
}

