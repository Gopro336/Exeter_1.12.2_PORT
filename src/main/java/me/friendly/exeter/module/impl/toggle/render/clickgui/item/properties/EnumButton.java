/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.module.impl.toggle.render.clickgui.item.properties;

import me.friendly.api.minecraft.render.CustomFont;
import me.friendly.api.minecraft.render.RenderMethods;
import me.friendly.exeter.module.impl.toggle.render.clickgui.ClickGui;
import me.friendly.exeter.module.impl.toggle.render.clickgui.item.Button;
import me.friendly.exeter.properties.EnumProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;

public class EnumButton
extends Button {
    private EnumProperty property;

    public EnumButton(EnumProperty property) {
        super(property.getAliases()[0]);
        this.property = property;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderMethods.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height, this.getState() ? (!this.isHovering(mouseX, mouseY) ? 2012955202 : -1711586750) : (!this.isHovering(mouseX, mouseY) ? 0x11333333 : -2009910477));
        ClickGui.getClickGui().guiFont.drawString(String.format("%s\u00a77 %s", this.getLabel(), this.property.getFixedValue()), this.x + 2.3f, this.y - 1.0f, CustomFont.FontType.SHADOW_THIN, this.getState() ? -1 : -5592406);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            //Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("random.click"), 1.0f));
            if (mouseButton == 0) {
                this.property.increment();
            } else if (mouseButton == 1) {
                this.property.decrement();
            }
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    @Override
    public void toggle() {
    }

    @Override
    public boolean getState() {
        return true;
    }
}

