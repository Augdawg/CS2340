package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 3/7/2017.
 */

public class ForgotPassUser {
    private String username;
    private static ForgotPassUser instance;

    private ForgotPassUser() {

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
}
