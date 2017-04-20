package com.example.kirin.cs2340.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.Toast;
import android.widget.RadioButton;

import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.DBHandler;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.Model.ValidationUtilities;
import com.example.kirin.cs2340.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Kirin on 2/19/2017.
 * Activity that allows users to register a new account
 */

public class RegistrationActivity extends AppCompatActivity {
    private EditText name;
    private EditText username;
    private EditText password;
    private EditText email;
    private EditText home_address;
    private EditText title;
    private RadioGroup acc_type;
    private FirebaseAuth mAuth;
    private DatabaseReference database;

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
        home_address = (EditText) findViewById(R.id.home_address);
        title = (EditText) findViewById(R.id.title);
        acc_type = (RadioGroup) findViewById(R.id.acc_type);
        ((RadioButton)acc_type.getChildAt(0)).setChecked(true);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference().child("users");
    }

    /**
     * Registers a new user given valid fields
     * @param view current view
     */
    public void registerClicked(View view) {
        String n = name.getText().toString();
        String u_name = username.getText().toString();
        String pass = password.getText().toString();
        String em = email.getText().toString();
        String home = home_address.getText().toString();
        String ti = title.getText().toString();
        String acc = ((RadioButton)findViewById(acc_type.getCheckedRadioButtonId())).getText().toString();
        final GeneralUser user = ValidationUtilities.registrationFieldsAreValid(n, u_name, pass, ti, em, home, acc);
        // register user to firebase and add their user values to the database
        mAuth.createUserWithEmailAndPassword(em, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && user != null) {
                            FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                            Toast.makeText(getBaseContext(), "New user registered", Toast.LENGTH_SHORT).show();
                            database.child(u.getUid()).setValue(user);
                            CurrentUser.getInstance().setCurrentUser(user);
                            Intent intent = new Intent(getBaseContext(), WelcomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getBaseContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        if (user != null) {
//            CurrentUser.getInstance().setCurrentUser(user);
            DBHandler db = new DBHandler(getApplicationContext());
            db.addUser(user);
//            Intent intent = new Intent(this, WelcomeActivity.class);
//            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid fields", Toast.LENGTH_LONG).show();
        }
    }
}
