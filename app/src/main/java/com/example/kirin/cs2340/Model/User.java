package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/14/2017.
 */

public class User {
    private String username;
    private String password;
    private String email;
    private String home;
    private String title;
    private AccountType accountType;
    private long id;

    public User(long id, String username, String password, String email, String home, String title, AccountType accountType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.home = home;
        this.title = title;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
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

    public long getId() {
        return this.id;
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

    public AccountType getAccountType() {
        return this.accountType;
    }
}