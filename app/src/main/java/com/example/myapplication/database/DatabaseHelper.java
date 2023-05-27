package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import com.example.myapplication.model.Book;
import com.example.myapplication.model.BorrowBook;
import com.example.myapplication.model.users;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    ArrayList<users> arrayList = new ArrayList<users>();
    ArrayList<Book> arrayList2 = new ArrayList<Book>();
    ArrayList<BorrowBook> arrayList3 = new ArrayList<BorrowBook>();
    ArrayList<BorrowBook> arrayList4 = new ArrayList<BorrowBook>();
    public static final String DatabaseName = "app.db";

    public DatabaseHelper(Context con) {
        super(con, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Book (id  INTEGER primary key AUTOINCREMENT, title TEXT , author Text , quantity TEXT )");
        db.execSQL("create table users (id  INTEGER primary key AUTOINCREMENT, username TEXT , password TEXT )");
        db.execSQL("create table Borrow (id  INTEGER primary key AUTOINCREMENT, userId INTEGER, bookId INTEGER, TDate TEXT , RDate Text, FOREIGN KEY(userid) REFERENCES users(id),FOREIGN KEY(bookId) REFERENCES Book(id))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users ");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS Book ");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS Borrow ");
        onCreate(db);
    }
    public String insert_Borrow_Book(BorrowBook borrowBook) {
        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", borrowBook.getUsers().getId());
        values.put("bookId", borrowBook.getBook().getID());
        values.put("TDate", borrowBook.getTday());
        values.put("RDate", borrowBook.getRday());
        long result = s.insert("Borrow", null, values);
        if (result == -1)
            return "error";
        else
            return "Borrowed Book inserted";
    }
    public ArrayList<BorrowBook> Get_Data_Borrow() {
        SQLiteDatabase s = this.getReadableDatabase();
        Cursor c = s.rawQuery("select * from Borrow", null);
        c.moveToFirst();
        if(c.getCount() < 1) {
            return new ArrayList<>();
        }


        do {
            String userId = c.getString(1);
            String bookId = c.getString(2);
            users user = checkData_id(userId);
            Book book = checkData_Bid(bookId);
            arrayList3.add(new BorrowBook(
                    Integer.parseInt(c.getString(0)),
                    c.getString(3),
                    c.getString(4),
                    user,
                    book));

        } while (c.moveToNext());
        return arrayList3;
    }
    public ArrayList<BorrowBook> SearchBorrowed_Bid(users user) {
        String querybook = "Select " + "*" + " From" + " Borrow " + "where " + "userId" + " = " + user.getId() ;
        SQLiteDatabase s = this.getReadableDatabase();
        Cursor searchbook = s.rawQuery(querybook, null);
        searchbook.moveToFirst();
        do {
            String bookId = searchbook.getString(2);
            Book book = checkData_Bid(bookId);
            BorrowBook book1 = new BorrowBook(
                    Integer.parseInt(searchbook.getString(0)),
                    searchbook.getString(3),
                    searchbook.getString(4),
                    user,
                    book);
            arrayList4.add(book1);
        } while (searchbook.moveToNext());
        return arrayList4;
    }
    public Book checkData_Bid(String id) {
        String queryuser = "Select " + "*" + " From" + " Book " + "where " + "id" + " = " + id ;
        SQLiteDatabase s = this.getReadableDatabase();
        Cursor searchbook = s.rawQuery(queryuser, null);
        if (searchbook.getCount() < 1) {
            return null;
        }
        while (searchbook.moveToFirst()) {
            Book book = new Book(Integer.parseInt(searchbook.getString(0)), searchbook.getString(1), searchbook.getString(2),searchbook.getString(3));
            return book;
        }
        return null;
    }
    public users checkData_id(String id) {
        String queryuser = "Select " + "*" + " From" + " users " + "where " + "id" + " = " + id ;
        SQLiteDatabase s = this.getReadableDatabase();
        Cursor searchuser = s.rawQuery(queryuser, null);
        if (searchuser.getCount() < 1) {
            Log.e("user", "user doesn't exist");
            return null;
        }
        while (searchuser.moveToFirst()) {
            Log.e("user", "user and password exists");
            users loggeduser = new users(Integer.parseInt(searchuser.getString(0)), searchuser.getString(1), searchuser.getString(2));
            return loggeduser;
        }
        return null;
    }

    public String insertBook(String Btitle, String Bauthor, String BQunatity) {
        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", Btitle);
        values.put("author", Bauthor);
        values.put("quantity", BQunatity);
        long result = s.insert("Book", null, values);
        if (result == -1)
            return "error";
        else
            return "Book inserted";
    }
    public ArrayList<Book> Get_Data_Book() {

        SQLiteDatabase s = this.getReadableDatabase();
        Cursor c = s.rawQuery("select * from Book", null);
        c.moveToFirst();
       do {
            Book book = new Book(
                    Integer.parseInt(c.getString(0)),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3));
            arrayList2.add(book);
        }while (c.moveToNext());
        return arrayList2;
    }

    public String insertUsers(String n, String p) {
        SQLiteDatabase s = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", n);
        values.put("password", p);
        long result = s.insert("users", null, values);
        if (result == -1)
            return "error";
        else
            return "User inserted";
    }

    public ArrayList<users> Get_Data_users() {

        SQLiteDatabase s = this.getReadableDatabase();
        Cursor c = s.rawQuery("select * from users", null);
        c.moveToFirst();
        do {
            arrayList.add(new users(
                    c.getString(1),
                    c.getString(2)
            ));
        } while (c.moveToNext());
        return arrayList;
    }

    public users checkData(View.OnClickListener context, String u) {
        String queryuser = "Select " + "*" + " From" + " users " + "where " + "username" + " = " + "'" + u + "'" ;
        SQLiteDatabase s = this.getReadableDatabase();
        Cursor searchuser = s.rawQuery(queryuser, null);
        if (searchuser.getCount() < 1) {
            Log.e("user", "user doesn't exist");
            return null;
        }
        while (searchuser.moveToFirst()) {
            Log.e("user", "user and password exists");
            users loggeduser = new users(Integer.parseInt(searchuser.getString(0)), searchuser.getString(1), searchuser.getString(2));
            return loggeduser;
        }
        return null;
    }
    public users checkData(View.OnClickListener context, String u, String p) {
        String queryuser = "Select " + "*" + " From" + " users " + "where " + "username" + " = " + "'" + u + "'" + " and" + " password" + " = " + "'" + p + "'";
        SQLiteDatabase s = this.getReadableDatabase();
        Cursor searchuser = s.rawQuery(queryuser, null);
        if (searchuser.getCount() < 1) {
            Log.e("user", "user doesn't exist");
            return null;
        }
        while (searchuser.moveToFirst()) {
            Log.e("user", "user and password exists");
            users loggeduser = new users(Integer.parseInt(searchuser.getString(0)), searchuser.getString(1), searchuser.getString(2));
            return loggeduser;
        }
        return null;
    }
    public int isExists= 0;

    public Book searchBook(View.OnClickListener context, String t) {
        String querysearch = "Select " + "*" + " From" + " Book " + "where " + "title" + " = " + "'" + t + "'";
        SQLiteDatabase s = this.getReadableDatabase();
        Cursor searchBook = s.rawQuery(querysearch, null);
        if (searchBook.getCount() < 1) {
            Log.e("Book", "Book doesn't exisit");
            isExists= -1;
            return null;
        }
        while (searchBook.moveToFirst()) {
            Log.e("Book", "Book exists");
            Book borrowed  = new Book(Integer.parseInt(searchBook.getString(0)), searchBook.getString(1), searchBook.getString(2) , searchBook.getString(3));
            isExists = 1 ;
            return borrowed;
        }
        return null;
    }
    public int UpdateBookData(BorrowBook borrowBook) {
            Book borrowed  = borrowBook.getBook();
            SQLiteDatabase s = this.getWritableDatabase();
            int q = Integer.parseInt(borrowed.getQuantity())-1;
            String QQ= String.valueOf(q);
            ContentValues values = new ContentValues();
            values.put("title",borrowed.getTitle());
            values.put("author",borrowed.getAuthor());
            values.put("quantity",QQ);
            String selection = "id = ?";
            String[] selecetionArgs = {String.valueOf(borrowed.getID())};
            int updateStatus = s.update("Book",values,selection,selecetionArgs);
            Log.e("test",String.valueOf(updateStatus));

            return updateStatus;
    }

    public int UpdateBookData2(BorrowBook borrowBook) {
        Book borrowed  = borrowBook.getBook();
        SQLiteDatabase s = this.getWritableDatabase();
        int q = Integer.parseInt(borrowed.getQuantity())+1;
        String QQ= String.valueOf(q);
        ContentValues values = new ContentValues();
        values.put("title",borrowed.getTitle());
        values.put("author",borrowed.getAuthor());
        values.put("quantity",QQ);
        String selection = "id = ?";
        String[] selecetionArgs = {String.valueOf(borrowed.getID())};
        int updateStatus = s.update("Book",values,selection,selecetionArgs);
        Log.e("test",String.valueOf(updateStatus));
        DeleteBook(borrowBook);
        return updateStatus;
    }

    public Boolean DeleteBook(BorrowBook book) {
        String queryBook = "delete " + " From" + " Borrow " + "where " + "id" + " = " + book.getId();
        SQLiteDatabase s = this.getReadableDatabase();
        Cursor searchBook = s.rawQuery(queryBook, null);
        searchBook.moveToFirst();
        return true;

    }

    public Boolean DeleteBook(View.OnClickListener context, String t) {
        String queryBook = "delete " + " From" + " Book " + "where " + "title" + " = " + "'" + t + "'";
        SQLiteDatabase s = this.getReadableDatabase();
        Cursor searchBook = s.rawQuery(queryBook, null);
        if (searchBook.getCount() < 1) {
            Log.e("Book", "Book doesn't exist");
            return false;
        }
        return true;
    }
    public static boolean tryParse(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

