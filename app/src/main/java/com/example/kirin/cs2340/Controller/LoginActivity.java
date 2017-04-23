package com.example.kirin.cs2340.Controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.ActivityLog;
import com.example.kirin.cs2340.Model.Admin;
import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.DB.DBHandler;
import com.example.kirin.cs2340.Model.ForgotPassUser;
import com.example.kirin.cs2340.Model.GMailSender;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.Model.LogType;
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
    private final DatabaseReference database=  FirebaseDatabase.getInstance().getReference().child("users");
    private String prevAttemp;
    private int attemptCntr = 0;
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
    private void signIn(final String email, String password) {
        attemptCntr++;
        if (prevAttemp == null || !prevAttemp.equals(email)) {
            prevAttemp = email;
            attemptCntr = 1;
        }
        if(email.equals("") || password.equals("")) {
            Toast.makeText(getBaseContext(), "Email or Password Invalid", Toast.LENGTH_SHORT).show();
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        final ActivityLog log = new ActivityLog();
                        log.setId1(email.hashCode());
                        log.setType(LogType.LOGIN);
                        Log.d("Pickup", "signInWithEmail:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        final FirebaseUser u = mAuth.getCurrentUser();
                        if (task.isSuccessful()) {
                                    GeneralUser user = null;
                                    for(GeneralUser gu: users) {
                                        if(gu.getEmail().equals(email)) {
                                            user = gu;
                                        }
                                    }
                                    if (user != null && user.getBanned()) {
                                        Toast.makeText(getBaseContext(), "Account is banned",
                                                Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    if (user != null && user.getBlocked()) {
                                        Toast.makeText(getBaseContext(), "Account is blocked",
                                                Toast.LENGTH_SHORT).show();
                                        return;
                                    } else if (user != null){
                                        Toast.makeText(getBaseContext(), "Login succeeded",
                                                Toast.LENGTH_SHORT).show();
                                        log.setStatus("Success");
                                        CurrentUser.getInstance().setCurrentUser(user);
                                        log.setId1(user.getId());
                                        database.getRoot().child("Security Log").push().setValue(log);
                                        Intent homePage = new Intent(getBaseContext(), WelcomeActivity.class);
                                        startActivity(homePage);
                                    } else {
                                        Toast.makeText(getBaseContext(), "Login failed, unknown reason",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                            String status = "Invalid Email";
                            Log.w("Pickup", "signInWithEmail:failed", task.getException());
                            GeneralUser aUser = null;
                            for (GeneralUser temp: users) {
                                if (temp.getEmail().equals(email)) {
                                    status = "Incorrect Password";
                                    log.setId1(temp.getId());
                                    aUser = temp;
                                }
                            }
                            if(aUser != null && attemptCntr == 4 && !(aUser instanceof Admin)) {
                                aUser.setBlocked(true);
                                database.child(aUser.getUID()).setValue(aUser);
                                Toast.makeText(getBaseContext(), "Too many login attempts\nAccount blocked",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            } else if (aUser != null && aUser.getBanned()) {
                                Toast.makeText(getBaseContext(), "Account is banned",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }else if (aUser != null && aUser.getBlocked()) {
                                Toast.makeText(getBaseContext(), "Account is blocked",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                            log.setStatus(status);
                            database.getRoot().child("Security Log").push().setValue(log).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getBaseContext(), "Login failed",
                                            Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            });
                        }
                    }
                });
    }

}

