package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.model.users;

public class Test extends AppCompatActivity {

   static users loggeduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        loggeduser = (users) getIntent().getSerializableExtra("user");




        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent (Test.this,Addbook.class);
                startActivity(i);
            }
        });


        findViewById(R.id.Bdelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent (Test.this,Delete.class);
                startActivity(i);
            }
        });

        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Listofissue.class);
                i.putExtra("user", Test.loggeduser);
                startActivity(i);
            }
        });

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent (Test.this,Listborrow.class);
                startActivity(i);
            }
        });


    }
}