package com.example.kirin.cs2340.Model;

import java.util.Date;

/**
 * Created by Kirin on 2/28/2017.
 * Models a Water Source Report
 */

public class WaterSourceReport {
    private String name;
    private String location;
    private WaterType type;
    private WaterCondition condition;
    private Date date;
    private int reportNumber;

    private static int reportCounter = 0;

    public WaterSourceReport(String name, double lat, double longi, String type, String condition, Date date) {
        this.name = name;
        this.location = lat + ", " + longi;
        this.type = WaterType.valueOf(type.toUpperCase());
        if (condition.equals("Treatable-Muddy")) {
            this.condition = WaterCondition.MUDDY;
        } else if (condition.equals("Treatable-Clear")) {
            this.condition = WaterCondition.CLEAR;
        } else {
            this.condition = WaterCondition.valueOf(condition.toUpperCase());
        }
        this.date = date;
        reportNumber = ++reportCounter;
    }

    /**
     * gets report number
     * @return report number
     */
    public int getReportNumber() {
        return reportNumber;
    }

    /**
     * gets name of submitter
     * @return name of submitter
     */
    public String getName() {
        return name;
    }

    /**
     * gets condition of water of report
     * @return condition of water of report
     */
    public WaterCondition getCondition() {
        return condition;
    }
}
