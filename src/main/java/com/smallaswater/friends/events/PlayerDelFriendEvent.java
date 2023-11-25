package com.smallaswater.friends.events;

import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;

public class PlayerDelFriendEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private String target;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public PlayerDelFriendEvent(Player player, String target) {
        this.target = target;
        this.player = player;
    }

    public String getTarget() {
        return this.target;
    }
}

