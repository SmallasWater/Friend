package com.smallaswater.friends.utils;

import java.util.Date;

public class Message {
    private String message;
    private Date time;
    private String name;

    public Message(String name, String message, Date time) {
        this.message = message;
        this.time = time;
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    String getMessage() {
        return this.message;
    }

    String getTime() {
        long weak;
        long temp = System.currentTimeMillis() - this.time.getTime();
        long hours = temp / 1000L / 3600L;
        long temp2 = temp % 3600000L;
        long mins = temp2 / 1000L / 60L;
        long day = hours > 0L ? hours / 24L : 0L;
        long l = weak = day > 0L ? day / 7L : 0L;
        if (weak > 0L) {
            return "§c(" + day + "周前)§r";
        }
        if (day > 0L) {
            return "§d(" + day + "天前)§r";
        }
        if (hours > 0L) {
            return "§e(" + hours + "小时前)§r";
        }
        if (mins > 3L) {
            return "§2(" + mins + "分钟前)§r";
        }
        return "§7(刚刚)§r";
    }
}

