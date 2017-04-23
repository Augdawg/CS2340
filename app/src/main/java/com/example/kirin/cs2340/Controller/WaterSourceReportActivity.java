package com.example.kirin.cs2340.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.kirin.cs2340.Manifest;
import com.example.kirin.cs2340.Model.CurrentLocation;
import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.WSRDBHandler;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.Model.ValidationUtilities;
import com.example.kirin.cs2340.Model.WaterSourceReport;
import com.example.kirin.cs2340.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

/**
 * Created by August Wagner
 * Activity to submit water source report
 */

public class WaterSourceReportActivity extends AppCompatActivity {

    private CheckBox currentLocation;
    private EditText latInput;
    private EditText longInput;
    private RadioGroup typeInput;
    private RadioGroup conditionInput;
    private DatabaseReference database;
    /**
     * Creates Water Source Report submitting activity
     * @param savedInstanceState bundle data transfer
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_source_report);
        currentLocation = (CheckBox) findViewById(R.id.current_location);
        latInput = (EditText) findViewById(R.id.latInput);
        longInput = (EditText) findViewById(R.id.longInput);
        typeInput = (RadioGroup) findViewById(R.id.typeInput);
        conditionInput = (RadioGroup) findViewById(R.id.conditionInput);
        ((RadioButton)typeInput.getChildAt(0)).setChecked(true);
        ((RadioButton)conditionInput.getChildAt(0)).setChecked(true);
        database = FirebaseDatabase.getInstance().getReference().child("Reports").child("WSR");
    }

    /**
     * submits water source report
     * @param view the current view
     *           
     */
    public void submitClick(View view) {
        Date date = new Date();
        double lat;
        double lng;
        if (currentLocation.isChecked()) {
            lat = CurrentLocation.getInstance(this).getCurrentLat();
            lng = CurrentLocation.getInstance(this).getCurrentLng();
        } else {
            lat = Double.parseDouble(latInput.getText().toString());
            lng = Double.parseDouble(longInput.getText().toString());
        }
        String type = ((RadioButton)findViewById(typeInput.getCheckedRadioButtonId())).getText().toString();
        String condition = ((RadioButton)findViewById(conditionInput.getCheckedRadioButtonId())).getText().toString();
        GeneralUser u = CurrentUser.getInstance().getCurrentUser();

        WaterSourceReport wsr = ValidationUtilities.tryCreateWSR(u.getName(), lat, lng, type, condition, date);

        if (wsr == null) {
            Toast.makeText(getApplicationContext(), "Invalid Fields", Toast.LENGTH_LONG);
        } else {
            database.push().setValue(wsr);
            WSRDBHandler db = new WSRDBHandler(getApplicationContext());
            db.addWSRReport(wsr);
            Toast.makeText(getApplicationContext(), "Water Source Report Submitted",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    /**
     * Cancels the submission
     * @param v the current view
     */
    public void cancelClick(View v) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
