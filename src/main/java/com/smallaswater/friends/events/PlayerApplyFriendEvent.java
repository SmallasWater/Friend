package com.smallaswater.friends.events;

import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;

public class PlayerApplyFriendEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private String target;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public PlayerApplyFriendEvent(Player player, String target) {
        this.player = player;
        this.target = target;
    }

    public String getTarget() {
        return this.target;
    }
}

