package com.example.kirin.cs2340.Model;

import java.util.ArrayList;

/**
 * Created by Kirin on 2/28/2017.
 * Singleton that keeps track of all reports submitted
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

    /**
     * Adds water source report to overall list
     * @param wsr report to be added
     */
    public void addWaterSourceReport(WaterSourceReport wsr) {
        instance.waterSourceReports.add(wsr);
    }

    /**
     * Gets all water source reports
     * @return all water source reports
     */
    public ArrayList<WaterSourceReport> getWaterSourceReports() {
        return instance.waterSourceReports;
    }
}
