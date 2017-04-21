package com.example.kirin.cs2340.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kirin.cs2340.R;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setTitle("User List");
    }
}
