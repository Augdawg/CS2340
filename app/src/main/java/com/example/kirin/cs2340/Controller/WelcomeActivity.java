package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.R;

import org.w3c.dom.Text;

/**
 * Created by Kirin on 2/14/2017.
 * WelcomeActivity Controller
 */

public class WelcomeActivity extends AppCompatActivity {

    private TextView text;

    /**
     * Creates Activity
     * @param savedInstanceState bundle data transfer
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        text = (TextView) findViewById(R.id.welcometext);
        text.setText("Welcome " + CurrentUser.getInstance().getCurrentUser().getUsername() + "!");
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
     * @param v current view
     */
    public void logoutPressed(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * Opens edit activity to edit profile
     * @param v current view
     */
    public void editPressed(View v) {
        Intent intent = new Intent(this,EditActivity.class);
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
}
