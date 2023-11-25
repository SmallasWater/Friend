package com.smallaswater.friends.commands;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import com.smallaswater.friends.Friend;

public class FriendMailCommand extends PluginCommand<Friend> {
    public FriendMailCommand(String name, Friend owner) {
        super(name, owner);
        this.setPermission("friends.fm");
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return true;
    }
}

