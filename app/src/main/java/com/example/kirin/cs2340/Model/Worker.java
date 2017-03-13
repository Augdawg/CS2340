package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/22/2017.
 * Models a worker account
 */

public class Worker extends User {
    /**
     * Worker constructor
     * @param username username of worker
     * @param password password of worker
     * @param email email address of worker
     * @param home home address of worker
     * @param title title of worker
     */
    public Worker(String name, String username, String password, String email, String home, String title) {
        super(name, username, password, email, home, title);
    }

    /**
     * Default constructor of worker, creates empty fields
     */
    public Worker() {
        super("", "", "", "", "", "");
    }


    /**
     * Gets string representation of Account Type
     * @return String representation of Account Type
     */
    public String getAccountType() {
        return "WORKER";
    }
}
