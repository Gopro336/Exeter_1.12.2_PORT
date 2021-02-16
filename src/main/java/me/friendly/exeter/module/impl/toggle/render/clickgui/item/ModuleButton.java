/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.module.impl.toggle.render.clickgui.item;

import java.util.ArrayList;
import java.util.List;
import me.friendly.api.minecraft.render.CustomFont;
import me.friendly.exeter.module.Module;
import me.friendly.exeter.module.ToggleableModule;
import me.friendly.exeter.module.impl.toggle.render.clickgui.ClickGui;
import me.friendly.exeter.module.impl.toggle.render.clickgui.item.Button;
import me.friendly.exeter.module.impl.toggle.render.clickgui.item.Item;
import me.friendly.exeter.module.impl.toggle.render.clickgui.item.properties.BooleanButton;
import me.friendly.exeter.module.impl.toggle.render.clickgui.item.properties.EnumButton;
import me.friendly.exeter.properties.EnumProperty;
import me.friendly.exeter.properties.NumberProperty;
import me.friendly.exeter.properties.Property;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;

public class ModuleButton
extends Button {
    private final Module module;
    private List<Item> items = new ArrayList<Item>();
    private boolean subOpen;

    public ModuleButton(Module module) {
        super(module.getLabel());
        this.module = module;
        if (!module.getProperties().isEmpty()) {
            for (Property property : module.getProperties()) {
                if (property.getValue() instanceof Boolean) {
                    this.items.add(new BooleanButton(property));
                }
                if (property instanceof EnumProperty) {
                    this.items.add(new EnumButton((EnumProperty)property));
                }
                if (!(property.getValue() instanceof NumberProperty)) continue;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (!this.items.isEmpty()) {
            ClickGui.getClickGui().guiFont.drawString("...", this.x - 1.0f + (float)this.width - 8.0f, this.y - 2.0f, CustomFont.FontType.SHADOW_THIN, -1);
            if (this.subOpen) {
                float height = 1.0f;
                for (Item item : this.items) {
                    item.setLocation(this.x + 1.0f, this.y + (height += 15.0f));
                    item.setHeight(15);
                    item.setWidth(this.width - 9);
                    item.drawScreen(mouseX, mouseY, partialTicks);
                }
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (!this.items.isEmpty()) {
            if (mouseButton == 1 && this.isHovering(mouseX, mouseY)) {
                this.subOpen = !this.subOpen;
                //Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("random.click"), 1.0f));
                Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            }
            if (this.subOpen) {
                for (Item item : this.items) {
                    item.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }

    @Override
    public int getHeight() {
        if (this.subOpen) {
            int height = 14;
            for (Item item : this.items) {
                height += item.getHeight() + 1;
            }
            return height + 2;
        }
        return 14;
    }

    @Override
    public void toggle() {
        if (this.module instanceof ToggleableModule) {
            ((ToggleableModule)this.module).toggle();
        }
    }

    @Override
    public boolean getState() {
        if (this.module instanceof ToggleableModule) {
            return ((ToggleableModule)this.module).isRunning();
        }
        return true;
    }
}

