package com.smallaswater.friends.utils;

import com.smallaswater.friends.Friend;
import com.smallaswater.friends.players.PlayerConfig;

import java.util.LinkedList;

public class PlayerChatMessage {
    private LinkedList<Message> chat = new LinkedList<>();
    private String player;

    public PlayerChatMessage(String player, Message message) {
        this.chat.add(message);
        this.player = player;
    }

    public String getChat() {
        PlayerConfig config = Friend.getPlayerConfig(this.player);
        StringBuilder builder = new StringBuilder("");
        for (Message message : this.chat) {
            String addColor = "§6";
            if (message.getName().equals(this.player)) {
                addColor = "§d";
            }
            String nameColor = "§a";
            if (!message.getName().equals(this.player)) {
                if (config.isFriend(message.getName())) {
                    builder.append("§d[好友]");
                    nameColor = "§e";
                } else {
                    builder.append("§c[陌生人]");
                    nameColor = "§6";
                }
            } else {
                builder.append("§6[你]");
            }
            //TODO
            /*if (Friend.loadVIP) {
                String vip = VIP.getVip().getVIPLevel(message.getName());
                builder.append("§e[").append(vip != null ? vip : "§a玩家").append("§e]§r");
            }*/
            builder.append(nameColor).append(message.getName()).append(message.getTime()).append("§b>>").append(addColor).append(message.getMessage()).append("\n");
        }
        return builder.toString();
    }

    public void addChat(Message message) {
        this.chat.add(message);
    }
}

