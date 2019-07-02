package Friends.events;


import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;
//同意好友
public class playerAcceptFriendEvent extends PlayerEvent implements Cancellable{

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    private String target;

    public playerAcceptFriendEvent(Player player,String target){
        this.player = player;
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
}
