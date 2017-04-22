package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kirin.cs2340.Model.Admin;
import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.WSRDBHandler;
import com.example.kirin.cs2340.Model.Manager;
import com.example.kirin.cs2340.Model.WaterQualityReport;
import com.example.kirin.cs2340.Model.WaterSourceReport;
import com.example.kirin.cs2340.Model.Worker;
import com.example.kirin.cs2340.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirin on 2/14/2017.
 * Activity that welcomes user upon login or registration
 */

public class WelcomeActivity extends FragmentActivity implements OnMapReadyCallback {

    private final List<WaterQualityReport> wqrReports = new ArrayList<>();
    private final List<WaterSourceReport> wsrReports = new ArrayList<>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private DatabaseReference database;
    private GoogleMap map;

    /**
     * Creates Welcoming Activity
     * @param savedInstanceState bundle data transfer
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ((MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment)).getMapAsync(this);
        if (!(CurrentUser.getInstance().getCurrentUser() instanceof Worker)) {
            Button btn = (Button) findViewById(R.id.submitWQR_btn);
            //btn.setEnabled(false);
            btn.setVisibility(View.INVISIBLE);
        }
        if (!(CurrentUser.getInstance().getCurrentUser() instanceof Manager)) {
            Button btn = (Button) findViewById(R.id.viewWQR_reports);
            //btn.setEnabled(false);
            btn.setVisibility(View.INVISIBLE);
            Button b = (Button) findViewById(R.id.qualityGraphBtn);
            //b.setEnabled(false);
            b.setVisibility(View.INVISIBLE);
        }
        if (!(CurrentUser.getInstance().getCurrentUser() instanceof Admin)) {
            Button btn = (Button) findViewById(R.id.security);
            btn.setVisibility(View.INVISIBLE);
        }
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        database = FirebaseDatabase.getInstance().getReference().child("Reports").child("QR");
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                WaterQualityReport r = dataSnapshot.getValue(WaterQualityReport.class);
                LatLng l = new LatLng(r.getLat(), r.getLng());
                map.addMarker(new MarkerOptions().position(l).title(r.getName()).snippet(r.toString()));
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
        database = database.getParent().child("WSR");
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                WaterSourceReport r = dataSnapshot.getValue(WaterSourceReport.class);
                LatLng l = new LatLng(r.getLat(), r.getLng());
                map.addMarker(new MarkerOptions().position(l).title(r.getName()).snippet(r.toString()));
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
    }

    /**
     * Runs when the google map is ready
     * @param map the google map to be viewed
     */
    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        //WSRDBHandler db = new WSRDBHandler(getApplicationContext());
        //List<WaterSourceReport> wsrReports = db.getAllWSRReports();
       // List<WaterQualityReport> wqrReports = db.getAllWQRReports();
//        for (WaterSourceReport r : wsrReports) {
//            LatLng l = new LatLng(r.getLat(), r.getLng());
//            map.addMarker(new MarkerOptions().position(l).title(r.getName()).snippet(r.toString()));
//        }
//
//        for (WaterQualityReport r : wqrReports) {
//            LatLng l = new LatLng(r.getLat(), r.getLng());
//            map.addMarker(new MarkerOptions().position(l).title(r.getName()).snippet(r.toString()));
//        }
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(getApplicationContext());
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(getApplicationContext());
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(getApplicationContext());
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
    }

    /**
     * Logs out current user
     * @param v current view
     */
    public void logoutPressed(View v) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * Opens edit activity to edit profile
     * @param v current view
     */
    public void editPressed(View v) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    /**
     * Migrates to Water Source Report submit page
     * @param v the current view
     */
    public void submitPressed(View v) {
        Intent intent = new Intent(this, WaterSourceReportActivity.class);
        startActivity(intent);
    }

    /**
     * Migrates to view Water Source Reports page
     * @param v the current view
     */
    public void viewReportPressed(View v) {
        Intent intent = new Intent(this, ViewSourceActivity.class);
        startActivity(intent);
    }

    /**
     * Migrates to View Quality Reports page
     * @param v the current view
     */
    public void viewQualityReports(View v) {
        Intent intent = new Intent(this, ViewQualityActivity.class);
        startActivity(intent);
    }

    public void adminClick(View v) {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    private Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Welcome Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    /**
     * Runs when activity starts
     */
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    /**
     * Runs when activity stops
     */
    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    /**
     * Runs when submit quality report is pressed to migrate to submission page
     * @param v current view
     */
    public void submitQualityReport(View v) {
        Intent intent = new Intent(this, SubmitQualityActivity.class);
        startActivity(intent);
    }

    /**
     * Runs when user wants to view the WQR history
     * @param v the current view
     */
    public void qualityGraphClick(View v) {
        Intent intent = new Intent(this, QualityGraphActivity.class);
        startActivity(intent);
    }
}
