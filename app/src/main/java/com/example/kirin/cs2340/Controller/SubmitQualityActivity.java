package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.WSRDBHandler;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.Model.OverallWaterCondition;
import com.example.kirin.cs2340.Model.WaterQualityReport;
import com.example.kirin.cs2340.R;

import java.util.Date;
import java.util.List;

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

    /**
     * Creates the Water Quality Report submission activity
     * @param savedInstanceState data passed to activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_quality);
        latInput = (EditText) findViewById(R.id.latInput);
        lngInput = (EditText) findViewById(longInput);
        conditionInput = (RadioGroup) findViewById(R.id.conditionInput);
        virusPPMInput = (EditText) findViewById(R.id.virusPPM);
        contaminantPPMInput = (EditText) findViewById(R.id.contaminantPPM);
    }

    /**
     * Runs on press of submit to submit report
     * @param v the current view
     */
    public void submitClick(View v) {
        Date date = new Date();
        double lat = Double.parseDouble(latInput.getText().toString());
        double longi = Double.parseDouble(lngInput.getText().toString());
        String cond = ((RadioButton)findViewById(conditionInput.getCheckedRadioButtonId())).getText().toString();
        int virusPPM = Integer.parseInt(virusPPMInput.getText().toString());
        int contaminantPPM = Integer.parseInt(contaminantPPMInput.getText().toString());
        OverallWaterCondition condition = OverallWaterCondition.valueOf(cond.toUpperCase());

        GeneralUser cu = CurrentUser.getInstance().getCurrentUser();
        WaterQualityReport wqr = new WaterQualityReport(cu.getName(), lat, longi, condition, virusPPM, contaminantPPM, date);

        WSRDBHandler db = new WSRDBHandler(getApplicationContext());
        db.addWQRReport(wqr);
        Toast.makeText(getApplicationContext(), "Water Quality Report Submitted",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
