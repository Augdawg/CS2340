package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/22/2017.
 * Generic user that has properties that all users have
 */

public abstract class GeneralUser {

    public abstract String getName();

    public abstract void setName(String value);

    public abstract String getUsername();

    public abstract void setUsername(String value);

    public abstract String getPassword();

    public abstract void setPassword(String value);

    public abstract String getEmail();

    public abstract void setEmail(String val);

    public abstract String getHome();

    public abstract void setHome(String val);

    public abstract String getTitle();

    public abstract void setTitle(String val);

    public abstract String getAccountType();
}
