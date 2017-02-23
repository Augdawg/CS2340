package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kirin.cs2340.R;

/**
 * MainActivity Controller
 */
public class MainActivity extends AppCompatActivity {

    private Button loginButton;

    /**
     * creates activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button) findViewById(R.id.login);
    }

    /**
     * Migrates to LoginActivity
     * @param view
     */
    public void loginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Migrates to RegisterActivity
     * @param view
     */
    public void registerPressed(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
