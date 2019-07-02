package Friends.events;


import Friends.utils.itemTile;
import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;
import cn.nukkit.item.Item;

import java.util.LinkedHashMap;
import java.util.LinkedList;

//获得物品
public class playerGetItemEvent extends PlayerEvent{
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    private String target;
    private itemTile tile;

    public playerGetItemEvent(Player player, String target, itemTile tile){
        this.target = target;
        this.player = player;
        this.tile = tile;
    }

    public itemTile getTile() {
        return tile;
    }

    public String getTarget() {
        return target;
    }
}
