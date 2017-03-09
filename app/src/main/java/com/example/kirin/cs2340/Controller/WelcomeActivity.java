package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.WSRDBHandler;
import com.example.kirin.cs2340.Model.WaterSourceReport;
import com.example.kirin.cs2340.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by Kirin on 2/14/2017.
 * WelcomeActivity Controller
 */

public class WelcomeActivity extends FragmentActivity implements OnMapReadyCallback {

    private TextView text;
    private MapView mv;
    private GoogleMap gm;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * Creates Activity
     *
     * @param savedInstanceState bundle data transfer
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        text = (TextView) findViewById(R.id.welcometext);
        text.setText("Welcome " + CurrentUser.getInstance().getCurrentUser().getUsername() + "!");
        ((MapFragment) getFragmentManager().findFragmentById(R.id.mapfragment)).getMapAsync(this);
        int x = 0;
        /*mv.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gm = googleMap;
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        gm = map;
        WSRDBHandler db = new WSRDBHandler(getApplicationContext());
        List<WaterSourceReport> wsrReports = db.getAllReports();
        for (WaterSourceReport r : wsrReports) {
            LatLng l = new LatLng(r.getLat(), r.getLng());
            gm.addMarker(new MarkerOptions().position(l).title(r.getName()).snippet(r.toString()));
        }
        gm.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
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
     * Resumes Activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        text.setText("Welcome " + CurrentUser.getInstance().getCurrentUser().getUsername() + "!");

    }

    /**
     * Logs out current user
     *
     * @param v current view
     */
    public void logoutPressed(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * Opens edit activity to edit profile
     *
     * @param v current view
     */
    public void editPressed(View v) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    public void submitPressed(View v) {
        Intent intent = new Intent(this, WaterSourceReportActivity.class);
        startActivity(intent);
    }

    public void viewReportPressed(View v) {
        Intent intent = new Intent(this, ViewSourceActivity.class);
        startActivity(intent);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
