package Friends.Form;


import Friends.Friend;
import Friends.utils.ItemIDSunName;
import Friends.utils.itemTile;
import Friends.utils.playerChatMessage;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.network.protocol.ModalFormRequestPacket;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class createForm {

    private static LinkedHashMap<String,String> menuButton = new LinkedHashMap<String,String>(){
        {
            put("好友列表","textures/ui/FriendsDiversity");
            put("查找好友","textures/ui/magnifyingGlass");
            put("申请列表","textures/ui/invite_hover");
            put("邮寄物品","textures/ui/trade_icon");
            put("仓库","textures/ui/inventory_icon");
            put("临时会话","textures/ui/Feedback");
            put("设置","textures/ui/Wrenches1");
        }
    };
    private static LinkedHashMap<String,String> setting = new LinkedHashMap<String,String>(){
        {
            put("修改头像","textures/items/armor_stand");
            put("修改个性签名","textures/items/book_writable");
            put("黑名单","textures/ui/invite_hover");
            put("其他设置","textures/ui/Wrenches1");
            put("返回","textures/ui/refresh_light");
        }
    };
    private static LinkedHashMap<String,String> playerButtons = new LinkedHashMap<String,String>(){
        {
            put("临时会话","textures/ui/Feedback");
            put("邮寄物品","textures/ui/trade_icon");
            put("加入黑名单","textures/ui/blindness_effect");
            put("删除好友","textures/ui/trash_default");
            put("传送好友","textures/ui/FriendsDiversity");
            put("返回","textures/ui/refresh_light");
        }
    };
    private static LinkedHashMap<String,String> buttonsApply = new LinkedHashMap<String,String>(){
        {
            put("临时会话","textures/ui/Feedback");
            put("添加好友","textures/ui/FriendsDiversity");
            put("加入黑名单","textures/ui/blindness_effect");
            put("返回","textures/ui/refresh_light");
        }
    };

    public static void sendMenu(Player player){
        int id = 0xddea0001;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----主菜单");
        data.put("type","form");
        ArrayList<LinkedHashMap<String,Object>> buttons = new ArrayList<>();
        for (String name:menuButton.keySet()){
            LinkedHashMap<String,Object> button = new LinkedHashMap<>(),
                    image = new LinkedHashMap<>();
            image.put("type","path");
            image.put("data",menuButton.get(name));
            switch (name) {
                case "好友列表":
                    name = name + "§e[在线§a" + Friend.getFriend().getFriendOnline(player.getName()).size() + "§e人]";
                    break;
                case "仓库":
                    int c = Friend.getFriend().getCounts(player.getName());
                    if(c > 0) {
                        name = name + "§c[你有 §d" + c + "§c件物品未领取]";
                    }
                    break;
                case "申请列表":
                    name = name + "§6(§c" + Friend.getFriend().getAccepts(player.getName()).size() + "§6)";
                    break;
            }
            button.put("text",name);
            button.put("image",image);
            buttons.add(button);
        }
        data.put("buttons",buttons);
        data.put("content",Friend.getFriend().getPlayerConfig(player.getName()).getString("个性签名"));
        send(player,id,data);
    }

    public static void sendPlayerList(Player player){
        int id = 0xddea0002;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----好友列表");
        data.put("type","form");
        ArrayList<LinkedHashMap<String,Object>> buttons = new ArrayList<>();
        LinkedHashMap<String,Object> me = asButton(player,player.getName());
        buttons.add(me);
        Friend.players.put(player,Friend.getFriend().getFriends(player.getName()));
        for(String name:Friend.getFriend().getFriends(player.getName())){
            buttons.add(asButton(player,name));
        }
        data.put("content","§d你共有 §a"+(buttons.size()-1)+"§d位好友   §b当前在线: §9"
                +Friend.getFriend().getFriendOnline(player.getName()).size()+"§c/§6"+(buttons.size() - 1));

        buttons.addAll(addBackAndRefresh());
        data.put("buttons",buttons);
        send(player,id,data);
    }

    public static void sendSeek(Player player){
        int id = 0xddea0003;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----查找");
        data.put("type","custom_form");
        ArrayList<LinkedHashMap<String,Object>> items = new ArrayList<>();
        LinkedHashMap<String,Object> content = new LinkedHashMap<>();
        content.put("type","input");
        content.put("text","§c请输入您查找的玩家名称");
        content.put("placeholder","玩家名称");
        content.put("default",null);
        items.add(content);
        data.put("content",items);
        send(player,id,data);
    }


    public static void sendSeelPlayers(Player player,String target){
        int id = 0xddea0004;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----查询结果");
        data.put("type","form");
        ArrayList<LinkedHashMap<String,Object>> buttons = new ArrayList<>();
        LinkedList<String> lists = Friend.getFriend().getQuery(player.getName(),target);

        for(String name:lists){
            buttons.add(asButton(player,name));
        }
        data.put("content","§e"+target+" §a的查找结果   §d共发现§a"+lists.size()+"§d个");
        LinkedHashMap<String,Object> refresh = new LinkedHashMap<>(),
                refreshImage = new LinkedHashMap<>();
        refreshImage.put("type","path");
        refreshImage.put("data","textures/ui/refresh_light");
        refresh.put("text","返回重新查找");
        refresh.put("image",refreshImage);
        buttons.add(refresh);
        data.put("buttons",buttons);
        send(player,id,data);
    }


    public static void sendInventory(Player player){
        int id = 0xddea0005;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----仓库");
        data.put("type","form");
        ArrayList<LinkedHashMap<String,Object>> buttons = new ArrayList<>();

        LinkedHashMap<String,LinkedList<itemTile>> items = Friend.getFriend().getItems(player.getName());
        LinkedList<itemTile> lists = new LinkedList<>();
        for(String name:items.keySet()){
            LinkedList<itemTile> types = items.get(name);
            for(itemTile item:types){
                LinkedHashMap<String,Object> me = new LinkedHashMap<>();
                int del = Friend.getFriend().getLong() - item.getTime();
                if(del <= 0){
                    Friend.getFriend().delItem(player.getName(),name,item);
                }else{
                    LinkedHashMap<String,Object> refreshImage = new LinkedHashMap<>();
                    refreshImage = getImageData(item.getItem());
                    me.put("text","§d来自:§9"+name+"§d的包裹"+"§c("+del+"天后过期)\n§7点击查看详情");
                    me.put("image",refreshImage);
                    buttons.add(me);
                    lists.add(item);
                }
            }
        }
        Friend.playerInventory.put(player,lists);
        data.put("content",buttons.size() == 0?"\n\n\n\n\n\n\n          §c空空如也\n\n\n":"");
        buttons.addAll(addBackAndRefresh());
        data.put("buttons",buttons);
        send(player,id,data);
    }

    private static LinkedHashMap<String,Object> getImageData(Item item){
        LinkedHashMap<String,Object> objectLinkedHashMap = new LinkedHashMap<>();
        objectLinkedHashMap.put("type","path");
        String path = ItemIDSunName.getIDByPath(item.getId(),item.getDamage()) != null ?
                ItemIDSunName.getIDByPath(item.getId(),item.getDamage()):"textures/ui/how_to_play_button_default";
        objectLinkedHashMap.put("data",path);
        return objectLinkedHashMap;
    }

    public static void sendModal(Player player,String text){
        int id = 0xddea0006;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----验证");
        data.put("type","modal");
        data.put("content",text);
        data.put("button1","§a确定");
        data.put("button2","§c取消");
        send(player,id,data);
    }

    public static void sendExtractItem(Player player){
        int id = 0xddea0007;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----包裹");
        data.put("type","form");
        if(!Friend.clickInventory.containsKey(player)){
            player.sendMessage("请再点击一次");
            return;
        }
        ArrayList<LinkedHashMap<String,Object>> buttons = new ArrayList<>();
        int d = Friend.clickInventory.get(player);
        itemTile itemTile = Friend.getFriend().getItemByData(player.getName(),d);
        StringBuilder builder = new StringBuilder("");
        builder.append("§e╭──────────────────────────────────────╮\n");
        builder.append("§e│§d发送者: ").append(itemTile.getTarget()).append("\n");
        builder.append("§e│§a物品名称: ").append(ItemIDSunName.getIDByName(itemTile.getItem())).append("\n");
        builder.append("§e│§b物品数量: ").append(itemTile.getItem().getCount()).append("\n");
        builder.append("§e│§e留言:").append(itemTile.getMessage()).append("\n");
        int del = Friend.getFriend().getLong() - itemTile.getTime();
        builder.append("§e│§c到期时间: ").append(del).append(" 天后到期").append("\n");
        builder.append("§e╰──────────────────────────────────────╯").append("\n");
        LinkedHashMap<String,Object> back = new LinkedHashMap<>(),
                refreshImage = new LinkedHashMap<>(),
                backImage = new LinkedHashMap<>(),
                refresh    = new LinkedHashMap<>();
        backImage.put("type","path");
        backImage.put("data","textures/ui/gift_square");
        back.put("text","领取");
        back.put("image",backImage);
        refreshImage.put("type","path");
        refreshImage.put("data","textures/ui/refresh_light");
        refresh.put("text","返回");
        refresh.put("image",refreshImage);
        buttons.add(back);
        buttons.add(refresh);
        data.put("content",builder.toString());
        data.put("buttons",buttons);
        send(player,id,data);

    }

    public static void sendPlayerListsSetting(Player player,String target){
        int id = 0xddea0008;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----好友设置");
        data.put("type","form");
        LinkedHashMap<String,String> list = buttonsApply;
        ArrayList<LinkedHashMap<String,Object>> buttons = new ArrayList<>();
        if(Friend.getFriend().isFriend(player.getName(),target)){
            list = playerButtons;
        }
        for(String text:list.keySet()){
            String s = text;
            if(text.equals("加入黑名单")){
                if(Friend.getFriend().isBlack(player.getName(),target)) {
                    s = "移出黑名单";
                }
            }
            buttons.add(getButton(s,list.get(text)));
        }
        data.put("content","");
        data.put("buttons",buttons);
        send(player,id,data);
    }


    public static void sendChat(Player player){
        int id = 0xddea0009;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----聊天");
        data.put("type","custom_form");
        ArrayList<LinkedHashMap<String,Object>> items = new ArrayList<>();
        String target = Friend.clickPlayer.get(player);
        LinkedHashMap<String,Object> content = new LinkedHashMap<>();
        LinkedHashMap<String,Object> label = new LinkedHashMap<>();
        StringBuilder builder = new StringBuilder("");
        if(Friend.playerChat.containsKey(player.getName())){
            LinkedHashMap<String,playerChatMessage> chatMessageLinkedHashMap = Friend.playerChat.get(player.getName());
            if(chatMessageLinkedHashMap.containsKey(target)){
                playerChatMessage message = chatMessageLinkedHashMap.get(target);
                builder.append(message.getChat());
            }
        }
        content.put("type","input");
        content.put("text","");
        content.put("placeholder","对话内容");
        content.put("default",null);
        label.put("type","label");
        if(builder.toString().equals("")){
            builder.append("此记录仅临时保存\n\n\n\n\n\n\n\n\n");
        }else if(builder.toString().split("\\n").length < 10){
            for(int i = 11; i > builder.toString().split("\\n").length;i--)
                builder.append("\n");
        }
        label.put("text",builder.toString());
        items.add(label);
        items.add(content);
        data.put("content",items);
        send(player,id,data);
    }

    public static void sendChatPlayers(Player player){
        int id = 0xddea0010;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----临时会话");
        data.put("type","form");
        ArrayList<LinkedHashMap<String,Object>> buttons = new ArrayList<>();
        if(Friend.playerChat.containsKey(player.getName())){
            LinkedHashMap<String,playerChatMessage> chatMessageLinkedHashMap = Friend.playerChat.get(player.getName());
            for(String target:chatMessageLinkedHashMap.keySet()){
                buttons.add(asButton(player,target));
            }
        }
        if(buttons.size() == 0){
            data.put("content","没有任何临时会话");
        }else{
            data.put("content","");
        }
        buttons.addAll(addBackAndRefresh());
        data.put("buttons",buttons);
        send(player,id,data);
    }

    public static void sendApplyPlayers(Player player){
        int id = 0xddea0011;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----申请列表");
        data.put("type","form");
        ArrayList<LinkedHashMap<String,Object>> buttons = new ArrayList<>();
        for(String target:Friend.getFriend().getAccepts(player.getName())){
            buttons.add(asButton(player,target));
        }
        if(buttons.size() == 0){
            data.put("content","§c没有任何好友申请");
        }else{
            data.put("content","");
        }
        buttons.addAll(addBackAndRefresh());
        data.put("buttons",buttons);
        send(player,id,data);
    }


    public static void sendPlayerInventorys(Player player){
        int id = 0xddea0012;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        LinkedHashMap<String,Object> image = new LinkedHashMap<>();
        image.put("type","path");
        image.put("data","textures/ui/loom_pattern_item_empty");
        ArrayList<LinkedHashMap<String,Object>> buttons = new ArrayList<>();
        for(Item item:Friend.getFriend().getItemsByInventory(player)){
            LinkedHashMap<String,Object> button = new LinkedHashMap<>();
            button.put("text",ItemIDSunName.getIDByName(item)+" * "+item.getCount());
            button.put("image",image);
            buttons.add(button);
        }

        Friend.clickItems.put(player,Friend.getFriend().getItemsByInventory(player));
        data.put("title","好友系统----邮寄物品");
        data.put("type","form");
        if(buttons.size() == 0){
            data.put("content"," §c你没有任何物品可以邮寄");
        }else{
            data.put("content","§d你有§a"+ buttons.size()+"§d件物品可以邮寄");
        }
        buttons.addAll(addBackAndRefresh());
        data.put("buttons",buttons);
        send(player,id,data);

    }


    public static void sendPlayerInventorysChat(Player player,itemTile itemTile){//留言
        int id = 0xddea0013;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        String builder = "" + "§e╭──────────────────────────────────────╮\n" +
                "§e│§a发送者: " + itemTile.getTarget() + "\n" +
                "§e│§6物品名称: " + ItemIDSunName.getIDByName(itemTile.getItem()) + "\n" +
                "§e│§d物品数量: " + itemTile.getItem().getCount() + "\n" +
                "§e╰──────────────────────────────────────╯" + "\n";
        ArrayList<LinkedHashMap<String,Object>> items = new ArrayList<>();
        LinkedHashMap<String,Object> content = new LinkedHashMap<>();
        content.put("type","input");
        content.put("text","§c请输入留言");
        content.put("placeholder","例如: 送给你一些"+ItemIDSunName.getIDByName(itemTile.getItem()));
        content.put("default",null);
        LinkedHashMap<String,Object> label = new LinkedHashMap<>();
        label.put("type","label");
        label.put("text", builder);
        items.add(label);
        items.add(content);
        data.put("content",items);
        data.put("title","好友系统----邮寄物品");
        data.put("type","custom_form");
        send(player,id,data);
    }


    public static void sendSetting(Player player){//设置
        int id = 0xddea0014;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----设置");
        data.put("type","form");
        ArrayList<LinkedHashMap<String,Object>> buttons = new ArrayList<>();
        for(String name:setting.keySet()){
            buttons.add(getButton(name,setting.get(name)));
        }
        data.put("content","");
        data.put("buttons",buttons);
        send(player,id,data);
    }

    public static void resetText(Player player){
        int id = 0xddea0015;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----修改签名");
        data.put("type","custom_form");
        ArrayList<LinkedHashMap<String,Object>> items = new ArrayList<>();
        LinkedHashMap<String,Object> content = new LinkedHashMap<>();
        content.put("type","input");
        content.put("text","§c请输入您的签名");
        content.put("placeholder","今天天气真好");
        content.put("default",null);
        items.add(content);
        data.put("content",items);
        send(player,id,data);
    }

    public static void resetImage(Player player){
        int id = 0xddea0016;
        LinkedHashMap<String,Object> data = new LinkedHashMap<>();
        data.put("title","好友系统----修改头像");
        data.put("type","custom_form");
        ArrayList<LinkedHashMap<String,Object>> items = new ArrayList<>();
        LinkedHashMap<String,Object> content = new LinkedHashMap<>();
        content.put("type","input");
        content.put("text","§c请输入您QQ号 插件会自动获取您的QQ头像");
        content.put("placeholder","您的QQ号");
        content.put("default",null);
        items.add(content);
        data.put("content",items);
        send(player,id,data);
    }

    public static void sendTeleport(Player target,Player player){
        int id = 0xddea0018;
        FormWindowSimple windowSimple = new FormWindowSimple("好友系统----传送请求","");
        String s = "§a§l您的好友向你发起传送请求"+"\n"+
                   "§a§l好友名称: §7"+player.getName()+"\n\n"+
                   "§a§l好友所在地图: "+player.getLevel().getFolderName()+"\n\n";
        windowSimple.setContent(s);
        windowSimple.addButton(new ElementButton("接受"));
        windowSimple.addButton(new ElementButton("拒绝"));
        target.showFormWindow(windowSimple,id);
    }


    //--------------------------------------------//

    private static LinkedHashMap<String,Object> getButton(String text,String data){
        LinkedHashMap<String,Object> button = new LinkedHashMap<>(),
                image = new LinkedHashMap<>();
        image.put("type","path");
        image.put("data",data);
        button.put("text",text);
        button.put("image",image);
        return button;
    }


    private static LinkedHashMap<String,Object> asButton(Player player,String name){
        if(Friend.getFriend().isGroup(name)){
            return asButton(player,Friend.getFriend().getGroupConfig(name).getString("群主"));
        }else {
            LinkedHashMap<String, Object> me = new LinkedHashMap<>(),
                    meImage = new LinkedHashMap<>();
            meImage.put("type", "url");
            meImage.put("data", "http://qlogo2.store.qq.com/qzone/" +
                    Friend.getFriend().getDefaultMessage(name,"QQ头像") + "/" +
                    Friend.getFriend().getDefaultMessage(name,"QQ头像") + "/100.png");
            Player player1 = Server.getInstance().getPlayer(name);
            me.put("text", name + "" + (Friend.getFriend().isFriend(player.getName(), name) ? "§d(好友)" : player.getName().equals(name) ? "§6(你)" : "§c(陌生人)")
                    + (player1 != null ? "§a[在线]" : "§7[离线]") + "\n§r" +
                    Friend.getFriend().getPlayerConfig(name).getString("个性签名", "ta很懒，什么也没留下"));
            me.put("image", meImage);
            return me;
        }
    }


    private static LinkedList<LinkedHashMap<String,Object>> addBackAndRefresh(){
        LinkedList<LinkedHashMap<String,Object>> linkedHashMaps = new LinkedList<>();
        LinkedHashMap<String,Object> back = new LinkedHashMap<>(),
                refreshImage = new LinkedHashMap<>(),
                backImage = new LinkedHashMap<>(),
                refresh    = new LinkedHashMap<>();
        backImage.put("type","path");
        backImage.put("data","textures/ui/refresh_light");
        back.put("text","返回");
        back.put("image",backImage);
        refreshImage.put("type","path");
        refreshImage.put("data","textures/ui/refresh");
        refresh.put("text","刷新");
        refresh.put("image",refreshImage);
        linkedHashMaps.add(refresh);
        linkedHashMaps.add(back);
        return linkedHashMaps;
    }

    private static void send(Player player,int id,LinkedHashMap<String,Object> data){
        ModalFormRequestPacket ui = new ModalFormRequestPacket();
        ui.formId = id;
        ui.data = new GsonBuilder().setPrettyPrinting().create().toJson(data);
        player.dataPacket(ui);
    }





}