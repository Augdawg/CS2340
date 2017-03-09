package com.example.kirin.cs2340.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kirin.cs2340.Model.DB.WSRDBHandler;
import com.example.kirin.cs2340.Model.WaterSourceReport;
import com.example.kirin.cs2340.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirin on 2/28/2017.
 * Displays list of submitted reports
 */

public class ViewSourceActivity extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsource);
        lv = (ListView) findViewById(R.id.sourcereports);
        ArrayList<String> reports = new ArrayList<>();
        WSRDBHandler db = new WSRDBHandler(getApplicationContext());
        List<WaterSourceReport> wsrReports = db.getAllReports();
        for (int i = 0; i < wsrReports.size(); i++) {
            WaterSourceReport wsr = wsrReports.get(i);
            reports.add(wsr.getReportNumber() + ". "  + wsr.getName() + ", " + wsr.getCondition().toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, reports);
        lv.setAdapter(adapter);
    }
}
