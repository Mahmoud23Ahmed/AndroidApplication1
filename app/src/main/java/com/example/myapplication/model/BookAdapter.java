package com.example.myapplication.model;

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

import com.example.myapplication.Listbook;
import com.example.myapplication.Listborrow;
import com.example.myapplication.Notification;
import com.example.myapplication.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book> bookList;


    public BookAdapter(List<Book> bookList){
        this.bookList= bookList;
    }
    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.bookview,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {

        if(bookList.isEmpty()){
            Toast.makeText(holder.itemView.getContext(), "No books yet", Toast.LENGTH_SHORT).show();
            return;
        }

        Book book = bookList.get(position);
        holder.authorName.setText(book.getAuthor());
        holder.bookName.setText(book.getTitle());
        Notification notification = new Notification();
        Context context = holder.context;

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = bookList.get(holder.getAdapterPosition());
                for (Book listbook:
                     bookList) {
                    Log.e("book",String.valueOf(listbook.getID()));
                }
                BorrowBook borrowBook = new BorrowBook("","", Listbook.getLoggedUser(),book);
                notification.setBorrowBook(borrowBook);
                Intent i = new Intent(context,Notification.class);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {

        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView authorName , bookName ;
        Button addBtn;
        Context context;
        Intent i;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.authorName);
            bookName = itemView.findViewById(R.id.bookName);
            addBtn = itemView.findViewById(R.id.plusbtn);
            context = itemView.getContext();

        }
    }
}
