package com.smallaswater.friends.utils;

import cn.nukkit.Player;
import cn.nukkit.item.Item;
import com.smallaswater.friends.players.PlayerConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.SplittableRandom;

public class DataTools {
    private static final SplittableRandom RANDOM = new SplittableRandom(System.currentTimeMillis());

    public static int rand(int min, int max) {
        return min == max ? max : RANDOM.nextInt(max + 1 - min) + min;
    }

    public static Date getDate(String format) {
        SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return lsdStrFormat.parse(format);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getDateToString(Date date) {
        SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd");
        return lsdStrFormat.format(date);
    }

    public static void writePlayer(PlayerConfig player, PlayerConfig target, boolean del) {
        ArrayList<String> player1s = player.getFriends();
        ArrayList<String> player2s = target.getFriends();
        if (del) {
            player1s.remove(target.getPlayerName());
            player2s.remove(player.getPlayerName());
        } else {
            if (!player1s.contains(target.getPlayerName())) {
                player1s.add(target.getPlayerName());
            }
            if (!player2s.contains(player.getPlayerName())) {
                player2s.add(player.getPlayerName());
            }
        }
        player.save();
        target.save();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.isEmpty() || "not".equals(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; ++i) {
            int pos = i * 2;
            d[i] = (byte) (DataTools.charToByte(hexChars[pos]) << 4 | DataTools.charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return "not";
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

    public static boolean canExit(Item newItem, Player player) {
        LinkedList<Item> items1 = new LinkedList<>(player.getInventory().getContents().values());
        int c = 0;
        for (Item i : items1) {
            if (!i.equals(newItem, true, true) || i.getCount() != newItem.getCount()) continue;
            c += i.getCount();
        }
        return c >= newItem.getCount();
    }
}

