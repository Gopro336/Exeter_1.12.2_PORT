/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.module.impl.toggle.render.clickgui;

import java.util.ArrayList;
import me.friendly.api.interfaces.Labeled;
import me.friendly.api.minecraft.render.CustomFont;
import me.friendly.api.minecraft.render.RenderMethods;
import me.friendly.exeter.module.impl.toggle.render.clickgui.ClickGui;
import me.friendly.exeter.module.impl.toggle.render.clickgui.item.Button;
import me.friendly.exeter.module.impl.toggle.render.clickgui.item.Item;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;

public abstract class Panel
implements Labeled {
    private final String label;
    private int x;
    private int y;
    private int x2;
    private int y2;
    private int width;
    private int height;
    private boolean open;
    public boolean drag;
    private final ArrayList<Item> items = new ArrayList();

    public Panel(String label, int x, int y, boolean open) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.width = 88;
        this.height = 18;
        this.open = open;
        this.setupItems();
    }

    public abstract void setupItems();

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drag(mouseX, mouseY);
        float totalItemHeight = this.open ? this.getTotalItemHeight() - 2.0f : 0.0f;
        RenderMethods.drawGradientRect(this.x, (float)this.y - 1.5f, this.x + this.width, this.y + this.height - 6, -7829368, -6710887);
        if (this.open) {
            RenderMethods.drawRect(this.x, (float)this.y + 12.5f, this.x + this.width, this.open ? (float)(this.y + this.height) + totalItemHeight : (float)(this.y + this.height - 1), 0x77000000);
        }
        ClickGui.getClickGui().guiFont.drawString(this.getLabel(), (float)this.x + 3.0f, (float)this.y - 4.0f, CustomFont.FontType.SHADOW_THIN, -1);
        if (this.open) {
            float y = (float)(this.getY() + this.getHeight()) - 3.0f;
            for (Item item : this.getItems()) {
                item.setLocation((float)this.x + 2.0f, y);
                item.setWidth(this.getWidth() - 4);
                item.drawScreen(mouseX, mouseY, partialTicks);
                y += (float)item.getHeight() + 1.5f;
            }
        }
    }

    private void drag(int mouseX, int mouseY) {
        if (!this.drag) {
            return;
        }
        this.x = this.x2 + mouseX;
        this.y = this.y2 + mouseY;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && this.isHovering(mouseX, mouseY)) {
            this.x2 = this.x - mouseX;
            this.y2 = this.y - mouseY;
            ClickGui.getClickGui().getPanels().forEach(panel -> {
                if (panel.drag) {
                    panel.drag = false;
                }
            });
            this.drag = true;
            return;
        }
        if (mouseButton == 1 && this.isHovering(mouseX, mouseY)) {
            this.open = !this.open;
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            //Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("random.click"), 1.0f));
            return;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseClicked(mouseX, mouseY, mouseButton));
    }

    public void addButton(Button button) {
        this.items.add(button);
    }

    public void mouseReleased(int mouseX, int mouseY, int releaseButton) {
        if (releaseButton == 0) {
            this.drag = false;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseReleased(mouseX, mouseY, releaseButton));
    }

    @Override
    public final String getLabel() {
        return this.label;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean getOpen() {
        return this.open;
    }

    public final ArrayList<Item> getItems() {
        return this.items;
    }

    private boolean isHovering(int mouseX, int mouseY) {
        return mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight() - (this.open ? 2 : 0);
    }

    private float getTotalItemHeight() {
        float height = 0.0f;
        for (Item item : this.getItems()) {
            height += (float)item.getHeight() + 1.5f;
        }
        return height;
    }

    public void setX(int dragX) {
        this.x = dragX;
    }

    public void setY(int dragY) {
        this.y = dragY;
    }
}

