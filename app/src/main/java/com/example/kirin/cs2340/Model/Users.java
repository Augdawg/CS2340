package com.example.kirin.cs2340.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import static java.lang.Math.toIntExact;

/**
 * Created by Kirin on 2/14/2017.
 */

public class Users {
    private HashMap<String, GeneralUser> users;

    private static Users instance;

    /**
     * Users constructor
     */
    private Users() {
        users = new HashMap<>();
        GeneralUser u = new User("user", "pass", "email", "home", "title");
        users.put(u.getUsername(), u);
    }

    /**
     * Returns active instance of Users in system
     * @return Active Instance of Users in system
     */
    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public HashMap<String,GeneralUser> getUsers() {
        return this.users;
    }

    /**
     * Adds users to system
     * @param user
     */
    public void addUser(GeneralUser user) {
        users.put(user.getUsername(), user);
    }
}