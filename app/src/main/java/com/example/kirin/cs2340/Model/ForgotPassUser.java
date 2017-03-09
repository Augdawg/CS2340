package com.example.kirin.cs2340.Model;

import java.util.HashMap;

/**
 * Created by Kirin on 3/7/2017.
 */

public class ForgotPassUser {
    private String username;
    private static ForgotPassUser instance;
    private HashMap<String, String> passwordCodes;

    private ForgotPassUser() {
        passwordCodes = new HashMap<>();
    }

    public static ForgotPassUser getInstance() {
        if (instance == null) {
            instance = new ForgotPassUser();
        }
        return instance;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String val) {
        username = val;
    }

    public void addPasswordCode(String user, String code) {
        passwordCodes.put(user, code);
    }

    public HashMap<String, String> getPasswordCodes() {
        return this.passwordCodes;
    }
}
