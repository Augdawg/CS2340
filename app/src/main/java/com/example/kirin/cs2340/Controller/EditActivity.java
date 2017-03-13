package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.DBHandler;
import com.example.kirin.cs2340.Model.User;
import com.example.kirin.cs2340.R;
import com.example.kirin.cs2340.Model.GeneralUser;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Kirin on 2/19/2017.
 * Activity that edits profile information
 */

public class EditActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText home;
    private EditText title;
    private TextView accType;

    /**
     * Creates edit activity
     * @param savedInstanceState data passed into activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        home = (EditText) findViewById(R.id.homeaddress);
        title = (EditText) findViewById(R.id.title);
        accType = (TextView) findViewById(R.id.acctype);
        GeneralUser cu = CurrentUser.getInstance().getCurrentUser();
        name.setText(cu.getName());
        email.setText(cu.getEmail());
        home.setText(cu.getHome());
        title.setText(cu.getTitle());
        accType.setText(cu.getAccountType());
    }

    /**
     * Saves edited data
     * @param v current view
     */
    public void savePressed(View v) {
        GeneralUser cu = CurrentUser.getInstance().getCurrentUser();
        cu.setName(name.getText().toString());
        cu.setEmail(email.getText().toString());
        cu.setHome(home.getText().toString());
        cu.setTitle(title.getText().toString());
        DBHandler db = new DBHandler(getApplicationContext());
        db.addUser(cu);
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        List<GeneralUser> users = db.getAllUsers();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
