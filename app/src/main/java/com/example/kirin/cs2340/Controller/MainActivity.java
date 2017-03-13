package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kirin.cs2340.Model.DB.DBHandler;
import com.example.kirin.cs2340.R;
import com.example.kirin.cs2340.Model.*;

import java.util.List;

/**
 * Represents the login or register choice activity
 */
public class MainActivity extends AppCompatActivity {

    private Button loginButton;

    /**
     * creates activity
     * @param savedInstanceState data passed into activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button) findViewById(R.id.login);
    }

    /**
     * Migrates to LoginActivity if credentials correct
     * @param view current view
     */
    public void loginPressed(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        DBHandler db = new DBHandler(getApplicationContext());
        List<GeneralUser> users = db.getAllUsers();
        int x = 0;
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
