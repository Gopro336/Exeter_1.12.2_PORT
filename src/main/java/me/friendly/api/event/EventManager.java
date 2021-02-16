package me.friendly.api.event;

import me.friendly.api.event.events.Render2DEvent;
import me.friendly.exeter.Exeter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.lwjgl.input.Keyboard;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;


public class EventManager
{
	private AtomicBoolean tickOngoing;
	private boolean keyTimeout;


	public void init() {
		MinecraftForge.EVENT_BUS.register((Object)this);
	}

	public void onUnload() {
		MinecraftForge.EVENT_BUS.unregister((Object)this);
	}



	public boolean ticksOngoing() {
		return this.tickOngoing.get();
	}

	/*@SubscribeEvent
	public void onClientConnect(final FMLNetworkEvent.ClientConnectedToServerEvent event) {

	}

	@SubscribeEvent
	public void onClientDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {

	}*/




	/*@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		if (Minecraft.getMinecraft().player == null) {
			return;
		}

		//Client.SendMessage("OnUpdate Fired");
		Zenith.moduleManager.onUpdate();
		//Config.saveGui();
	}*/

	/*@SubscribeEvent(priority = EventPriority.LOW)
	public void onRenderGameOverlayEvent(final RenderGameOverlayEvent.Text event) {
		if (event.getType().equals((Object)RenderGameOverlayEvent.ElementType.TEXT)) {
			final ScaledResolution resolution = new ScaledResolution(EventManager.mc);
			final Render2DEvent render2DEvent = new Render2DEvent(event.getPartialTicks(), resolution);
			Exeter.moduleManager.onRender2D(render2DEvent);
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		}
	}*/

	
}
