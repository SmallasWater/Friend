package com.smallaswater.friends.events;

import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;

public class PlayerFriendChatEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private String target;
    private String message;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public PlayerFriendChatEvent(Player player, String target, String message) {
        this.player = player;
        this.target = target;
        this.message = message;
    }

    public String getTarget() {
        return this.target;
    }

    public String getMessage() {
        return this.message;
    }
}

