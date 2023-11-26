package com.smallaswater.friends.utils;

import com.smallaswater.friends.Friend;

import java.util.LinkedList;

public class FriendsListByPort {
    private int port;
    private LinkedList<String> friendsAll;

    public FriendsListByPort(int port, LinkedList<String> friends) {
        this.port = port;
        this.friendsAll = friends;
    }

    public int getPort() {
        return this.port;
    }

    public LinkedList<String> getFriendsAll() {
        return this.friendsAll;
    }

    public LinkedList<String> getFriends() {
        LinkedList<String> f = new LinkedList<>();
        int i = 0;
        for (int j = 0; i < this.friendsAll.size() && j != Friend.getMaxListSize(); ++i, ++j) {
            if (this.friendsAll.size() <= i + this.port * Friend.getMaxListSize()) continue;
            f.add(this.friendsAll.get(i + this.port * Friend.getMaxListSize()));
        }
        return f;
    }

    public void setFriendsAll(LinkedList<String> friendsAll) {
        this.friendsAll = friendsAll;
    }

    public int getMaxPage() {
        int max = (int) Math.ceil((double) this.friendsAll.size() / (double) Friend.getMaxListSize());
        if (max == 0) {
            max = 1;
        }
        return max;
    }

    public int getButtonSize() {
        int max = this.getMaxPage();
        if (this.port + 1 == max && max == 1) {
            return 0;
        }
        if (this.port + 1 == 1 && this.port < max) {
            return 1;
        }
        if (this.port + 1 == max) {
            return 1;
        }
        return 2;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

