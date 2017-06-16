package com.example.sviostali.e_dnevnik.ListViews.UsersListAdapter;

/**
 * Created by Edo on 16.6.2017..
 */

public class UserList {
    public String user;
    public boolean isMarked;

    UserList(String footballClub){
        this.user = footballClub;
        this.isMarked = false;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
