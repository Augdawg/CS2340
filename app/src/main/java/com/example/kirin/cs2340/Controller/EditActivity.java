package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.DBHandler;
import com.example.kirin.cs2340.R;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Kirin on 2/19/2017.
 * Activity that edits profile information
 */

public class EditActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText home;
    private EditText title;

    /**
     * Creates edit activity
     * @param savedInstanceState data passed into activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        home = (EditText) findViewById(R.id.home_address);
        title = (EditText) findViewById(R.id.title);
        GeneralUser cu = CurrentUser.getInstance().getCurrentUser();
        name.setText(cu.getName());
        email.setText(cu.getEmail());
        home.setText(cu.getHome());
        title.setText(cu.getTitle());
        TextView accType = (TextView) findViewById(R.id.acc_type);
        accType.setText(cu.getAccountType());
    }

    /**
     * Saves edited data
     * @param v current view
     */
    public void savePressed(View v) {
        final GeneralUser cu = CurrentUser.getInstance().getCurrentUser();
        cu.setName(name.getText().toString());
        FirebaseAuth.getInstance().getCurrentUser().updateEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    cu.setEmail(email.getText().toString());
                    Toast.makeText(getBaseContext(), "Email change successfull", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Email change unsuccesfull", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cu.setHome(home.getText().toString());
        cu.setTitle(title.getText().toString());
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("users");
        database.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(cu);
        //DBHandler db = new DBHandler(getApplicationContext());
        //db.addUser(cu);
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
