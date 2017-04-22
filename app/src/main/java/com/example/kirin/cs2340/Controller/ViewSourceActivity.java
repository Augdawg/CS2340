package com.example.kirin.cs2340.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.DB.WSRDBHandler;
import com.example.kirin.cs2340.Model.WaterQualityReport;
import com.example.kirin.cs2340.Model.WaterSourceReport;
import com.example.kirin.cs2340.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirin on 2/28/2017.
 * Displays list of submitted reports
 */

public class ViewSourceActivity extends AppCompatActivity {

    private DatabaseReference database;
    /**
     * Creates Source Report viewing activity
     * @param savedInstanceState data passed into the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsource);
        RecyclerView rv = (RecyclerView) findViewById(R.id.source_reports);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(mLayoutManager);

        //WSRDBHandler db = new WSRDBHandler(getApplicationContext());
        final List<WaterSourceReport> reports = new ArrayList<>();
        final ReportAdapter adapter = new ReportAdapter(reports);
        adapter.setContext(this);
        //reports.addAll(db.getAllWSRReports());
        database = FirebaseDatabase.getInstance().getReference().child("Reports").child("WSR");
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                WaterSourceReport wsr = dataSnapshot.getValue(WaterSourceReport.class);
                reports.add(wsr);
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
        rv.setAdapter(adapter);
    }
}
