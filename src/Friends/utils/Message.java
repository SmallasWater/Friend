package Friends.utils;


import java.util.Date;

public class Message {

    private String message;
    private Date time;
    private String name;

    public Message(String name,String message,Date time){
        this.message = message;
        this.time = time;
        this.name = name;
    }

    String getName() {
        return name;
    }

    String getMessage() {
        return message;
    }

    String getTime() {
        long temp = new Date().getTime() - time.getTime();
        long hours = temp / 1000 / 3600;                //相差小时数
        long temp2 = temp % (1000 * 3600);
        long mins = temp2 / 1000 / 60;
        long day = hours > 0?hours / 24:0;
        long weak = day > 0?day / 7:0;
        if(weak > 0){
            return "§c("+day+"周前)§r";
        }else if(day > 0){
            return "§d("+day+"天前)§r";
        }else if(hours > 0){
            return "§e("+hours+"小时前)§r";
        }else if(mins > 3){
            return "§2("+mins+"分钟前)§r";
        }else{
            return "§7(刚刚)§r";
        }
    }
}
