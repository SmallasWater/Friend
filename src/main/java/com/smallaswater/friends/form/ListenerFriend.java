package com.smallaswater.friends.form;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementToggle;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.item.Item;
import com.smallaswater.friends.Friend;
import com.smallaswater.friends.events.*;
import com.smallaswater.friends.players.PlayerConfig;
import com.smallaswater.friends.players.settings.PlayerOtherSetting;
import com.smallaswater.friends.utils.DataTools;
import com.smallaswater.friends.utils.FriendsListByPort;
import com.smallaswater.friends.utils.Message;
import com.smallaswater.friends.utils.PlayerChatMessage;
import com.smallaswater.friends.utils.items.ItemIDSunName;
import com.smallaswater.friends.utils.items.ItemTile;
import me.onebone.economyapi.EconomyAPI;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ListenerFriend implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerConfig config = Friend.getPlayerConfig(player.getName());
        for (PlayerConfig fr : config.getFriendConfigs()) {
            Player friends;
            if (!fr.getSetting().isLoginMessage() || (friends = Server.getInstance().getPlayer(fr.getPlayerName())) == null)
                continue;
            friends.sendMessage("§d[好友]§b您的好友§a" + player.getName() + "§b上线啦");
        }
    }

    @EventHandler
    public void getUI(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        if (!event.wasClosed()) {
            int fromId = event.getFormID();
            block2:
            switch (fromId) {
                case -571867135: {
                    int clickedButtonId = ((FormResponseSimple) event.getResponse()).getClickedButtonId();
                    if (Friend.getFriend().isGameMode() && (clickedButtonId == 4 || clickedButtonId == 5)) {
                        clickedButtonId += 2; //启用小游戏模式时跳过邮寄和仓库
                    }
                    switch (clickedButtonId) {
                        case 0: {
                            Friend.canClick.remove(player);
                            CreateForm.sendPlayerList(player);
                            break block2;
                        }
                        case 1: {
                            CreateForm.sendSeek(player);
                            break block2;
                        }
                        case 2: {
                            CreateForm.sendApplyPlayers(player);
                            break block2;
                        }
                        case 3: {
                            Friend.canClick.put(player, true);
                            CreateForm.sendPlayerList(player);
                            break block2;
                        }
                        case 4: {
                            CreateForm.sendInventory(player);
                            break block2;
                        }
                        case 5: {
                            CreateForm.sendChatPlayers(player);
                            break block2;
                        }
                        case 6: {
                            CreateForm.sendSetting(player);
                            break block2;
                        }
                    }
                    break;
                }
                case -571867134: {
                    PlayerConfig config = Friend.getPlayerConfig(player.getName());
                    FriendsListByPort friends = Friend.players.get(player);
                    int d = ((FormResponseSimple) event.getResponse()).getClickedButtonId();
                    if (d == 0 && friends.getPort() == 0) {
                        Friend.canClick.remove(player);
                        CreateForm.sendSetting(player);
                        return;
                    }
                    if (d >= 0 && d < friends.getFriends().size() + (friends.getPort() == 0 ? 1 : 0)) {
                        String target = friends.getFriends().get(d - (friends.getPort() == 0 ? 1 : 0));
                        PlayerConfig targetConfig = Friend.getPlayerConfig(target);
                        Friend.clickPlayer.put(player, target);
                        if (Friend.canClick.containsKey(player)) {
                            Friend.canClick.remove(player);
                            if (!config.getBlackList().contains(target) && !targetConfig.getBlackList().contains(player.getName())) {
                                CreateForm.sendPlayerInventorys(player);
                            } else {
                                player.sendMessage("§c" + target + "在你的黑名单内或你在他的黑名单内，无法给他发送包裹，如需发送，请解除黑名单关系");
                            }
                        } else {
                            Friend.canClick.remove(player);
                            CreateForm.sendPlayerListsSetting(player, target);
                            return;
                        }
                    }
                    if (d >= friends.getFriends().size() + (friends.getPort() == 0 ? 1 : 0) && d < friends.getFriends().size() + (friends.getPort() == 0 ? 1 : 0) + friends.getButtonSize()) {
                        if (d == friends.getFriends().size() + (friends.getPort() == 0 ? 1 : 0) && friends.getPort() == 0) {
                            friends.setPort(friends.getPort() + 1);
                            CreateForm.sendPlayerList(player);
                            return;
                        }
                        if (d == friends.getFriends().size() + (friends.getPort() == 0 ? 1 : 0) + 1 && friends.getButtonSize() == 1 && friends.getPort() == friends.getMaxPage()) {
                            friends.setPort(friends.getPort() - 1);
                            CreateForm.sendPlayerList(player);
                            return;
                        }
                        if (d == friends.getFriends().size() + (friends.getPort() == 0 ? 1 : 0) + friends.getButtonSize() - 1 && friends.getButtonSize() > 1) {
                            friends.setPort(friends.getPort() + 1);
                            CreateForm.sendPlayerList(player);
                            return;
                        }
                        friends.setPort(friends.getPort() - 1);
                        CreateForm.sendPlayerList(player);
                        return;
                    }
                    if (d == friends.getFriends().size() + (friends.getPort() == 0 ? 1 : 0) + friends.getButtonSize()) {
                        Friend.canClick.remove(player);
                        CreateForm.sendPlayerList(player);
                        return;
                    }
                    Friend.canClick.remove(player);
                    CreateForm.sendMenu(player);
                    return;
                }
                case -571867133: {
                    String text = ((FormResponseCustom) event.getResponse()).getInputResponse(0);
                    Friend.playerInput.put(player, text);
                    CreateForm.sendSeelPlayers(player, text);
                    break;
                }
                case -571867132: {
                    try {
                        String target = Friend.playerInput.get(player);
                        if (target != null && !target.isEmpty()) {
                            LinkedList<String> lists = Friend.getFriend().getQuery(player.getName(), target);
                            if (lists.size() > ((FormResponseSimple) event.getResponse()).getClickedButtonId()) {
                                String name = lists.get(((FormResponseSimple) event.getResponse()).getClickedButtonId());
                                Friend.clickPlayer.put(player, name);
                                CreateForm.sendPlayerListsSetting(player, name);
                            } else {
                                CreateForm.sendSeek(player);
                            }
                        }
                        Friend.playerInput.remove(player);
                    } catch (Exception e) {
                        CreateForm.sendSeek(player);
                    }
                    break;
                }
                case -571867131: {
                    PlayerConfig config = Friend.getPlayerConfig(player.getName());
                    if (config.getPlayerWareHouse().getItemTiles().size() > ((FormResponseSimple) event.getResponse()).getClickedButtonId()) {
                        Friend.clickInventory.put(player, config.getPlayerWareHouse().getItemTiles().get(((FormResponseSimple) event.getResponse()).getClickedButtonId()));
                        CreateForm.sendExtractItem(player);
                        return;
                    }
                    if (((FormResponseSimple) event.getResponse()).getClickedButtonId() == config.getPlayerWareHouse().getItemTiles().size() + 1) {
                        CreateForm.sendMenu(player);
                        break;
                    }
                    CreateForm.sendInventory(player);
                    break;
                }
                case -571867130: {
                    PlayerDelFriendEvent event1;
                    if (Friend.canSend.containsKey(player)) {
                        Friend.canSend.remove(player);
                        if (Friend.clickItem.containsKey(player)) {
                            PlayerConfig playerConfig = Friend.getPlayerConfig(player.getName());
                            PlayerConfig target = Friend.getPlayerConfig(Friend.clickPlayer.get(player));
                            if (((FormResponseModal) event.getResponse()).getClickedButtonId() == 0) {
                                if (player.getGamemode() == 1) {
                                    player.sendMessage("§c创造模式无法邮寄物品");
                                    return;
                                }
                                int coin = Friend.getFriend().getConfig().getInt("物品邮寄手续费");
                                if (EconomyAPI.getInstance().myMoney(player) >= (double) coin) {
                                    CreateForm.sendPlayerInventorysChat(player, new ItemTile(new Date(), player.getName(), Friend.clickItem.get(player)));
                                } else {
                                    Friend.clickItem.remove(player);
                                    player.sendMessage("§c抱歉，您的金钱不足，无法邮寄");
                                }
                            } else {
                                Friend.clickItem.remove(player);
                                if (!playerConfig.getBlackList().contains(target.getPlayerName()) && !target.getBlackList().contains(player.getName())) {
                                    CreateForm.sendPlayerInventorys(player);
                                } else {
                                    player.sendMessage("§c" + Friend.clickPlayer.get(player) + "在你的黑名单内或你在他的黑名单内，无法给他发送包裹，如需发送，请解除黑名单关系");
                                }
                            }
                            return;
                        }
                    }
                    if (Friend.canRemove.containsKey(player)) {
                        Friend.canRemove.remove(player);
                        if (((FormResponseModal) event.getResponse()).getClickedButtonId() == 0) {
                            String t = Friend.clickPlayer.get(player);
                            player.sendMessage("§d你已经解除§a" + t + "§d的好友关系");
                            event1 = new PlayerDelFriendEvent(player, t);
                            Server.getInstance().getPluginManager().callEvent(event1);
                        }
                    }
                    if (!Friend.canBlack.containsKey(player)) break;
                    Friend.canBlack.remove(player);
                    PlayerConfig config = Friend.getPlayerConfig(player.getName());
                    PlayerConfig target = Friend.getPlayerConfig(Friend.clickPlayer.get(player));
                    if (((FormResponseModal) event.getResponse()).getClickedButtonId() != 0) break;
                    if (config.isFriend(target.getPlayerName())) {
                        player.sendMessage("§d你已经解除§a" + target.getPlayerName() + "§d的好友关系 并将他拉黑");
                        config.getBlackList().add(target.getPlayerName());
                        event1 = new PlayerDelFriendEvent(player, target.getPlayerName());
                        Server.getInstance().getPluginManager().callEvent(event1);
                        break;
                    }
                    player.sendMessage("§d你拉黑了" + target.getPlayerName());
                    config.getBlackList().add(target.getPlayerName());
                    break;
                }
                case -571867129: {
                    if (((FormResponseSimple) event.getResponse()).getClickedButtonId() == 0) {
                        PlayerConfig config = Friend.getPlayerConfig(player.getName());
                        if (Friend.getFriend().getConfig().getStringList("无法取货世界").contains(player.getLevel().getFolderName())) {
                            player.sendMessage("§a[包裹]§c当前世界无法领取");
                            return;
                        }
                        ItemTile itemTile = Friend.clickInventory.get(player);
                        Item item = itemTile.getItem();
                        if (player.getInventory().canAddItem(item)) {
                            player.sendMessage("§a[包裹]§a领取成功");
                            player.getInventory().addItem(item);
                        } else {
                            player.sendMessage("§a[包裹]§b领取成功 你的背包满啦，掉出来啦");
                            player.level.dropItem(player.getPosition(), item);
                        }
                        config.getPlayerWareHouse().getItemTiles().remove(itemTile);
                        if (!config.getPlayerWareHouse().getItemTiles().isEmpty()) {
                            CreateForm.sendInventory(player);
                            break;
                        }
                        CreateForm.sendMenu(player);
                        break;
                    }
                    CreateForm.sendInventory(player);
                    break;
                }
                case -571867128: { //好友设置
                    PlayerConfig config = Friend.getPlayerConfig(player.getName());
                    PlayerConfig target = Friend.getPlayerConfig(Friend.clickPlayer.get(player));
                    if (config.isFriend(target.getPlayerName())) {
                        int clickedButtonId = ((FormResponseSimple) event.getResponse()).getClickedButtonId();
                        if (Friend.getFriend().isGameMode()) { // 启用小游戏模式时 跳过邮寄和传送功能
                            if (clickedButtonId >= 1) {
                                clickedButtonId++;
                            }
                            if (clickedButtonId >= 4) {
                                clickedButtonId++;
                            }
                        }
                        switch (clickedButtonId) {
                            case 0: {
                                if (Friend.getFriend().isBlack(config, target)) {
                                    player.sendMessage("§c" + target + "在你的黑名单内或你在他的黑名单内，无法给他发送消息，如需要聊天，请解除黑名单关系");
                                    break;
                                }
                                CreateForm.sendChat(player);
                                break;
                            }
                            case 1: {
                                if (!Friend.getFriend().isBlack(config, target)) {
                                    CreateForm.sendPlayerInventorys(player);
                                    break;
                                }
                                player.sendMessage("§c" + Friend.clickPlayer.get(player) + "在你的黑名单内或你在他的黑名单内，无法给他发送包裹，如需发送，请解除黑名单关系");
                                break;
                            }
                            case 2: {
                                if (Friend.getFriend().isBlack(config, target)) {
                                    player.sendMessage("§e你已解除" + target + "的黑名单关系");
                                    config.getBlackList().remove(target.getPlayerName());
                                    break;
                                }
                                Friend.canBlack.put(player, true);
                                CreateForm.sendModal(player, "§c你确定要拉黑" + Friend.clickPlayer.get(player) + "吗?\n拉黑后对方将不能添加你为好友 不能发起临时会话，不能邮寄物品");
                                break;
                            }
                            case 3: {
                                CreateForm.sendModal(player, "§c你确定要解除§a" + Friend.clickPlayer.get(player) + "§c的好友关系吗");
                                Friend.canRemove.put(player, true);
                                break;
                            }
                            case 4: {
                                if (!Friend.getFriend().isBlack(config, target)) {
                                    Player tar = Server.getInstance().getPlayer(target.getPlayerName());
                                    if (tar != null) {
                                        Friend.getFriend().tp.put(tar, player);
                                        player.sendMessage("§a已向" + Friend.clickPlayer.get(player) + "发送请求..等待回应");
                                        CreateForm.sendTeleport(tar, player);
                                        break;
                                    }
                                    player.sendMessage("§c" + Friend.clickPlayer.get(player) + "不在线");
                                    break;
                                }
                                player.sendMessage("§c" + Friend.clickPlayer.get(player) + "在你的黑名单内或你在他的黑名单内，无法向他发起传送请求");
                                break;
                            }
                            case 5: {
                                CreateForm.sendMenu(player);
                            }
                        }
                        break;
                    }
                    switch (((FormResponseSimple) event.getResponse()).getClickedButtonId()) {
                        case 0: {
                            if (Friend.getFriend().isBlack(config, target)) {
                                player.sendMessage("§c" + target + "在你的黑名单内或你在他的黑名单内，无法给他发送消息，如需要聊天，请解除黑名单关系");
                                break block2;
                            }
                            CreateForm.sendChat(player);
                            break block2;
                        }
                        case 1: {
                            if (Friend.getFriend().isBlack(config, target)) {
                                player.sendMessage("§c" + target + "在你的黑名单内或你在他的黑名单内，无法加他为好友");
                                return;
                            }
                            if (config.getAcceptList().contains(target.getPlayerName())) {
                                PlayerAcceptFriendEvent event1 = new PlayerAcceptFriendEvent(player, target.getPlayerName());
                                Server.getInstance().getPluginManager().callEvent(event1);
                                break block2;
                            }
                            if (!target.getAcceptList().contains(player.getName())) {
                                PlayerApplyFriendEvent event1 = new PlayerApplyFriendEvent(player, target.getPlayerName());
                                Server.getInstance().getPluginManager().callEvent(event1);
                                break block2;
                            }
                            player.sendMessage("§e[好友]§a请耐心等待回复");
                            break block2;
                        }
                        case 2: {
                            if (Friend.getFriend().isBlack(config, target)) {
                                player.sendMessage("§e你已解除" + target + "的黑名单关系");
                                config.getBlackList().remove(target.getPlayerName());
                                break block2;
                            }
                            Friend.canBlack.put(player, true);
                            CreateForm.sendModal(player, "§4你确定要拉黑" + Friend.clickPlayer.get(player) + "吗?\n拉黑后对方将不能添加你为好友 不能发起临时会话，不能邮寄物品");
                            break block2;
                        }
                        case 3: {
                            CreateForm.sendMenu(player);
                            break block2;
                        }
                    }
                    break;
                }
                case -571867127: {
                    FormResponseCustom uiData = (FormResponseCustom) event.getResponse();
                    String chatmessage = uiData.getInputResponse(1);
                    if (chatmessage != null && !chatmessage.isEmpty()) {
                        PlayerFriendChatEvent event1 = new PlayerFriendChatEvent(player, Friend.clickPlayer.get(player), chatmessage);
                        Server.getInstance().getPluginManager().callEvent(event1);
                        CreateForm.sendChat(player);
                        break;
                    }
                    CreateForm.sendChat(player);
                    break;
                }
                case -571867120: {
                    int data = ((FormResponseSimple) event.getResponse()).getClickedButtonId();
                    if (Friend.playerChat.containsKey(player.getName())) {
                        LinkedHashMap<String, PlayerChatMessage> chatMessageLinkedHashMap = Friend.playerChat.get(player.getName());
                        if (chatMessageLinkedHashMap.size() > data) {
                            int i = 0;
                            for (String name : chatMessageLinkedHashMap.keySet()) {
                                if (i == data) {
                                    Friend.clickPlayer.put(player, name);
                                    CreateForm.sendChat(player);
                                    break block2;
                                }
                                ++i;
                            }
                            break;
                        }
                        if (data == chatMessageLinkedHashMap.size() + 1) {
                            CreateForm.sendMenu(player);
                            break;
                        }
                        CreateForm.sendChatPlayers(player);
                        break;
                    }
                    if (data == 0) {
                        CreateForm.sendChatPlayers(player);
                        break;
                    }
                    CreateForm.sendMenu(player);
                    break;
                }
                case -571867119: {
                    PlayerConfig config = Friend.getPlayerConfig(player.getName());
                    ArrayList<String> a = config.getAcceptList();
                    if (a.size() > ((FormResponseSimple) event.getResponse()).getClickedButtonId()) {
                        String string = a.get(((FormResponseSimple) event.getResponse()).getClickedButtonId());
                        CreateForm.sendPlayerListsSetting(player, string);
                        Friend.clickPlayer.put(player, string);
                        break;
                    }
                    if (((FormResponseSimple) event.getResponse()).getClickedButtonId() == a.size() + 1) {
                        CreateForm.sendMenu(player);
                        break;
                    }
                    CreateForm.sendApplyPlayers(player);
                    break;
                }
                case -571867118: {
                    int clickData = ((FormResponseSimple) event.getResponse()).getClickedButtonId();
                    if (Friend.clickItems.get(player).size() > clickData) {
                        Item items = Friend.clickItems.get(player).get(clickData);
                        Friend.clickItem.put(player, items);
                        Friend.canSend.put(player, true);
                        int coin = Friend.getFriend().getConfig().getInt("物品邮寄手续费");
                        CreateForm.sendModal(player, "§c你确定要邮寄§a" + ItemIDSunName.getIDByName(items) + "§c吗?\n§d(本次邮寄需要花费 §e" + coin + "§d游戏币)");
                        break;
                    }
                    if (clickData == Friend.clickItems.get(player).size() + 1) {
                        CreateForm.sendMenu(player);
                        break;
                    }
                    CreateForm.sendPlayerInventorys(player);
                    break;
                }
                case -571867117: {
                    FormResponseCustom uiData = (FormResponseCustom) event.getResponse();
                    String message = uiData.getInputResponse(1);
                    if (message != null) {
                        PlayerConfig target;
                        int coin = Friend.getFriend().getConfig().getInt("物品邮寄手续费");
                        Item item = Friend.clickItem.get(player);
                        Friend.clickItem.remove(player);
                        ItemTile itemTile = new ItemTile(new Date(), message, player.getName(), item);
                        try {
                            target = Friend.getPlayerConfig(Friend.clickPlayer.get(player));
                            PlayerGetItemEvent event1 = new PlayerGetItemEvent(player, target.getPlayerName(), itemTile);
                            Server.getInstance().getPluginManager().callEvent(event1);
                            if (event1.isCancelled()) {
                                player.sendMessage("§e[包裹]§c包裹未送出");
                                return;
                            }
                            player.sendMessage("§e[包裹]§a包裹已成功送出");
                        } catch (Exception e) {
                            player.sendMessage("§e[包裹]§c包裹异常，请重新邮寄");
                            return;
                        }
                        if (!DataTools.canExit(item, player)) {
                            player.sendMessage("§e[包裹]§c包裹未送出");
                            return;
                        }
                        EconomyAPI.getInstance().reduceMoney(player, coin);
                        player.getInventory().removeItem(item);
                        PlayerConfig c = Friend.getPlayerConfig(target.getPlayerName());
                        c.getPlayerWareHouse().getItemTiles().add(itemTile);
                        Player t = Server.getInstance().getPlayer(target.getPlayerName());
                        if (t != null) {
                            t.sendMessage("§e[包裹]§a你获得了来自§e" + player.getName() + "§a的一个包裹\n§d留言: §r" + itemTile.getMessage());
                        }
                        c.save();
                        break;
                    }
                    Friend.clickItem.remove(player);
                    CreateForm.sendPlayerInventorys(player);
                    break;
                }
                case -571867116: {
                    PlayerConfig config = Friend.getPlayerConfig(player.getName());
                    switch (((FormResponseSimple) event.getResponse()).getClickedButtonId()) {
                        case 0: {
                            CreateForm.resetImage(player);
                            break block2;
                        }
                        case 1: {
                            CreateForm.resetText(player);
                            break block2;
                        }
                        case 2: {
                            StringBuilder builder1 = new StringBuilder();
                            builder1.append("§7=======黑名单========\n");
                            int i = 0;
                            StringBuilder stringBuilder = new StringBuilder();
                            for (String name : config.getBlackList()) {
                                stringBuilder.append(name).append("\n");
                                ++i;
                            }
                            builder1.append("共 ").append(i).append("位").append("\n");
                            if (stringBuilder.toString().isEmpty()) {
                                stringBuilder.append("无\n");
                            }
                            builder1.append(stringBuilder);
                            builder1.append("§7=======黑名单========\n");
                            player.sendMessage(builder1.toString());
                            break block2;
                        }
                        case 3: {
                            FormWindowCustom custom = new FormWindowCustom("好友系统----其它设置");
                            ElementDropdown dropdown = new ElementDropdown("好友申请设置");
                            ArrayList<String> list = new ArrayList<String>() {
                                {
                                    this.add("手动接受");
                                    this.add("同意所有申请");
                                    this.add("拒绝所有申请");
                                }
                            };
                            String pls = config.getSetting().getAboutAccept().getName();
                            dropdown.addOption(list.get(0), list.get(0).equals(pls));
                            dropdown.addOption(list.get(1), list.get(1).equals(pls));
                            dropdown.addOption(list.get(2), list.get(2).equals(pls));
                            ElementToggle stepSlider = new ElementToggle("私聊信息提醒");
                            stepSlider.setDefaultValue(config.getSetting().isTellMessage());
                            ElementToggle stepSlider1 = new ElementToggle("好友申请提示");
                            stepSlider1.setDefaultValue(config.getSetting().isFriendMessage());
                            ElementToggle stepSlider2 = new ElementToggle("好友上线提醒");
                            stepSlider2.setDefaultValue(config.getSetting().isLoginMessage());
                            custom.addElement(dropdown);
                            custom.addElement(stepSlider);
                            custom.addElement(stepSlider1);
                            custom.addElement(stepSlider2);
                            player.showFormWindow(custom, -571867113);
                            break block2;
                        }
                        case 4: {
                            CreateForm.sendMenu(player);
                            break block2;
                        }
                    }
                    break;
                }
                case -571867115: {
                    FormResponseCustom uiData = (FormResponseCustom) event.getResponse();
                    if (uiData == null) {
                        CreateForm.sendSetting(player);
                        return;
                    }
                    if (uiData.getInputResponse(0).isEmpty()) {
                        CreateForm.resetText(player);
                        return;
                    }
                    String t = uiData.getInputResponse(0);
                    String chat = t != null && !t.isEmpty() ? t : "这个人很懒，什么也没留下";
                    PlayerConfig config = Friend.getPlayerConfig(player.getName());
                    config.getPlayerSettings().setTitle(chat);
                    player.sendMessage("§b[签名]§6您的签名已更改为: §r" + chat);
                    break;
                }
                case -571867114: {
                    FormResponseCustom uiData = (FormResponseCustom) event.getResponse();
                    if (uiData == null) {
                        CreateForm.sendSetting(player);
                        return;
                    }
                    if (uiData.getInputResponse(0).isEmpty()) {
                        CreateForm.resetText(player);
                        return;
                    }
                    String t = uiData.getInputResponse(0);
                    if (t == null || t.isEmpty()) {
                        player.sendMessage("§c错误格式");
                        return;
                    }
                    String chat = t;
                    PlayerConfig config = Friend.getPlayerConfig(player.getName());
                    if (Friend.getFriend().isChinese()) {
                        if (chat.split(":").length > 1) {
                            chat = ItemIDSunName.getIDByName(chat);
                        }
                        player.sendMessage("§b[签名]§6您的头像已改为物品§r" + chat);
                    } else {
                        player.sendMessage("§b[签名]§6您的头像已改为QQ用户§r" + chat + "的头像");
                    }
                    config.getPlayerSettings().setHand(chat);
                    break;
                }
                case -571867113: {
                    FormResponseCustom uiData = (FormResponseCustom) event.getResponse();
                    PlayerConfig config = Friend.getPlayerConfig(player.getName());
                    if (!uiData.getDropdownResponse(0).getElementContent().equals(config.getSetting().getAboutAccept().getName())) {
                        player.sendMessage("§b[设置]§e好友申请设置已修改为" + uiData.getDropdownResponse(0).getElementContent());
                        config.getSetting().setAboutAccept(PlayerOtherSetting.getOtherSettingAboutAcceptByName(uiData.getDropdownResponse(0).getElementContent()));
                    }
                    if (uiData.getToggleResponse(1) != config.getSetting().isTellMessage()) {
                        config.getSetting().setTellMessage(uiData.getToggleResponse(1));
                        player.sendMessage("§b[设置]§e私聊信息提示已设置为" + (uiData.getToggleResponse(1) ? "§a true" : "§c false"));
                    }
                    if (uiData.getToggleResponse(2) != config.getSetting().isFriendMessage()) {
                        player.sendMessage("§b[设置]§e好友申请信息提示信息已设置为" + (uiData.getToggleResponse(2) ? "§a true" : "§c false"));
                        config.getSetting().setFriendMessage(uiData.getToggleResponse(2));
                    }
                    if (uiData.getToggleResponse(3) == config.getSetting().isLoginMessage()) break;
                    player.sendMessage("§b[设置]§e好友上线提醒已设置为" + (uiData.getToggleResponse(3) ? "§a true" : "§c false"));
                    config.getSetting().setLoginMessage(uiData.getToggleResponse(3));
                    break;
                }
                case -571867112: {
                    Player tar = Friend.getFriend().tp.get(player);
                    Friend.getFriend().tp.remove(player);
                    if (tar.isOnline()) {
                        if (((FormResponseSimple) event.getResponse()).getClickedButtonId() == 0) {
                            tar.sendMessage("§a[好友]传送成功!...");
                            player.sendMessage("§a[好友]" + tar.getName() + "来到了你的身边");
                            tar.teleport(player);
                            break;
                        }
                        tar.sendMessage("§a[好友]§c" + player.getName() + "拒绝了你的传送请求");
                        break;
                    }
                    player.sendMessage("§a[好友]" + tar.getName() + "离线了");
                    break;
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void Accept(PlayerAcceptFriendEvent event) {
        Player player = event.getPlayer();
        PlayerConfig config = Friend.getPlayerConfig(player.getName());
        PlayerConfig target = Friend.getPlayerConfig(event.getTarget());
        if (event.isCancelled()) {
            player.sendMessage("§a[好友]§c抱歉，你不能添加好友..");
            return;
        }
        config.getAcceptList().remove(target.getPlayerName());
        Player t = Server.getInstance().getPlayer(target.getPlayerName());
        if (t != null) {
            t.sendMessage("§e[好友] §e成功添加" + player.getName() + "为好友");
        }
        DataTools.writePlayer(config, target, false);
        player.sendMessage("§a[好友]成功添加" + target.getPlayerName() + "好友");
    }

    @EventHandler
    public void onApply(PlayerApplyFriendEvent event) {
        Player player = event.getPlayer();
        PlayerConfig config = Friend.getPlayerConfig(player.getName());
        PlayerConfig target = Friend.getPlayerConfig(event.getTarget());
        player.sendMessage("§e[好友] 好友请求已发送");
        if (PlayerOtherSetting.OtherSettingAboutAccept.AllAccept == target.getSetting().getAboutAccept()) {
            Player target1 = Server.getInstance().getPlayer(target.getPlayerName());
            if (target1 != null) {
                PlayerAcceptFriendEvent event1 = new PlayerAcceptFriendEvent(target1, player.getName());
                Server.getInstance().getPluginManager().callEvent(event1);
            } else {
                player.sendMessage("§a[好友]成功添加" + target.getPlayerName() + "好友");
                DataTools.writePlayer(config, target, false);
            }
            return;
        }
        if (PlayerOtherSetting.OtherSettingAboutAccept.RefuseAccept == target.getSetting().getAboutAccept()) {
            player.sendMessage("§a[好友]§c对方已拒绝好友申请");
            return;
        }
        Player t = Server.getInstance().getPlayer(target.getPlayerName());
        if (t != null && target.getSetting().isFriendMessage()) {
            t.sendMessage("§e[好友] §a" + player.getName() + "§e请求添加你为好友....");
        }
        target.getAcceptList().add(player.getName());
    }

    @EventHandler
    public void onChat(PlayerFriendChatEvent event) {
        LinkedHashMap<String, PlayerChatMessage> chatMessageLinkedHashMap;
        Player player = event.getPlayer();
        PlayerConfig target = Friend.getPlayerConfig(event.getTarget());
        if (Friend.playerChat.containsKey(player.getName())) {
            PlayerChatMessage chatMessage;
            chatMessageLinkedHashMap = Friend.playerChat.get(player.getName());
            Message message = new Message(player.getName(), event.getMessage(), new Date());
            if (chatMessageLinkedHashMap.containsKey(target.getPlayerName())) {
                chatMessage = chatMessageLinkedHashMap.get(target.getPlayerName());
                chatMessage.addChat(message);
                chatMessageLinkedHashMap.put(target.getPlayerName(), chatMessage);
            } else {
                chatMessage = new PlayerChatMessage(player.getName(), new Message(player.getName(), event.getMessage(), new Date()));
                chatMessageLinkedHashMap.put(target.getPlayerName(), chatMessage);
            }
            Friend.playerChat.put(player.getName(), chatMessageLinkedHashMap);
        } else {
            chatMessageLinkedHashMap = new LinkedHashMap<>();
            chatMessageLinkedHashMap.put(target.getPlayerName(), new PlayerChatMessage(player.getName(), new Message(player.getName(), event.getMessage(), new Date())));
            Friend.playerChat.put(player.getName(), chatMessageLinkedHashMap);
        }
        Player t = Server.getInstance().getPlayer(target.getPlayerName());
        if (t != null && target.getSetting().isTellMessage()) {
            t.sendMessage("§e[信息]§a" + player.getName() + "§a给您发了一条消息 请打开临时会话查看");
        }
        if (Friend.playerChat.containsKey(target.getPlayerName())) {
            LinkedHashMap<String, PlayerChatMessage> messageLinkedHashMap = Friend.playerChat.get(target.getPlayerName());
            Message message = new Message(player.getName(), event.getMessage(), new Date());
            if (messageLinkedHashMap.containsKey(player.getName())) {
                PlayerChatMessage chatMessage = messageLinkedHashMap.get(player.getName());
                chatMessage.addChat(message);
                messageLinkedHashMap.put(player.getName(), chatMessage);
            } else {
                PlayerChatMessage chatMessage = new PlayerChatMessage(player.getName(), message);
                messageLinkedHashMap.put(target.getPlayerName(), chatMessage);
            }
            Friend.playerChat.put(target.getPlayerName(), messageLinkedHashMap);
        } else {
            LinkedHashMap<String, PlayerChatMessage> targetMessage = new LinkedHashMap<>();
            targetMessage.put(player.getName(), new PlayerChatMessage(target.getPlayerName(), new Message(player.getName(), event.getMessage(), new Date())));
            Friend.playerChat.put(target.getPlayerName(), targetMessage);
        }
    }

    @EventHandler
    public void onDel(PlayerDelFriendEvent event) {
        Player player = event.getPlayer();
        Player player1 = Server.getInstance().getPlayer(event.getTarget());
        if (player1 != null) {
            player1.sendMessage("§d[好友]§c您的好友" + player.getName() + "解除了好友关系");
        }
        DataTools.writePlayer(Friend.getPlayerConfig(player.getName()), Friend.getPlayerConfig(event.getTarget()), true);
    }

    @EventHandler
    public void onGet(PlayerGetItemEvent event) {
        Player player = event.getPlayer();
        PlayerConfig config = Friend.getPlayerConfig(event.getTarget());
        if (config.getPlayerWareHouse().getItemTiles().size() == Friend.getFriend().getConfig().getInt("仓库最大存储上限", 20)) {
            event.setCancelled();
            Player target = Server.getInstance().getPlayer(event.getTarget());
            if (target != null) {
                target.sendMessage("§e[包裹]§c你无法获得§e" + player.getName() + "§a的一个包裹 [背包已满!]");
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerConfig config = Friend.getPlayerConfig(player.getName());
        Friend.clickPlayer.remove(player);
        Friend.players.remove(player);
        Friend.clickInventory.remove(player);
        Friend.playerChat.remove(player.getName());
        Friend.playerInput.remove(player);
        config.save();
    }
}

