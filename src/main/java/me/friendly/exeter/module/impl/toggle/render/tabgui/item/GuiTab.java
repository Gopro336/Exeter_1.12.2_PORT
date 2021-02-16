/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.module.impl.toggle.render.tabgui.item;

import java.util.ArrayList;
import me.friendly.api.interfaces.Labeled;
import me.friendly.api.minecraft.render.RenderMethods;
import me.friendly.exeter.module.impl.toggle.render.tabgui.GuiTabHandler;
import me.friendly.exeter.module.impl.toggle.render.tabgui.item.GuiItem;
import net.minecraft.client.Minecraft;

public class GuiTab
implements Labeled {
    private final GuiTabHandler gui;
    private ArrayList<GuiItem> mods = new ArrayList();
    private int menuHeight = 0;
    private int menuWidth = 0;
    private final String label;

    public GuiTab(GuiTabHandler gui, String label) {
        this.gui = gui;
        this.label = label;
    }

    public void drawTabMenu(Minecraft mc, int x, int y) {
        this.countMenuSize(mc);
        int boxY = y;
        RenderMethods.drawBorderedRectReliant(x, (float)y - 0.4f, (float)(x + this.menuWidth) + 4.5f, (float)(y + this.menuHeight) + 0.4f, 1.5f, 0x66000000, -2013265920);
        for (int i = 0; i < this.mods.size(); ++i) {
            int transitionTop = this.gui.getTransition() + (this.gui.getSelectedItem() == 0 && this.gui.getTransition() < 0 ? -this.gui.getTransition() : 0);
            int transitionBottom = this.gui.getTransition() + (this.gui.getSelectedItem() == this.mods.size() - 1 && this.gui.getTransition() > 0 ? -this.gui.getTransition() : 0);
            if (this.gui.getSelectedItem() == i) {
                RenderMethods.drawGradientBorderedRectReliant(x, (float)(boxY + transitionTop) - 0.3f, (float)(x + this.menuWidth) + 4.5f, (float)(boxY + 12 + transitionBottom) + 0.3f, 1.5f, -2013265920, -1141161406, -1141161406);
            }
            mc.fontRenderer.drawStringWithShadow(this.mods.get(i).getToggleableModule().getLabel(), x + 2, y + this.gui.getTabHeight() * i + 2, this.mods.get(i).getToggleableModule().isRunning() ? -310718 : -197380);
            boxY += 12;
        }
    }

    private void countMenuSize(Minecraft mc) {
        int maxWidth = 0;
        for (GuiItem module : this.mods) {
            if (mc.fontRenderer.getStringWidth(module.getToggleableModule().getAliases()[0]) <= maxWidth) continue;
            maxWidth = mc.fontRenderer.getStringWidth(module.getToggleableModule().getAliases()[0]) + 4;
        }
        this.menuWidth = maxWidth;
        this.menuHeight = this.mods.size() * this.gui.getTabHeight();
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    public ArrayList<GuiItem> getMods() {
        return this.mods;
    }
}

