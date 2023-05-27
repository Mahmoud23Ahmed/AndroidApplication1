package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.AdminBorrowedAdapter;
import com.example.myapplication.model.Book;
import com.example.myapplication.model.BorrowAdaptor;
import com.example.myapplication.model.BorrowBook;
import com.example.myapplication.model.users;

import java.util.ArrayList;

public class Listborrow extends AppCompatActivity {

    public static users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(users loggedUser) {
        this.loggedUser = loggedUser;
    }

    static users loggedUser;

    Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listborrow);

        ArrayList<BorrowBook> borrowArrayList = new ArrayList<>();
        DatabaseHelper data = new DatabaseHelper(this);
        borrowArrayList = data.Get_Data_Borrow();
        RecyclerView rv = findViewById(R.id.recyclerView);
        AdminBorrowedAdapter borrowBookAdapter = new AdminBorrowedAdapter(borrowArrayList);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(borrowBookAdapter);

    }

}