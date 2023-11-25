package com.smallaswater.friends.players.settings;

import cn.nukkit.utils.Config;

import java.util.LinkedHashMap;

public class PlayerSettings {
    private String title = "这个人很懒 什么也没留下";
    private String hand = "3098143605";

    public PlayerSettings() {
    }

    private PlayerSettings(String title, String hand) {
        this.title = title;
        this.hand = hand;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String getTitle() {
        return this.title;
    }

    public String getHand() {
        return this.hand;
    }

    public static PlayerSettings getInstance(Config config) {
        String title = config.getString("个性签名");
        String hand = config.getString("QQ头像");
        return new PlayerSettings(title, hand);
    }

    public LinkedHashMap<String, Object> toSaveConfig() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("QQ头像", this.hand);
        map.put("个性签名", this.title);
        return map;
    }
}

