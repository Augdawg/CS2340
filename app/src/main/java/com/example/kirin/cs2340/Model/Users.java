package com.example.kirin.cs2340.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import static java.lang.Math.toIntExact;

/**
 * Created by Kirin on 2/14/2017.
 */

public class Users {
    private HashMap<String, User> users;

    private static Users instance;

    private Users() {
        users = new HashMap<>();
    }

    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public HashMap<String,User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }
}