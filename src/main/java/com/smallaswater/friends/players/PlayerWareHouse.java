package com.smallaswater.friends.players;

import cn.nukkit.item.Item;
import com.smallaswater.friends.utils.DataTools;
import com.smallaswater.friends.utils.items.ItemTile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PlayerWareHouse {
    private final ArrayList<ItemTile> itemTiles;

    public PlayerWareHouse(ArrayList<ItemTile> itemTiles) {
        this.itemTiles = itemTiles;
    }

    PlayerWareHouse() {
        this.itemTiles = new ArrayList<>();
    }

    public ArrayList<ItemTile> getItemTiles() {
        return this.itemTiles;
    }

    public static PlayerWareHouse getInstance(List config) {
        ArrayList<ItemTile> arrayList = new ArrayList<>();
        if (config == null) {
            return new PlayerWareHouse(new ArrayList<>());
        }
        for (Object map : config) {
            if (!(map instanceof Map)) continue;
            String target = ((Map) map).get("target").toString();
            Item i = Item.get(Integer.parseInt(((Map) map).get("id").toString().split(":")[0]), Integer.parseInt(((Map) map).get("id").toString().split(":")[1]));
            i.setCount(Integer.parseInt(((Map) map).get("count").toString()));
            byte[] bytes = DataTools.hexStringToBytes(((Map) map).get("tag").toString());
            if (bytes != null) {
                i.setCompoundTag(bytes);
            }
            String message = ((Map) map).get("message").toString();
            Date time = DataTools.getDate(((Map) map).get("time").toString());
            arrayList.add(new ItemTile(time, message, target, i));
        }
        return new PlayerWareHouse(arrayList);
    }

    public ArrayList<Object> toSaveConfig() {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (ItemTile tile : this.itemTiles) {
            arrayList.add(tile.toSaveConfig());
        }
        return arrayList;
    }
}

