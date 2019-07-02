package Friends.utils;


import Friends.Friend;

import java.util.LinkedList;

public class playerChatMessage {

    private LinkedList<Message> chat = new LinkedList<>();
    private String player;

    public playerChatMessage(String player,Message message){
        chat.add(message);
        this.player = player;
    }


    public String getChat(){
        StringBuilder builder = new StringBuilder("");
        for(Message message:chat){

            String addColor = "§6";
            if(message.getName().equals(player)){
                addColor = "§d";
            }
            String nameColor = "§a";
            if(!message.getName().equals(player)){
                if(Friend.getFriend().isFriend(player,message.getName())){
                    builder.append("§d[好友]");
                    nameColor = "§e";
                }else{
                    builder.append("§c[陌生人]");
                    nameColor = "§6";
                }
            }else{
                builder.append("§6[你]");
            }
            if(Friend.loadVIP){
                String vip = SVIP.VIP.getVip().getVIPLevel(message.getName());
                builder.append("§e[").append(vip != null ?vip:"§a玩家").append("§e]§r");
            }

            builder.append(nameColor)
            .append(message.getName()).append(message.getTime()).append("§b>>").append(addColor).append(message.getMessage()).append("\n");
        }
        return builder.toString();
    }

    public void addChat(Message message){
        chat.add(message);
    }
}
