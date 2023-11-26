package com.smallaswater.friends.events;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;
import com.smallaswater.friends.utils.items.ItemTile;

public class PlayerGetItemEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private String target;
    private ItemTile tile;

    public static HandlerList getHandlers() {
        return handlers;
    }

    public PlayerGetItemEvent(Player player, String target, ItemTile tile) {
        this.target = target;
        this.player = player;
        this.tile = tile;
    }

    public ItemTile getTile() {
        return this.tile;
    }

    public String getTarget() {
        return this.target;
    }
}

