/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.module.impl.toggle.render.clickgui.item.properties;

import me.friendly.api.minecraft.render.CustomFont;
import me.friendly.api.minecraft.render.RenderMethods;
import me.friendly.exeter.module.impl.toggle.render.clickgui.ClickGui;
import me.friendly.exeter.module.impl.toggle.render.clickgui.Panel;
import me.friendly.exeter.module.impl.toggle.render.clickgui.item.Item;
import me.friendly.exeter.properties.NumberProperty;

public class NumberSlider
extends Item {
    private NumberProperty numberProperty;

    public NumberSlider(NumberProperty numberProperty) {
        super(numberProperty.getAliases()[0]);
        this.numberProperty = numberProperty;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderMethods.drawRect(this.x, this.y, this.x + this.getValueWidth(), this.y + (float)this.height, !this.isHovering(mouseX, mouseY) ? 2002577475 : -1721964477);
        ClickGui.getClickGui().guiFont.drawString(String.format("%s\u00a77 %s", this.getLabel(), this.numberProperty.getValue()), this.x + 2.3f, this.y - 1.0f, CustomFont.FontType.SHADOW_THIN, -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (!this.isHovering(mouseX, mouseY) || mouseButton == 0) {
            // empty if block
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    private boolean isHovering(int mouseX, int mouseY) {
        for (Panel panel : ClickGui.getClickGui().getPanels()) {
            if (!panel.drag) continue;
            return false;
        }
        return (float)mouseX >= this.getX() && (float)mouseX <= this.getX() + (float)this.getWidth() && (float)mouseY >= this.getY() && (float)mouseY <= this.getY() + (float)this.height;
    }

    private float getValueWidth() {
        return ((Number)this.numberProperty.getMaximum()).floatValue() - ((Number)this.numberProperty.getMinimum()).floatValue() + ((Number)this.numberProperty.getValue()).floatValue();
    }
}

