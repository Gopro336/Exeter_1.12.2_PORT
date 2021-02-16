/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.module.impl.toggle.render;

import me.friendly.exeter.Exeter;
//import me.friendly.exeter.events.RenderGameOverlayEvent;
import me.friendly.exeter.module.ModuleType;
import me.friendly.exeter.module.ToggleableModule;
import me.friendly.exeter.module.impl.active.render.TextGUI;
import me.friendly.exeter.module.impl.toggle.render.tabgui.GuiTabHandler;
import me.friendly.exeter.properties.EnumProperty;
import me.friendly.exeter.properties.Property;
import net.minecraft.client.renderer.GlStateManager;

public final class TabGui
extends ToggleableModule {
    private GuiTabHandler guiTabHandler;
    private final EnumProperty<Mode> mode = new EnumProperty<Mode>(Mode.DEFAULT, "mode", "m");
    public int hexVal = -1152209207;

    public TabGui() {
        super("TabGui", new String[]{"tabgui", "tg"}, ModuleType.RENDER);
        this.listeners.add(new Listener<RenderGameOverlayEvent>("tab_gui_render_game_overlay_listener"){

            @Override
            public void call(RenderGameOverlayEvent event) {
                TextGUI textGUI = (TextGUI)Exeter.getInstance().getModuleManager().getModuleByAlias("textgui");
                Property watermark = textGUI.getPropertyByAlias("Watermark");
                if (TabGui.this.guiTabHandler == null) {
                    TabGui.this.guiTabHandler = new GuiTabHandler();
                }
                if (((TabGui)TabGui.this).minecraft.gameSettings.showDebugInfo) {
                    return;
                }
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                TabGui.this.guiTabHandler.drawGui(3, (Boolean)watermark.getValue() != false ? 13 : 3);
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        });
        this.listeners.add(new Listener<InputEvent>("tab_gui_input_listener"){

            @Override
            public void call(InputEvent event) {
                if (event.getType() == InputEvent.Type.KEYBOARD_KEY_PRESS) {
                    switch (event.getKey()) {
                        case 200: {
                            if (!((TabGui)TabGui.this).guiTabHandler.visible) break;
                            if (((TabGui)TabGui.this).guiTabHandler.mainMenu) {
                                --((TabGui)TabGui.this).guiTabHandler.selectedTab;
                                if (((TabGui)TabGui.this).guiTabHandler.selectedTab < 0) {
                                    ((TabGui)TabGui.this).guiTabHandler.selectedTab = ((TabGui)TabGui.this).guiTabHandler.tabs.size() - 1;
                                }
                                ((TabGui)TabGui.this).guiTabHandler.transition = 11;
                                break;
                            }
                            --((TabGui)TabGui.this).guiTabHandler.selectedItem;
                            if (((TabGui)TabGui.this).guiTabHandler.selectedItem < 0) {
                                ((TabGui)TabGui.this).guiTabHandler.selectedItem = ((TabGui)TabGui.this).guiTabHandler.tabs.get(((TabGui)TabGui.this).guiTabHandler.selectedTab).getMods().size() - 1;
                            }
                            if (((TabGui)TabGui.this).guiTabHandler.tabs.get(((TabGui)TabGui.this).guiTabHandler.selectedTab).getMods().size() <= 1) break;
                            ((TabGui)TabGui.this).guiTabHandler.transition = 11;
                            break;
                        }
                        case 208: {
                            if (!((TabGui)TabGui.this).guiTabHandler.visible) break;
                            if (((TabGui)TabGui.this).guiTabHandler.mainMenu) {
                                ++((TabGui)TabGui.this).guiTabHandler.selectedTab;
                                if (((TabGui)TabGui.this).guiTabHandler.selectedTab > ((TabGui)TabGui.this).guiTabHandler.tabs.size() - 1) {
                                    ((TabGui)TabGui.this).guiTabHandler.selectedTab = 0;
                                }
                                ((TabGui)TabGui.this).guiTabHandler.transition = -11;
                                break;
                            }
                            ++((TabGui)TabGui.this).guiTabHandler.selectedItem;
                            if (((TabGui)TabGui.this).guiTabHandler.selectedItem > ((TabGui)TabGui.this).guiTabHandler.tabs.get(((TabGui)TabGui.this).guiTabHandler.selectedTab).getMods().size() - 1) {
                                ((TabGui)TabGui.this).guiTabHandler.selectedItem = 0;
                            }
                            if (((TabGui)TabGui.this).guiTabHandler.tabs.get(((TabGui)TabGui.this).guiTabHandler.selectedTab).getMods().size() <= 1) break;
                            ((TabGui)TabGui.this).guiTabHandler.transition = -11;
                            break;
                        }
                        case 203: {
                            if (((TabGui)TabGui.this).guiTabHandler.mainMenu) break;
                            ((TabGui)TabGui.this).guiTabHandler.mainMenu = true;
                            break;
                        }
                        case 205: {
                            if (((TabGui)TabGui.this).guiTabHandler.mainMenu) {
                                ((TabGui)TabGui.this).guiTabHandler.mainMenu = false;
                                ((TabGui)TabGui.this).guiTabHandler.selectedItem = 0;
                                break;
                            }
                            if (!((TabGui)TabGui.this).guiTabHandler.visible) {
                                ((TabGui)TabGui.this).guiTabHandler.visible = true;
                                ((TabGui)TabGui.this).guiTabHandler.mainMenu = true;
                                break;
                            }
                            ((TabGui)TabGui.this).guiTabHandler.tabs.get(((TabGui)TabGui.this).guiTabHandler.selectedTab).getMods().get(((TabGui)TabGui.this).guiTabHandler.selectedItem).getToggleableModule().toggle();
                            break;
                        }
                        case 28: {
                            if (((TabGui)TabGui.this).guiTabHandler.mainMenu || !((TabGui)TabGui.this).guiTabHandler.visible) break;
                            ((TabGui)TabGui.this).guiTabHandler.tabs.get(((TabGui)TabGui.this).guiTabHandler.selectedTab).getMods().get(((TabGui)TabGui.this).guiTabHandler.selectedItem).getToggleableModule().toggle();
                        }
                    }
                }
            }
        });
        this.setRunning(true);
    }

    private static enum Mode {
        DEFAULT,
        BLUE,
        PURPLE;

    }
}

