package com.example.myapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.MailTo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper data;
    EditText username, password;
    String name, pass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new DatabaseHelper(this);


        Log.e("DateEx", String.valueOf(LocalDate.now().plusDays(10)));






        findViewById(R.id.loginbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = (EditText) findViewById(R.id.username1);
                password = (EditText) findViewById(R.id.password1);
                name = username.getText().toString();
                pass = password.getText().toString();
                users loggedUser = data.checkData(this, name, pass);
                if (loggedUser == null) {
                    if(name.isEmpty() || pass.isEmpty()){
                        Toast.makeText(MainActivity.this, "username or password is Empty", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getApplicationContext(), "User doesn't exist", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(MainActivity.this, "login Success", Toast.LENGTH_LONG).show();

                if (loggedUser.getUsername().equals("Test") && loggedUser.getPassword().equals("Test123")) {
                    Intent i = new Intent(MainActivity.this, Test.class);
                    i.putExtra("user", loggedUser);
                    startActivity(i);
                    return;
                }
                Intent i = new Intent(MainActivity.this, Studenthome.class);
                i.putExtra("user", loggedUser);
                startActivity(i);
                }});

        findViewById(R.id.signupbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent (MainActivity.this,Singup.class);
                startActivity(i);
            }
        });



    }
}