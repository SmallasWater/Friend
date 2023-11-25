package com.smallaswater.friends.utils.items;

import cn.nukkit.item.Item;
import com.smallaswater.friends.utils.DataTools;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

public class ItemTile {
    private Date time;
    private String message;
    private String target;
    private Item item;

    @Deprecated
    public ItemTile(Date time, String message) {
        this.time = time;
        this.message = message;
    }

    public ItemTile(Date time, String message, String target, Item item) {
        this.time = time;
        this.message = message;
        this.target = target;
        this.item = item;
    }

    public ItemTile(Date time, String target, Item item) {
        this.time = time;
        this.message = "§a给你一些" + ItemIDSunName.getIDByName(item);
        this.target = target;
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

    public Date getDate() {
        return this.time;
    }

    public String getTarget() {
        return this.target;
    }

    public int getTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.time);
        long time1 = cal.getTimeInMillis();
        cal.setTime(new Date());
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ItemTile) {
            return ((ItemTile) obj).item.equalsExact(this.item) && ((ItemTile) obj).message.equalsIgnoreCase(this.message) && ((ItemTile) obj).time.equals(this.time) && ((ItemTile) obj).target.equalsIgnoreCase(this.target);
        }
        return false;
    }

    public LinkedHashMap<String, Object> toSaveConfig() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
        linkedHashMap.put("target", this.target);
        linkedHashMap.put("id", this.item.getId() + ":" + this.item.getDamage());
        linkedHashMap.put("count", this.item.getCount());
        linkedHashMap.put("tag", DataTools.bytesToHexString(this.item.getCompoundTag()));
        linkedHashMap.put("time", DataTools.getDateToString(this.time));
        linkedHashMap.put("message", this.message);
        return linkedHashMap;
    }
}

