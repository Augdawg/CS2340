package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.AccountType;
import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.User;
import com.example.kirin.cs2340.Model.Users;
import com.example.kirin.cs2340.R;

import java.util.Calendar;

/**
 * Created by Kirin on 2/19/2017.
 */

public class RegistrationActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText email;
    private EditText homeaddress;
    private EditText title;
    private RadioGroup acctype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        homeaddress = (EditText) findViewById(R.id.homeaddress);
        title = (EditText) findViewById(R.id.title);
        acctype = (RadioGroup) findViewById(R.id.acctype);
    }

    public void registerClicked(View view) {
        String uname = username.getText().toString();
        String pass = password.getText().toString();
        if (uname.trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Invalid Username", Toast.LENGTH_SHORT).show();
        } else {
            String em = email.getText().toString();
            String home = homeaddress.getText().toString();
            String ti = title.getText().toString();
            int checked = acctype.getCheckedRadioButtonId();
            if (checked == -1) {
                Toast.makeText(getApplicationContext(), "Select an Account Type", Toast.LENGTH_SHORT).show();
            } else {
                AccountType acc = AccountType.valueOf(((RadioButton)findViewById(checked)).getText().toString());
                long id = Calendar.getInstance().getTimeInMillis() / 1000;
                User u = new User(id, uname, pass, em, home, ti, acc);
                Users.getInstance().addUser(u);
                CurrentUser.getInstance().setCurrentUser(u);
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
            }
        }
    }
}
