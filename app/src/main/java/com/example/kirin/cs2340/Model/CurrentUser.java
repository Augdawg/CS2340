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
     */
    private CurrentUser() {

    }

    /**
     * Gets active instance of CurrentUser
     * @return CurrentUser that is logged in
     */
    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    /**
     * Gets current user of application
     * @return current user of application
     */
    public GeneralUser getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Sets current user of application
     * @param val user to set current user to
     */
    public void setCurrentUser(GeneralUser val) {
        this.currentUser = val;
    }

}
