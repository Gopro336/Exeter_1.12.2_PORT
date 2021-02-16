/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.module.impl.active.render;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import me.friendly.api.interfaces.Toggleable;
import me.friendly.api.minecraft.helper.PlayerHelper;
import me.friendly.exeter.Exeter;
import me.friendly.exeter.module.Module;
import me.friendly.exeter.module.ToggleableModule;
import me.friendly.exeter.properties.EnumProperty;
import me.friendly.exeter.properties.Property;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class TextGUI
extends Module {
    private final Property<Boolean> watermark = new Property<Boolean>(false, "Watermark", "wm", "water");
    private final Property<Boolean> transparent = new Property<Boolean>(true, "Transparent", "trans");
    private final Property<Boolean> potions = new Property<Boolean>(true, "Potions", "pots");
    private final Property<Boolean> armor = new Property<Boolean>(true, "Armor", "a");
    private final Property<Boolean> direction = new Property<Boolean>(true, "Direction", "facing", "d");
    private final Property<Boolean> time = new Property<Boolean>(true, "Time", "t");
    private final Property<Boolean> coords = new Property<Boolean>(true, "Coords", "coord", "c", "cord");
    private final Property<Boolean> arraylist = new Property<Boolean>(true, "ArrayList", "array", "al");
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
    private final EnumProperty<Organize> organize = new EnumProperty<Organize>(Organize.LENGTH, "Organize", "o");
    private final EnumProperty<Look> look = new EnumProperty<Look>(Look.DEFAULT, "Casing", "c");

    public TextGUI() {
        super("Text GUI", new String[]{"textgui", "hud", "overlay"});
        this.offerProperties(this.look, this.watermark, this.organize, this.transparent, this.potions, this.armor, this.time, this.direction, this.arraylist, this.coords);


    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {

        /*if (TextGUI.this.minecraft.gameSettings.showDebugInfo || event.getType() != RenderGameOverlayEvent.Type.IN_GAME) {
            return;
        }*/


        if (TextGUI.this.watermark.getValue()) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            TextGUI.this.minecraft.fontRenderer.drawStringWithShadow(String.format("%s §7b%s", "Exeter", 23), 2.0f, 2.0f, TextGUI.this.transparent.getValue() ? -1711276033 : -1);
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
        //final ScaledResolution scaledResolution = event.getScaledResolution();
        int positionY = -7;
        if (TextGUI.this.arraylist.getValue()) {
            final List<Module> modules = Exeter.getInstance().getModuleManager().getRegistry();
            switch (TextGUI.this.organize.getValue()) {
                case ABC: {
                    modules.sort((mod1, mod2) -> mod1.getTag().compareTo(mod2.getTag()));
                    break;
                }
                case LENGTH: {
                    modules.sort((mod1, mod2) -> TextGUI.this.minecraft.fontRenderer.getStringWidth(mod2.getTag()) - TextGUI.this.minecraft.fontRenderer.getStringWidth(mod1.getTag()));
                    break;
                }
            }
            for (final Module module : modules) {
                if (module instanceof Toggleable) {
                    final ToggleableModule toggleableModule = (ToggleableModule)module;
                    if (!toggleableModule.isDrawn() || toggleableModule.getColor() == 0 || !toggleableModule.isRunning()) {
                        continue;
                    }
                    final int labelWidth = TextGUI.this.minecraft.fontRenderer.getStringWidth(TextGUI.this.getTag(toggleableModule.getTag()));
                    final FontRenderer fontRenderer = TextGUI.this.minecraft.fontRenderer;
                    final String access$600 = TextGUI.this.getTag(toggleableModule.getTag());
                    final float p_175063_2_ = (float)(ScaledResolution.getScaledWidth() - labelWidth - 2);
                    positionY += 9;
                    fontRenderer.drawStringWithShadow(access$600, p_175063_2_, (float)positionY, toggleableModule.getColor());
                }
            }
        }
        /*if (TextGUI.this.armor.getValue()) {
            int x = 15;
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            for (int index = 3; index >= 0; --index) {
                final ItemStack stack = TextGUI.this.minecraft.player.inventory.armorInventory[index];
                if (stack != null) {
                    int y;
                    if (TextGUI.this.minecraft.player.isInsideOfMaterial(Material.WATER) && !TextGUI.this.minecraft.player.capabilities.isCreativeMode) {
                        y = 65;
                    }
                    else if (TextGUI.this.minecraft.player.capabilities.isCreativeMode) {
                        y = 38;
                    }
                    else {
                        y = 55;
                    }
                    TextGUI.this.minecraft.getRenderItem().renderItemAndEffectIntoGUI(stack, scaledResolution.getScaledWidth() / 2 + x, scaledResolution.getScaledHeight() - y);
                    TextGUI.this.minecraft.getRenderItem().renderItemOverlays(TextGUI.this.minecraft.fontRenderer, stack, scaledResolution.getScaledWidth() / 2 + x, scaledResolution.getScaledHeight() - y);
                    x += 18;
                }
            }
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popMatrix();
        }*/
        int y2 = ScaledResolution.getScaledHeight() - ((TextGUI.this.minecraft.currentScreen instanceof GuiChat) ? 24 : 10);
        /*if (TextGUI.this.potions.getValue()) {
            final Collection<PotionEffect> effects = (Collection<PotionEffect>)TextGUI.this.minecraft.player.getActivePotionEffects();
            if (effects != null && !effects.isEmpty()) {
                for (final PotionEffect effect : effects) {
                    if (effect != null) {
                        final Potion potion = Potion.POTION_TYPES[effect.getPotionID()];
                        if (potion == null) {
                            continue;
                        }
                        String name = StatCollector.translateToLocal(potion.getName());
                        name += String.format(" §7%s : %s", effect.getAmplifier() + 1, Potion.getDurationString(effect));
                        final int align = scaledResolution.getScaledWidth() - TextGUI.this.minecraft.fontRenderer.getStringWidth(name) - 2;
                        TextGUI.this.minecraft.fontRenderer.drawStringWithShadow(name, (float)align, (float)y2, potion.getLiquidColor());
                        y2 -= 9;
                    }
                }
            }
        }*/
        y2 += 9;
        if (TextGUI.this.coords.getValue()) {
            y2 -= 9;
            final String coordinatesFormat = String.format("§f%s, %s, %s §7XYZ", (int)TextGUI.this.minecraft.player.posX, (int)TextGUI.this.minecraft.player.posY, (int)TextGUI.this.minecraft.player.posZ);
            TextGUI.this.minecraft.fontRenderer.drawStringWithShadow(coordinatesFormat, (float)(ScaledResolution.getScaledWidth() - TextGUI.this.minecraft.fontRenderer.getStringWidth(coordinatesFormat) - 2), (float)y2, -1);
        }
        if (TextGUI.this.time.getValue()) {
            y2 -= 9;
            final String time = String.format("§7%s", TextGUI.this.dateFormat.format(new Date()));
            TextGUI.this.minecraft.fontRenderer.drawStringWithShadow(time, (float)(ScaledResolution.getScaledWidth() - TextGUI.this.minecraft.fontRenderer.getStringWidth(time) - 2), (float)y2, -1);
        }
        if (TextGUI.this.direction.getValue()) {
            y2 -= 9;
            final String direction = String.format("§7%s", PlayerHelper.getFacingWithProperCapitals().toUpperCase());
            TextGUI.this.minecraft.fontRenderer.drawStringWithShadow(direction, (float)(ScaledResolution.getScaledWidth() - TextGUI.this.minecraft.fontRenderer.getStringWidth(direction) - 2), (float)y2, -1);
        }

        /*
        Collection effects;
        if (((TextGUI)TextGUI.this).minecraft.gameSettings.showDebugInfo || event.getType() != RenderGameOverlayEvent.Type.IN_GAME) {
            return;
        }
        if (((Boolean)TextGUI.this.watermark.getValue()).booleanValue()) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            ((TextGUI)TextGUI.this).minecraft.fontRenderer.drawStringWithShadow(String.format("%s \u00a77b%s", "Exeter", 23), 2.0f, 2.0f, (Boolean)TextGUI.this.transparent.getValue() != false ? -1711276033 : -1);
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
        ScaledResolution scaledResolution = event.getScaledResolution();
        int positionY = -7;
        if (((Boolean)TextGUI.this.arraylist.getValue()).booleanValue()) {
            List modules = Exeter.getInstance().getModuleManager().getRegistry();
            switch ((Organize)((Object)TextGUI.this.organize.getValue())) {
                case ABC: {
                    modules.sort((mod1, mod2) -> mod1.getTag().compareTo(mod2.getTag()));
                    break;
                }
                case LENGTH: {
                    modules.sort((mod1, mod2) -> ((TextGUI)TextGUI.this).minecraft.fontRenderer.getStringWidth(mod2.getTag()) - ((TextGUI)TextGUI.this).minecraft.fontRenderer.getStringWidth(mod1.getTag()));
                }
            }
            for (Module module : modules) {
                ToggleableModule toggleableModule;
                if (!(module instanceof Toggleable) || !(toggleableModule = (ToggleableModule)module).isDrawn() || toggleableModule.getColor() == 0 || !toggleableModule.isRunning()) continue;
                int labelWidth = ((TextGUI)TextGUI.this).minecraft.fontRenderer.getStringWidth(TextGUI.this.getTag(toggleableModule.getTag()));
                ((TextGUI)TextGUI.this).minecraft.fontRenderer.drawStringWithShadow(TextGUI.this.getTag(toggleableModule.getTag()), scaledResolution.getScaledWidth() - labelWidth - 2, positionY += 9, toggleableModule.getColor());
            }
        }*/
        /*if (((Boolean)TextGUI.this.armor.getValue()).booleanValue()) {
            int x = 15;
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            for (int index = 3; index >= 0; --index) {
                ItemStack stack = ((TextGUI)TextGUI.this).minecraft.player.inventory.armorInventory[index];
                if (stack == null) continue;
                int y = ((TextGUI)TextGUI.this).minecraft.player.isInsideOfMaterial(Material.WATER) && !((TextGUI)TextGUI.this).minecraft.player.capabilities.isCreativeMode ? 65 : (((TextGUI)TextGUI.this).minecraft.player.capabilities.isCreativeMode ? 38 : 55);
                TextGUI.this.minecraft.getRenderItem().renderItemAndEffectIntoGUI(stack, scaledResolution.getScaledWidth() / 2 + x, scaledResolution.getScaledHeight() - y);
                TextGUI.this.minecraft.getRenderItem().renderItemOverlays(((TextGUI)TextGUI.this).minecraft.fontRenderer, stack, scaledResolution.getScaledWidth() / 2 + x, scaledResolution.getScaledHeight() - y);
                x += 18;
            }
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popMatrix();
        }*/
        //int y = scaledResolution.getScaledHeight() - (((TextGUI)TextGUI.this).minecraft.currentScreen instanceof GuiChat ? 24 : 10);
        /*if (((Boolean)TextGUI.this.potions.getValue()).booleanValue() && (effects = ((TextGUI)TextGUI.this).minecraft.player.getActivePotionEffects()) != null && !effects.isEmpty()) {
            for (PotionEffect effect : effects) {
                Potion potion;
                if (effect == null || (potion = Potion.POTION_TYPES[effect.getPotion()]) == null) continue;
                //String name = StatCollector.translateToLocal(potion.getName());
                String name = potion.getName();
                name = name + String.format(" \u00a77%s : %s", effect.getAmplifier() + 1, Potion.getPotionDurationString(effect, 1.0f));
                int align = scaledResolution.getScaledWidth() - ((TextGUI)TextGUI.this).minecraft.fontRenderer.getStringWidth(name) - 2;
                ((TextGUI)TextGUI.this).minecraft.fontRenderer.drawStringWithShadow(name, align, y, potion.getLiquidColor());
                y -= 9;
            }
        }*/
       /*y += 9;
        if (((Boolean)TextGUI.this.coords.getValue()).booleanValue()) {
            String coordinatesFormat = String.format("\u00a7f%s, %s, %s \u00a77XYZ", (int)((TextGUI)TextGUI.this).minecraft.player.posX, (int)((TextGUI)TextGUI.this).minecraft.player.posY, (int)((TextGUI)TextGUI.this).minecraft.player.posZ);
            ((TextGUI)TextGUI.this).minecraft.fontRenderer.drawStringWithShadow(coordinatesFormat, scaledResolution.getScaledWidth() - ((TextGUI)TextGUI.this).minecraft.fontRenderer.getStringWidth(coordinatesFormat) - 2, y -= 9, -1);
        }
        if (((Boolean)TextGUI.this.time.getValue()).booleanValue()) {
            String time = String.format("\u00a77%s", TextGUI.this.dateFormat.format(new Date()));
            ((TextGUI)TextGUI.this).minecraft.fontRenderer.drawStringWithShadow(time, scaledResolution.getScaledWidth() - ((TextGUI)TextGUI.this).minecraft.fontRenderer.getStringWidth(time) - 2, y -= 9, -1);
        }
        if (((Boolean)TextGUI.this.direction.getValue()).booleanValue()) {
            String direction = String.format("\u00a77%s", PlayerHelper.getFacingWithProperCapitals().toUpperCase());
            ((TextGUI)TextGUI.this).minecraft.fontRenderer.drawStringWithShadow(direction, scaledResolution.getScaledWidth() - ((TextGUI)TextGUI.this).minecraft.fontRenderer.getStringWidth(direction) - 2, y -= 9, -1);
        }*/
    }

    private String getTag(String tag) {
        switch ((Look) this.look.getValue()) {
            case UPPER: {
                tag = tag.toUpperCase();
                break;
            }
            case LOWER: {
                tag = tag.toLowerCase();
                break;
            }
            case CUB: {
                tag = String.format("[%s]", tag.toLowerCase());
            }
        }
        return tag;
    }

    private enum Look {
        DEFAULT,
        LOWER,
        UPPER,
        CUB

    }

    private enum Organize {
        ABC,
        LENGTH

    }
}

