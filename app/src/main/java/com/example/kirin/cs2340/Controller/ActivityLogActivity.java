package com.example.kirin.cs2340.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kirin.cs2340.R;

public class ActivityLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        setTitle("Activity Log");
    }
}
