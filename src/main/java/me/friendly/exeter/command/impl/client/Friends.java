/*
 * Decompiled with CFR 0.150.
 */
package me.friendly.exeter.command.impl.client;

import me.friendly.exeter.command.Argument;
import me.friendly.exeter.command.Command;
import me.friendly.exeter.Exeter;
import me.friendly.exeter.friend.Friend;

public final class Friends {

    public static final class Remove
    extends Command {
        public Remove() {
            super(new String[]{"remove", "rem"}, new Argument("username/alias"));
        }

        @Override
        public String dispatch() {
            String name = this.getArgument("username/alias").getValue();
            if (!Exeter.getInstance().getFriendManager().isFriend(name)) {
                return "That user is not a friend.";
            }
            Friend friend = Exeter.getInstance().getFriendManager().getFriendByAliasOrLabel(name);
            String oldAlias = friend.getAlias();
            Exeter.getInstance().getFriendManager().unregister(friend);
            return String.format("Removed friend with alias %s.", oldAlias);
        }
    }

    public static final class Add
    extends Command {
        public Add() {
            super(new String[]{"add", "a"}, new Argument("username"), new Argument("alias"));
        }

        @Override
        public String dispatch() {
            String username = this.getArgument("username").getValue();
            String alias = this.getArgument("alias").getValue();
            if (Exeter.getInstance().getFriendManager().isFriend(username)) {
                return "That user is already a friend.";
            }
            Exeter.getInstance().getFriendManager().register(new Friend(username, alias));
            return String.format("Added friend with alias %s.", alias);
        }
    }
}

