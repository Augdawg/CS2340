package com.example.kirin.cs2340.Model;

import java.util.Date;

/**
 * Created by Kirin on 2/28/2017.
 * Models a Water Source Report
 */

public class WaterSourceReport {
    private String name;
    private double lat;
    private double lng;
    private WaterType type;
    private WaterCondition condition;
    private Date date;
    private int reportNumber;

    public WaterSourceReport(String name, double lat, double lng, String type, String condition, Date date) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.type = WaterType.valueOf(type.toUpperCase());
        if (condition.equals("Treatable-Muddy")) {
            this.condition = WaterCondition.MUDDY;
        } else if (condition.equals("Treatable-Clear")) {
            this.condition = WaterCondition.CLEAR;
        } else {
            this.condition = WaterCondition.valueOf(condition.toUpperCase());
        }
        this.date = date;
        reportNumber = Math.abs(this.hashCode());
    }

    public WaterSourceReport() {

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

    /**
     * Gets latitude of water report
     * @return latitude of water report
     */
    public double getLat() {
        return lat;
    }

    /**
     * Gets longitude of water report
     * @return longitude of water report
     */
    public double getLng() {
        return lng;
    }

    public String getWaterType() {
        return this.type.toString();
    }

    public String getWaterCondition() {
        return this.condition.toString();
    }

    public Date getDate() {
        return this.date;
    }

    public void setName(String val) {
        this.name = val;
    }

    public void setLat(double val) {
        this.lat = val;
    }

    public void setLng(double val) {
        this.lng = val;
    }

    public void setType(WaterType wt) {
        this.type = wt;
    }

    public void setCondition(WaterCondition wc) {
        this.condition = wc;
    }

    public void setDate(Date val) {
        this.date = val;
    }

    public void setReportNumber(int val) {
        this.reportNumber = val;
    }

    @Override
    public String toString() {
        return  "Report Id: " + this.reportNumber + "\n"
                + "Submitter: " + this.name + "\n"
                + "Lat: " + this.lat + "\n"
                + "Lng: " + this.lng + "\n"
                + "Water Type: " + this.type.toString() + "\n"
                + "Water Condition: " + this.condition.toString() + "\n"
                + "Date: " + this.date.toString();
    }
}
