package com.example.kirin.cs2340.Controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.Admin;
import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.DBHandler;
import com.example.kirin.cs2340.Model.ForgotPassUser;
import com.example.kirin.cs2340.Model.GMailSender;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.Model.Manager;
import com.example.kirin.cs2340.Model.User;
import com.example.kirin.cs2340.Model.ValidationUtilities;
import com.example.kirin.cs2340.Model.Worker;
import com.example.kirin.cs2340.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.*;
/**
 * Created by Kirin on 2/14/2017.
 * Activity where user puts in login credentials
 */

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private final List<GeneralUser> users = new ArrayList<>();

    /**
     * creates login activity
     * @param savedInstanceState data passed into activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference().child("users");
        final DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("users");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                        if (child.child("accountType").getValue().equals("USER")) {
                            users.add(child.getValue(User.class));
                        } else if (child.child("accountType").getValue().equals("MANAGER")) {
                            users.add(child.getValue(Manager.class));
                        } else if (child.child("accountType").getValue().equals("WORKER")) {
                            users.add(child.getValue(Worker.class));
                        } else if (child.child("accountType").getValue().equals("ADMIN")) {
                            users.add(child.getValue(Admin.class));
                        }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /**
     * Logs in the user
     * @param view current view
     */
    public void loginPressed(View view) {
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();
        signIn(usernameInput, passwordInput);
        boolean valid = ValidationUtilities.loginFieldsAreValid(usernameInput, passwordInput);
        if (valid) {
//            DBHandler db = new DBHandler(getApplicationContext());
//            GeneralUser u = db.getUserByUsername(username.getText().toString());
//
//            if (u == null || !u.getPassword().equals(password.getText().toString())) {
//                Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
//            } else {
////                Intent intent = new Intent(this, WelcomeActivity.class);
////                startActivity(intent);
////                CurrentUser.getInstance().setCurrentUser(u);
//            }
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Fields", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Cancels login and finishes login
     * @param v current view
     */
    public void cancelPressed(View v) {
        finish();
    }

    /**
     * implements forgot password functionality
     * @param v current view
     */
    public void forgotPressed(View v) {
        if (username.getText() == null) {
            return;
        }
        GeneralUser user = null;
        for (GeneralUser u: users) {
            if (u.getEmail().equals(username.getText().toString())) {
                user = u;
                break;
            }
        }
        if (user != null) {
            try {
                String key;
                Random r = new Random(System.currentTimeMillis());
                key = Integer.toString(10000 + r.nextInt(20000));
                ForgotPassUser.getInstance().addPasswordCode(user.getUsername(), key);
                GMailSender sender = new GMailSender();
                sender.sendMail("Type in this code: " + key, sender.getUser(), user.getEmail());
                Intent intent = new Intent(this, ForgotPasswordActivity.class);
                ForgotPassUser.getInstance().setUsername(user.getUsername());
                startActivity(intent);
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
        }
        //DBHandler db = new DBHandler(getApplicationContext());
        //GeneralUser userNeeded = db.getUserByUsername(username.getText().toString());

//        if (username.getText() != null && userNeeded != null) {
//            try {
//                String key;
//                Random r = new Random(System.currentTimeMillis());
//                key = Integer.toString(10000 + r.nextInt(20000));
//                ForgotPassUser.getInstance().addPasswordCode(userNeeded.getUsername(), key);
//                GMailSender sender = new GMailSender();
//                sender.sendMail("Type in this code: " + key, sender.getUser(), userNeeded.getEmail());
//                Intent intent = new Intent(this, ForgotPasswordActivity.class);
//                ForgotPassUser.getInstance().setUsername(userNeeded.getUsername());
//                startActivity(intent);
//            } catch (Exception e) {
//                Log.e("SendMail", e.getMessage(), e);
//            }
//        } else {
//            Toast.makeText(getApplicationContext(), "Invalid Username", Toast.LENGTH_LONG).show();
//        }
    }
    private void signIn(String email, String password) {
        if(email.equals("") || password.equals("")) {
            Toast.makeText(getBaseContext(), "Email or Password Invalid", Toast.LENGTH_SHORT).show();
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Pickup", "signInWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            Toast.makeText(getBaseContext(), "Authentication succeeded",
                                    Toast.LENGTH_SHORT).show();
                            final FirebaseUser u = mAuth.getCurrentUser();
                            database.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    GeneralUser user = null;
                                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                                        if (child.getKey().equals(u.getUid())) {
                                            if (child.child("accountType").getValue().equals("USER")) {
                                                user = child.getValue(User.class);
                                            } else if (child.child("accountType").getValue().equals("MANAGER")) {
                                                user = child.getValue(Manager.class);
                                            } else if (child.child("accountType").getValue().equals("WORKER")) {
                                                user = child.getValue(Worker.class);
                                            } else if (child.child("accountType").getValue().equals("ADMIN")) {
                                                user = child.getValue(Admin.class);
                                            }
                                            break;
                                        }
                                    }
                                    CurrentUser.getInstance().setCurrentUser(user);
                                    Intent homePage = new Intent(getBaseContext(), WelcomeActivity.class);
                                    startActivity(homePage);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        } else {
                            Log.w("Pickup", "signInWithEmail:failed", task.getException());
                            Toast.makeText(getBaseContext(), "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}

