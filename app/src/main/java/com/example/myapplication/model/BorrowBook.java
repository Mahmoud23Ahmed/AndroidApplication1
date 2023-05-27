package com.example.myapplication.model;

import java.util.ArrayList;

public class BorrowBook {



    int id;
    String Tday , Rday;
    users users;
    Book book;

    public BorrowBook(int id, String tday, String rday, users users, Book book) {
        this.id = id;
        Tday = tday;
        Rday = rday;
        this.users = users;
        this.book = book;
    }
    public BorrowBook(String tday, String rday, users users, Book book) {
        Tday = tday;
        Rday = rday;
        this.users = users;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTday() {
        return Tday;
    }

    public void setTday(String tday) {
        Tday = tday;
    }

    public String getRday() {
        return Rday;
    }

    public void setRday(String rday) {
        Rday = rday;
    }

    public com.example.myapplication.model.users getUsers() {
        return users;
    }

    public void setUsers(com.example.myapplication.model.users users) {
        this.users = users;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }




}
