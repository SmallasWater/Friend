package Friends.events;


import Friends.utils.Message;
import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;

//临时会话
public class playerFriendChatEvent extends PlayerEvent{

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    private String target;
    private String message;

    public playerFriendChatEvent(Player player, String target, String message){
        this.player = player;
        this.target = target;
        this.message = message;
    }

    public String getTarget() {
        return target;
    }

    public String getMessage() {
        return message;
    }
}
