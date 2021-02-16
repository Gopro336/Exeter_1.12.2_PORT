/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.config;

import java.util.ArrayList;
import me.friendly.api.registry.ListRegistry;
import me.friendly.exeter.config.Config;

public final class ConfigManager
extends ListRegistry<Config> {
    public ConfigManager() {
        this.registry = new ArrayList();
    }
}

