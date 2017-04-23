package com.example.kirin.cs2340.Controller;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.CurrentLocation;
import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.WSRDBHandler;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.Model.ValidationUtilities;
import com.example.kirin.cs2340.Model.WaterQualityReport;
import com.example.kirin.cs2340.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import static com.example.kirin.cs2340.R.id.longInput;

/**
 * Created by Kirin on 3/8/2017.
 * Activity where user submits a Water Quality Report
 */
public class SubmitQualityActivity extends AppCompatActivity {

    private EditText latInput;
    private EditText lngInput;
    private RadioGroup conditionInput;
    private EditText virusPPMInput;
    private EditText contaminantPPMInput;
    private CheckBox useCurrentLoc;
    private DatabaseReference database;

    /**
     * Creates the Water Quality Report submission activity
     * @param savedInstanceState data passed to activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_quality);
        useCurrentLoc = (CheckBox) findViewById(R.id.current_location);
        latInput = (EditText) findViewById(R.id.latInput);
        lngInput = (EditText) findViewById(longInput);
        conditionInput = (RadioGroup) findViewById(R.id.conditionInput);
        virusPPMInput = (EditText) findViewById(R.id.virusPPM);
        contaminantPPMInput = (EditText) findViewById(R.id.contaminantPPM);
        ((RadioButton)conditionInput.getChildAt(0)).setChecked(true);
        database = FirebaseDatabase.getInstance().getReference().child("Reports").child("QR");
    }

    /**
     * Runs on press of submit to submit report
     * @param v the current view
     */
    public void submitClick(View v) {
        Date date = new Date();
        double lat;
        double lng;
        boolean currentLoc = useCurrentLoc.isChecked();
        if (currentLoc) {
            lat = CurrentLocation.getInstance(this).getCurrentLat();
            lng = CurrentLocation.getInstance(this).getCurrentLng();
        } else {
            lat = Double.parseDouble(latInput.getText().toString());
            lng = Double.parseDouble(lngInput.getText().toString());
        }
        String cond = ((RadioButton)findViewById(conditionInput.getCheckedRadioButtonId())).getText().toString();
        int virusPPM = -1;
        int contaminantPPM = -1;
        try {
            virusPPM = Integer.parseInt(virusPPMInput.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            contaminantPPM = Integer.parseInt(contaminantPPMInput.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        GeneralUser cu = CurrentUser.getInstance().getCurrentUser();
        WaterQualityReport wqr = ValidationUtilities.tryCreateWQR(cu.getName(), lat, lng, cond, virusPPM, contaminantPPM, date);
        if (wqr == null) {
            Toast.makeText(getApplicationContext(), "Invalid Fields", Toast.LENGTH_LONG).show();
        } else {
            database.push().setValue(wqr);
            WSRDBHandler db = new WSRDBHandler(getApplicationContext());
            db.addWQRReport(wqr);
            Toast.makeText(getApplicationContext(), "Water Quality Report Submitted",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
