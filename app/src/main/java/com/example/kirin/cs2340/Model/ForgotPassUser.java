package com.example.kirin.cs2340.Model;

import java.util.HashMap;

/**
 * Created by Kirin on 3/7/2017.
 * Represents user who has forgot password
 */

public class ForgotPassUser {
    private String username;
    private static ForgotPassUser instance;
    private final HashMap<String, String> passwordCodes;

    /**
     * Constructor for a forgot password user
     */
    private ForgotPassUser() {
        passwordCodes = new HashMap<>();
    }

    /**
     * Gets singleton instance of forgot password user
     * @return singleton instance of forgot password user
     */
    public static ForgotPassUser getInstance() {
        if (instance == null) {
            instance = new ForgotPassUser();
        }
        return instance;
    }

    /**
     * Gets username of forgot password user
     * @return username of forgot password user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets username of forgot password user
     * @param val value to set username to
     */
    public void setUsername(String val) {
        username = val;
    }

    /**
     * Adds a password code to the HashMap to help user recover password
     * @param user username of user who forgot password
     * @param code code to recover password
     */
    public void addPasswordCode(String user, String code) {
        passwordCodes.put(user, code);
    }

    /**
     * Gets all password codes currently active
     * @return all password codes currently active
     */
    public HashMap<String, String> getPasswordCodes() {
        return this.passwordCodes;
    }
}
