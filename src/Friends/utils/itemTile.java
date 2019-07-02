package Friends.utils;


import cn.nukkit.item.Item;

import java.util.Calendar;
import java.util.Date;

public class itemTile {

    private Date time;
    private String message;
    private String target;
    private Item item;

    /** @deprecated */
    @Deprecated
    public itemTile(Date time,String message){
        this.time = time;
        this.message = message;
    }
    public itemTile(Date time,String message,String target,Item item){
        this.time = time;
        this.message = message;
        this.target = target;
        this.item = item;
    }
    public itemTile(Date time,String target,Item item){
        this.time = time;
        this.message = "§a给你一些"+ItemIDSunName.getIDByName(item);
        this.target = target;
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public Date getDate(){
        return time;
    }

    public String getTarget() {
        return target;
    }

    //获取过去的时间
    public int getTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        long time1 = cal.getTimeInMillis();
        cal.setTime(new Date());
        long time2 = cal.getTimeInMillis();
        long between_days = (time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
