package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.AdminBookAdapter;
import com.example.myapplication.model.Book;
import com.example.myapplication.model.BookAdapter;
import com.example.myapplication.model.users;

import java.util.ArrayList;

public class Listbook extends AppCompatActivity {
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
        setContentView(R.layout.activity_listbook);
        ArrayList<Book> bookArrayList = new ArrayList<Book>();

        DatabaseHelper data = new DatabaseHelper(this);
        bookArrayList = data.Get_Data_Book();

        for (Book book:
             bookArrayList) {
            Log.e("Listbook",String.valueOf(bookArrayList.get(0).getID()));

        }

        BookAdapter bookAdapter = new BookAdapter(bookArrayList);
        RecyclerView rv = findViewById(R.id.recyclerView);
        loggedUser = (users) getIntent().getSerializableExtra("user");
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(bookAdapter);

    }

    public  void goToPage(){

    }
}