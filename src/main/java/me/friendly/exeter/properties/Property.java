/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.properties;

public class Property<T> {
    private final String[] aliases;
    protected T value;

    public Property(T value, String ... aliases) {
        this.value = value;
        this.aliases = aliases;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

