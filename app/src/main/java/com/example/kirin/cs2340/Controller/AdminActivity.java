package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kirin.cs2340.R;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setTitle("Admin page");
        list = new ArrayList<>();
        list.add("User List");
        list.add("Security Log");
        ListView lv = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = adapter.getItem(position);
                if (item.equals("User List")) {
                    Intent intent = new Intent(getBaseContext(), UserListActivity.class);
                    startActivity(intent);
                } else if (item.equals("Security Log")) {
                    Intent intent = new Intent(getBaseContext(), ActivityLogActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Invalid item clicked?", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lv.setAdapter(adapter);
    }

}
