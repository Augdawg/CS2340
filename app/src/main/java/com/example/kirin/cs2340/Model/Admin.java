package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/22/2017.
 * Represents Admin User object
 */
public class Admin extends GeneralUser {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String home;
    private String title;

    /**
     * Admin constructor
     * @param username username of new admin
     * @param password password of new admin
     * @param email email address of new admin
     * @param home home address of new admin
     * @param title title of new admin
     */
    public Admin(String name, String username, String password, String email, String home, String title) {
        this.id = name.hashCode();
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.home = home;
        this.title = title;
    }

    /**
     * Default constructor for admin. Creates empty fields
     */
    public Admin() {
        this("", "", "", "", "", "");
    }

    /**
     * Gets name of admin
     * @return name of admin
     */
    public String getName() { return this.name; }

    /**
     * Sets name of admin
     * @param value value to set name to
     */
    public void setName(String value) { this.name = value; }

    /**
     * Gets username of admin
     * @return username of admin
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets username of admin
     * @param value value to set username to
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets password of admin
     * @return password of admin
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets password of admin
     * @param value value to set password to
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets email of admin
     * @return email of admin
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets email of admin
     * @param val value to set email to
     */
    public void setEmail(String val) {
        this.email = val;
    }

    /**
     * Gets home address of admin
     * @return home address of admin
     */
    public String getHome() {
        return this.home;
    }

    /**
     * Sets home address of admin
     * @param val value to set home address to
     */
    public void setHome(String val) {
        this.home = val;
    }

    /**
     * Gets title of admin
     * @return title of admin
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title of admin
     * @param val value to set title to
     */
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

    /**
     * Gets id of admin
     * @return id of admin
     */
    public int getId() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

}

