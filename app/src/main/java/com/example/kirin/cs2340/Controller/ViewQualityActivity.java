package com.example.kirin.cs2340.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kirin.cs2340.Model.DB.WSRDBHandler;
import com.example.kirin.cs2340.Model.WaterQualityReport;
import com.example.kirin.cs2340.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirin on 3/11/2017.
 * Activity that displays all Water Quality Reports submitted so far
 */

public class ViewQualityActivity extends AppCompatActivity {
    private DatabaseReference database;
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

        final List<WaterQualityReport> reports = new ArrayList<>();
        final WQRReportAdapter adapter = new WQRReportAdapter(reports);

        database = FirebaseDatabase.getInstance().getReference().child("Reports").child("QR");
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                WaterQualityReport wqr = dataSnapshot.getValue(WaterQualityReport.class);
                reports.add(wqr);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

      //  WSRDBHandler db = new WSRDBHandler(getApplicationContext());
       // reports.addAll(db.getAllWQRReports());
        rv.setAdapter(adapter);
    }
}
