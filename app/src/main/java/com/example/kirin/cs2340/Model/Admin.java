package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/22/2017.
 */

/**
 * Represents Admin User object
 */
public class Admin extends GeneralUser {
    private String name;
    private String username;
    private String password;
    private String email;
    private String home;
    private String title;

    /**
     * Admin constructor
     * @param username
     * @param password
     * @param email
     * @param home
     * @param title
     */
    public Admin(String name, String username, String password, String email, String home, String title) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.home = home;
        this.title = title;
    }

    /**
     * Deletes an account
     * @param user
     */
    public void deleteAccount(User user) {
        //implement
    }

    /**
     * Bans user from submitting reports
     * @param user
     */
    public void banUser(User user) {
        //implement
    }

    /**
     * Unbans user from submitting reports
     * @param user
     */
    public void unblockUser(User user) {
        //implement
    }

    /**
     * Shows security log to admin
     */
    public void viewSecurityLog() {
        //implement
    }

    public String getName() { return this.name; }

    public void setName(String value) { this.name = value; }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String val) {
        this.email = val;
    }

    public String getHome() {
        return this.home;
    }

    public void setHome(String val) {
        this.home = val;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String val) {
        this.title = val;
    }

    /**
     * Gets string representation of Account Type
     * @return String of account type
     */
    public String getAccountType() {
        return "ADMIN";
    }
}

