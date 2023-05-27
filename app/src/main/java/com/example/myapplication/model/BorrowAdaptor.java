package com.example.myapplication.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;

import java.util.List;

public class BorrowAdaptor extends RecyclerView.Adapter<BorrowAdaptor.ViewHolder>{
    private List<BorrowBook> bookList;

    public BorrowAdaptor(List<BorrowBook> bookList){
        this.bookList = bookList;
    }
    @NonNull
    @Override

    public BorrowAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.borrowedview,parent,false);
        return new ViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull BorrowAdaptor.ViewHolder holder, int position) {
        if(bookList.isEmpty()){
            Toast.makeText(holder.itemView.getContext(), "No books yet", Toast.LENGTH_SHORT).show();
        return;
        }
        Log.e("tag", "onBindViewHolder: ");
        holder.t1.setText(bookList.get(position).getBook().getTitle());
        holder.t2.setText(bookList.get(position).getBook().getAuthor());
        Context context = holder.context;

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1 , t2 ;
        Button returnButton;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.ntxt);
            t2 = itemView.findViewById(R.id.auTxt);



        }
    }
}
