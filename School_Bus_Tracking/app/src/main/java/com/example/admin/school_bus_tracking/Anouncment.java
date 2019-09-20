package com.example.admin.school_bus_tracking;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.school_bus_tracking.helper.CardAdapter;
import com.example.admin.school_bus_tracking.helper.PrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Anouncment extends AppCompatActivity {
    //Creating a List of superheroes
    private List<SuperHeroes> listSuperHeroes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    public static final String DATA_URL = "http://172.16.0.170/schoolbustracking/android_ann.php";
    public static final String TAG_ID = "id";
    public static final String TAG_DATE = "date";
    public static final String TAG_NAME = "announcement";


    PrefManager pref;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anouncment);

        context = Anouncment.this;
        pref = new PrefManager(this);


        //Initializing Views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Initializing our superheroes list
        listSuperHeroes = new ArrayList<>();

        getData();
    }

    public void getData() {
        /*String pname = editTextId.getText().toString().trim();
        if (pname.equals("")) {
            Toast.makeText(this, "Please enter an your station name", Toast.LENGTH_LONG).show();
            return;
        }*/


        //String url = DATA_URL+editTextId.getText().toString().trim();

        //Creating a json array request
        StringRequest strReq = new StringRequest(Request.Method.GET,
                DATA_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("User", response.toString());

                try {


                    JSONObject responseObj = new JSONObject(response);

                    JSONArray datas = responseObj.getJSONArray("data");
                    parseData(datas);


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();


                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("USER", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                //progressBar.setVisibility(View.GONE);
                //dialog.dismiss();
            }
        });

        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        strReq.setRetryPolicy(policy);
        //Creating request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(strReq);
        // Adding request to request queue
        // MyApplication.getInstance().addToRequestQueue(strReq);

    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {

            Toast.makeText(this, array.length() + "l", Toast.LENGTH_LONG).show();

            SuperHeroes Hero = new SuperHeroes();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                Hero.setId(json.getString(TAG_ID));
                Hero.setDate(json.getString(TAG_DATE));
                Hero.setAnnouncement(json.getString(TAG_NAME));

                //Hero.setImage(json.getString(TAG_URL));


            } catch (JSONException e) {
                e.printStackTrace();
            }
            listSuperHeroes.add(Hero);
        }

        //Finally initializing our adapter
        adapter = new CardAdapter(listSuperHeroes,Anouncment.this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder back = new AlertDialog.Builder(Anouncment.this);
        back.setTitle("Are you sure want to leave now?");
        back.setCancelable(true)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int arg1) {
                                endActivity();
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialogback = back.create();
        dialogback.show();
    }

    public void endActivity() {
        this.finish();
    }
}
