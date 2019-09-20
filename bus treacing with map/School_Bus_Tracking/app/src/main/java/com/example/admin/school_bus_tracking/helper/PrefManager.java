package com.example.admin.school_bus_tracking.helper;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import com.example.admin.school_bus_tracking.Login;

import java.util.HashMap;

public class PrefManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "USER";

    // All Shared Preferences Keys


    public static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setUserDetail(String email, String password) {

        editor.putString(KEY_EMAIL, email);

        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }




    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);


    }


    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> profile = new HashMap<>();

        profile.put("email", pref.getString(KEY_EMAIL, ""));

        profile.put("password", pref.getString(KEY_PASSWORD, ""));
        return profile;
    }


    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }
}

