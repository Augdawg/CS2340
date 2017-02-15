package com.example.kirin.cs2340.Model;

import java.util.ArrayList;

/**
 * Created by Kirin on 2/14/2017.
 */

public class Users {
    private ArrayList<User> users;
    private static Users instance;

    private Users() {
        users = new ArrayList<User>();
        users.add(new User("User", "Pass"));
    }

    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }
}