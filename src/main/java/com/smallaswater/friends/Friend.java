package com.smallaswater.friends;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import com.smallaswater.friends.form.CreateForm;
import com.smallaswater.friends.form.ListenerFriend;
import com.smallaswater.friends.players.PlayerConfig;
import com.smallaswater.friends.utils.FriendsListByPort;
import com.smallaswater.friends.utils.PlayerChatMessage;
import com.smallaswater.friends.utils.items.ItemTile;
import com.smallaswater.friends.variables.FriendVariable;
import tip.utils.Api;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Friend extends PluginBase {
    public static LinkedHashMap<String, LinkedHashMap<String, PlayerChatMessage>> playerChat = new LinkedHashMap<>();
    public static LinkedHashMap<Player, FriendsListByPort> players = new LinkedHashMap<>();
    public static LinkedHashMap<Player, String> playerInput = new LinkedHashMap<>();
    private static final ArrayList<PlayerConfig> playerConfigs = new ArrayList<>();
    public static LinkedHashMap<Player, ItemTile> clickInventory = new LinkedHashMap<>();
    public static LinkedHashMap<Player, String> clickPlayer = new LinkedHashMap<>();
    public static LinkedHashMap<Player, LinkedList<Item>> clickItems = new LinkedHashMap<>();
    public static LinkedHashMap<Player, Item> clickItem = new LinkedHashMap<>();
    public static LinkedHashMap<Player, Boolean> canClick = new LinkedHashMap<>();
    public static LinkedHashMap<Player, Boolean> canRemove = new LinkedHashMap<>();
    public static LinkedHashMap<Player, Boolean> canBlack = new LinkedHashMap<>();
    public static LinkedHashMap<Player, Boolean> canSend = new LinkedHashMap<>();
    public LinkedHashMap<Player, Player> tp = new LinkedHashMap<>();
    private static Friend friend;
    public static boolean loadVIP;

    public void onEnable() {
        File group;
        File file;
        Plugin plugin;
        friend = this;
        try {
            Class.forName("tip.utils.variables.BaseVariable");
            Api.registerVariables("friend", FriendVariable.class);
        } catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
        File config = new File(this.getDataFolder() + "/config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            this.reloadConfig();
        }
        if ((plugin = this.getServer().getPluginManager().getPlugin("SVIP")) != null) {
            loadVIP = true;
        }
        if (!(file = new File(this.getDataFolder() + "/Players")).exists() && !file.mkdirs()) {
            this.getLogger().warning("Players文件夹创建失败");
        }
        if (!(group = new File(this.getDataFolder() + "/Groups")).exists() && !group.mkdirs()) {
            this.getLogger().warning("Group文件夹创建失败");
        }
        this.getServer().getPluginManager().registerEvents(new ListenerFriend(), this);
    }

    public static Friend getFriend() {
        return friend;
    }

    public File getPlayerFile(String playerName) {
        return new File(this.getDataFolder() + "/Players/" + playerName + ".yml");
    }

    private File getGroupFile(String playerName) {
        return new File(this.getDataFolder() + "/Groups/" + playerName + ".yml");
    }

    public Config getGroupConfig(String name) {
        File file = this.getGroupFile(name);
        if (!file.exists()) {
            this.saveResource("groupDefaultFile.yml", "/Groups/" + name + ".yml", false);
        }
        return new Config(this.getGroupFile(name), 2);
    }

    public boolean isChinese() {
        return this.getConfig().getBoolean("启动中国版模式", false);
    }

    public boolean isGameMode() {
        return this.getConfig().getBoolean("小游戏服务器模式", false);
    }

    public int getLong() {
        return this.getConfig().getInt("仓库物品存放时长(天)", 7);
    }

    public LinkedList<Item> getItemsByInventory(Player player) {
        LinkedList<Item> items = new LinkedList<>();
        for (int i = 0; i < player.getInventory().getSize(); ++i) {
            Item item = player.getInventory().getItem(i);
            if (item.getId() == 0) continue;
            items.add(item);
        }
        return items;
    }

    public boolean isBlack(PlayerConfig playerConfig, PlayerConfig target) {
        return playerConfig.getBlackList().contains(target.getPlayerName()) || target.getBlackList().contains(playerConfig.getPlayerName());
    }

    public static PlayerConfig getPlayerConfig(String playerName) {
        PlayerConfig config = new PlayerConfig(playerName);
        if (!playerConfigs.contains(config)) {
            if (!Friend.getFriend().getPlayerFile(playerName).exists()) {
                Friend.getFriend().saveResource("playerDefaultFile.yml", "/Players/" + playerName + ".yml", false);
            }
            playerConfigs.add(PlayerConfig.getInstance(playerName, new Config(Friend.getFriend().getPlayerFile(playerName), 2)));
        }
        return playerConfigs.get(playerConfigs.indexOf(config));
    }

    public LinkedList<String> getQuery(String player, String target) {
        LinkedList<String> get = new LinkedList<>();
        PlayerConfig config = Friend.getPlayerConfig(player);
        if (player != null && target != null) {
            LinkedList<String> friendNames = this.getPlayerNames();
            friendNames.addAll(this.getGroupNames());
            friendNames.addAll(config.getFriends());
            StringBuilder builder = new StringBuilder(target);
            char[] searchChars = target.toCharArray();
            int max = 0;
            for (int i = 0; i < target.length(); ++i) {
                int index = builder.indexOf(searchChars[i] + "");
                max = max == 0 ? index : (index = builder.indexOf(searchChars[i] + "", max));
                builder.insert(index, "[\\s\\S]*");
            }
            int size = 0;
            for (String name : friendNames) {
                PlayerConfig config1 = Friend.getPlayerConfig(name);
                if (get.contains(name)
                        || name.equals(player)
                        || size >= Friend.getMaxListSize()
                        || (!name.toLowerCase().matches("(" + builder.append("[\\s\\S]*") + ")")
                        && !config1.getPlayerSettings().getTitle().toLowerCase().matches("(" + builder.append("[\\s\\S]*") + ")")
                        && !config1.getPlayerSettings().getHand().toLowerCase().matches("(" + builder.append("[\\s\\S]*") + ")"))) {
                    continue;
                }
                get.add(name);
                ++size;
            }
        }
        return get;
    }

    public boolean isGroup(String name) {
        return this.getGroupNames().contains(name);
    }

    public static int getMaxListSize() {
        return Friend.getFriend().getConfig().getInt("好友最大显示数量", 20);
    }

    private LinkedList<String> getPlayerNames() {
        LinkedList<String> friends = new LinkedList<>();
        for (Player player : Server.getInstance().getOnlinePlayers().values()) {
            friends.add(player.getName());
        }
        return friends;
    }

    private LinkedList<String> getDefaultNames(String name) {
        File file = new File(this.getDataFolder() + "/" + name);
        LinkedList<String> names = new LinkedList<>();
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (!file1.isFile()) continue;
                String fileName = file1.getName().substring(0, file1.getName().lastIndexOf("."));
                names.add(fileName);
            }
        }
        return names;
    }

    private LinkedList<String> getGroupNames() {
        return this.getDefaultNames("Groups");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("好友".equals(command.getName()) && sender instanceof Player) {
            CreateForm.sendMenu((Player) sender);
        }
        return true;
    }

    public int getOnlineFriends(Player config) {
        return this.getOnlineFriends(Friend.getPlayerConfig(config.getName()));
    }

    public int getOnlineFriends(PlayerConfig config) {
        int i = 0;
        for (String f : config.getFriends()) {
            Player player = Server.getInstance().getPlayer(f);
            if (player == null) continue;
            ++i;
        }
        return i;
    }

    static {
        loadVIP = false;
    }
}

