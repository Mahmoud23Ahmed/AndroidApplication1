package com.example.myapplication.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Notification;
import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;

import java.util.List;

public class AdminBookAdapter extends RecyclerView.Adapter<AdminBookAdapter.ViewHolder> {
    private List<Book> AbookList;


    public AdminBookAdapter(List<Book> AbookList) {
        this.AbookList = AbookList;
    }

    @NonNull
    @Override
    public AdminBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminbooklist, parent, false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminBookAdapter.ViewHolder holder, int position) {

        if (AbookList.isEmpty()) {
            Toast.makeText(holder.itemView.getContext(), "No books yet", Toast.LENGTH_SHORT).show();
            return;
        }

        Book book = AbookList.get(position);
        holder.authorName.setText(book.getAuthor());
        holder.bookName.setText(book.getTitle());
        Notification notification = new Notification();
        Context context = holder.context;


    }

    @Override
    public int getItemCount() {

        return AbookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView authorName , bookName ;

        Button returnButton;
        Context context;
        Intent i;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.auTxt);
            bookName = itemView.findViewById(R.id.ntxt);


        }
    }
}
