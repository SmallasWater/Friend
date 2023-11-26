package com.smallaswater.friends.players;

import cn.nukkit.utils.Config;
import com.smallaswater.friends.Friend;
import com.smallaswater.friends.players.settings.PlayerOtherSetting;
import com.smallaswater.friends.players.settings.PlayerSettings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerConfig {
    private String playerName;
    private ArrayList<String> friends = new ArrayList<>();
    private PlayerWareHouse playerWareHouse = new PlayerWareHouse();
    private ArrayList<String> blackList = new ArrayList<>();
    private ArrayList<String> acceptList = new ArrayList<>();
    private PlayerSettings playerSettings = new PlayerSettings();
    private ArrayList<String> groups = new ArrayList<>();
    private PlayerOtherSetting setting = new PlayerOtherSetting();

    private PlayerConfig(String playerName, ArrayList<String> friends, PlayerWareHouse playerWareHouse, ArrayList<String> blackList, PlayerSettings settings, ArrayList<String> acceptList, PlayerOtherSetting otherSetting) {
        this.playerName = playerName;
        this.friends = friends;
        this.playerWareHouse = playerWareHouse;
        this.blackList = blackList;
        this.playerSettings = settings;
        this.acceptList = acceptList;
        this.setting = otherSetting;
    }

    public ArrayList<String> getAcceptList() {
        return this.acceptList;
    }

    public PlayerConfig(String playerName) {
        this.playerName = playerName;
    }

    public boolean isFriend(String target) {
        return this.friends.contains(target);
    }

    public PlayerConfig getFriendConfig(String target) {
        if (this.friends.contains(target)) {
            return Friend.getPlayerConfig(target);
        }
        return null;
    }

    public ArrayList<PlayerConfig> getFriendConfigs() {
        ArrayList<PlayerConfig> configs = new ArrayList<>();
        for (String target : this.getFriends()) {
            configs.add(Friend.getPlayerConfig(target));
        }
        return configs;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public ArrayList<String> getBlackList() {
        return this.blackList;
    }

    public ArrayList<String> getFriends() {
        return this.friends;
    }

    public PlayerSettings getPlayerSettings() {
        return this.playerSettings;
    }

    public PlayerOtherSetting getSetting() {
        return this.setting;
    }

    public PlayerWareHouse getPlayerWareHouse() {
        return this.playerWareHouse;
    }

    public static PlayerConfig getInstance(String playerName, Config config) {
        ArrayList<String> friends = new ArrayList<>(config.getStringList("好友"));
        PlayerSettings settings = PlayerSettings.getInstance(config);
        ArrayList<String> groups = new ArrayList<>(config.getStringList("群组"));
        PlayerWareHouse wareHouse = PlayerWareHouse.getInstance(config.getList("仓库"));
        PlayerOtherSetting otherSetting = PlayerOtherSetting.getInstance((Map) config.get("设置"));
        ArrayList<String> blackList = new ArrayList<>(config.getStringList("黑名单"));
        ArrayList<String> acceptList = new ArrayList<>(config.getStringList("申请列表"));
        PlayerConfig config1 = new PlayerConfig(playerName, friends, wareHouse, blackList, settings, acceptList, otherSetting);
        config1.setGroups(groups);
        return config1;
    }

    public ArrayList<String> getGroups() {
        return this.groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }

    public void save() {
        LinkedHashMap<String, Object> configMap = new LinkedHashMap<>();
        configMap.put("好友", this.friends);
        configMap.putAll(this.playerSettings.toSaveConfig());
        configMap.put("申请列表", this.acceptList);
        configMap.put("黑名单", this.blackList);
        configMap.put("仓库", this.playerWareHouse.toSaveConfig());
        configMap.put("设置", this.setting.toSaveConfig());
        configMap.put("群组", this.groups);
        Config config = new Config(Friend.getFriend().getPlayerFile(this.playerName), 2);
        config.setAll(configMap);
        config.save();
    }

    public boolean equals(Object obj) {
        if (obj instanceof PlayerConfig) {
            return ((PlayerConfig) obj).playerName.equalsIgnoreCase(this.playerName);
        }
        return false;
    }
}

