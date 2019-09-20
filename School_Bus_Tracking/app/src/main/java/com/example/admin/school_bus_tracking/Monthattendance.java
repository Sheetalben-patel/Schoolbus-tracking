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

import java.util.Calendar;
import java.util.HashMap;

public class Monthattendance extends AppCompatActivity implements View.OnClickListener {

    public static final String REGISTER_URL = "http://172.16.0.170/schoolbustracking/year_attandance.php?fname=";

    PrefManager pref;
    Context context;
    //private EditText editTextdate;


    private Button buttonRegister;

    TextView Attedance_status,editTextdate;

    public static final String KEY_PRESENT = "present";
    public static final String JSON_ARRAY = "result";

    private String jsonResponse;
    private String jsonResponse1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthattendance);
        context =Monthattendance.this;
        pref = new PrefManager(this);

        editTextdate = (TextView) findViewById(R.id.Month);

        buttonRegister = (Button) findViewById(R.id.St_enter);

        Attedance_status= (TextView) findViewById(R.id.Show_attendence);
        buttonRegister.setOnClickListener(this);

       HashMap<String, String> user=  pref.getUserDetails();
        String fname = user.get(pref.KEY_EMAIL);

        Attedance_status.setText(fname);

    }

    private void AddData() {


        HashMap<String, String> user=  pref.getUserDetails();
        String fname = user.get(pref.KEY_EMAIL);

       // String month=editTextdate.getText().toString().trim();



        String url = REGISTER_URL +fname;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Monthattendance.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String present="";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            present= collegeData.getString("present");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Attedance_status.setText("Present:\t"+present);
    }








    @Override
    public void onClick(View v) {

        if (v ==  buttonRegister) {
            AddData();
        }




    }





}
