package com.example.admin.school_bus_tracking;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.school_bus_tracking.helper.PrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dateaatendance extends AppCompatActivity implements View.OnClickListener{

    public static final String REGISTER_URL = "http://172.16.0.170//schoolbustracking/year_attandance.php?fname=&date=";




    PrefManager pref;
    Context context;
    private EditText editTextdate;

    private int mYear, mMonth, mDay;
    private Button buttonRegister;

    TextView Attedance_status;

    public static final String KEY_PRESENT = "present";
    public static final String JSON_ARRAY = "result";

    private String jsonResponse;
    private String jsonResponse1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dateaatendance);

        context =Dateaatendance.this;
        pref = new PrefManager(this);

        editTextdate = (EditText) findViewById(R.id.dob);

        buttonRegister = (Button) findViewById(R.id.St_enter);

        Attedance_status= (TextView) findViewById(R.id.Show_attendence);
        buttonRegister.setOnClickListener(this);
        editTextdate.setOnClickListener(this);




    }

    private void AddData() {


        HashMap<String, String> user=  pref.getUserDetails();
        String fname = user.get(pref.KEY_EMAIL);

        String date=editTextdate.getText().toString().trim();



        String url = REGISTER_URL +fname+date;

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d(TAG, response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            //jsonResponse1 = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject attedence = (JSONObject) response.get(i);

                                String present = attedence.getString("present");
                               // String fname = attedence.getString("fname");


                                jsonResponse += "Present: " + present;
                               // jsonResponse += "Fname: " + fname;

                            }

                            Attedance_status.setText(jsonResponse);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        //hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                //hidepDialog();
            }
        });

        // Adding request to request queue
        //Config.getInstance().addToRequestQueue(req);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
        //Config.getmInstance(getApplicationContext()).addReq(req);
    }






    public void date() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(Dateaatendance.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        String month = "";
                        if ((monthOfYear + 1) < 10)
                            month = "0" + (monthOfYear + 1);
                        else
                            month = String.valueOf((monthOfYear + 1));

                        String day = "";
                        if (dayOfMonth < 10)
                            day = "0" + dayOfMonth;
                        else
                            day = String.valueOf(dayOfMonth);

                        editTextdate.setText(day + "/"
                                + (month) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();

    }


    @Override
    public void onClick(View v) {

        if (v ==  buttonRegister) {
            AddData();
        }



        if (v == editTextdate) {
            date();
        }
    }





}
