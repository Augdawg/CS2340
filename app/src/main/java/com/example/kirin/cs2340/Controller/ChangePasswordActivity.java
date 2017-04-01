package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.DBHandler;
import com.example.kirin.cs2340.Model.ForgotPassUser;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.R;

/**
 * Created by Kirin on 3/7/2017.
 * Activity when  user inputs a new password
 */
public class ChangePasswordActivity extends AppCompatActivity {

    private EditText newPass;

    /**
     * Runs on creation of activity
     * @param savedInstanceState any data passed into the activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        newPass = (EditText) findViewById(R.id.new_password);
    }

    /**
     * Executes the password change
     * @param v the current view
     */
    public void changePassword(View v) {
        if (newPass.getText() != null && !newPass.getText().toString().equals("")) {
            String currentUser = ForgotPassUser.getInstance().getUsername();
            DBHandler db = new DBHandler(getApplicationContext());
            GeneralUser g = db.getUserByUsername(currentUser);
            g.setPassword(newPass.getText().toString());
            db.addUser(g);
            Intent intent = new Intent(this, WelcomeActivity.class);
            Toast.makeText(getApplicationContext(), "Password changed", Toast.LENGTH_LONG).show();
            CurrentUser.getInstance().setCurrentUser(g);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show();
        }
    }
}
