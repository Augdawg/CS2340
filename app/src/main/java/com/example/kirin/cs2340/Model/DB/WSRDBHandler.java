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
import com.example.kirin.cs2340.Model.User;
import com.example.kirin.cs2340.Model.WaterCondition;
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
 */

public class WSRDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "DBWSRReports";
    private static final String TABLE_REPORTS = "WSRReportsTable";
    private static final String REPORT_NUM = "num";
    private static final String NAME = "name";
    private static final String LAT = "lat";
    private static final String LNG = "lng";
    private static final String WATERTYPE = "type";
    private static final String WATERCONDITION = "condition";
    private static final String DATE = "date";

    public WSRDBHandler(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REPORTS_TABLE = "CREATE TABLE " + TABLE_REPORTS
                + "(" + REPORT_NUM + " TEXT," + NAME + " TEXT," + LAT + " TEXT,"
                + LNG + " TEXT," + WATERTYPE + " TEXT," + WATERCONDITION + " TEXT,"
                + DATE + " TEXT)";
        db.execSQL(CREATE_REPORTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);
        onCreate(db);
    }

    public void addReport(WaterSourceReport report) {
        //deleteReportByReportNum(report.getReportNumber());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REPORT_NUM, report.getReportNumber());
        values.put(NAME, report.getName());
        values.put(LAT, report.getLat());
        values.put(LNG, report.getLng());
        values.put(WATERTYPE, report.getWaterType());
        values.put(WATERCONDITION, report.getWaterCondition());
        values.put(DATE, report.getDate().toString());
        db.insert(TABLE_REPORTS, null, values);
        db.close();
    }

    public List<WaterSourceReport> getAllReports() {
        List<WaterSourceReport> reports = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_REPORTS;
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

    public void deleteReportByReportNum(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REPORTS, REPORT_NUM + " = ?", new String[]{Integer.toString(id)});
        db.close();
    }
}
