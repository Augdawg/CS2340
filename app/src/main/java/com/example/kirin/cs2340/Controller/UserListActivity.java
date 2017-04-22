package com.example.kirin.cs2340.Controller;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kirin.cs2340.Model.ActivityLog;
import com.example.kirin.cs2340.Model.Admin;
import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.Model.LogType;
import com.example.kirin.cs2340.Model.Manager;
import com.example.kirin.cs2340.Model.User;
import com.example.kirin.cs2340.Model.UserArrayAdapter;
import com.example.kirin.cs2340.Model.Worker;
import com.example.kirin.cs2340.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private List<GeneralUser> users;
    private DatabaseReference database;
    private ListView lv;
    private UserArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setTitle("User List");
        users = new ArrayList<>();
        lv = (ListView) findViewById(R.id.userList);
        adapter = new UserArrayAdapter(this, R.layout.user_list_item);
        database = FirebaseDatabase.getInstance().getReference().child("users");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    if (!child.child("email").equals(CurrentUser.getInstance().getCurrentUser().getEmail())) {
                        if (child.child("accountType").getValue().equals("USER")) {
                            adapter.add(child.getValue(User.class));
                        } else if (child.child("accountType").getValue().equals("MANAGER")) {
                            adapter.add(child.getValue(Manager.class));
                        } else if (child.child("accountType").getValue().equals("WORKER")) {
                            adapter.add(child.getValue(Worker.class));
                        } else if (child.child("accountType").getValue().equals("ADMIN")) {
                            adapter.add(child.getValue(Admin.class));
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        lv.setAdapter(adapter);
        lv.setLongClickable(true);
        lv.setClickable(true);
        final UserListActivity here = this;
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(here);
                final GeneralUser user = (GeneralUser) parent.getItemAtPosition(position);
                builder.setTitle("Admin Action");
                builder.setMessage("Choose an action");
                builder.setPositiveButton("Ban",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                adapter.remove(user);
                                user.setBanned(true);
                                database.child(user.getUID()).setValue(user);
                                ActivityLog log = new ActivityLog();
                                log.setId1(CurrentUser.getInstance().getCurrentUser().getId());
                                log.setId2(user.getId());
                                log.setType(LogType.USER_BAN);
                                database.getRoot().child("Security Log").push().setValue(log);
                                dialog.cancel();
                            }
                        });

                builder.setNeutralButton("Unblock",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                adapter.remove(user);
                                user.setBlocked(false);
                                database.child(user.getUID()).setValue(user);
                                ActivityLog log = new ActivityLog();
                                log.setId1(CurrentUser.getInstance().getCurrentUser().getId());
                                log.setId2(user.getId());
                                log.setType(LogType.UNBLOCK_ACCOUNT);
                                database.getRoot().child("Security Log").push().setValue(log);
                                dialog.cancel();
                            }
                        });

                builder.setNegativeButton("Delete",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                ActivityLog log = new ActivityLog();
                                log.setId1(CurrentUser.getInstance().getCurrentUser().getId());
                                log.setId2(user.getId());
                                log.setType(LogType.ACCOUNT_DELETE);
                                database.getRoot().child("Security Log").push().setValue(log);
                                dialog.cancel();
                            }
                        });
                builder.create().show();
                return true;
            }
        });
    }
}
