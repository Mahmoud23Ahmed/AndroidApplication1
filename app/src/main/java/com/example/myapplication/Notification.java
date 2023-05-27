package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.BorrowBook;
import com.example.myapplication.model.users;

import java.time.LocalDate;

public class Notification extends AppCompatActivity {

    static BorrowBook borrowBook;
    users loggedUser;
    TextView Tdate , Rdate;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Tdate = findViewById(R.id.bDateTxt);
        Rdate = findViewById(R.id.RDateTxt);
        Tdate.setText(String.valueOf(LocalDate.now()));
        Rdate.setText(String.valueOf(LocalDate.now().plusDays(10)));
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        loggedUser = (users) getIntent().getSerializableExtra("user");


        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tdate.getText().toString().isEmpty() || Rdate.getText().toString().isEmpty()) {
                    return;
                }
                borrowBook.setTday(Tdate.getText().toString());
                borrowBook.setRday(Rdate.getText().toString());
                databaseHelper.UpdateBookData(borrowBook);
                databaseHelper.insert_Borrow_Book(borrowBook);
                sendNotification();
                Intent intent = new Intent(Notification.this, Listbook.class);
                startActivity(intent);
            }

        });

        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Notification.this, Listbook.class);
                startActivity(i);
            }
        });
    }


    public BorrowBook getBorrowBook() {
        return borrowBook;
    }

    public void setBorrowBook(BorrowBook borrowBook) {
        this.borrowBook = borrowBook;

    }

    private void sendNotification() {
        // Create notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            createNotificationChannel(notificationManager);
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "BorrowChannel")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Order Confirmation")
                .setContentText("Your Book has been ordered successfully.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        // Show the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(NotificationManager notificationManager) {
        String channelName = "Borrow Channel";
        String channelDescription = "Channel for borrowed notifications";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = new NotificationChannel("BorrowChannel", channelName, importance);
        channel.setDescription(channelDescription);
        notificationManager.createNotificationChannel(channel);
    }
}