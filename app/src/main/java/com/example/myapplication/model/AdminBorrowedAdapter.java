package com.example.myapplication.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class AdminBorrowedAdapter  extends RecyclerView.Adapter<AdminBorrowedAdapter.ViewHolder> {
    private List<BorrowBook> AbookList;


    public AdminBorrowedAdapter(List<BorrowBook> AbookList) {
        this.AbookList = AbookList;
    }

    @NonNull
    @Override
    public AdminBorrowedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminborrowedlist, parent, false);
        return new AdminBorrowedAdapter.ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminBorrowedAdapter.ViewHolder holder, int position) {

        if (AbookList.isEmpty()) {
            Toast.makeText(holder.itemView.getContext(), "No books yet", Toast.LENGTH_SHORT).show();
            return;
        }

        BorrowBook book = AbookList.get(position);
        holder.authorName.setText(book.getBook().getAuthor());
        holder.bookName.setText(book.getBook().getTitle());
        Context context = holder.context;
        holder.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag", "onClick: ");
                DatabaseHelper db = new DatabaseHelper(holder.itemView.getContext());
                Log.e("UPDATE", String.valueOf(db.UpdateBookData2(AbookList.get(holder.getAdapterPosition()))));
                AbookList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                Toast.makeText(holder.itemView.getContext(), "Book Returned", Toast.LENGTH_SHORT).show();
            }
        });


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
        authorName = itemView.findViewById(R.id.title_text);
        bookName = itemView.findViewById(R.id.author_text);
        returnButton = itemView.findViewById(R.id.Returned);
        context = itemView.getContext();

    }
}
}
