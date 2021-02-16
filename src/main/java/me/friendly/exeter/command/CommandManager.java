/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import me.friendly.api.event.Listener;
import me.friendly.api.registry.ListRegistry;
import me.friendly.exeter.command.impl.client.Bind;
import me.friendly.exeter.command.impl.client.Friends;
import me.friendly.exeter.command.impl.client.Help;
import me.friendly.exeter.command.impl.client.Modules;
import me.friendly.exeter.command.impl.client.Prefix;
import me.friendly.exeter.command.impl.client.Presets;
import me.friendly.exeter.command.impl.client.Runtime;
import me.friendly.exeter.command.impl.client.ScreenShot;
import me.friendly.exeter.command.impl.client.Toggle;
//import me.friendly.exeter.command.impl.player.Breed;
import me.friendly.exeter.command.impl.player.Grab;
import me.friendly.exeter.command.impl.player.HClip;
import me.friendly.exeter.command.impl.player.StackSize;
import me.friendly.exeter.command.impl.player.VClip;
import me.friendly.exeter.command.impl.server.Ban;
import me.friendly.exeter.command.impl.server.Connect;
import me.friendly.exeter.config.Config;
//import me.friendly.exeter.events.PacketEvent;
//import net.minecraft.network.play.client.CPacketChatMessage;


public final class CommandManager
extends ListRegistry<Command> {
    private String prefix = ".";

    public CommandManager() {
        this.registry = new ArrayList();
        this.register(new Toggle());
        this.register(new Runtime());
        this.register(new Grab());
        this.register(new Help());
        this.register(new Modules());
        //this.register(new Breed());
        this.register(new Prefix());
        this.register(new Connect());
        //this.register(new Crash());
        this.register(new StackSize());
        //this.register(new Damage());
        this.register(new Presets());
        this.register(new HClip());
        //this.register(new Drown());
        this.register(new VClip());
        this.register(new Friends.Add());
        this.register(new Friends.Remove());
        this.register(new Bind());
        this.register(new Ban());
        this.register(new ScreenShot());
        this.registry.sort((cmd1, cmd2) -> cmd1.getAliases()[0].compareTo(cmd2.getAliases()[0]));
        /*Exeter.getInstance().getEventManager().register(new Listener<PacketEvent>("commands_packet_listener"){

            @Override
            public void call(PacketEvent event) {
                CPacketChatMessage packet;
                String message;
                if (event.getPacket() instanceof CPacketChatMessage && (message = (packet = (CPacketChatMessage)event.getPacket()).getMessage().trim()).startsWith(CommandManager.this.getPrefix())) {
                    event.setCanceled(true);
                    boolean exists = false;
                    String[] arguments = message.split(" ");
                    if (message.length() < 1) {
                        Logger.getLogger().printToChat("No command was entered.");
                        return;
                    }
                    String execute = message.contains(" ") ? arguments[0] : message;
                    for (Command command : CommandManager.this.getRegistry()) {
                        for (String alias : command.getAliases()) {
                            if (!execute.replace(CommandManager.this.getPrefix(), "").equalsIgnoreCase(alias.replaceAll(" ", ""))) continue;
                            exists = true;
                            try {
                                Logger.getLogger().printToChat(command.dispatch(arguments));
                            }
                            catch (Exception e) {
                                Logger.getLogger().printToChat(String.format("%s%s %s", CommandManager.this.getPrefix(), alias, command.getSyntax()));
                            }
                        }
                    }
                    String[] argz = message.split(" ");
                    for (Module mod : Exeter.getInstance().getModuleManager().getRegistry()) {
                        for (String alias : mod.getAliases()) {
                            try {
                                if (!argz[0].equalsIgnoreCase(CommandManager.this.getPrefix() + alias.replace(" ", ""))) continue;
                                exists = true;
                                if (argz.length > 1) {
                                    String valueName = argz[1];
                                    if (argz[1].equalsIgnoreCase("list")) {
                                        if (mod.getProperties().size() > 0) {
                                            StringJoiner stringJoiner = new StringJoiner(", ");
                                            for (Property property : mod.getProperties()) {
                                                stringJoiner.add(String.format("%s&e[%s]&7", property.getAliases()[0], property.getValue() instanceof Enum ? ((EnumProperty)property).getFixedValue() : property.getValue()));
                                            }
                                            Logger.getLogger().printToChat(String.format("Properties (%s) %s.", mod.getProperties().size(), stringJoiner.toString()));
                                            continue;
                                        }
                                        Logger.getLogger().printToChat(String.format("&e%s&7 has no properties.", mod.getLabel()));
                                        continue;
                                    }
                                    Property property = mod.getPropertyByAlias(valueName);
                                    if (property == null) continue;
                                    if (property.getValue() instanceof Number) {
                                        if (!argz[2].equalsIgnoreCase("get")) {
                                            if (property.getValue() instanceof Double) {
                                                property.setValue(Double.parseDouble(argz[2]));
                                            }
                                            if (property.getValue() instanceof Integer) {
                                                property.setValue(Integer.parseInt(argz[2]));
                                            }
                                            if (property.getValue() instanceof Float) {
                                                property.setValue(Float.valueOf(Float.parseFloat(argz[2])));
                                            }
                                            if (property.getValue() instanceof Long) {
                                                property.setValue(Long.parseLong(argz[2]));
                                            }
                                            Logger.getLogger().printToChat(String.format("&e%s&7 has been set to &e%s&7 for &e%s&7.", property.getAliases()[0], property.getValue(), mod.getLabel()));
                                            continue;
                                        }
                                        Logger.getLogger().printToChat(String.format("&e%s&7 current value is &e%s&7 for &e%s&7.", property.getAliases()[0], property.getValue(), mod.getLabel()));
                                        continue;
                                    }
                                    if (property.getValue() instanceof Enum) {
                                        if (!argz[2].equalsIgnoreCase("list")) {
                                            ((EnumProperty)property).setValue(argz[2]);
                                            Logger.getLogger().printToChat(String.format("&e%s&7 has been set to &e%s&7 for &e%s&7.", property.getAliases()[0], ((EnumProperty)property).getFixedValue(), mod.getLabel()));
                                            continue;
                                        }
                                        StringJoiner stringJoiner = new StringJoiner(", ");
                                        Enum[] array = (Enum[])property.getValue().getClass().getEnumConstants();
                                        int length = array.length;
                                        for (int i = 0; i < length; ++i) {
                                            stringJoiner.add(String.format("%s%s&7", array[i].name().equalsIgnoreCase(property.getValue().toString()) ? "&a" : "&c", CommandManager.this.getFixedValue(array[i])));
                                        }
                                        Logger.getLogger().printToChat(String.format("Modes (%s) %s.", array.length, stringJoiner.toString()));
                                        continue;
                                    }
                                    if (property.getValue() instanceof String) {
                                        property.setValue(argz[2]);
                                        Logger.getLogger().printToChat(String.format("&e%s&7 has been set to &e\"%s\"&7 for &e%s&7.", property.getAliases()[0], property.getValue(), mod.getLabel()));
                                        continue;
                                    }
                                    if (!(property.getValue() instanceof Boolean)) continue;
                                    property.setValue((Boolean)property.getValue() == false);
                                    Logger.getLogger().printToChat(String.format("&e%s&7 has been %s&7 for &e%s&7.", property.getAliases()[0], (Boolean)property.getValue() != false ? "&aenabled" : "&cdisabled", mod.getLabel()));
                                    continue;
                                }
                                Logger.getLogger().printToChat(String.format("%s &e[list|valuename] [list|get]", argz[0]));
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (!exists) {
                        Logger.getLogger().printToChat("Invalid command entered.");
                    }
                }
            }
        });*/
        new Config("command_prefix.txt"){

            @Override
            public void load(Object ... source) {
                try {
                    if (!this.getFile().exists()) {
                        this.getFile().createNewFile();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                if (!this.getFile().exists()) {
                    return;
                }
                try {
                    String readLine;
                    BufferedReader br = new BufferedReader(new FileReader(this.getFile()));
                    while ((readLine = br.readLine()) != null) {
                        try {
                            String[] split = readLine.split(":");
                            CommandManager.this.prefix = split[0];
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    br.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void save(Object ... destination) {
                try {
                    if (!this.getFile().exists()) {
                        this.getFile().createNewFile();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(this.getFile()));
                    bw.write(CommandManager.this.prefix);
                    bw.newLine();
                    bw.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    private String getFixedValue(Enum enumd) {
        return Character.toString(enumd.name().charAt(0)) + enumd.name().toLowerCase().replace(Character.toString(enumd.name().charAt(0)).toLowerCase(), "");
    }
}

