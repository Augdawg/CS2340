package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/22/2017.
 */

public class Worker extends User {
    /**
     * Worker constructor
     * @param username
     * @param password
     * @param email
     * @param home
     * @param title
     */
    public Worker(String name, String username, String password, String email, String home, String title) {
        super(name, username, password, email, home, title);
    }

    /**
     * Submits water purity report
     */
    public void submitWaterPurityReport() {

    }

    /**
     * Gets string representation of Account Type
     * @return String representation of Account Type
     */
    public String getAccountType() {
        return "WORKER";
    }
}
