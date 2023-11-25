package com.smallaswater.friends.variables;

import cn.nukkit.Player;
import com.smallaswater.friends.Friend;
import com.smallaswater.friends.players.PlayerConfig;
import tip.utils.variables.BaseVariable;

public class FriendVariable extends BaseVariable {
    public FriendVariable(Player player) {
        super(player);
    }

    public void strReplace() {
        PlayerConfig config = Friend.getPlayerConfig(this.player.getName());
        this.addStrReplaceString("{mailcount}", config.getPlayerWareHouse().getItemTiles().size() + "");
        this.addStrReplaceString("{onlineFriend}", Friend.getFriend().getOnlineFriends(config) + "");
        this.addStrReplaceString("{maxFriend}", config.getFriends().size() + "");
    }
}

