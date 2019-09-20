package com.example.admin.school_bus_tracking;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.admin.school_bus_tracking.helper.PrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Findlocation extends AppCompatActivity{

    public static final String REGISTER_URL = "http://172.16.0.170/schoolbustracking/St_location.php?phone=";



    EditText Dr_phone;
    Button St_fetch;
    private List<Heroes> listHeroes;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;



    public static final String KEY_BUSNO = "busno";
    public static final String KEY_ADDRESS = "locationAddress";
    public static final String KEY_DATE = "date";

    PrefManager pref;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findlocation);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);






        context =Findlocation.this;
        pref = new PrefManager(this);

        pref.checkLogin();

        //St_address= (TextView) findViewById(R.id.ft_addr);
        Dr_phone= (EditText) findViewById(R.id.En_mobile);

        St_fetch = (Button) findViewById(R.id.ft_button);

        listHeroes = new ArrayList<>();

        St_fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   getData();
            }
        });



    }


    public void getData(){



        String url = REGISTER_URL +Dr_phone.getText().toString().trim();



        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

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

            Heroes Hero = new Heroes();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);


                Hero.setBusno(json.getString(KEY_BUSNO));
                Hero.setLocationAddress(json.getString(KEY_ADDRESS));
                Hero.setDate(json.getString(KEY_DATE));

                //Hero.setImage(json.getString(TAG_URL));


            } catch (JSONException e) {
                e.printStackTrace();
            }
            listHeroes.add(Hero);
        }

        //Finally initializing our adapter
        adapter = new CardAdapterf(listHeroes,Findlocation.this);

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }





    @Override
    public void onBackPressed() {
        AlertDialog.Builder back = new AlertDialog.Builder(Findlocation.this);
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


