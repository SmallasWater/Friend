package Friends.events;


import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;

//删除好友
public class playerDelFriendEvent extends PlayerEvent{
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    private String target;

    public playerDelFriendEvent(Player player, String target){
        this.target = target;
        this.player = player;
    }

    public String getTarget() {
        return target;
    }
}
