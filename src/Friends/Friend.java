package Friends;


import Friends.Form.createForm;
import Friends.utils.itemTile;
import Friends.utils.playerChatMessage;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Friend extends PluginBase{

    public static LinkedHashMap<String,LinkedHashMap<String,playerChatMessage>> playerChat = new LinkedHashMap<>();

    public static LinkedHashMap<Player,LinkedList<String>> players = new LinkedHashMap<>();//存储状态

    static LinkedHashMap<Player,String> playerInput = new LinkedHashMap<>(); //查找输入

    public static LinkedHashMap<Player,LinkedList<itemTile>> playerInventory = new LinkedHashMap<>(); //仓库

    public static LinkedHashMap<Player,Integer> clickInventory = new LinkedHashMap<>();

    public static LinkedHashMap<Player,String> clickPlayer = new LinkedHashMap<>(); //点击玩家

    public static LinkedHashMap<Player,LinkedList<Item>> clickItems = new LinkedHashMap<>();//邮件

    static LinkedHashMap<Player,Item> clickItem = new LinkedHashMap<>();//选中物品

    static LinkedHashMap<Player,Boolean> canClick = new LinkedHashMap<>();//邮件点击邮件

    static LinkedHashMap<Player,Boolean> canRemove = new LinkedHashMap<>();//点击删除玩家

    static LinkedHashMap<Player,Boolean> canBlack = new LinkedHashMap<>();//拉黑

    static LinkedHashMap<Player,Boolean> canSend = new LinkedHashMap<>();//

    public LinkedHashMap<Player,Player> tp = new LinkedHashMap<>();
    private static Friend friend;

    public static boolean loadVIP = false;

    @Override
    public void onEnable() {
        friend = this;
        File config = new File(this.getDataFolder()+"/config.yml");
        if(!config.exists()){
            this.saveDefaultConfig();
            this.reloadConfig();
        }
        Plugin plugin = this.getServer().getPluginManager().getPlugin("SVIP");
        if(plugin != null){
            loadVIP = true;
        }
        File file = new File(this.getDataFolder()+"/Players");
        if(!file.exists())
            if(!file.mkdirs()){
                this.getLogger().warning("Players文件夹创建失败");
            }
        File group = new File(this.getDataFolder()+"/Groups");
        if(!group.exists())
            if(!group.mkdirs()){
                this.getLogger().warning("Group文件夹创建失败");
            }
        this.getServer().getPluginManager().registerEvents(new ListenerFriend(),this);
    }


    public static Friend getFriend() {
        return friend;
    }

    private File getPlayerFile(String playerName){
        return new File(this.getDataFolder()+"/Players/"+playerName+".yml");
    }
    private File getGroupFile(String playerName){
        return new File(this.getDataFolder()+"/Groups/"+playerName+".yml");
    }

    public Config getPlayerConfig(String playerName){
        File file = getPlayerFile(playerName);
        if(!file.exists()){
            this.saveResource("playerDefaultFile.yml","/Players/"+playerName+".yml",false);
        }
        return new Config(this.getPlayerFile(playerName),Config.YAML);
    }

    public Config getGroupConfig(String name){
        File file = getGroupFile(name);
        if(!file.exists()){
            this.saveResource("groupDefaultFile.yml","/Groups/"+name+".yml",false);
        }
        return new Config(this.getGroupFile(name),Config.YAML);
    }


    public LinkedList<Player> getFriendOnline(String playerName){
        LinkedList<String> list = getFriends(playerName);
        LinkedList<Player> players = new LinkedList<>();
        for(String OStr:list){
                Player player = this.getServer().getPlayer(OStr);
                if(player != null)
                    players.add(player);
        }
        return players;
    }


    boolean isAccept(String player, String target){
        LinkedList<String> players = getAccepts(player);
        return players.contains(target);
    }

    public LinkedList<String> getAccepts(String player){
        return getDefaultList(player,"申请列表");
    }


    public boolean isBlack(String player,String target){
        LinkedList<String> players = getBlacks(player);
        return players.contains(target);
    }


    LinkedList<String> getBlacks(String player){
        return getDefaultList(player,"黑名单");
    }

    public boolean isFriend(String player,String target){
        LinkedList<String> players = getFriends(player);
        return players.contains(target);
    }


    void writeAccept(String player, String target){
        this.writeDefaultAlone(player,target,"申请列表",false);
    }

    void writeBlack(String player,String target){
        this.writeDefaultAlone(player,target,"黑名单",false);

    }

    //添加好友
    void writeFriend(String player,String target){
        this.writeDefault(player,target, false);
    }

    //删除玩家
    void delFriend(String player,String target){
        this.writeDefault(player,target, true);
    }

    //删除申请列表
    void delAccept(String player,String target){
        this.writeDefaultAlone(player,target,"申请列表",true);
    }

    void delBlack(String player,String target){
        this.writeDefaultAlone(player,target,"黑名单",true);
    }

    public int getLong(){
        return getConfig().getInt("仓库物品存放时长(天)",7);
    }




    public LinkedList<String> getFriends(String playerName){
        return getDefaultList(playerName,"好友");
    }




    public void delItem(String playerName,String target,itemTile tile){
        Config config = getPlayerConfig(playerName);
        LinkedHashMap<String,LinkedList<LinkedHashMap<String,Object>>> defaultItems = tryToGetConfig(playerName);
        if(defaultItems == null) return;
        LinkedList<LinkedHashMap<String,Object>> lists = defaultItems.get(target);
        for(LinkedHashMap<String,Object> map:lists){
            Date date = tile.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(map.get("id").equals(tile.getItem().getId()+":"+tile.getItem().getDamage()) &&
                    map.get("count").equals(tile.getItem().getCount()) &&
                    map.get("message").equals(tile.getMessage()) &&
                    map.get("time").equals(sdf.format(date))){
                lists.remove(map);
                if(lists.size() > 0){
                    defaultItems.put(target,lists);
                }else{
                    defaultItems.remove(target);
                }
                config.set("仓库",defaultItems);
                config.save();
                return;

            }
        }
    }


    void delItem(String playerName, String target, int i){
        LinkedHashMap<String,LinkedList<LinkedHashMap<String,Object>>> defaultItems = tryToGetConfig(playerName);
        if(defaultItems == null) return;
        LinkedList<LinkedHashMap<String,Object>> lists = defaultItems.get(target);
        if(lists.size() > i){
            LinkedHashMap<String,Object> maps = lists.get(i);
            this.delItem(playerName,target,getFriendItem(target,maps));
        }
    }

    public itemTile getItemByData(String playerName,int data){
        LinkedHashMap<String,LinkedList<LinkedHashMap<String,Object>>> defaultItems = tryToGetConfig(playerName);
        if(defaultItems == null) return null;
        int i = 0;
        for(String target:defaultItems.keySet()){
            LinkedList<LinkedHashMap<String,Object>> defaults = defaultItems.get(target);
            for(LinkedHashMap<String,Object> hashMap:defaults){
                if(i == data){
                    return getFriendItem(target,hashMap);
                }
                i++;
            }
        }
        return null;
    }

    void addItems(String playerName,String target,itemTile items){
        Config config = getPlayerConfig(playerName);
        LinkedHashMap<String,LinkedList<LinkedHashMap<String,Object>>> defaultItems = tryToGetConfig(playerName);
        LinkedList<LinkedHashMap<String,Object>> lists;
        if(defaultItems != null && !defaultItems.isEmpty()){
            lists = defaultItems.get(target);
        }else{
            lists = new LinkedList<>();
            defaultItems = new LinkedHashMap<>();
        }
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        map.put("id",items.getItem().getId()+":"+items.getItem().getDamage());
        map.put("count",items.getItem().getCount());
        map.put("tag",items.getItem().hasCompoundTag()?bytesToHexString(items.getItem().getCompoundTag()):"not");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("time",sdf.format(items.getDate()));
        map.put("message",items.getMessage());
        lists.add(map);
        defaultItems.put(target,lists);
        config.set("仓库",defaultItems);
        config.save();
    }

    public LinkedList<Item> getItemsByInventory(Player player){
        LinkedList<Item> items = new LinkedList<>();
        for(int i = 0;i < player.getInventory().getSize();i++){
            Item item = player.getInventory().getItem(i);
            if(item.getId() != 0){
                items.add(item);
            }
        }
        return items;
    }




    // 解码 Config.yml数据
    private LinkedHashMap<String,LinkedList<LinkedHashMap<String,Object>>> tryToGetConfig(String playerName){
        Config config = getPlayerConfig(playerName);
        LinkedHashMap<String,LinkedList<LinkedHashMap<String,Object>>> maps = new LinkedHashMap<>();
        LinkedList<LinkedHashMap<String,Object>> lists = new LinkedList<>();
        Object map =  config.get("仓库");
        if(map != null){
            if(map instanceof Map){
                for(Object targetName:((Map) map).keySet()){
                    if(targetName instanceof String){
                        List itemList = (List) ((Map) map).get(targetName);
                        for(Object itemType:itemList){
                            if (itemType instanceof Map){
                                LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                                linkedHashMap.put("id", ((Map) itemType).get("id"));
                                linkedHashMap.put("count", ((Map) itemType).get("count"));
                                linkedHashMap.put("tag", ((Map) itemType).get("tag"));
                                linkedHashMap.put("time", ((Map) itemType).get("time"));
                                linkedHashMap.put("message", ((Map) itemType).get("message"));
                                lists.add(linkedHashMap);
                            }
                        }
                        maps.put((String) targetName, lists);
                    }
                }
            }

        }
        return maps;

    }

    //获得玩家的仓库列表
    public LinkedHashMap<String,LinkedList<itemTile>> getItems(String playerName){
        LinkedHashMap<String,LinkedList<itemTile>> gettings = new LinkedHashMap<>();
        LinkedHashMap<String,LinkedList<LinkedHashMap<String,Object>>> maps = tryToGetConfig(playerName);
        if(maps == null) return null;
        for(String target:maps.keySet()){
            LinkedList<LinkedHashMap<String,Object>> linkedHashMaps = maps.get(target);
            LinkedList<itemTile> tiles = new LinkedList<>();
            for(LinkedHashMap<String,Object> map:linkedHashMaps){
                itemTile i = getFriendItem(target,map);
                tiles.add(i);

            }
            gettings.put(target,tiles);
        }
        return gettings;
    }

    public int getCounts(String playerName){
        int i = 0;
        LinkedHashMap<String,LinkedList<itemTile>> maps = getItems(playerName);
        if(maps == null) return 0;
        for(String target:maps.keySet()){
            i += maps.get(target).size();
        }
        return i;
    }

    public String getDefaultMessage(String playerName,String type){
        return getPlayerConfig(playerName).getString(type);
    }

    private itemTile getFriendItem(String target,LinkedHashMap<String,Object> map){
        Item item = Item.fromString((String) map.get("id"));
        item.setCount((int)map.get("count"));
        String tags = (String)map.get("tag");
        if(!tags.equals("not")){
            CompoundTag tag = Item.parseCompoundTag(hexStringToBytes(tags));
            item.setNamedTag(tag);
        }
        Date date = getDate((String)map.get("time"));
        return new itemTile(date,(String)map.get("message"),target,item);
    }


    public LinkedList<String> getQuery(String player,String target){
        LinkedList<String> Get = new LinkedList<>();
        List<String> friendNames = getPlayerNames();
        friendNames.addAll(getGroupNames());
        StringBuilder builder = new StringBuilder(target);
        char[] searchChars = target.toCharArray();
        int max = 0;
        for(int i = 0;i<target.length();i++){
            int index = builder.indexOf(searchChars[i]+"");
            if(max==0){
                max = index;
            }else{
                index = builder.indexOf(searchChars[i]+"",max);
                max = index;
            }
            builder.insert(index,"[\\s\\S]*");
        }
        //之前的
        for(String name:friendNames){
            if(name.matches("("+builder.append("[\\s\\S]*").toString()+")")
                    || getDefaultMessage(name,"QQ头像").matches("("+builder.append("[\\s\\S]*").toString()+")")
                    || getDefaultMessage(name,"个性签名").matches("("+builder.append("[\\s\\S]*").toString()+")")){
                if(!name.equals(player))
                    Get.add(name);
            }
        }
        return Get;
    }


    public boolean isGroup(String name){
        return getGroupNames().contains(name);
    }


    boolean getCanSetting(String playerName, String type){
        LinkedHashMap map = (LinkedHashMap) getPlayerConfig(playerName).get("设置");
        if(type.equals("好友申请设置")){
            String s = (String) map.get(type);
            return s.equals("同意所有申请");
        }
        return (boolean) map.get(type);
    }

    String getPlayerSettings(String playerName){
        Map map = (Map) getPlayerConfig(playerName).get("设置");
        return (String) map.get("好友申请设置");
    }


    void setSetting(String playerName,String setting,Object value){
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        Map map1 = (Map) getPlayerConfig(playerName).get("设置");
        for(Object m:map1.keySet()){
            map.put((String)m,map1.get(m));
        }
        map.put(setting,value);
        Config config = getPlayerConfig(playerName);
        config.set("设置",map);
        config.save();

    }



    //获取文件下目录
    private LinkedList<String> getPlayerNames(){
        return getDefaultNames("Players");
    }

    private LinkedList<String> getDefaultNames(String name){
        File file = new File(this.getDataFolder()+"/"+name);
        LinkedList<String> names_ = new LinkedList<>();
        File[] files = file.listFiles();
        if(files != null){
            for(File file1:files){
                if(file1.isFile()){
                    String names = file1.getName().substring(0,file1.getName().lastIndexOf("."));
                    names_.add(names);
                }
            }
        }

        return names_;
    }


    //获取文件下目录
    private LinkedList<String> getGroupNames(){
        return getDefaultNames("Groups");
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("好友")){
            if(sender instanceof Player){
                createForm.sendMenu((Player) sender);
            }
        }
        return true;
    }

    //----------封装-----//

    private Date getDate(String format){
        SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return lsdStrFormat.parse(format);
        }catch (ParseException e){
            return null;
        }
    }

    private LinkedList<String> getDefaultList(String playerName,String type){
        List list = getPlayerConfig(playerName).getList(type);
        LinkedList<String> players = new LinkedList<>();
        for(Object OStr:list){
            if(OStr instanceof String){
                players.add((String) OStr);
            }
        }
        return players;
    }
    private void writeDefault(String player, String target, boolean del){
        Config player1 = getPlayerConfig(player);
        Config player2 = getPlayerConfig(target);
        LinkedList<String> player1s = getFriends(player);
        LinkedList<String> player2s = getFriends(target);
        if(del){
            player1s.remove(target);
            player2s.remove(player);
        }else{
            player1s.add(target);
            player2s.add(player);
        }
        player1.set("好友",player1s);
        player2.set("好友",player2s);
        player1.save();
        player2.save();
    }

    private void writeDefaultAlone(String player,String target,String type,boolean del){
        Config player1 = getPlayerConfig(player);

        LinkedList<String> player1s = getDefaultList(player,type);
        if(del){
            player1s.remove(target);
        }else{
            player1s.add(target);
        }
        player1.set(type,player1s);
        player1.save();
    }

    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    private static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


}
