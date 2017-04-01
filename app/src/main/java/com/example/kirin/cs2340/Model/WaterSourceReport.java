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

    /**
     * Constructor for WaterSourceReportObject
     * @param name name of submitter
     * @param lat latitude of report
     * @param lng longitude of report
     * @param type type of water
     * @param condition condition of water
     * @param date date of report
     */
    public WaterSourceReport(String name, double lat, double lng, String type, String condition, Date date) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.type = WaterType.valueOf(type.toUpperCase());
        switch (condition) {
            case "Treatable-Muddy":
                this.condition = WaterCondition.MUDDY;
                break;
            case "Treatable-Clear":
                this.condition = WaterCondition.CLEAR;
                break;
            default:
                this.condition = WaterCondition.valueOf(condition.toUpperCase());
                break;
        }
        this.date = date;
        reportNumber = Math.abs(this.hashCode());
    }

    /**
     * Default constructor of report
     */
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

    /**
     * Gets water type of report
     * @return water type of report
     */
    public String getWaterType() {
        return this.type.toString();
    }

    /**
     * Gets condition of water
     * @return condition of water
     */
    public String getWaterCondition() {
        return this.condition.toString();
    }

    /**
     * Gets date of report
     * @return date of report
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets name of submitter
     * @param val value to set name to
     */
    public void setName(String val) {
        this.name = val;
    }

    /**
     * Sets latitude of report
     * @param val value to set latitude to
     */
    public void setLat(double val) {
        this.lat = val;
    }

    /**
     * Sets longitude of report
     * @param val value to set longitude to
     */
    public void setLng(double val) {
        this.lng = val;
    }

    /**
     * Sets water type of report
     * @param wt water type to set to
     */
    public void setType(WaterType wt) {
        this.type = wt;
    }

    /**
     * Sets water condition of report
     * @param wc value to set condition to
     */
    public void setCondition(WaterCondition wc) {
        this.condition = wc;
    }

    /**
     * Sets date of report
     * @param val value to set date to
     */
    public void setDate(Date val) {
        this.date = val;
    }

    /**
     * Sets report number of report
     * @param val value to set report number to
     */
    public void setReportNumber(int val) {
        this.reportNumber = val;
    }

    /**
     * Returns a string representation of a Water Source Report
     * @return string representation of a Water Source Report
     */
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
