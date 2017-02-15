package com.example.kirin.cs2340.Model;

import java.util.ArrayList;

/**
 * Created by Kirin on 2/14/2017.
 */

public class Users {
    private ArrayList<User> users;
    private Users instance;

    private Users() {
        users = new ArrayList<User>();
    }

    public Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }
}