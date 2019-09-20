package com.example.admin.bus_tracking_driver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;



import java.util.HashMap;


/**
 * Created by Admin on 22/03/2017.
 */
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

    public static final String KEY_PHONE = "phone";
    public static final String KEY_BUSNO = "busno";



    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setUserDetail(String phone, String busno) {

        editor.putString(KEY_PHONE,phone);

        editor.putString(KEY_BUSNO, busno);

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
        Intent i = new Intent(_context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);


    }


    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> profile = new HashMap<>();

        profile.put("phone", pref.getString(KEY_PHONE, ""));

        profile.put("busno", pref.getString(KEY_BUSNO, ""));

        return profile;
    }


    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }
}
