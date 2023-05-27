package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.DatabaseHelper;

import java.util.ArrayList;

public class Singup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username , password;
                username = findViewById(R.id.usernameTxt);
                password = findViewById(R.id.passwordTxt);

                if(databaseHelper.checkData(this , username.getText().toString())==null) {
                    databaseHelper.insertUsers(username.getText().toString().trim(), password.getText().toString().trim());
                    Toast.makeText(Singup.this, "User Added", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(Singup.this, "User is Exist", Toast.LENGTH_SHORT).show();
                }
        });

    }
}