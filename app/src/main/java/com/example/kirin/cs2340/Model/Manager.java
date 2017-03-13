package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/22/2017.
 * Represents Manager User object
 */

public class Manager extends Worker {
    /**
     * Manager constructor
     * @param username username of manager
     * @param password password of manager
     * @param email email address of manager
     * @param home home address of manager
     * @param title title of manager
     */
    public Manager(String name, String username, String password, String email, String home, String title) {
        super(name, username, password, email, home, title);
    }

    /**
     * Default constructor of manager, creates empty fields
     */
    public Manager() {
        super("", "", "", "", "", "");
    }


    /**
     * String representation of Account Type
     * @return String representation of Account Type
     */
    public String getAccountType() {
        return "MANAGER";
    }
}
