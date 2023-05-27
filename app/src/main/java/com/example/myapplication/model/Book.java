package com.example.myapplication.model;

import java.io.Serializable;

public class Book implements Serializable {
    String Title , Author,Quantity ;
    int ID;

    public Book(String title, String author, String quantity) {
        Title = title;
        Author = author;
        Quantity = quantity;
    }
    public Book(int id , String title, String author, String quantity) {
        ID=id;
        Title = title;
        Author = author;
        Quantity = quantity;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getQuantity() {
        return Quantity;
    }
}


