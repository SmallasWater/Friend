package com.smallaswater.friends.events;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;

public class PlayerAcceptFriendEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private String target;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public PlayerAcceptFriendEvent(Player player, String target) {
        this.player = player;
        this.target = target;
    }

    public String getTarget() {
        return this.target;
    }
}

