package com.example.kirin.cs2340.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.DBHandler;
import com.example.kirin.cs2340.Model.ForgotPassUser;
import com.example.kirin.cs2340.Model.GMailSender;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.R;
import java.util.*;
/**
 * Created by Kirin on 2/14/2017.
 * Activity where user puts in login credentials
 */

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;

    /**
     * creates login activity
     * @param savedInstanceState data passed into activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    /**
     * Logs in the user
     * @param view current view
     */
    public void loginPressed(View view) {
        DBHandler db = new DBHandler(getApplicationContext());
        GeneralUser u = db.getUserByUsername(username.getText().toString());
        List<GeneralUser> users = db.getAllUsers();

        if (u == null || !u.getPassword().equals(password.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            CurrentUser.getInstance().setCurrentUser(u);
        }
    }

    /**
     * Cancels login and finishes login
     * @param v current view
     */
    public void cancelPressed(View v) {
        finish();
    }

    /**
     * implements forgot password functionality
     * @param v current view
     */
    public void forgotPressed(View v) {
        DBHandler db = new DBHandler(getApplicationContext());
        GeneralUser userNeeded = db.getUserByUsername(username.getText().toString());
        if (username.getText() != null && userNeeded != null) {
            try {
                String key;
                Random r = new Random(System.currentTimeMillis());
                key = Integer.toString(10000 + r.nextInt(20000));
                ForgotPassUser.getInstance().addPasswordCode(userNeeded.getUsername(), key);
                GMailSender sender = new GMailSender("waterdonotreply@yahoo.com", "water12345");
                sender.sendMail("Water Password Reset", "Type in this code: " + key, sender.getUser(), userNeeded.getEmail());
                Intent intent = new Intent(this, ForgotPasswordActivity.class);
                ForgotPassUser.getInstance().setUsername(userNeeded.getUsername());
                startActivity(intent);
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);

            }
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Username", Toast.LENGTH_LONG);
        }
    }
}
