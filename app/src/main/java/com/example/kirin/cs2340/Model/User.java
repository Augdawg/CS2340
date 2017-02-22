package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/14/2017.
 */

public class User extends GeneralUser {
    private String username;
    private String password;
    private String email;
    private String home;
    private String title;
    public User(String username, String password, String email, String home, String title) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.home = home;
        this.title = title;
    }


    public void submitWaterAvailabilityReport() {
        //implement
    }

    public void submitWaterSourceReport() {
        //implement
    }

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

    public String getAccountType() {
        return "USER";
    }
}