package com.library.book;

import com.library.book.util.ConsoleReading;

public class BookPresenter {

    private static final String OPTIONS = "Choose what to the with books: \n" +
            "1. Read all books\n2.Add book\n3.Edit book\n" + "4. Delete book";


    public void showBookMenu(){
        System.out.println(OPTIONS);
        int choice= ConsoleReading.readInt();
        switch (choice){

            case 1:
                printAllBooks();
                break;
            case 2:
                addBook();
                break;
            case 3:
                editBook();
                break;
            case 4:
                removeBook();
                break;

        }
    }
    public void addBook(){

    }

    public void printAllBooks(){

    }
    public void editBook(){

    }
    public void removeBook(){

    }
}
