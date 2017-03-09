package com.example.kirin.cs2340.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsource);
        rv = (RecyclerView) findViewById(R.id.sourcereports);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(mLayoutManager);

        WSRDBHandler db = new WSRDBHandler(getApplicationContext());
        List<WaterSourceReport> wsrReports = db.getAllReports();

        ReportAdapter adapter = new ReportAdapter(wsrReports);
        rv.setAdapter(adapter);
    }
}
