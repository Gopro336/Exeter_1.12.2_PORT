/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.module.impl.active.render;

import me.friendly.api.minecraft.render.RenderMethods;
import me.friendly.exeter.Exeter;
import me.friendly.exeter.module.Module;
import me.friendly.exeter.properties.NumberProperty;
import me.friendly.exeter.properties.Property;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class Render
extends Module {
    private final Property<Boolean> customBars = new Property<Boolean>(true, "CustomBars", "c", "custom", "cb");
    private final Property<Boolean> blockAnimation = new Property<Boolean>(true, "BlockAnimation", "ba", "block");
    private final Property<Boolean> pumpkin = new Property<Boolean>(true, "NoPumpkin", "p", "np");
    private final Property<Boolean> fire = new Property<Boolean>(true, "NoFire", "fire", "nf");
    private final Property<Boolean> hurtcam = new Property<Boolean>(false, "NoHurtcam", "hurtcam", "nh");
    private final Property<Boolean> items = new Property<Boolean>(false, "NoItems", "items", "item", "ni");
    public final Property<Float> blockHeight = new NumberProperty<Float>(Float.valueOf(-0.2f), Float.valueOf(-1.5f), Float.valueOf(1.5f), "BlockHeight");

    public Render() {
        super("Render", new String[]{"render", "norender", "nr"});
        this.offerProperties(this.pumpkin, this.customBars, this.fire, this.hurtcam, this.items, this.blockAnimation, this.blockHeight);


    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event){

        /*switch (event.getType()) {
            case FIRE: {
                event.setRenderFire((Boolean) Render.this.fire.getValue());
                break;
            }
            case HURTCAM: {
                event.setRenderHurtcam((Boolean) Render.this.hurtcam.getValue());
                break;
            }
            case ITEM: {
                event.setRenderItems((Boolean) Render.this.items.getValue());
                if (!((Boolean) Render.this.items.getValue()).booleanValue()) break;
                ((Render) Render.this).minecraft.world.removeEntity(event.getEntityItem());
                break;
            }
            case PUMPKIN: {
                event.setRenderPumpkin((Boolean) Render.this.pumpkin.getValue());
            }
        }*/

        //ScaledResolution scaledResolution = event.getScaledResolution();
        /*if (((Boolean)Render.this.customBars.getValue()).booleanValue()) {
            int minusHealth = (int)((Render)Render.this).minecraft.player.getHealth();
            int minusFood = ((Render)Render.this).minecraft.player.getFoodStats().getFoodLevel();
            if (minusFood > 20) {
                minusFood = 20;
            }
            if (minusHealth > 20) {
                minusHealth = 20;
            }
            RenderMethods.drawGradientBorderedRect(scaledResolution.getScaledWidth() / 2 - 91, scaledResolution.getScaledHeight() - 39, scaledResolution.getScaledWidth() / 2 - 10, scaledResolution.getScaledHeight() - 30, 1.0f, -587202560, -12303292, -14540254);
            RenderMethods.drawGradientBorderedRect(scaledResolution.getScaledWidth() / 2 - 91, scaledResolution.getScaledHeight() - 39, scaledResolution.getScaledWidth() / 2 - 90 + minusHealth * 4, scaledResolution.getScaledHeight() - 30, 1.0f, -587202560, -577175552, -582746112);
            ((Render)Render.this).minecraft.fontRenderer.drawStringWithShadow(String.format("%s", (int)((Render)Render.this).minecraft.player.getHealth()), scaledResolution.getScaledWidth() / 2 - 56, scaledResolution.getScaledHeight() - 38, -1);
            RenderMethods.drawGradientBorderedRect(scaledResolution.getScaledWidth() / 2 + 10, scaledResolution.getScaledHeight() - 39, scaledResolution.getScaledWidth() / 2 + 91, scaledResolution.getScaledHeight() - 30, 1.0f, -587202560, -12303292, -14540254);
            RenderMethods.drawGradientBorderedRect(scaledResolution.getScaledWidth() / 2 + 90 - minusFood * 4, scaledResolution.getScaledHeight() - 39, scaledResolution.getScaledWidth() / 2 + 91, scaledResolution.getScaledHeight() - 30, 1.0f, -587202560, -586565120, -586849536);
            ((Render)Render.this).minecraft.fontRenderer.drawStringWithShadow(String.format("%s", ((Render)Render.this).minecraft.player.getFoodStats().getFoodLevel()), scaledResolution.getScaledWidth() / 2 + 45, scaledResolution.getScaledHeight() - 38, -1);
        }
        if (((Boolean)Render.this.blockAnimation.getValue()).booleanValue()) {
            ItemRenderer.blockHeigh = Render.this.blockHeight.getValue().floatValue();
            ItemRenderer.blockAnimation = true;
        } else {
            ItemRenderer.blockAnimation = false;
            ItemRenderer.blockHeigh = -0.2f;
        }*/
    }

}

