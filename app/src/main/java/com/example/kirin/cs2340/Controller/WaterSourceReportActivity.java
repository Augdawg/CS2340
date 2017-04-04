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
import com.example.kirin.cs2340.Model.ValidationUtilities;
import com.example.kirin.cs2340.Model.WaterSourceReport;
import com.example.kirin.cs2340.R;

import java.util.Date;

/**
 * Created by August Wagner
 * Activity to submit water source report
 */

public class WaterSourceReportActivity extends AppCompatActivity {

    private EditText latInput;
    private EditText longInput;
    private RadioGroup typeInput;
    private RadioGroup conditionInput;
    /**
     * Creates Water Source Report submitting activity
     * @param savedInstanceState bundle data transfer
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_source_report);
        latInput = (EditText) findViewById(R.id.latInput);
        longInput = (EditText) findViewById(R.id.longInput);
        typeInput = (RadioGroup) findViewById(R.id.typeInput);
        conditionInput = (RadioGroup) findViewById(R.id.conditionInput);
        ((RadioButton)typeInput.getChildAt(0)).setChecked(true);
        ((RadioButton)conditionInput.getChildAt(0)).setChecked(true);
    }

    /**
     * submits water source report
     * @param view the current view
     *           
     */
    public void submitClick(View view) {
        Date date = new Date();
        double lat = Double.parseDouble(latInput.getText().toString());
        double lng = Double.parseDouble(longInput.getText().toString());
        String type = ((RadioButton)findViewById(typeInput.getCheckedRadioButtonId())).getText().toString();
        String condition = ((RadioButton)findViewById(conditionInput.getCheckedRadioButtonId())).getText().toString();
        GeneralUser u = CurrentUser.getInstance().getCurrentUser();

        WaterSourceReport wsr = ValidationUtilities.tryCreateWSR(u.getName(), lat, lng, type, condition, date);

        if (wsr == null) {
            Toast.makeText(getApplicationContext(), "Invalid Fields", Toast.LENGTH_LONG);
        } else {
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
