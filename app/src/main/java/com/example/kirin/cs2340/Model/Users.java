package com.example.kirin.cs2340.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import static java.lang.Math.toIntExact;

/**
 * Created by Kirin on 2/14/2017.
 * Keeps track of all Users in application
 */

public class Users {
    private HashMap<String, GeneralUser> users;
    private HashMap<String, String> passwordCodes;

    private static Users instance;

    /**
     * Users constructor
     */
    private Users() {
        users = new HashMap<>();
        GeneralUser u = new User("Kirin Bettadapur", "kbettadapur3", "Wafflecone1!", "kirinbetta97@gmail.com", "5400 Saint Lyonn Place", "Student");
        users.put(u.getUsername(), u);
        passwordCodes = new HashMap<>();
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
     * @param user user to be added
     */
    public void addUser(GeneralUser user) {
        users.put(user.getUsername(), user);
    }

    public void addPasswordCode(String username, String code) {
        passwordCodes.put(username, code);
    }

    public HashMap<String, String> getPasswordCodes() {
        return this.passwordCodes;
    }
}