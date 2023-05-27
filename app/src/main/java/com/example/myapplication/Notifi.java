package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.Book;
import com.example.myapplication.model.BookAdapter;
import com.example.myapplication.model.BorrowBook;
import com.example.myapplication.model.userBookAdapter;
import com.example.myapplication.model.users;

import java.util.ArrayList;

public class Notifi extends AppCompatActivity {
    public static users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(users loggedUser) {
        this.loggedUser = loggedUser;
    }

    static users loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifi);
        ArrayList<BorrowBook> bookArrayList = new ArrayList<>();
        loggedUser = (users) getIntent().getSerializableExtra("user");
        DatabaseHelper data = new DatabaseHelper(this);
        bookArrayList = data.SearchBorrowed_Bid(loggedUser);

        userBookAdapter userAdapter = new userBookAdapter(bookArrayList);
        RecyclerView rv = findViewById(R.id.notificationResView);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(userAdapter);

    }
}