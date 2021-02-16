/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.Display
 */
package me.friendly.exeter;

import java.io.File;
import java.io.IOException;

import me.friendly.api.event.EventManager;
import me.friendly.exeter.command.CommandManager;
import me.friendly.exeter.config.ConfigManager;
import me.friendly.exeter.friend.FriendManager;
import me.friendly.exeter.keybind.KeybindManager;
import me.friendly.exeter.logging.Logger;
import me.friendly.exeter.module.ModuleManager;
import me.friendly.exeter.plugin.PluginManager;
import org.lwjgl.opengl.Display;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

/*@Mod(
        modid = exeter.MOD_ID,
        name = exeter.MOD_NAME,
        version = exeter.VERSION
)*/
public final class Exeter {

    public static final String MOD_ID = "friendly";
    public static final String MOD_NAME = "Exeter 1.12.2";
    public static final String VERSION = "b23";

    @Mod.Instance(MOD_ID)
    private static Exeter instance = null;

    public static final String TITLE = "Exeter";
    public static final int BUILD = 23;
    public static EventManager eventManager;
    public final long startTime = System.nanoTime() / 1000000L;
    private static KeybindManager keybindManager;
    private static ModuleManager moduleManager;
    private static CommandManager commandManager;
    private static FriendManager friendManager;
    private static ConfigManager configManager;
    private static PluginManager pluginManager;
    private static File directory;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        eventManager.init();
        //public Exeter() {
        Logger.getLogger().print("Initializing...");
        instance = this;
        this.directory = new File(System.getProperty("user.home"), "clarinet");
        if (!this.directory.exists()) {
            Logger.getLogger().print(String.format("%s client directory.", this.directory.mkdir() ? "Created" : "Failed to create"));
        }
        this.configManager = new ConfigManager();
        this.friendManager = new FriendManager();
        this.keybindManager = new KeybindManager();
        this.commandManager = new CommandManager();
        this.moduleManager = new ModuleManager();
        this.pluginManager = new PluginManager();
        this.getConfigManager().getRegistry().forEach(config -> config.load(new Object[0]));
        try {
            this.pluginManager.onLoad();
            System.out.println("Plugin manager started.");
            System.out.println(this.pluginManager.getList() + "has been loaded.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(new Thread("Shutdown Hook Thread") {

            @Override
            public void run() {
                Logger.getLogger().print("Shutting down...");
                Exeter.this.getConfigManager().getRegistry().forEach(config -> config.save(new Object[0]));
                Logger.getLogger().print("Shutdown.");
            }
        });
        Display.setTitle((String) String.format("%s b%s  ", TITLE, 23));
        Logger.getLogger().print(String.format("Initialized, took %s milliseconds.", System.nanoTime() / 1000000L - this.startTime));
    }


    public static Exeter getInstance() {
        return instance;
    }

    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public KeybindManager getKeybindManager() {
        return this.keybindManager;
    }

    public FriendManager getFriendManager() {
        return this.friendManager;
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }


    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    public File getDirectory() {
        return this.directory;
    }
}

