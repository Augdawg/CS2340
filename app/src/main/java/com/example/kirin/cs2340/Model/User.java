package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/14/2017.
 * Represents User user object
 */

public class User extends GeneralUser {
    private int id;
    private String username;
    private String password;
    private String email;
    private String home;
    private String title;
    private String name;

    /**
     * User constructor
     * @param username username of user
     * @param password password of user
     * @param email email address of user
     * @param home home address of user
     * @param title title of user
     */
    public User(String name, String username, String password, String email, String home, String title) {
        this.id = username.hashCode();
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.home = home;
        this.title = title;
    }

    /**
     * Default constructor of user, creates empty fields
     */
    public User() {
        this("","","","","","");
    }

    /**
     * Gets name of user
     * @return name of user
     */
    public String getName() { return this.name; }

    /**
     * Sets name of user
     * @param value value to set name to
     */
    public void setName(String value) {this.name = value;}

    /**
     * Gets username of user
     * @return username of user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets username of user
     * @param value value to set username to
     */
    public void setUsername(String value) {
        this.username = value;
        this.id = this.username.hashCode();
    }

    /**
     * Gets password of user
     * @return password of user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password of user
     * @param value value to set password to
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets email address of user
     * @return email address of user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets email address of user
     * @param val value to set email to
     */
    public void setEmail(String val) {
        this.email = val;
    }

    /**
     * Gets home address of user
     * @return  home address of user
     */
    public String getHome() {
        return this.home;
    }

    /**
     * Sets home address of user
     * @param val value to set home address to
     */
    public void setHome(String val) {
        this.home = val;
    }

    /**
     * Gets title of user
     * @return title of user
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title of user
     * @param val value to set title to
     */
    public void setTitle(String val) {
        this.title = val;
    }

    /**
     * Gets String representation of Account Type
     * @return String representation of Account Type
     */
    public String getAccountType() {
        return "USER";
    }

    /**
     * Gets id of user
     * @return id of user
     */
    public int getId() {
        return this.id;
    }

}