/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.module.impl.toggle.render.tabgui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import me.friendly.api.interfaces.Toggleable;
import me.friendly.api.minecraft.render.RenderMethods;
import me.friendly.exeter.Exeter;
import me.friendly.exeter.module.Module;
import me.friendly.exeter.module.ModuleType;
import me.friendly.exeter.module.ToggleableModule;
import me.friendly.exeter.module.impl.toggle.render.tabgui.item.GuiItem;
import me.friendly.exeter.module.impl.toggle.render.tabgui.item.GuiTab;
import net.minecraft.client.Minecraft;

public final class GuiTabHandler {
    private final Minecraft mc = Minecraft.getMinecraft();
    private float width = 0.7F;
    private int guiHeight = 0;
    public boolean mainMenu = true;
    public int selectedItem = 0;
    public int selectedTab = 0;
    private final int tabHeight = 12;
    public final ArrayList tabs = new ArrayList();
    public int transition = 0;
    public boolean visible = true;

    public GuiTabHandler() {
        List modules = Exeter.getInstance().getModuleManager().getRegistry();
        modules.sort((mod1, mod2) -> {
            //return mod1.getLabel().compareTo(mod2.getLabel());
            return mod1.toString().compareTo(mod2.toString());
        });
        //ModuleType[]
        modules.sort(Comparator.comparing(Object::toString));
        /*modules.sort((mod1, mod2) -> {
            //return mod1.toString().compareTo(mod2.toString());
            return mod1.toString().compareTo(mod2.toString());
        });*/
        /*modules.sort((mod1, mod2) -> {
            //return mod1.getLabel().compareTo(mod2.getLabel());
            return mod1.getLabel().compareTo(mod2.getLabel());
        });*/
        ModuleType[] var2 = ModuleType.values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            ModuleType moduleType = var2[var4];
            GuiTab guiTab = new GuiTab(this, moduleType.getLabel());
            modules.stream().forEach((module) -> {
                if (module instanceof Toggleable) {
                    ToggleableModule toggle = (ToggleableModule)module;
                    if (toggle.getModuleType() == moduleType && !toggle.getLabel().equalsIgnoreCase("Click Gui") && !toggle.getLabel().equalsIgnoreCase("Tab Gui")) {
                        guiTab.getMods().add(new GuiItem(toggle));
                    }
                }

            });
            this.tabs.add(guiTab);
        }

        Collections.sort(this.tabs, Comparator.comparing(Object::toString));
        /*Collections.sort(this.tabs, (category1, category2) -> {
            //return category1.toString().compareTo(category2.toString());
            //return category1.toString().compareTo(category2.toString());
        });*/
        /*Collections.sort(this.tabs, (category1, category2) -> {
            //return category1.getLabel().compareTo(category2.getLabel());
            //return category1.getLabel().compareTo(category2.getLabel());
        });*/
        this.guiHeight = this.tabs.size() * this.tabHeight;
    }

    public void drawGui(int x, int y) {
        if (this.visible) {
            int guiWidth = 73;
            RenderMethods.drawBorderedRectReliant((float)x, (float)y - 0.4F, (float)(x + guiWidth - 2), (float)(y + this.guiHeight) + 0.4F, 1.5F, 1711276032, -2013265920);

            int yOff;
            int index;
            for(yOff = 0; yOff < this.tabs.size(); ++yOff) {
                index = !this.mainMenu ? 0 : this.transition + (this.selectedTab == 0 && this.transition < 0 ? -this.transition : 0);
                int transitionBottom = !this.mainMenu ? 0 : this.transition + (this.selectedTab == this.tabs.size() - 1 && this.transition > 0 ? -this.transition : 0);
                if (this.selectedTab == yOff) {
                    RenderMethods.drawGradientBorderedRectReliant((float)x, (float)(yOff * 12 + y + index) - 0.3F, (float)(x + guiWidth) - 2.2F, (float)(yOff + y + 12 + yOff * 11 + transitionBottom) + 0.3F, 1.5F, -2013265920, -1141161406, -1141161406);
                }
            }

            yOff = y + 2;

            for(index = 0; index < this.tabs.size(); ++index) {
                GuiTab tab = (GuiTab)this.tabs.get(index);
                this.mc.fontRenderer.drawStringWithShadow(tab.getLabel(), (float)(x + 2), (float)yOff, -197380);
                if (this.selectedTab == index && !this.mainMenu) {
                    tab.drawTabMenu(this.mc, x + guiWidth, yOff - 2);
                }

                yOff += this.tabHeight;
            }

            if (this.transition > 0) {
                --this.transition;
            } else if (this.transition < 0) {
                ++this.transition;
            }

        }
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public int getSelectedItem() {
        return this.selectedItem;
    }

    public int getTabHeight() {
        return this.tabHeight;
    }

    public int getTransition() {
        return this.transition;
    }
}
