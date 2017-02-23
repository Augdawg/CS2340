package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/22/2017.
 * Represents Manager User object
 */

public class Manager extends Worker {
    /**
     * Manager constructor
     * @param username
     * @param password
     * @param email
     * @param home
     * @param title
     */
    public Manager(String username, String password, String email, String home, String title) {
        super(username, password, email, home, title);
    }

    /**
     * Submits historical report
     */
    public void submitHistoricalReport() {
        //implement
    }

    /**
     * Views Water purity trends
     */
    public void viewWaterPurityTrends() {
        //implement
    }

    /**
     * String representation of Account Type
     * @return String representation of Account Type
     */
    public String getAccountType() {
        return "MANAGER";
    }
}
