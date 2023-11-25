package com.smallaswater.friends.players.settings;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerOtherSetting {
    private OtherSettingAboutAccept aboutAccept = OtherSettingAboutAccept.HandAccept;
    private boolean loginMessage = true;
    private boolean tellMessage = true;
    private boolean friendMessage = true;

    public OtherSettingAboutAccept getAboutAccept() {
        return this.aboutAccept;
    }

    public PlayerOtherSetting() {
    }

    private PlayerOtherSetting(OtherSettingAboutAccept aboutAccept, boolean loginMessage, boolean tellMessage, boolean friendMessage) {
        this.aboutAccept = aboutAccept;
        this.loginMessage = loginMessage;
        this.tellMessage = tellMessage;
        this.friendMessage = friendMessage;
    }

    public boolean isFriendMessage() {
        return this.friendMessage;
    }

    public boolean isLoginMessage() {
        return this.loginMessage;
    }

    public boolean isTellMessage() {
        return this.tellMessage;
    }

    public void setAboutAccept(OtherSettingAboutAccept aboutAccept) {
        this.aboutAccept = aboutAccept;
    }

    public void setFriendMessage(boolean friendMessage) {
        this.friendMessage = friendMessage;
    }

    public void setLoginMessage(boolean loginMessage) {
        this.loginMessage = loginMessage;
    }

    public void setTellMessage(boolean tellMessage) {
        this.tellMessage = tellMessage;
    }

    public static PlayerOtherSetting getInstance(Map map) {
        if (map == null) {
            return new PlayerOtherSetting();
        }
        OtherSettingAboutAccept aboutAccept = PlayerOtherSetting.getOtherSettingAboutAcceptByName(map.get("好友申请设置").toString());
        boolean loginMessage = Boolean.parseBoolean(map.get("好友上线提醒").toString());
        boolean tellMessage = Boolean.parseBoolean(map.get("自动弹消息框").toString());
        boolean friendMessage = Boolean.parseBoolean(map.get("自动弹好友申请").toString());
        return new PlayerOtherSetting(aboutAccept, loginMessage, tellMessage, friendMessage);
    }

    public LinkedHashMap<String, Object> toSaveConfig() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("好友申请设置", this.aboutAccept.getName());
        map.put("自动弹消息框", this.tellMessage);
        map.put("自动弹好友申请", this.friendMessage);
        map.put("好友上线提醒", this.loginMessage);
        return map;
    }

    public static OtherSettingAboutAccept getOtherSettingAboutAcceptByName(String name) {
        for (OtherSettingAboutAccept aboutAccept : OtherSettingAboutAccept.values()) {
            if (!aboutAccept.name.equalsIgnoreCase(name)) continue;
            return aboutAccept;
        }
        return OtherSettingAboutAccept.HandAccept;
    }

    public enum OtherSettingAboutAccept {
        HandAccept("手动接受"),
        AllAccept("同意所有申请"),
        RefuseAccept("拒绝所有申请");

        private String name;

        OtherSettingAboutAccept(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}

