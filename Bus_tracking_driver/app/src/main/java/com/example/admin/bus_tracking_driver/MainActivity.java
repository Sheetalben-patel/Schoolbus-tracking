package com.example.admin.bus_tracking_driver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText D_no,B_no;
    Button Submit;
    PrefManager pref;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        D_no= (EditText) findViewById(R.id.Phone);
        B_no= (EditText) findViewById(R.id.Busno);
       Submit = (Button) findViewById(R.id.B_submit);

        context = MainActivity.this;
        pref = new PrefManager(this);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone=D_no.getText().toString().trim();
                String bno=B_no.getText().toString().trim();
                pref.setUserDetail(phone,bno);

                Intent intent=new Intent(MainActivity.this,Profile.class);
                startActivity(intent);



            }
        });
    }
}
