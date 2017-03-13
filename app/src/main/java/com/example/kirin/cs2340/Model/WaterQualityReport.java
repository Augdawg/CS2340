package com.example.kirin.cs2340.Model;

import java.util.Date;

/**
 * Created by Kirin on 3/11/2017.
 */

public class WaterQualityReport {
    private Date date;
    private String name;
    private double lat;
    private double lng;
    private OverallWaterCondition condition;
    private int virusPPM;
    private int contaminantPPM;

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

    public WaterQualityReport() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getReportId() {
        return this.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public OverallWaterCondition getCondition() {
        return condition;
    }

    public void setCondition(OverallWaterCondition condition) {
        this.condition = condition;
    }

    public int getVirusPPM() {
        return virusPPM;
    }

    public void setVirusPPM(int virusPPM) {
        this.virusPPM = virusPPM;
    }

    public int getContaminantPPM() {
        return contaminantPPM;
    }

    public void setContaminantPPM(int contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }

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
