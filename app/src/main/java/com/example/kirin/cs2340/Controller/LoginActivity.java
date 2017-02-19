package com.example.kirin.cs2340.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.User;
import com.example.kirin.cs2340.Model.Users;
import com.example.kirin.cs2340.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kirin on 2/14/2017.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

    }

    public void loginpressed(View view) {
        HashMap users = Users.getInstance().getUsers();
        User u = (User)users.get(username.getText().toString());
        if (u == null || !u.getPassword().equals(password.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();;
        } else {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            CurrentUser.getInstance().setCurrentUser(u);
        }
    }

    public void cancelpressed(View v) {
        finish();
    }
}
