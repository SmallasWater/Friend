package Friends;


import Friends.Form.createForm;
import Friends.events.*;
import Friends.utils.ItemIDSunName;
import Friends.utils.Message;
import Friends.utils.itemTile;
import Friends.utils.playerChatMessage;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementToggle;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.item.Item;
import cn.nukkit.network.protocol.ModalFormResponsePacket;
import cn.nukkit.utils.Config;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.onebone.economyapi.EconomyAPI;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;


public class ListenerFriend implements Listener{
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Friend.getFriend().getPlayerConfig(player.getName());
        for (String friend:Friend.getFriend().getFriends(player.getName())){
            if(Friend.getFriend().getCanSetting(friend,"好友上线提醒")){
                Player friends = Server.getInstance().getPlayer(friend);
                if(friends != null){
                    friends.sendMessage("§d[好友]§b您的好友§a"+player.getName()+"§b上线啦");
                }
            }
        }
    }

    @EventHandler
    public void getUI(DataPacketReceiveEvent event){
        String data;
        ModalFormResponsePacket ui;
        Player player = event.getPlayer();

        if(!(event.getPacket() instanceof ModalFormResponsePacket)) return;
        ui = (ModalFormResponsePacket)event.getPacket();
        data = ui.data.trim();
        int fromId = ui.formId;
        switch (fromId){
            case 0xddea0001:
                if(data.equals("null")) return;
                switch (Integer.parseInt(data)){
                    case 0:
                        if(Friend.canClick.containsKey(player))
                            Friend.canClick.remove(player);
                        createForm.sendPlayerList(player);
                        break;
                    case 1:
                        createForm.sendSeek(player);
                        break;
                    case 2:
                        createForm.sendApplyPlayers(player);
                        break;
                    case 3:
                        Friend.canClick.put(player,true);
                        createForm.sendPlayerList(player);
                        break;
                    case 4:
                        createForm.sendInventory(player);
                        break;
                    case 5:
                        createForm.sendChatPlayers(player);
                        break;
                    case 6:
                        createForm.sendSetting(player);
                        break;
                }
                break;
            case 0xddea0002:
                if(data.equals("null")) return;
                LinkedList<String> friends = Friend.players.get(player);
                int d = Integer.parseInt(data);//点击位置
                if(d > 0){
                    if(friends.size() > d - 1){
                        String target = friends.get(d - 1);
                        Friend.clickPlayer.put(player,target);
                        if(Friend.canClick.containsKey(player)){
                            Friend.canClick.remove(player);
                            if(!Friend.getFriend().isBlack(player.getName(),target) && !Friend.getFriend().isBlack(target,player.getName()))
                                createForm.sendPlayerInventorys(player);
                            else
                                player.sendMessage("§c"+target+"在你的黑名单内或你在他的黑名单内，无法给他发送包裹，如需发送，请解除黑名单关系");
                        }else{
                            Friend.canClick.remove(player);
                            createForm.sendPlayerListsSetting(player,target);
                        }
                    }else{
                        if( d == friends.size() + 1){
                            Friend.canClick.put(player,true);
                            createForm.sendPlayerList(player);
                        }else{
                            if(Friend.canClick.containsKey(player)){
                                Friend.canClick.remove(player);
                            }
                            createForm.sendMenu(player);
                        }
                    }

                }else{
                    if(Friend.canClick.containsKey(player)){
                        Friend.canClick.remove(player);
                    }
                    createForm.sendSetting(player);
                }
                break;
            case 0xddea0003:
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                @SuppressWarnings("serial")
                Object[] UIData = gson.fromJson(data,new TypeToken<Object[]>(){}.getType());
                if(UIData == null) return;
                if(UIData.length < 1) {
                    return;
                }
                String text = (String) UIData[0];
                Friend.playerInput.put(player,text);
                createForm.sendSeelPlayers(player,text);
                break;
            case 0xddea0004:
                if(data.equals("null")) return;
                String target = Friend.playerInput.get(player);
                Friend.playerInput.remove(player);
                LinkedList<String> lists = Friend.getFriend().getQuery(player.getName(),target);
                if(lists.size() > Integer.parseInt(data)){
                    String name = lists.get(Integer.parseInt(data));
                    Friend.clickPlayer.put(player,name);
                    createForm.sendPlayerListsSetting(player,name);
                }else{
                    createForm.sendSeek(player);
                }
                break;
            case 0xddea0005:
                if(data.equals("null")) return;
                if(Friend.playerInventory.get(player).size() > Integer.parseInt(data)){
                    Friend.clickInventory.put(player,Integer.parseInt(data));
                    createForm.sendExtractItem(player);
                    return;
                }
                if(Integer.parseInt(data) == Friend.playerInventory.get(player).size() + 1){
                    createForm.sendMenu(player);

                }else{
                    createForm.sendInventory(player);
                }
                break;
            case 0xddea0006:
                if(data.equals("null")) return;
                if(Friend.canSend.containsKey(player)){
                    Friend.canSend.remove(player);
                    if(Friend.clickItem.containsKey(player)){
                        //邮寄物品
                        if(data.equals("true")){
                            int coin = Friend.getFriend().getConfig().getInt("物品邮寄手续费");
                            if(EconomyAPI.getInstance().myMoney(player) >= coin){
                                //发送
                                createForm.sendPlayerInventorysChat(player,new itemTile(new Date(),player.getName(),Friend.clickItem.get(player)));
                                //
                            }else{
                                Friend.clickItem.remove(player);
                                player.sendMessage("§c抱歉，您的金钱不足，无法邮寄");
                            }
                        }else{
                            Friend.clickItem.remove(player);
                            if(!Friend.getFriend().isBlack(player.getName(),Friend.clickPlayer.get(player)) && !Friend.getFriend().isBlack(Friend.clickPlayer.get(player),player.getName()))
                                createForm.sendPlayerInventorys(player);
                            else
                                player.sendMessage("§c"+Friend.clickPlayer.get(player)+"在你的黑名单内或你在他的黑名单内，无法给他发送包裹，如需发送，请解除黑名单关系");

                        }
                        return;
                    }
                }
                if(Friend.canRemove.containsKey(player)){
                    Friend.canRemove.remove(player);
                    if(data.equals("true")){
                        String t = Friend.clickPlayer.get(player);
                        player.sendMessage("§d你已经解除§a"+t+"§d的好友关系");
                        playerDelFriendEvent event1 = new playerDelFriendEvent(player,t);
                        Server.getInstance().getPluginManager().callEvent(event1);

                    }
                }
                if(Friend.canBlack.containsKey(player)){
                    Friend.canBlack.remove(player);
                    if(data.equals("true")){
                        String t = Friend.clickPlayer.get(player);
                        if(Friend.getFriend().isFriend(player.getName(),t)){
                            player.sendMessage("§d你已经解除§a"+t+"§d的好友关系 并将他拉黑");
                            Friend.getFriend().writeBlack(player.getName(),t);
                            playerDelFriendEvent event1 = new playerDelFriendEvent(player,t);
                            Server.getInstance().getPluginManager().callEvent(event1);
                        }else {
                            player.sendMessage("§d你拉黑"+t);
                            Friend.getFriend().writeBlack(player.getName(),t);
                        }


                    }
                }
                break;

            case 0xddea0007:
                if(data.equals("null")) return;
                if(Integer.parseInt(data) == 0){
                    int da = Friend.clickInventory.get(player);
                    itemTile itemTile = Friend.getFriend().getItemByData(player.getName(),da);
                    Friend.getFriend().delItem(player.getName(),itemTile.getTarget(),Integer.parseInt(data));
                    Item item = itemTile.getItem();
                    if(player.getInventory().canAddItem(item)){
                        player.sendMessage("§a[包裹]领取成功");
                        player.getInventory().addItem(item);
                    }else{
                        player.sendMessage("§a[包裹]领取成功 你的背包满啦，掉出来啦");
                        player.level.dropItem(player.getPosition(),item);
                    }
                    Friend.playerInventory.remove(player);
                }else{
                    createForm.sendInventory(player);
                }
                break;
            case 0xddea0008:
                if(data.equals("null")) return;
                target = Friend.clickPlayer.get(player);
                if(Friend.getFriend().isFriend(player.getName(),target)){
                    switch (Integer.parseInt(data)){
                        case 0:
                            if(Friend.getFriend().isBlack(player.getName(),target) || Friend.getFriend().isBlack(target,player.getName()))
                                player.sendMessage("§c"+target+"在你的黑名单内或你在他的黑名单内，无法给他发送消息，如需要聊天，请解除黑名单关系");
                            else
                                createForm.sendChat(player);
                            break;
                        case 1:
                            if(!Friend.getFriend().isBlack(player.getName(),target) && !Friend.getFriend().isBlack(target,player.getName()))
                                createForm.sendPlayerInventorys(player);
                            else
                                player.sendMessage("§c"+Friend.clickPlayer.get(player)+"在你的黑名单内或你在他的黑名单内，无法给他发送包裹，如需发送，请解除黑名单关系");
                            break;
                        case 2:
                            if(Friend.getFriend().isBlack(player.getName(),target)){
                                player.sendMessage("§e你已解除"+target+"的黑名单关系");
                                Friend.getFriend().delBlack(player.getName(),target);
                            }else{
                                Friend.canBlack.put(player,true);
                                createForm.sendModal(player,"§c你确定要拉黑"+Friend.clickPlayer.get(player)+"吗?\n拉黑后对方将不能添加你为好友 不能发起临时会话，不能邮寄物品");
                            }
                            break;
                        case 3:
                            createForm.sendModal(player,"§c你确定要解除§a"+Friend.clickPlayer.get(player)+"§c的好友关系吗");
                            Friend.canRemove.put(player,true);

                            break;
                        case 4:
                            createForm.sendMenu(player);
                    }
                }else{
                    switch (Integer.parseInt(data)){
                        case 0:
                            if(Friend.getFriend().isBlack(player.getName(),target) || Friend.getFriend().isBlack(target,player.getName())){
                                player.sendMessage("§c"+target+"在你的黑名单内或你在他的黑名单内，无法给他发送消息，如需要聊天，请解除黑名单关系");
                            }else{
                                createForm.sendChat(player);
                            }

                            break;
                        case 1:
                            if(Friend.getFriend().isBlack(player.getName(),target) || Friend.getFriend().isBlack(target,player.getName())){
                                player.sendMessage("§c"+target+"在你的黑名单内或你在他的黑名单内，无法加他为好友");
                                return;
                            }
                            if(Friend.getFriend().isAccept(player.getName(),target)){
                                playerAcceptFriendEvent event1 = new playerAcceptFriendEvent(player,target);
                                Server.getInstance().getPluginManager().callEvent(event1);
                            }else{
                                if(!Friend.getFriend().isAccept(target,player.getName())){
                                    playerApplyFriendEvent event1 = new playerApplyFriendEvent(player,target);
                                    Server.getInstance().getPluginManager().callEvent(event1);
                                }else{
                                    player.sendMessage("§e[好友]请耐心等待回复");
                                }
                            }
                            break;
                        case 2:
                            if(Friend.getFriend().isBlack(player.getName(),target)){
                                player.sendMessage("§e你已解除"+target+"的黑名单关系");
                                Friend.getFriend().delBlack(player.getName(),target);
                            }else{
                                Friend.canBlack.put(player,true);
                                createForm.sendModal(player,"§4你确定要拉黑"+Friend.clickPlayer.get(player)+"吗?\n拉黑后对方将不能添加你为好友 不能发起临时会话，不能邮寄物品");
                            }
                            break;
                        case 3:
                            createForm.sendMenu(player);
                            break;
                    }
                }
                break;
            case 0xddea0009:
                if(data.equals("null")) return;
                builder = new GsonBuilder();
                gson = builder.create();
                UIData = gson.fromJson(data,new TypeToken<Object[]>(){}.getType());
                if(UIData.length < 1) {
                    createForm.sendChat(player);
                    return;
                }
                String chatmessage = (String) UIData[1];
                if(chatmessage != null && !chatmessage.equals("")){
                    playerFriendChatEvent event1 = new playerFriendChatEvent(player,Friend.clickPlayer.get(player),chatmessage);
                    Server.getInstance().getPluginManager().callEvent(event1);
                    createForm.sendChat(player);
                }else{
                    createForm.sendChat(player);
                }
                break;
            case 0xddea0010:
                if(data.equals("null")) return;
                if(Friend.playerChat.containsKey(player.getName())){
                    LinkedHashMap<String,playerChatMessage> chatMessageLinkedHashMap = Friend.playerChat.get(player.getName());
                    int da = Integer.parseInt(data);
                    if(chatMessageLinkedHashMap.size() > da){
                        int i = 0;
                        for(String name:chatMessageLinkedHashMap.keySet()){
                            if(i == da){
                                Friend.clickPlayer.put(player,name);
                                createForm.sendChat(player);
                                break;
                            }
                            i++;
                        }
                    }else{
                        if(da == chatMessageLinkedHashMap.size() + 1){
                            createForm.sendMenu(player);
                        }else{
                            createForm.sendChatPlayers(player);
                        }
                    }
                }else{
                    if(Integer.parseInt(data) == 0){
                        createForm.sendChatPlayers(player);
                    }else{
                        createForm.sendMenu(player);
                    }
                }
                break;
            case 0xddea0011:
                if(data.equals("null")) return;
                LinkedList<String> a = Friend.getFriend().getAccepts(player.getName());
                if(a.size() > Integer.parseInt(data)){
                    String string = a.get(Integer.parseInt(data));
                    createForm.sendPlayerListsSetting(player,string);
                    Friend.clickPlayer.put(player,string);
                }else{
                    if(Integer.parseInt(data) == a.size() + 1){
                        createForm.sendMenu(player);
                    }else{
                        createForm.sendApplyPlayers(player);
                    }
                }
                break;
            case 0xddea0012:
                if(data.equals("null")) return;
                int clickData = Integer.parseInt(data);
                if(Friend.clickItems.get(player).size() > clickData){
                    Item items = Friend.clickItems.get(player).get(clickData);
                    Friend.clickItem.put(player,items);
                    Friend.canSend.put(player,true);
                    int coin = Friend.getFriend().getConfig().getInt("物品邮寄手续费");
                    createForm.sendModal(player,"§c你确定要邮寄§a"+ ItemIDSunName.getIDByName(items)+"§c吗?\n§d(本次邮寄需要花费 §e"+coin+"§d游戏币)");
                }else{
                    if(clickData == Friend.clickItems.get(player).size() + 1){
                        createForm.sendMenu(player);
                    }else{

                        createForm.sendPlayerInventorys(player);
                    }
                }
                break;
            case 0xddea0013:
                builder = new GsonBuilder();
                gson = builder.create();
                UIData = gson.fromJson(data,new TypeToken<Object[]>(){}.getType());
                if(UIData == null) return;
                if(UIData.length < 1) {
                    return;
                }
                String message = (String) UIData[1];
                if(message != null){
                    int coin = Friend.getFriend().getConfig().getInt("物品邮寄手续费");
                    Item item = Friend.clickItem.get(player);
                    Friend.clickItem.remove(player);
                    EconomyAPI.getInstance().reduceMoney(player,coin);
                    player.getInventory().removeItem(item);
                    target = Friend.clickPlayer.get(player);
                    itemTile itemTile = new itemTile(new Date(),message,player.getName(),item);
                    Friend.getFriend().addItems(target,player.getName(),itemTile);
                    Player player1 = Server.getInstance().getPlayer(target);
                    if(player1 != null){
                        playerGetItemEvent event1 = new playerGetItemEvent(player1,player.getName(),itemTile);
                        Server.getInstance().getPluginManager().callEvent(event1);
                    }
                    player.sendMessage("§e[包裹]包裹已成功送出");
                }else{
                    Friend.clickItem.remove(player);
                    createForm.sendPlayerInventorys(player);

                }
                break;
            case 0xddea0014:
                if(data.equals("null")) return;
                switch (Integer.parseInt(data)){
                    case 0:
                        createForm.resetImage(player);
                        break;
                    case 1:
                        createForm.resetText(player);
                        break;
                    case 2:
                        StringBuilder builder1 = new StringBuilder("");
                        builder1.append("§7=======黑名单========\n");
                        int i = 0;
                        StringBuilder stringBuilder = new StringBuilder("");
                        for(String name:Friend.getFriend().getBlacks(player.getName())){
                            stringBuilder.append(name).append("\n");
                            i++;
                        }
                        builder1.append("共 ").append(i).append("位").append("\n");
                        if(stringBuilder.toString().equals("")){
                            stringBuilder.append("无\n");
                        }
                        builder1.append(stringBuilder);
                        builder1.append("§7=======黑名单========\n");
                        player.sendMessage(builder1.toString());
                        break;
                    case 3:
                        FormWindowCustom custom = new FormWindowCustom("好友系统----其它设置");
                        ElementDropdown dropdown = new ElementDropdown("好友申请设置");
                        ArrayList<String> list = new ArrayList<String>(){{
                            add("手动接受");
                            add("同意所有申请");
                            add("拒绝所有申请");
                        }};
                        String pls = Friend.getFriend().getPlayerSettings(player.getName());
                        dropdown.addOption(list.get(0),list.get(0).equals(pls));
                        dropdown.addOption(list.get(1),list.get(1).equals(pls));
                        dropdown.addOption(list.get(2),list.get(2).equals(pls));
                        ElementToggle stepSlider = new ElementToggle("私聊信息提醒");
                        stepSlider.setDefaultValue(Friend.getFriend().getCanSetting(player.getName(),"自动弹消息框"));
                        ElementToggle stepSlider1 = new ElementToggle("好友申请提示");
                        stepSlider1.setDefaultValue(Friend.getFriend().getCanSetting(player.getName(),"自动弹好友申请"));
                        ElementToggle stepSlider2 = new ElementToggle("好友上线提醒");
                        stepSlider2.setDefaultValue(Friend.getFriend().getCanSetting(player.getName(),"好友上线提醒"));
                        custom.addElement(dropdown);
                        custom.addElement(stepSlider);
                        custom.addElement(stepSlider1);
                        custom.addElement(stepSlider2);
                        player.showFormWindow(custom,0xddea0017);
                        break;
                    case 4:
                        createForm.sendMenu(player);
                        break;
                }
                break;
            case 0xddea0015:
                builder = new GsonBuilder();
                gson = builder.create();
                UIData = gson.fromJson(data,new TypeToken<Object[]>(){}.getType());
                if(UIData == null){
                    createForm.sendSetting(player);
                    return;
                }
                if(UIData.length < 1) {
                    createForm.resetText(player);
                    return;
                }
                String t = (String) UIData[0];
                String chat;
                if(t != null && !t.equals("")){
                    chat = t;
                }else{
                    chat = "这个人很懒，什么也没留下";
                }
                Config config = Friend.getFriend().getPlayerConfig(player.getName());
                config.set("个性签名",chat);
                config.save();
                player.sendMessage("§b[签名]§6您的签名已更改为: §r"+chat);
                break;
            case 0xddea0016:
                builder = new GsonBuilder();
                gson = builder.create();
                UIData = gson.fromJson(data,new TypeToken<Object[]>(){}.getType());
                if(UIData == null){
                    createForm.sendSetting(player);
                    return;
                }
                if(UIData.length < 1) {
                    createForm.resetText(player);
                    return;
                }
                t = (String) UIData[0];
                if(t != null && !t.equals("")){
                    chat = t;
                }else{
                    player.sendMessage("§c错误格式");
                    return;
                }
                config = Friend.getFriend().getPlayerConfig(player.getName());
                config.set("QQ头像",chat);
                config.save();
                player.sendMessage("§b[签名]§6您的头像已改为QQ用户§r"+chat+"的头像");
                break;
            case 0xddea0017:
                builder = new GsonBuilder();
                gson = builder.create();
                UIData = gson.fromJson(data,new TypeToken<Object[]>(){}.getType());
                if(UIData == null) return;
                ArrayList<String> list = new ArrayList<String>(){{
                    add("手动接受");
                    add("同意所有申请");
                    add("拒绝所有申请");
                }};
                int t1 = (int)((double) UIData[0]);
                boolean t2 = (boolean) UIData[1];
                boolean t3 = (boolean) UIData[2];
                boolean t4 = (boolean) UIData[3];
                if(!list.get(t1).equals(Friend.getFriend().getPlayerSettings(player.getName()))){
                    player.sendMessage("§b[设置]§e好友申请设置已修改为"+list.get(t1));
                    Friend.getFriend().setSetting(player.getName(),"好友申请设置",list.get(t1));
                }
                if(t2 != (Friend.getFriend().getCanSetting(player.getName(),"自动弹消息框"))){
                    player.sendMessage("§b[设置]§e私聊信息提示已设置为"+((t2)?"§a true":"§c false"));
                    Friend.getFriend().setSetting(player.getName(),"自动弹消息框",(t2));
                }
                if(t3 != (Friend.getFriend().getCanSetting(player.getName(),"自动弹好友申请"))){
                    player.sendMessage("§b[设置]§e好友申请信息提示信息已设置为"+((t3)?"§a true":"§c false"));
                    Friend.getFriend().setSetting(player.getName(),"自动弹好友申请",(t3));
                }
                if(t4 != (Friend.getFriend().getCanSetting(player.getName(),"好友上线提醒"))){
                    player.sendMessage("§b[设置]§e好友上线提醒已设置为"+((t4)?"§a true":"§c false"));
                    Friend.getFriend().setSetting(player.getName(),"好友上线提醒",(t4));
                }

                break;
        }
    }
    @EventHandler(priority = EventPriority.MONITOR,ignoreCancelled = true)
    public void Accept(playerAcceptFriendEvent event){
        Player player = event.getPlayer();
        String target = event.getTarget();
        if(event.isCancelled()){
            player.sendMessage("§a[好友]§c抱歉，你不能添加好友..");
            return;
        }
        Friend.getFriend().delAccept(player.getName(),target);
        Player t = Server.getInstance().getPlayer(target);
        if(t != null){
            t.sendMessage("§e[好友] §e成功添加"+player.getName()+"为好友");
        }
        Friend.getFriend().writeFriend(player.getName(),target);
        player.sendMessage("§a[好友]成功添加"+target+"好友");

    }

    @EventHandler
    public void onApply(playerApplyFriendEvent event){
        Player player = event.getPlayer();
        String target = event.getTarget();
        player.sendMessage("§e[好友] 好友请求已发送");
        if(Friend.getFriend().getPlayerSettings(target).equals("同意所有申请")){
            Player target1 = Server.getInstance().getPlayer(target);
            if(target1 != null){
                playerAcceptFriendEvent event1 = new playerAcceptFriendEvent(target1,player.getName());
                Server.getInstance().getPluginManager().callEvent(event1);
            }else{
                player.sendMessage("§a[好友]成功添加"+target+"好友");
                Friend.getFriend().writeFriend(player.getName(),target);
            }
            return;
        }else if(Friend.getFriend().getPlayerSettings(target).equals("拒绝所有申请")){
            player.sendMessage("§a[好友]§c对方已拒绝好友申请");
            return;
        }
        Player t = Server.getInstance().getPlayer(target);
        if(t != null && Friend.getFriend().getCanSetting(target,"自动弹好友申请")){
            t.sendMessage("§e[好友] §a"+player.getName()+"§e请求添加你为好友....");
        }
        Friend.getFriend().writeAccept(target,player.getName());
    }

    @EventHandler
    public void onChat(playerFriendChatEvent event){
        Player player = event.getPlayer();
        String target = event.getTarget();

        if(Friend.playerChat.containsKey(player.getName())){
            LinkedHashMap<String,playerChatMessage> chatMessageLinkedHashMap = Friend.playerChat.get(player.getName());
            Message message = new Message(player.getName(),event.getMessage(),new Date());
            if(chatMessageLinkedHashMap.containsKey(target)){
                playerChatMessage chatMessage = chatMessageLinkedHashMap.get(target);
                chatMessage.addChat(message);
                chatMessageLinkedHashMap.put(target,chatMessage);
            }else{
                playerChatMessage chatMessage = new playerChatMessage(player.getName(),new Message(player.getName(),event.getMessage(),new Date()));
                chatMessageLinkedHashMap.put(target,chatMessage);
            }
            Friend.playerChat.put(player.getName(),chatMessageLinkedHashMap);
        }else{
            LinkedHashMap<String,playerChatMessage> chatMessageLinkedHashMap = new LinkedHashMap<>();
            chatMessageLinkedHashMap.put(target,new playerChatMessage(player.getName(),new Message(player.getName(),event.getMessage(),new Date())));
            Friend.playerChat.put(player.getName(),chatMessageLinkedHashMap);
        }
        Player t = Server.getInstance().getPlayer(target);
        if(t != null && Friend.getFriend().getCanSetting(target,"自动弹消息框")){
            t.sendMessage("§e[信息]§a"+player.getName()+"§a给您发了一条消息 请打开临时会话查看");
        }
        if(Friend.playerChat.containsKey(target)){
            LinkedHashMap<String,playerChatMessage> messageLinkedHashMap = Friend.playerChat.get(target);
            Message message = new Message(player.getName(),event.getMessage(),new Date());
            if(messageLinkedHashMap.containsKey(player.getName())){
                playerChatMessage chatMessage = messageLinkedHashMap.get(player.getName());
                chatMessage.addChat(message);
                messageLinkedHashMap.put(player.getName(),chatMessage);
            }else{
                playerChatMessage chatMessage = new playerChatMessage(player.getName(),message);
                messageLinkedHashMap.put(target,chatMessage);
            }
            Friend.playerChat.put(target,messageLinkedHashMap);
        }else{
            LinkedHashMap<String,playerChatMessage> targetMessage = new LinkedHashMap<>();
            targetMessage.put(player.getName(),new playerChatMessage(target,new Message(player.getName(),event.getMessage(),new Date())));
            Friend.playerChat.put(target,targetMessage);
        }

    }
    @EventHandler
    public void onDel(playerDelFriendEvent event){
        Player player = event.getPlayer();
        Player player1 = Server.getInstance().getPlayer(event.getTarget());
        if(player1 != null)
            player1.sendMessage("§d[好友]§c您的好友"+player.getName()+"解除了好友关系");
        Friend.getFriend().delFriend(player.getName(),event.getTarget());
    }

    @EventHandler
    public void onGet(playerGetItemEvent event){
        Player player = event.getPlayer();
        player.sendMessage("§e[包裹]§a你获得了来自§e"+event.getTarget()+"§a的一个包裹\n§d留言: §r"+event.getTile().getMessage());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(Friend.clickPlayer.containsKey(player))
            Friend.clickPlayer.remove(player);
        if(Friend.players.containsKey(player))
            Friend.players.remove(player);
        if(Friend.clickInventory.containsKey(player))
            Friend.clickInventory.remove(player);
        if(Friend.playerChat.containsKey(player.getName()))
            Friend.playerChat.remove(player.getName());
        if(Friend.playerInput.containsKey(player))
            Friend.playerInput.remove(player);
        if(Friend.playerInventory.containsKey(player))
            Friend.playerInventory.remove(player);
    }

}
