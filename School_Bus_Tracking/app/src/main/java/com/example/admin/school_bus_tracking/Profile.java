package com.example.admin.school_bus_tracking;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.school_bus_tracking.helper.PrefManager;

import java.util.HashMap;

public class Profile extends AppCompatActivity implements View.OnClickListener{
    TextView St_email;
    Button log;

    ImageButton St_attendanc,St_ancmnt,St_tracklocation;
    //TextView useremail;

    PrefManager pref;
    Context context;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        St_email= (TextView) findViewById(R.id.studentemail);


        log= (Button) findViewById(R.id.logout);

        St_attendanc= (ImageButton) findViewById(R.id.St_attendance);
        St_ancmnt= (ImageButton) findViewById(R.id.St_announment);
        St_tracklocation= (ImageButton) findViewById(R.id.St_Track);

        context = Profile.this;
        pref = new PrefManager(this);



        log.setOnClickListener(this);
        St_attendanc.setOnClickListener(this);
        St_ancmnt.setOnClickListener(this);
        St_tracklocation.setOnClickListener(this);



        Toast.makeText(getApplicationContext(), "User Login Status: " + pref.isLoggedIn(), Toast.LENGTH_LONG).show();


        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        pref.checkLogin();

        HashMap<String, String> user=  pref.getUserDetails();
        String email = user.get(pref.KEY_EMAIL);

        St_email.setText(email);

        //lblEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));


    }

    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {



                        //Starting login activity
                        // Intent intent = new Intent(WomenProfile.this, Login.class);
                        //startActivity(intent);
                        pref.logoutUser();

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public void onClick(View v) {

        if(v==log){
            logout();

        }


        if(v==St_attendanc){


                         Intent i = new Intent(Profile.this, Monthattendance.class);
                            startActivity(i);
        }

        if(v==St_ancmnt){


            Intent i = new Intent(Profile.this, Anouncment.class);
            startActivity(i);
        }

        if(v==St_tracklocation){


            Intent i = new Intent(Profile.this, Findlocation.class);
            startActivity(i);
        }





    }



    }





