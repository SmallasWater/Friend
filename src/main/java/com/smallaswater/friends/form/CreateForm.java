package com.smallaswater.friends.form;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.*;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import com.smallaswater.friends.Friend;
import com.smallaswater.friends.players.PlayerConfig;
import com.smallaswater.friends.players.PlayerWareHouse;
import com.smallaswater.friends.utils.FriendsListByPort;
import com.smallaswater.friends.utils.PlayerChatMessage;
import com.smallaswater.friends.utils.items.ItemIDSunName;
import com.smallaswater.friends.utils.items.ItemTile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class CreateForm {
    private static final LinkedHashMap<String, String> menuButton = new LinkedHashMap<String, String>() {
        {
            this.put("好友列表", "textures/ui/FriendsDiversity");
            this.put("查找好友", "textures/ui/magnifyingGlass");
            this.put("申请列表", "textures/ui/subscription_glyph_color");
            this.put("邮寄物品", "textures/ui/trade_icon");
            this.put("仓库", "textures/ui/inventory_icon");
            this.put("临时会话", "textures/ui/Feedback");
            this.put("设置", "textures/ui/Wrenches1");
        }
    };
    private static final LinkedHashMap<String, String> setting = new LinkedHashMap<String, String>() {
        {
            this.put("修改头像", "textures/items/armor_stand");
            this.put("修改个性签名", "textures/items/book_writable");
            this.put("黑名单", "textures/ui/subscription_glyph_color");
            this.put("其他设置", "textures/ui/Wrenches1");
            this.put("返回", "textures/ui/refresh_light");
        }
    };
    private static final LinkedHashMap<String, String> playerButtons = new LinkedHashMap<String, String>() {
        {
            this.put("临时会话", "textures/ui/Feedback");
            this.put("邮寄物品", "textures/ui/trade_icon");
            this.put("加入黑名单", "textures/ui/blindness_effect");
            this.put("删除好友", "textures/ui/trash_default");
            this.put("传送好友", "textures/ui/FriendsDiversity");
            this.put("返回", "textures/ui/refresh_light");
        }
    };
    private static final LinkedHashMap<String, String> buttonsApply = new LinkedHashMap<String, String>() {
        {
            this.put("临时会话", "textures/ui/Feedback");
            this.put("添加好友", "textures/ui/FriendsDiversity");
            this.put("加入黑名单", "textures/ui/blindness_effect");
            this.put("返回", "textures/ui/refresh_light");
        }
    };

    public static void sendMenu(Player player) {
        int id = -571867135;
        FormWindowSimple simple = new FormWindowSimple("好友系统----主菜单", "");
        PlayerConfig config = Friend.getPlayerConfig(player.getName());
        for (String s : menuButton.keySet()) {
            if (Friend.getFriend().isGameMode() && ("邮寄物品".equals(s) || "仓库".equals(s))) {
                continue;
            }
            String name;
            String fn = name = s;
            switch (name) {
                case "好友列表": {
                    name = name + "§e[在线§a" + Friend.getFriend().getOnlineFriends(player) + "§e人]";
                    break;
                }
                case "仓库": {
                    int c = config.getPlayerWareHouse().getItemTiles().size();
                    if (c <= 0) break;
                    name = name + "§c[你有 §d" + c + "§c件物品未领取]";
                    break;
                }
                case "申请列表": {
                    name = name + "§6(§c" + config.getAcceptList().size() + "§6)";
                    break;
                }
            }
            simple.addButton(new ElementButton(name, new ElementButtonImageData("path", menuButton.get(fn))));
        }
        simple.setContent(config.getPlayerSettings().getTitle());
        CreateForm.send(player, id, simple);
    }

    public static void sendPlayerList(Player player) {
        int id = -571867134;
        PlayerConfig config = Friend.getPlayerConfig(player.getName());
        FormWindowSimple simple = new FormWindowSimple("好友系统----好友列表", "");
        if (!Friend.players.containsKey(player)) {
            Friend.players.put(player, new FriendsListByPort(0, new LinkedList<>(config.getFriends())));
        }
        FriendsListByPort friendsListByPort = Friend.players.get(player);
        ArrayList<ElementButton> buttons = new ArrayList<>();
        ElementButton me = CreateForm.asButton(player, player.getName());
        if (friendsListByPort.getPort() == 0) {
            buttons.add(me);
        }
        for (String name : friendsListByPort.getFriends()) {
            buttons.add(CreateForm.asButton(player, name));
        }
        int max = (int) Math.ceil((double) config.getFriends().size() / (double) Friend.getMaxListSize());
        if (max == 0) {
            max = 1;
        }
        simple.setContent("你共有 §a " + friendsListByPort.getFriendsAll().size() + " §r位好友    当前在线: §a" + Friend.getFriend().getOnlineFriends(player) + "§r/" + friendsListByPort.getFriendsAll().size() + "  \n第 §a" + (friendsListByPort.getPort() + 1) + " §r/ §e" + max + "§r 页");
        buttons.addAll(CreateForm.getNextOrLastButton(friendsListByPort.getPort() + 1, max));
        buttons.addAll(CreateForm.addBackAndRefresh());
        for (ElementButton elementButton : buttons) {
            simple.addButton(elementButton);
        }
        CreateForm.send(player, id, simple);
    }

    private static ArrayList<ElementButton> getNextOrLastButton(int id, int max) {
        ArrayList<ElementButton> linkedHashMaps = new ArrayList<>();
        ElementButton next = new ElementButton("下一页", new ElementButtonImageData("path", "textures/ui/arrow_dark_right"));
        ElementButton last = new ElementButton("上一页", new ElementButtonImageData("path", "textures/ui/arrow_dark_left_stretch"));
        if (id == max && max == 1) {
            return new ArrayList<>();
        }
        if (id == 1 && id < max) {
            linkedHashMaps.add(next);
        } else if (id == max) {
            linkedHashMaps.add(last);
        } else {
            linkedHashMaps.add(last);
            linkedHashMaps.add(next);
        }
        return linkedHashMaps;
    }

    public static void sendGroup(Player player) {
        FormWindowSimple simple = new FormWindowSimple("好友系统----群组", "");
        PlayerConfig config = Friend.getPlayerConfig(player.getName());
        simple.addButton(new ElementButton("我的群组 (" + config.getGroups().size() + ")", new ElementButtonImageData("path", "textures/ui/multiplayer_glyph_color")));
        simple.addButton(new ElementButton("查找群组", new ElementButtonImageData("path", "textures/ui/magnifyingGlass")));
    }

    public static void sendSeek(Player player) {
        int id = -571867133;
        FormWindowCustom custom = new FormWindowCustom("好友系统----查找");
        custom.addElement(new ElementInput("§c请输入您查找的玩家名称", "玩家名称", null));
        CreateForm.send(player, id, custom);
    }

    public static void sendSeelPlayers(Player player, String target) {
        int id = -571867132;
        FormWindowSimple simple = new FormWindowSimple("好友系统----查询结果", "");
        ArrayList<ElementButton> buttons = new ArrayList<>();
        LinkedList<String> lists = Friend.getFriend().getQuery(player.getName(), target.toLowerCase());
        for (String name : lists) {
            buttons.add(CreateForm.asButton(player, name));
        }
        simple.setContent("§e" + target + " §a的查找结果   §d共发现§a " + lists.size() + " §d个");
        for (ElementButton elementButton : buttons) {
            simple.addButton(elementButton);
        }
        simple.addButton(new ElementButton("返回重新查找", new ElementButtonImageData("path", "textures/ui/refresh_light")));
        CreateForm.send(player, id, simple);
    }

    public static void sendInventory(Player player) {
        int id = -571867131;
        FormWindowSimple simple = new FormWindowSimple("好友系统----仓库", "");
        ArrayList<ElementButton> buttons = new ArrayList<>();
        PlayerConfig config = Friend.getPlayerConfig(player.getName());
        PlayerWareHouse items = config.getPlayerWareHouse();
        Iterator<ItemTile> iterator = items.getItemTiles().iterator();
        while (iterator.hasNext()) {
            ItemTile item = iterator.next();
            if (Friend.getFriend().getLong() - item.getTime() <= 0) {
                iterator.remove();
                continue;
            }
            buttons.add(new ElementButton("§d来自:§9" + item.getTarget() + "§d的包裹§c(" + (Friend.getFriend().getLong() - item.getTime()) + "天后过期)\n§7点击查看详情", CreateForm.getImageData(item.getItem())));
        }
        simple.setContent(buttons.isEmpty() ? "\n\n\n\n\n\n\n          §c空空如也\n\n\n" : "");
        buttons.addAll(CreateForm.addBackAndRefresh());
        for (ElementButton elementButton : buttons) {
            simple.addButton(elementButton);
        }
        CreateForm.send(player, id, simple);
    }

    private static ElementButtonImageData getImageData(Item item) {
        String path = ItemIDSunName.getIDByPath(item.getId(), item.getDamage()) != null ? ItemIDSunName.getIDByPath(item.getId(), item.getDamage()) : "textures/ui/how_to_play_button_default";
        return new ElementButtonImageData("path", path);
    }

    public static void sendModal(Player player, String text) {
        int id = -571867130;
        FormWindowModal modal = new FormWindowModal("好友系统----验证", text, "§a确定", "§c取消");
        CreateForm.send(player, id, modal);
    }

    public static void sendExtractItem(Player player) {
        int id = -571867129;
        FormWindowSimple simple = new FormWindowSimple("好友系统----包裹", "");
        if (!Friend.clickInventory.containsKey(player)) {
            player.sendMessage("请再点击一次");
            return;
        }
        ItemTile itemTile = Friend.clickInventory.get(player);
        StringBuilder builder = new StringBuilder();
        builder.append("发送者: ").append(itemTile.getTarget()).append("\n\n");
        builder.append("物品名称: ").append(ItemIDSunName.getIDByName(itemTile.getItem())).append("\n\n");
        builder.append("物品数量: ").append(itemTile.getItem().getCount()).append("\n\n");
        builder.append("留言:").append(itemTile.getMessage()).append("\n\n");
        int del = Friend.getFriend().getLong() - itemTile.getTime();
        builder.append("到期时间: ").append(del).append(" 天后到期").append("\n\n");
        builder.append("\n");
        simple.addButton(new ElementButton("领取", new ElementButtonImageData("path", "textures/ui/gift_square")));
        simple.addButton(new ElementButton("返回", new ElementButtonImageData("path", "textures/ui/refresh_light")));
        simple.setContent(builder.toString());
        CreateForm.send(player, id, simple);
    }

    public static void sendPlayerListsSetting(Player player, String target) {
        int id = -571867128;
        FormWindowSimple simple = new FormWindowSimple("好友系统----好友设置", "");
        PlayerConfig config = Friend.getPlayerConfig(player.getName());
        LinkedHashMap<String, String> list = buttonsApply;
        if (config.isFriend(target)) {
            list = playerButtons;
        }
        for (String string : list.keySet()) {
            if (Friend.getFriend().isGameMode() && ("邮寄物品".equals(string) || "传送好友".equals(string))) {
                continue;
            }
            String text;
            String s = text = string;
            if ("加入黑名单".equals(text) && config.getBlackList().contains(target)) {
                s = "移出黑名单";
            }
            simple.addButton(CreateForm.getButton(s, list.get(text)));
        }
        CreateForm.send(player, id, simple);
    }

    public static void sendChat(Player player) {
        LinkedHashMap<String, PlayerChatMessage> chatMessageLinkedHashMap;
        int id = -571867127;
        FormWindowCustom custom = new FormWindowCustom("好友系统----聊天");
        String target = Friend.clickPlayer.get(player);
        StringBuilder builder = new StringBuilder();
        if (Friend.playerChat.containsKey(player.getName()) && (chatMessageLinkedHashMap = Friend.playerChat.get(player.getName())).containsKey(target)) {
            PlayerChatMessage message = chatMessageLinkedHashMap.get(target);
            builder.append(message.getChat());
        }
        if ("".contentEquals(builder)) {
            builder.append("此记录仅临时保存\n\n\n\n\n\n\n\n\n");
        } else if (builder.toString().split("\\n").length < 10) {
            for (int i = 11; i > builder.toString().split("\\n").length; --i) {
                builder.append("\n");
            }
        }
        custom.addElement(new ElementLabel(builder.toString()));
        custom.addElement(new ElementInput("", "对话内容", null));
        CreateForm.send(player, id, custom);
    }

    public static void sendChatPlayers(Player player) {
        int id = -571867120;
        FormWindowSimple simple = new FormWindowSimple("好友系统----临时会话", "");
        ArrayList<ElementButton> buttons = new ArrayList<>();
        if (Friend.playerChat.containsKey(player.getName())) {
            LinkedHashMap<String, PlayerChatMessage> chatMessageLinkedHashMap = Friend.playerChat.get(player.getName());
            for (String target : chatMessageLinkedHashMap.keySet()) {
                buttons.add(CreateForm.asButton(player, target));
            }
        }
        if (buttons.isEmpty()) {
            simple.setContent("没有任何临时会话");
        } else {
            simple.setContent("");
        }
        buttons.addAll(CreateForm.addBackAndRefresh());
        for (ElementButton elementButton : buttons) {
            simple.addButton(elementButton);
        }
        CreateForm.send(player, id, simple);
    }

    public static void sendApplyPlayers(Player player) {
        int id = -571867119;
        FormWindowSimple simple = new FormWindowSimple("好友系统----申请列表", "");
        PlayerConfig config = Friend.getPlayerConfig(player.getName());
        ArrayList<ElementButton> buttons = new ArrayList<>();
        for (String target : config.getAcceptList()) {
            buttons.add(CreateForm.asButton(player, target));
        }
        if (buttons.isEmpty()) {
            simple.setContent("§c没有任何好友申请");
        } else {
            simple.setContent("");
        }
        buttons.addAll(CreateForm.addBackAndRefresh());
        for (ElementButton elementButton : buttons) {
            simple.addButton(elementButton);
        }
        CreateForm.send(player, id, simple);
    }

    public static void sendPlayerInventorys(Player player) {
        int id = -571867118;
        FormWindowSimple simple = new FormWindowSimple("好友系统----邮寄物品", "");
        for (Item item : Friend.getFriend().getItemsByInventory(player)) {
            simple.addButton(new ElementButton(ItemIDSunName.getIDByName(item) + " * " + item.getCount(), new ElementButtonImageData("path", "textures/ui/loom_pattern_item_empty")));
        }
        Friend.clickItems.put(player, Friend.getFriend().getItemsByInventory(player));
        if (simple.getButtons().isEmpty()) {
            simple.setContent(" §c你没有任何物品可以邮寄");
        } else {
            simple.setContent("§d你有§a" + simple.getButtons().size() + "§d件物品可以邮寄");
        }
        for (ElementButton elementButton : CreateForm.addBackAndRefresh()) {
            simple.addButton(elementButton);
        }
        CreateForm.send(player, id, simple);
    }

    public static void sendPlayerInventorysChat(Player player, ItemTile itemTile) {
        int id = -571867117;
        FormWindowCustom custom = new FormWindowCustom("好友系统----邮寄物品");
        String builder = "接受者: §a" + Friend.clickPlayer.get(player) + "\n\n物品名称: §e" + ItemIDSunName.getIDByName(itemTile.getItem()) + "\n\n物品数量: §a" + itemTile.getItem().getCount() + "\n\n";
        custom.addElement(new ElementLabel(builder));
        custom.addElement(new ElementInput("§c请输入留言", "例如: 送给你一些" + ItemIDSunName.getIDByName(itemTile.getItem())));
        CreateForm.send(player, id, custom);
    }

    public static void sendSetting(Player player) {
        int id = -571867116;
        FormWindowSimple simple = new FormWindowSimple("好友系统----设置", "");
        ArrayList<ElementButton> buttons = new ArrayList<>();
        for (String name : setting.keySet()) {
            buttons.add(CreateForm.getButton(name, setting.get(name)));
        }
        for (ElementButton button : buttons) {
            simple.addButton(button);
        }
        CreateForm.send(player, id, simple);
    }

    public static void resetText(Player player) {
        int id = -571867115;
        FormWindowCustom custom = new FormWindowCustom("好友系统----修改签名");
        custom.addElement(new ElementInput("今天天气真好", "§c请输入您的签名"));
        CreateForm.send(player, id, custom);
    }

    public static void resetImage(Player player) {
        int id = -571867114;
        FormWindowCustom custom = new FormWindowCustom("好友系统----修改头像");
        if (Friend.getFriend().isChinese()) {
            custom.addElement(new ElementInput("请输入你要使用的物品名称 或 ID", "输入物品的id:特殊值 或者 物品名称"));
        } else {
            custom.addElement(new ElementInput("§c请输入您QQ号 插件会自动获取您的QQ头像", "您的QQ号"));
        }
        CreateForm.send(player, id, custom);
    }

    public static void sendTeleport(Player target, Player player) {
        int id = -571867112;
        FormWindowSimple windowSimple = new FormWindowSimple("好友系统----传送请求", "");
        String s = "§a§l您的好友向你发起传送请求\n§a§l好友名称: §7" + player.getName() + "\n\n§a§l好友所在地图: " + player.getLevel().getFolderName() + "\n\n";
        windowSimple.setContent(s);
        windowSimple.addButton(new ElementButton("接受"));
        windowSimple.addButton(new ElementButton("拒绝"));
        target.showFormWindow(windowSimple, id);
    }

    private static ElementButton getButton(String text, String data) {
        return new ElementButton(text, new ElementButtonImageData("path", data));
    }

    private static ElementButton asButton(Player player, String name) {
        PlayerConfig config = Friend.getPlayerConfig(player.getName());
        PlayerConfig target = Friend.getPlayerConfig(name);
        ElementButton button = new ElementButton("");
        if (Friend.getFriend().isGroup(name)) {
            return CreateForm.asButton(player, Friend.getFriend().getGroupConfig(name).getString("群主"));
        }
        ElementButtonImageData elementButton = new ElementButtonImageData("path", "");
        if (!Friend.getFriend().isChinese()) {
            elementButton.setType("url");
            elementButton.setData("http://q1.qlogo.cn/g?b=qq&nk=" + target.getPlayerSettings().getHand() + "&s=100");
        } else {
            String itemName = target.getPlayerSettings().getHand();
            String path = ItemIDSunName.getNameByPath(itemName);
            if (itemName.split(":").length > 1) {
                path = ItemIDSunName.getIDByPath(itemName);
            }
            if (path == null) {
                path = "textures/ui/Friend2";
            }
            elementButton.setData(path);
        }
        Player player1 = Server.getInstance().getPlayer(name);
        button.setText(name + (config.isFriend(name) ? "§d(好友)" : (player.getName().equals(name) ? "§6(你)" : "§c(陌生人)")) + (player1 != null ? "§a[在线]" : "§7[离线]") + "\n§r" + target.getPlayerSettings().getTitle());
        button.addImage(elementButton);
        return button;
    }

    private static LinkedList<ElementButton> addBackAndRefresh() {
        LinkedList<ElementButton> linkedHashMaps = new LinkedList<>();
        linkedHashMaps.add(new ElementButton("刷新", new ElementButtonImageData("path", "textures/ui/refresh")));
        linkedHashMaps.add(new ElementButton("返回", new ElementButtonImageData("path", "textures/ui/refresh_light")));
        return linkedHashMaps;
    }

    private static void send(Player player, int id, FormWindow window) {
        player.showFormWindow(window, id);
    }
}

