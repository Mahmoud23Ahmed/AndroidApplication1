package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.DatabaseHelper;

public class Addbook extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);
        DatabaseHelper data = new DatabaseHelper(this);


        findViewById(R.id.addbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Title, Author, Quantity;
                String title, author, quantity;
                Title = findViewById(R.id.TitleText);
                Author = findViewById(R.id.authorTxt);
                Quantity = findViewById(R.id.QuantityTxt);
                title = Title.getText().toString().trim();
                author = Author.getText().toString().trim();
                quantity = Quantity.getText().toString().trim();

                    if (data.tryParse(author) == false && data.tryParse(quantity) == true) {
                        if (data.searchBook(this, title) == null) {
                            data.insertBook(title, author, quantity);
                            Toast.makeText(Addbook.this, "Book Added", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(Addbook.this, "Book Exists", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(Addbook.this, "Data not valid", Toast.LENGTH_SHORT).show();
            }
        });


    }
}