package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.User;
import com.example.kirin.cs2340.R;

/**
 * Created by Kirin on 2/19/2017.
 */

public class EditActivity extends AppCompatActivity {
    private EditText email;
    private EditText home;
    private EditText title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        email = (EditText) findViewById(R.id.email);
        home = (EditText) findViewById(R.id.homeaddress);
        title = (EditText) findViewById(R.id.title);
        User cu = CurrentUser.getInstance().getCurrentUser();
        email.setText(cu.getEmail());
        home.setText(cu.getHome());
        title.setText(cu.getTitle());
    }

    public void savePressed(View v) {
        User cu = CurrentUser.getInstance().getCurrentUser();
        cu.setEmail(email.getText().toString());
        cu.setHome(home.getText().toString());
        cu.setTitle(title.getText().toString());
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
