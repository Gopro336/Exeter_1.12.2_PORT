/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.keybind;

import java.util.ArrayList;
import me.friendly.api.registry.ListRegistry;
import me.friendly.exeter.Exeter;
import me.friendly.exeter.keybind.Keybind;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public final class KeybindManager
extends ListRegistry<Keybind> {
    public KeybindManager() {
        this.registry = new ArrayList();


    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(final InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            KeybindManager.this.registry.forEach(keybind -> {
                if (keybind.getKey() != 0 && keybind.getKey() == Keyboard.getEventKey()) {
                    keybind.onPressed();
                }
            });
        }
    }

    /*@SubscribeEvent
    public void onKeyPress(final InputEvent.KeyInputEvent event) {
        if (event.getType() == InputEvent.Type.KEYBOARD_KEY_PRESS) {
            KeybindManager.this.registry.forEach(keybind -> {
                if (keybind.getKey() != 0 && keybind.getKey() == event.getKey()) {
                    keybind.onPressed();
                }
            });
        }
    }*/

    public Keybind getKeybindByLabel(String label) {
        for (Keybind keybind : this.registry) {
            if (!label.equalsIgnoreCase(keybind.getLabel())) continue;
            return keybind;
        }
        return null;
    }
}

