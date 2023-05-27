package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.AdminBookAdapter;
import com.example.myapplication.model.Book;
import com.example.myapplication.model.BookAdapter;

import java.util.ArrayList;

public class Listofissue extends AppCompatActivity {

    ArrayList<Book> bookArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofissue);


        DatabaseHelper data = new DatabaseHelper(this);
        bookArrayList = data.Get_Data_Book();

        AdminBookAdapter AdminBookAdapter = new AdminBookAdapter(bookArrayList);
        RecyclerView rv = findViewById(R.id.listOfIssueResView);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(AdminBookAdapter);

    }
}