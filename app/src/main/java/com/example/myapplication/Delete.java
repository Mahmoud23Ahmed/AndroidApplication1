package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.Book;
import com.example.myapplication.model.users;

import java.util.ArrayList;

public class Delete extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        DatabaseHelper data = new DatabaseHelper(this);

        findViewById(R.id.Bdelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Title ;
                String title  ;
                Title = findViewById(R.id.BnameTxt);
                title = Title.getText().toString().trim();

                data.searchBook(this, title);
                if(data.isExists==1){
                    data.DeleteBook(this , title);
                    Toast.makeText(Delete.this, "Book Deleted", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Delete.this, "Book Doesn't Exist", Toast.LENGTH_SHORT).show();

                }


        });


    }
}