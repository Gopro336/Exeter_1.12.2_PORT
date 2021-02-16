/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.properties;

import me.friendly.exeter.properties.Property;

public class NumberProperty<T extends Number>
extends Property<T> {
    private final T minimum;
    private final T maximum;
    private boolean clamp;

    public NumberProperty(T value, T minimum, T maximum, String ... aliases) {
        super(value, aliases);
        this.clamp = true;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public NumberProperty(T value, String ... aliases) {
        super(value, aliases);
        this.clamp = false;
        this.maximum = null;
        this.minimum = null;
    }

    public T getMaximum() {
        return this.maximum;
    }

    public T getMinimum() {
        return this.minimum;
    }

    @Override
    public void setValue(T value) {
        if (this.clamp) {
            if (value instanceof Integer) {
                if (((Number)value).intValue() > ((Number)this.maximum).intValue()) {
                    value = this.maximum;
                } else if (((Number)value).intValue() < ((Number)this.minimum).intValue()) {
                    value = this.minimum;
                }
            } else if (value instanceof Float) {
                if (((Number)value).floatValue() > ((Number)this.maximum).floatValue()) {
                    value = this.maximum;
                } else if (((Number)value).floatValue() < ((Number)this.minimum).floatValue()) {
                    value = this.minimum;
                }
            } else if (value instanceof Double) {
                if (((Number)value).doubleValue() > ((Number)this.maximum).doubleValue()) {
                    value = this.maximum;
                } else if (((Number)value).doubleValue() < ((Number)this.minimum).doubleValue()) {
                    value = this.minimum;
                }
            } else if (value instanceof Long) {
                if (((Number)value).longValue() > ((Number)this.maximum).longValue()) {
                    value = this.maximum;
                } else if (((Number)value).longValue() < ((Number)this.minimum).longValue()) {
                    value = this.minimum;
                }
            } else if (value instanceof Short) {
                if (((Number)value).shortValue() > ((Number)this.maximum).shortValue()) {
                    value = this.maximum;
                } else if (((Number)value).shortValue() < ((Number)this.minimum).shortValue()) {
                    value = this.minimum;
                }
            } else if (value instanceof Byte) {
                if (((Number)value).byteValue() > ((Number)this.maximum).byteValue()) {
                    value = this.maximum;
                } else if (((Number)value).byteValue() < ((Number)this.minimum).byteValue()) {
                    value = this.minimum;
                }
            }
        }
        super.setValue(value);
    }
}

