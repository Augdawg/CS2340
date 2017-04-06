package com.example.kirin.cs2340.Model;

import java.util.Date;

/**
 * Created by Kirin on 4/2/2017.
 * Utility methods to validate input fields
 */

public class ValidationUtilities {
    /**
     * Checks if validation fields are valid
     * @param name name of user
     * @param username username of user
     * @param password password of user
     * @param title title of user
     * @param email email of user
     * @param home home of user
     * @param accType Account Type of user
     * @return Returns a GeneralUser if the fields are valid, null if valid
     */
    public static GeneralUser registrationFieldsAreValid(String name, String username, String password,
                                                     String title, String email, String home, String accType) {

        GeneralUser user;
        switch (accType) {
            case "USER":
                user = new User();
                break;
            case "WORKER":
                user = new Worker();
                break;
            case "MANAGER":
                user = new Manager();
                break;
            default:
                user = new Admin();
                break;
        }
        if (name == null || name.trim().equals("")) {
            return null;
        } else if (username == null || username.trim().equals("")) {
            return null;
        } else if (password == null || password.trim().equals("")) {
            return null;
        } else if (title == null || title.trim().equals("")) {
            return null;
        } else if (email == null || email.trim().equals("")) {
            return null;
        } else if (home == null || home.trim().equals("")) {
            return null;
        }
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setTitle(title);
        user.setEmail(email);
        return user;
    }

    /**
     * Attempts to create WSR. Succeeds if fields are valid, fails if not
     * @param name Name of user who submitted report
     * @param lat Latitude of report
     * @param lng Longitude of report
     * @param type Type of water in report
     * @param condition Condition of water in report
     * @param date Date of submission
     * @return New WSR if fields are valid, null if invalid
     */
    public static WaterSourceReport tryCreateWSR(String name, double lat, double lng, String type, String condition, Date date) {
        if (name == null || name.trim().equals("")) {
            return null;
        } else if (lat < -90 || lat > 90) {
            return null;
        } else if (lng > 180 || lng < -180) {
            return null;
        } else if (type == null || type.trim().equals("")) {
            return null;
        } else if (condition == null || condition.trim().equals("")) {
            return null;
        } else if (date == null) {
            return null;
        }
        WaterSourceReport wsr = new WaterSourceReport();
        wsr.setName(name);
        wsr.setLat(lat);
        wsr.setLng(lng);
        switch (type) {
            case "Bottled":
                wsr.setType(WaterType.BOTTLED);
                break;
            case "Well":
                wsr.setType(WaterType.WELL);
                break;
            case "Stream":
                wsr.setType(WaterType.STREAM);
                break;
            case "Lake":
                wsr.setType(WaterType.LAKE);
                break;
            case "Spring":
                wsr.setType(WaterType.SPRING);
                break;
            case "Other":
                wsr.setType(WaterType.OTHER);
                break;
            default:
                return null;
        }
        switch (condition) {
            case "Treatable-Muddy":
                wsr.setCondition(WaterCondition.MUDDY);
                break;
            case "Treatable-Clear":
                wsr.setCondition(WaterCondition.CLEAR);
                break;
            case "Potable":
                wsr.setCondition(WaterCondition.POTABLE);
                break;
            case "Waste":
                wsr.setCondition(WaterCondition.WASTE);
                break;
            default:
                return null;
        }
        wsr.setDate(date);
        wsr.setReportNumber(wsr.hashCode());
        return wsr;
    }

    /**
     * Attempts to create WQR. Succeeds if fields are valid, fails if not
     * @param name Name of submitter
     * @param lat Latitude of report
     * @param lng Longitude of report
     * @param condition Condition of water in report
     * @param virus Virus PPM of water in report
     * @param contaminant Contaminant PPM of water in report
     * @param date Date of submission
     * @return New WQR report if fields valid, null if invalid
     */
    public static WaterQualityReport tryCreateWQR(String name, double lat, double lng, String condition, int virus, int contaminant, Date date) {
        if (name == null || name.trim().equals("")) {
            return null;
        } else if (lat < -90 || lat > 90) {
            return null;
        } else if (lng > 180 || lng < -180) {
            return null;
        } else if (virus < 0) {
            return null;
        } else if (contaminant < 0) {
            return null;
        } else if (date == null) {
            return null;
        } else if (condition.trim().equals("")) {
            return null;
        }

        WaterQualityReport wqr = new WaterQualityReport();
        wqr.setName(name);
        wqr.setLat(lat);
        wqr.setLng(lng);
        switch (condition) {
            case "Safe":
                wqr.setCondition(OverallWaterCondition.SAFE);
                break;
            case "Treatable":
                wqr.setCondition(OverallWaterCondition.TREATABLE);
                break;
            case "Unsafe":
                wqr.setCondition(OverallWaterCondition.UNSAFE);
                break;
            default:
                return null;
        }
        wqr.setVirusPPM(virus);
        wqr.setContaminantPPM(contaminant);
        wqr.setDate(date);
        return wqr;
    }

    /**
     * Determines if graph inputs are valid
     * @param lat Latitude to check data against in graph
     * @param lng Longitude to check data against in graph
     * @param year Year to check data against in graph
     * @param selection What data user wants to display, virus ppm or contaminant ppm
     * @return True if fields are valid, false otherwise
     */
    public static boolean graphFieldsAreValid(double lat, double lng, int year, String selection) {
        if (lat > 90 || lat < -90) {
            return false;
        } else if (lng > 180 || lng < -180) {
            return false;
        } else if (year < 0) {
            return false;
        } else if (!(selection.equals("VIRUS") || selection.equals("CONTAMINANT"))) {
            return false;
        }
        return true;
    }

    /**
     * Checks if login fields are valid
     * @param username username typed to attempt to login
     * @param password password typed to attempt to login
     * @return True if username and password are valid, false otherwise
     */
    public static boolean loginFieldsAreValid(String username, String password) {
        if (username == null || username.trim().equals("")) {
            return false;
        } else if (password == null || password.trim().equals("")) {
            return false;
        }
        return true;
    }
}
