package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.kirin.cs2340.R;

/**
 * Represents the login or register choice activity
 */
public class MainActivity extends AppCompatActivity {

    /**
     * creates activity
     * @param savedInstanceState data passed into activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Migrates to LoginActivity if credentials correct
     * @param view current view
     */
    public void loginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Migrates to RegisterActivity
     * @param view current view
     */
    public void registerPressed(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
