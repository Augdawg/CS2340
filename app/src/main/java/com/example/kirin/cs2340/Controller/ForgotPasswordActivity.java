package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.ForgotPassUser;
import com.example.kirin.cs2340.Model.Users;
import com.example.kirin.cs2340.R;

/**
 * Created by Kirin on 3/7/2017.
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    private String username;
    private EditText givenCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        givenCode = (EditText) findViewById(R.id.code);
    }

    public void checkCode(View view) {
        ForgotPassUser user = ForgotPassUser.getInstance();
        if (Users.getInstance().getPasswordCodes().get(user.getUsername()).equals(givenCode.getText().toString())) {
            Intent intent = new Intent(this, ChangePasswordActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid code", Toast.LENGTH_LONG).show();
        }
    }
}