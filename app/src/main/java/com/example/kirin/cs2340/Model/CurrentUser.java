package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/19/2017.
 */

public class CurrentUser {
    private static CurrentUser instance;
    private GeneralUser currentUser;

    private CurrentUser(GeneralUser user) {
        this.currentUser = user;
    }

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser(null);
        }
        return instance;
    }

    public GeneralUser getCurrentUser() {
        return this.currentUser;
    }
    public void setCurrentUser(GeneralUser val) {
        this.currentUser = val;
    }

}
