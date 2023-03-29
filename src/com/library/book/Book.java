package com.library.book;

import java.time.LocalDate;
import java.util.Date;

public class Book {

    private int bookId;
    private String title;
    private String author;
    private String date;



    public Book(int bookId, String title, String author, String date) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.date = date;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return
                 bookId +"| "+
                " " + title +
                " " + author +
                " " + date +
                '|';
    }

    public void printBooks(){
        System.out.printf("%-5s%-21s%20s%20s\n",bookId,title,author,date);
    }


}
