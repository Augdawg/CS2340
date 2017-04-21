package com.example.kirin.cs2340.Model;

import java.util.Date;

/**
 * Created by August on 4/21/2017.
 */

public class ActivityLog {
    private LogType type;
    private Date day;
    private int id1;
    private int id2;
    private String status;

    public ActivityLog() {
        day = new Date();
    }
    public void setType(LogType type) {
        this.type = type;
    }

    public LogType getType() {
        return type;
    }

    public void setId1(int id) {
        id1 = id;
    }
    public int getId1() {
        return id1;
    }
    public void setId2(int id) {
        id2 = id;
    }
    public int getId2() {
        return id2;
    }
    public Date getDay() {
        return day;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String s) {
        status = s;
    }
}