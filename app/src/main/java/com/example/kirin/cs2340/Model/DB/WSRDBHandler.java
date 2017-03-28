package com.example.kirin.cs2340.Model.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kirin.cs2340.Model.Admin;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.Model.Manager;
import com.example.kirin.cs2340.Model.OverallWaterCondition;
import com.example.kirin.cs2340.Model.User;
import com.example.kirin.cs2340.Model.WaterCondition;
import com.example.kirin.cs2340.Model.WaterQualityReport;
import com.example.kirin.cs2340.Model.WaterSourceReport;
import com.example.kirin.cs2340.Model.WaterType;
import com.example.kirin.cs2340.Model.Worker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kirin on 3/8/2017.
 * Database that holds all reports
 */

public class WSRDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "DBReports";

    private static final String WSR_TABLE = "WSRReportsTable";
    private static final String WSR_REPORT_NUM = "num";
    private static final String WSR_NAME = "name";
    private static final String WSR_LAT = "lat";
    private static final String WSR_LNG = "lng";
    private static final String WSR_WATERTYPE = "type";
    private static final String WSR_WATERCONDITION = "condition";
    private static final String WSR_DATE = "date";

    private static final String WQR_TABLE = "WQRReportsTable";
    private static final String WQR_NUM = "num";
    private static final String WQR_NAME = "name";
    private static final String WQR_LAT = "lat";
    private static final String WQR_LNG = "lng";
    private static final String WQR_CONDITION = "condition";
    private static final String WQR_VIRUS = "virusPPM";
    private static final String WQR_CONTAMINANT = "contaminantPPM";
    private static final String WQR_DATE = "date";



    /**
     * Constructor for db handler of database
     * @param context current context of the application
     */
    public WSRDBHandler(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    /**
     * Runs when database is created, creates tables
     * @param db the current db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WSR_TABLE = "CREATE TABLE " + WSR_TABLE
                + "(" + WSR_REPORT_NUM + " TEXT," + WSR_NAME + " TEXT," + WSR_LAT + " TEXT,"
                + WSR_LNG + " TEXT," + WSR_WATERTYPE + " TEXT," + WSR_WATERCONDITION + " TEXT,"
                + WSR_DATE + " TEXT)";
        String CREATE_WQR_TABLE = "CREATE TABLE " + WQR_TABLE
                + "(" + WQR_NUM + " TEXT," + WQR_NAME + " TEXT,"
                + WQR_LAT + " TEXT, " + WQR_LNG + " TEXT,"
                + WQR_CONDITION + " TEXT," + WQR_VIRUS + " TEXT,"
                + WQR_CONTAMINANT + " TEXT," + WQR_DATE + " TEXT)";
        db.execSQL(CREATE_WSR_TABLE);
        db.execSQL(CREATE_WQR_TABLE);
    }

    /**
     * Runs when version number is incremented
     * @param db the current db
     * @param oldVersion old version no.
     * @param newVersion new version no.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WSR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + WQR_TABLE);
        onCreate(db);
    }

    /**
     * Adds a wsr report to the db
     * @param report the report to be added
     */
    public void addWSRReport(WaterSourceReport report) {
        //deleteReportByReportNum(report.getReportNumber());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WSR_REPORT_NUM, report.getReportNumber());
        values.put(WSR_NAME, report.getName());
        values.put(WSR_LAT, report.getLat());
        values.put(WSR_LNG, report.getLng());
        values.put(WSR_WATERTYPE, report.getWaterType());
        values.put(WSR_WATERCONDITION, report.getWaterCondition());
        values.put(WSR_DATE, report.getDate().toString());
        db.insert(WSR_TABLE, null, values);
        db.close();
    }

    /**
     * Adds a wqr report to the db
     * @param report the report to be added
     */
    public void addWQRReport(WaterQualityReport report) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WQR_NUM, Integer.toString(report.getReportId()));
        values.put(WQR_NAME, report.getName());
        values.put(WQR_LAT, report.getLat());
        values.put(WQR_LNG, report.getLng());
        values.put(WQR_CONDITION, report.getCondition().toString());
        values.put(WQR_VIRUS, report.getVirusPPM());
        values.put(WQR_CONTAMINANT, report.getContaminantPPM());
        values.put(WQR_DATE, report.getDate().toString());
        db.insert(WQR_TABLE, null, values);
        db.close();
    }

    /**
     * Gets all the reports from the db
     * @return list of all the water source reports
     */
    public List<WaterSourceReport> getAllWSRReports() {
        List<WaterSourceReport> reports = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + WSR_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            WaterSourceReport report;
            do {
                report = new WaterSourceReport();
                report.setReportNumber(Integer.parseInt(cursor.getString(0)));
                report.setName(cursor.getString(1));
                report.setLat(Double.parseDouble(cursor.getString(2)));
                report.setLng(Double.parseDouble(cursor.getString(3)));
                report.setType(WaterType.valueOf(cursor.getString(4)));
                report.setCondition(WaterCondition.valueOf(cursor.getString(5)));
                String date = cursor.getString(6);
                DateFormat format = new SimpleDateFormat("EEE MMM dd kk:mm:ss zzz yyyy", Locale.ENGLISH);
                try {
                    Date d = format.parse(date);
                    report.setDate(d);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                reports.add(report);
            } while (cursor.moveToNext());
        }
        return reports;
    }

    /**
     * Gets all wqr reports from db
     * @return all wqr reports from db
     */
    public List<WaterQualityReport> getAllWQRReports() {
        List<WaterQualityReport> reports = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + WQR_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(0);
        if (cursor.getCount() > 0) {
            WaterQualityReport report;
            do {
                report = new WaterQualityReport();
                report.setName(cursor.getString(1));
                report.setLat(Double.parseDouble(cursor.getString(2)));
                report.setLng(Double.parseDouble(cursor.getString(3)));
                report.setCondition(OverallWaterCondition.valueOf(cursor.getString(4)));
                report.setVirusPPM(Integer.parseInt(cursor.getString(5)));
                report.setContaminantPPM(Integer.parseInt(cursor.getString(6)));
                String date = cursor.getString(7);
                DateFormat format = new SimpleDateFormat("EEE MMM dd kk:mm:ss zzz yyyy", Locale.ENGLISH);
                try {
                    Date d = format.parse(date);
                    report.setDate(d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                reports.add(report);
            } while (cursor.moveToNext());
        }


        return reports;
    }

    /**
     * Deletes report by id passed in
     * @param id the id of the report to be deleted
     */
    public void deleteWSRReportByReportNum(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WSR_TABLE, WSR_REPORT_NUM + " = ?", new String[]{Integer.toString(id)});
        db.close();
    }
}
