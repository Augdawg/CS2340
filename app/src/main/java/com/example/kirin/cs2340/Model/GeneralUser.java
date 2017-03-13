package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/22/2017.
 * Generic user that has properties that all users have
 */

public abstract class GeneralUser {

    /**
     * Gets name of user
     * @return name of user
     */
    public abstract String getName();

    /**
     * Sets name of user
     * @param value value to set name to
     */
    public abstract void setName(String value);

    /**
     * Gets username of user
     * @return username of user
     */
    public abstract String getUsername();

    /**
     * Sets username of user
     * @param value value to set username to
     */
    public abstract void setUsername(String value);

    /**
     * Gets password of user
     * @return password of user
     */
    public abstract String getPassword();

    /**
     * Sets password of user
     * @param value value to set password to
     */
    public abstract void setPassword(String value);

    /**
     * Gets email of user
     * @return email of user
     */
    public abstract String getEmail();

    /**
     * Sets email of user
     * @param val value to set email to
     */
    public abstract void setEmail(String val);

    /**
     * Gets home address of user
     * @return home address of user
     */
    public abstract String getHome();

    /**
     * Sets home address of user
     * @param val value to set home address to
     */
    public abstract void setHome(String val);

    /**
     * Gets title of user
     * @return title of user
     */
    public abstract String getTitle();

    /**
     * Sets title of user
     * @param val value to set title to
     */
    public abstract void setTitle(String val);

    /**
     * Gets account type of user
     * @return account type of user
     */
    public abstract String getAccountType();

    /**
     * Gets id of user
     * @return id of user
     */
    public abstract int getId();

}
