package com.example.kirin.cs2340.Model;

/**
 * Created by Kirin on 2/22/2017.
 */

public class Manager extends Worker {
    public Manager(String username, String password, String email, String home, String title) {
        super(username, password, email, home, title);
    }

    public void submitHistoricalReport() {
        //implement
    }

    public void viewWaterPurityTrends() {
        //implement
    }

    public String getAccountType() {
        return "MANAGER";
    }
}
