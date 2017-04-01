package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.ForgotPassUser;
import com.example.kirin.cs2340.R;

/**
 * Created by Kirin on 3/7/2017.
 * Activity that asks for email code to validate user
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText givenCode;

    /**
     * Runs on creation of activity
     * @param savedInstanceState data passed in
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        givenCode = (EditText) findViewById(R.id.code);
    }

    /**
     * Checks that email code user inputs is valid
     * @param view current view
     */
    public void checkCode(View view) {
        ForgotPassUser user = ForgotPassUser.getInstance();
        if (ForgotPassUser.getInstance().getPasswordCodes().get(user.getUsername()).equals(givenCode.getText().toString())) {
            Intent intent = new Intent(this, ChangePasswordActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid code", Toast.LENGTH_LONG).show();
        }
    }
}