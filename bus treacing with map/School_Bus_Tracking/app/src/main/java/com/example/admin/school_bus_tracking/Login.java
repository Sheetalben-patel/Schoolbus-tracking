package com.example.admin.school_bus_tracking;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.school_bus_tracking.helper.PrefManager;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener{
    public static final String LOGIN_URL = "http://192.168.0.105/schoolbustracking/login_android.php";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button buttonLogin;

    PrefManager pref;
    Context context;


   // ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        editTextUsername = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.edtpwd);

        context = Login.this;
        pref = new PrefManager(this);

        buttonLogin = (Button) findViewById(R.id.btnlogin);
        buttonLogin.setOnClickListener(this);
    }


    private void login() {
        //Getting values from edit texts
        final String email = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if (response.trim().equals("success")) {
                            //Creating a shared preference


                            pref.setUserDetail(email,password);
                            //Starting profile activity
                            Intent intent = new Intent(Login.this, Profile.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                            endActivity();

                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(KEY_EMAIL, email);
                params.put(KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){



            case R.id.btnlogin: /** AlerDialog when click on Exit */
                login();
                break;
        }


    }

    public void endActivity() {
        this.finish();
    }
}


