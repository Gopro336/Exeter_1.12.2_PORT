/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.properties;

import me.friendly.exeter.properties.Property;

public class EnumProperty<T extends Enum> extends Property<T>
{
    public EnumProperty(final T value, final String... aliases) {
        super(value, aliases);
    }

    public String getFixedValue() {
        return Character.toString(this.value.name().charAt(0)) + this.value.name().toLowerCase().replaceFirst(Character.toString(this.value.name().charAt(0)).toLowerCase(), "");
    }

    public void setValue(final String value) {
        Enum[] array;
        for (int length = (array = (Enum[])this.getValue().getClass().getEnumConstants()).length, i = 0; i < length; ++i) {
            if (array[i].name().equalsIgnoreCase(value)) {
                this.value = (T)array[i];
            }
        }
    }

    public void increment() {
        Enum[] array;
        for (int length = (array = (Enum[])this.getValue().getClass().getEnumConstants()).length, i = 0; i < length; ++i) {
            if (array[i].name().equalsIgnoreCase(this.getFixedValue())) {
                if (++i > array.length - 1) {
                    i = 0;
                }
                this.setValue(array[i].toString());
            }
        }
    }

    public void decrement() {
        Enum[] array;
        for (int length = (array = (Enum[])this.getValue().getClass().getEnumConstants()).length, i = 0; i < length; ++i) {
            if (array[i].name().equalsIgnoreCase(this.getFixedValue())) {
                if (--i < 0) {
                    i = array.length - 1;
                }
                this.setValue(array[i].toString());
            }
        }
    }
}
