package com.example.kirin.cs2340.Model;

import java.util.Date;

/**
 * Created by Kirin on 3/11/2017.
 * Represents Water Quality Report object
 */

public class WaterQualityReport {
    private Date date;
    private String name;
    private double lat;
    private double lng;
    private OverallWaterCondition condition;
    private int virusPPM;
    private int contaminantPPM;

    /**
     * Constructor for a Water Quality Report
     * @param name name of submitter
     * @param lat latitude of location
     * @param lng longitude of location
     * @param condition condition of water of report submitted
     * @param virusPPM ppm of viruses in water of report submitted
     * @param contaminantPPM ppm of contaminants in water of report submitted
     * @param date date that report is submitted
     */
    public WaterQualityReport(String name, double lat, double lng, OverallWaterCondition condition,
                              int virusPPM, int contaminantPPM, Date date) {
        this.date = date;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.condition = condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }

    /**
     * Default Constructor
     */
    public WaterQualityReport() {

    }

    /**
     * Gets date of report
     * @return date of report
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date of report
     * @param date value to set date to
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets reportId of report
     * @return reportId of report
     */
    public int getReportId() {
        return this.hashCode();
    }

    /**
     * Gets name of submitter
     * @return name of submitter
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of submitter
     * @param name value to set name to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets latitude of report
     * @return latitude of report
     */
    public double getLat() {
        return lat;
    }

    /**
     * Sets latitude of report
     * @param lat value to set lat to
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Gets longitude of report
     * @return longitude of report
     */
    public double getLng() {
        return lng;
    }

    /**
     * Sets longitude of report
     * @param lng value to set lng to
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * Gets overall condition of report
     * @return overall condition of report
     */
    public OverallWaterCondition getCondition() {
        return condition;
    }

    /**
     * Sets overall condition of report
     * @param condition value to set overall condition to
     */
    public void setCondition(OverallWaterCondition condition) {
        this.condition = condition;
    }

    /**
     * Gets virus ppm of report
     * @return virus ppm of report
     */
    public int getVirusPPM() {
        return virusPPM;
    }

    /**
     * Sets virus ppm of report
     * @param virusPPM value to set virusppm to
     */
    public void setVirusPPM(int virusPPM) {
        this.virusPPM = virusPPM;
    }

    /**
     * Gets contaminant ppm of report
     * @return contaminant ppm of report
     */
    public int getContaminantPPM() {
        return contaminantPPM;
    }

    /**
     * Sets contaminant ppm of report
     * @param contaminantPPM value to set contaminantppm to
     */
    public void setContaminantPPM(int contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }

    /**
     * String representation of a Water Quality Report
     * @return String representation of a Water Quality Report
     */
    @Override
    public String toString() {
        return  "Report Id: " + this.getReportId() + "\n"
                + "Submitter: " + this.name + "\n"
                + "Lat: " + this.lat + "\n"
                + "Lng: " + this.lng + "\n"
                + "Overall Condition: " + this.condition.toString() + "\n"
                + "Virus PPM: " + this.virusPPM + "\n"
                + "Contaminant PPM: " + this.contaminantPPM + "\n"
                + "Date: " + this.date.toString();
    }
}
