/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package me.friendly.exeter.friend;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import me.friendly.api.registry.ListRegistry;
import me.friendly.exeter.config.Config;
import me.friendly.exeter.Exeter;
import me.friendly.exeter.friend.Friend;

public final class FriendManager
extends ListRegistry<Friend> {
    public FriendManager() {
        this.registry = new ArrayList();
        new Config("friends.json"){

            @Override
            public void load(Object ... source) {
                JsonElement root;
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
                try (FileReader reader = new FileReader(this.getFile());){
                    root = new JsonParser().parse((Reader)reader);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                if (!(root instanceof JsonArray)) {
                    return;
                }
                JsonArray friends = (JsonArray)root;
                friends.forEach(node -> {
                    if (!(node instanceof JsonObject)) {
                        return;
                    }
                    try {
                        JsonObject friendNode = (JsonObject)node;
                        Exeter.getInstance().getFriendManager().getRegistry().add(new Friend(friendNode.get("friend-label").getAsString(), friendNode.get("friend-alias").getAsString()));
                    }
                    catch (Throwable e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void save(Object ... destination) {
                if (this.getFile().exists()) {
                    this.getFile().delete();
                }
                if (Exeter.getInstance().getFriendManager().getRegistry().isEmpty()) {
                    return;
                }
                JsonArray friends = new JsonArray();
                Exeter.getInstance().getFriendManager().getRegistry().forEach(friend -> {
                    try {
                        JsonObject friendObject;
                        JsonObject properties = friendObject = new JsonObject();
                        properties.addProperty("friend-label", friend.getLabel());
                        properties.addProperty("friend-alias", friend.getAlias());
                        friends.add((JsonElement)properties);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                try (FileWriter writer = new FileWriter(this.getFile());){
                    writer.write(new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)friends));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public Friend getFriendByAliasOrLabel(String aliasOrLabel) {
        for (Friend friend : this.registry) {
            if (!aliasOrLabel.equalsIgnoreCase(friend.getLabel()) && !aliasOrLabel.equalsIgnoreCase(friend.getAlias())) continue;
            return friend;
        }
        return null;
    }

    public boolean isFriend(String aliasOrLabel) {
        for (Friend friend : this.registry) {
            if (!aliasOrLabel.equalsIgnoreCase(friend.getLabel()) && !aliasOrLabel.equalsIgnoreCase(friend.getAlias())) continue;
            return true;
        }
        return false;
    }
}

