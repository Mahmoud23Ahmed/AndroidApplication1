package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.model.users;

public class Studenthome extends AppCompatActivity {

    Intent intent;
    users loggeduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studenthome);
        loggeduser = (users) getIntent().getSerializableExtra("user");
        Toast.makeText(this, "Welcome, " + loggeduser.getUsername(), Toast.LENGTH_SHORT).show();


        findViewById(R.id.lis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Listbook.class);
                i.putExtra("user", loggeduser);
                startActivity(i);
            }
        });


        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent (Studenthome.this,Notifi.class);
                i.putExtra("user" , loggeduser);
                startActivity(i);
            }
        });



        findViewById(R.id.contactus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent (Studenthome.this,contactus1.class);
                startActivity(i);
            }
        });

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent (Studenthome.this,MainActivity.class);
                startActivity(i);
            }
        });


    }
}