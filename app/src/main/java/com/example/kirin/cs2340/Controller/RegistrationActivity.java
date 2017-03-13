package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.Toast;
import android.widget.RadioButton;

import com.example.kirin.cs2340.Model.Admin;
import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.DBHandler;
import com.example.kirin.cs2340.Model.Manager;
import com.example.kirin.cs2340.Model.User;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.Model.Worker;
import com.example.kirin.cs2340.R;

/**
 * Created by Kirin on 2/19/2017.
 * Activity that allows users to register a new account
 */

public class RegistrationActivity extends AppCompatActivity {
    private EditText name;
    private EditText username;
    private EditText password;
    private EditText email;
    private EditText homeaddress;
    private EditText title;
    private RadioGroup acctype;

    /**
     * Creates Activity
     * @param savedInstanceState bundle data transfer
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        homeaddress = (EditText) findViewById(R.id.homeaddress);
        title = (EditText) findViewById(R.id.title);
        acctype = (RadioGroup) findViewById(R.id.acctype);
    }

    /**
     * Registers a new user given valid fields
     * @param view current view
     */
    public void registerClicked(View view) {
        String n = name.getText().toString();
        String uname = username.getText().toString();
        String pass = password.getText().toString();
        if (uname.trim().equals("") || pass.trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Invalid login credentials", Toast.LENGTH_SHORT).show();
        } else {
            String em = email.getText().toString();
            String home = homeaddress.getText().toString();
            String ti = title.getText().toString();
            int checked = acctype.getCheckedRadioButtonId();
            if (checked == -1) {
                Toast.makeText(getApplicationContext(), "Select an Account Type", Toast.LENGTH_SHORT).show();
            } else {
                GeneralUser u;
                String type = ((RadioButton)findViewById(checked)).getText().toString();
                if (type.equals("USER")) {
                    u = new User(n, uname, pass, em, home, ti);
                } else if (type.equals("WORKER")) {
                    u = new Worker(n, uname, pass, em, home, ti);
                } else if (type.equals("MANAGER")) {
                    u = new Manager(n, uname, pass, em, home, ti);
                } else {
                    u = new Admin(n, uname, pass, em, home, ti);
                }
                CurrentUser.getInstance().setCurrentUser(u);
                DBHandler db = new DBHandler(getApplicationContext());
                db.addUser(u);
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
            }
        }
    }
}
