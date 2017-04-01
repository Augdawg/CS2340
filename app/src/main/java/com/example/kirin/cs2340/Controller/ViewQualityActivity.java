package com.example.kirin.cs2340.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kirin.cs2340.Model.DB.WSRDBHandler;
import com.example.kirin.cs2340.Model.WaterQualityReport;
import com.example.kirin.cs2340.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirin on 3/11/2017.
 * Activity that displays all Water Quality Reports submitted so far
 */

public class ViewQualityActivity extends AppCompatActivity {
    /**
     * Creates Quality Report viewing activity
     * @param savedInstanceState data passed into the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quality);

        RecyclerView rv = (RecyclerView) findViewById(R.id.quality_reports);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(mLayoutManager);

        WSRDBHandler db = new WSRDBHandler(getApplicationContext());
        List<WaterQualityReport> reports = new ArrayList<>();
        reports.addAll(db.getAllWQRReports());

        WQRReportAdapter adapter = new WQRReportAdapter(reports);
        rv.setAdapter(adapter);
    }
}
