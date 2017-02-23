package com.example.kirin.cs2340.Model;

/**
 * Keeps track of current user to prevent constant activity passing
 * Created by Kirin on 2/19/2017.
 */

public class CurrentUser {
    private static CurrentUser instance;
    private GeneralUser currentUser;

    /**
     * CurrentUser private Singleton Constructor
     * @param user
     */
    private CurrentUser(GeneralUser user) {
        this.currentUser = user;
    }

    /**
     * Gets active instance of CurrentUser
     * @return CurrentUser that is logged in
     */
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
