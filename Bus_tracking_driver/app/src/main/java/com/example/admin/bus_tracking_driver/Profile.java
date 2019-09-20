package com.example.admin.bus_tracking_driver;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    AppLocationService appLocationService;

    PrefManager pref;
    Context getContext;

   // public static final String REGISTER_URL = "http://pramukh.esy.es/location.php";
   public static final String REGISTER_URL = "http://192.168.0.105/schoolbustracking/location.php";


    public static final String KEY_PHONE = "phone";
    public static final String KEY_BUSNO = "busno";
    public static final String KEY_ADDRESS = "locationAddress";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        appLocationService = new AppLocationService(Profile.this);

        getContext = Profile.this;
        pref = new PrefManager(this);

        getdata();

    }
    public void getdata(){

        Location location = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);

        //you can hard-code the lat & long if you have issues with getting it
        //remove the below if-condition and use the following couple of lines
        //double latitude = 37.422005;
        //double longitude = -122.084095

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LocationAddress locationAddress = new LocationAddress();
            locationAddress.getAddressFromLocation(latitude, longitude,
                    getApplicationContext(), new GeocoderHandler());




        } else {
            showSettingsAlert();
        }

    }


    public void showSettingsAlert() {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(
                Profile.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        Profile.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }


    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }

            fet(locationAddress);

        }


    }

    public void fet(final String locationAddress) {

        HashMap<String, String> user = pref.getUserDetails();
        final String phone = user.get(pref.KEY_PHONE);
        final String busno = user.get(pref.KEY_BUSNO);

        //String url = urlJsonArry +women_id ;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                        Log.v("response 1","#"+response);
                        openProfile();*/


                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String message = jsonObject.getString("message");
                            String code = jsonObject.getString("code");
                            if (code.equalsIgnoreCase("success")) {
                                //women_id = jsonObject.getString("women_id");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();


                            } else if (code.equalsIgnoreCase("failed")) {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Response Error !!", Toast.LENGTH_LONG).show();
                            //dialog.dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Error 1","#"+error.toString());
                        Toast.makeText(Profile.this, error.toString(), Toast.LENGTH_LONG).show();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();



                params.put(KEY_PHONE,phone);

                params.put(KEY_BUSNO,busno);
                params.put(KEY_ADDRESS,locationAddress);




                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

}






