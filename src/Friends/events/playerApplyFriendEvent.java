package Friends.events;


import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;

//发出申请
public class playerApplyFriendEvent extends PlayerEvent{
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    private String target;

    public playerApplyFriendEvent(Player player,String target){
        this.player = player;
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
}
