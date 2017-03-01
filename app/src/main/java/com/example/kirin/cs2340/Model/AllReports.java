package com.example.kirin.cs2340.Model;

import java.util.ArrayList;

/**
 * Created by Kirin on 2/28/2017.
 */

public class AllReports {
    private static AllReports instance;

    private ArrayList<WaterSourceReport> waterSourceReports;

    private AllReports() {
        waterSourceReports = new ArrayList<>();
    }

    public static AllReports getInstance() {
        if (instance == null) {
            instance = new AllReports();
        }
        return instance;
    }

    public void addWaterSourceReport(WaterSourceReport wsr) {
        instance.waterSourceReports.add(wsr);
    }

    public ArrayList<WaterSourceReport> getWaterSourceReports() {
        return instance.waterSourceReports;
    }
}
