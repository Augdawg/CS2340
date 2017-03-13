package com.example.kirin.cs2340.Model.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kirin.cs2340.Model.Admin;
import com.example.kirin.cs2340.Model.GeneralUser;
import com.example.kirin.cs2340.Model.Manager;
import com.example.kirin.cs2340.Model.User;
import com.example.kirin.cs2340.Model.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirin on 3/8/2017.
 * Database that holds all accounts
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DB_NAME = "DBAccounts";
    private static final String TABLE_ACCOUNTS = "AccountsTable";
    private static final String ACCOUNTS_ID = "id";
    private static final String ACCOUNTS_NAME = "name";
    private static final String ACCOUNTS_USERNAME = "username";
    private static final String ACCOUNTS_PASSWORD = "password";
    private static final String ACCOUNTS_EMAIL = "email";
    private static final String ACCOUNTS_HOME = "home";
    private static final String ACCOUNTS_TITLE = "title";
    private static final String ACCOUNTS_TYPE = "type";

    /**
     * Constructor for account database handler
     * @param context current context of the application
     */
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    /**
     * Runs when database is created to create tables
     * @param db the db created
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS
                + "(" + ACCOUNTS_ID + " INTEGER PRIMARY KEY," + ACCOUNTS_NAME
                + " TEXT," + ACCOUNTS_USERNAME + " TEXT," + ACCOUNTS_PASSWORD + " TEXT,"
                + ACCOUNTS_EMAIL + " TEXT," + ACCOUNTS_HOME + " HOME," + ACCOUNTS_TITLE + " TEXT,"
                + ACCOUNTS_TYPE + " TEXT)";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    /**
     * Runs when db version is incremented (upgraded)
     * @param db current db
     * @param oldVersion old version no.
     * @param newVersion new version no.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        onCreate(db);
    }

    /**
     * Adds a user to the db
     * @param user the user to be added
     */
    public void addUser(GeneralUser user) {
        deleteUserByUsername(user.getUsername());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACCOUNTS_ID, user.getId());
        values.put(ACCOUNTS_NAME, user.getName());
        values.put(ACCOUNTS_USERNAME, user.getUsername());
        values.put(ACCOUNTS_PASSWORD, user.getPassword());
        values.put(ACCOUNTS_EMAIL, user.getEmail());
        values.put(ACCOUNTS_HOME, user.getHome());
        values.put(ACCOUNTS_TITLE, user.getTitle());
        values.put(ACCOUNTS_TYPE, user.getAccountType());


        db.insert(TABLE_ACCOUNTS, null, values);

        db.close();
    }

    /**
     * Gets all users from db
     * @return list of all users from db
     */
    public List<GeneralUser> getAllUsers() {
        List<GeneralUser> users = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ACCOUNTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            GeneralUser user;
            do {
                if (cursor.getString(7).equals("USER")) {
                    user = new User();
                } else if (cursor.getString(7).equals("WORKER")) {
                    user = new Worker();
                } else if (cursor.getString(7).equals("MANAGER")) {
                    user = new Manager();
                } else {
                    user = new Admin();
                }
                user.setName(cursor.getString(1));
                user.setUsername(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setEmail(cursor.getString(4));
                user.setHome(cursor.getString(5));
                user.setTitle(cursor.getString(6));
                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }

    /**
     * Gets a user by passed in username
     * @param username the username to be checked against
     * @return user that has username of parameter
     */
    public GeneralUser getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] {ACCOUNTS_ID, ACCOUNTS_NAME, ACCOUNTS_USERNAME,
                ACCOUNTS_PASSWORD, ACCOUNTS_EMAIL, ACCOUNTS_HOME, ACCOUNTS_TITLE, ACCOUNTS_TYPE},
                ACCOUNTS_USERNAME + "=?", new String[] {username}, null, null, null, null);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            GeneralUser user;
            String name = cursor.getString(1);
            String usern = cursor.getString(2);
            String pass = cursor.getString(3);
            String email = cursor.getString(4);
            String home = cursor.getString(5);
            String title = cursor.getString(6);
            if (cursor.getString(7).equals("USER")) {
                user = new User(name, usern, pass, email, home, title);
            } else if (cursor.getString(7).equals("WORKER")) {
                user = new Worker(name, usern, pass, email, home, title);
            } else if (cursor.getString(7).equals("MANAGER")) {
                user = new Manager(name, usern, pass, email, home, title);
            } else {
                user = new Admin(name, usern, pass, email, home, title);
            }
            return user;
        }
        return null;
    }

    /**
     * Deletes a user by passed in username
     * @param username username of user to be deleted
     */
    public void deleteUserByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNTS, ACCOUNTS_USERNAME + " = ?", new String[]{username});
        db.close();
    }
}
