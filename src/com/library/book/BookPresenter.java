package com.library.book;

import com.library.book.util.ConsoleRangeReader;
import com.library.book.util.ConsoleReading;
import com.library.book.util.Validator;

import java.util.ArrayList;
import java.util.List;

public class BookPresenter {

    private static final BookService bookService=new BookService();
    Validator validator = new Validator();
    private static final String BOOK_TITLE_PROMPT="Enter book title";
    private static final String BOOK_AUTHOR_PROMPT="Enter author name";
    private static final String BOOK_DATE_PROMPT="Enter date of publishing __/__/__";
    private static final String BOOK_ID_PROMPT="Enter ID of the book";


    private static final String OPTIONS_MESSAGE = "Choose what to the with books: \n" +
            "1. Read all books\n2.Add book\n3.Edit book\n" + "4. Delete book";
    private static final int MIN_MENU_OPTION=1;
    private static final int MAX_MENU_OPTION=4;


    public void showBookMenu(){
        System.out.println(OPTIONS_MESSAGE);
        int choice= ConsoleRangeReader.readInt(MIN_MENU_OPTION,MAX_MENU_OPTION);
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
                deleteBook();
                break;

        }
    }
    public void addBook(){

        String title=   validator.validateAuthorTitle("Title");

        String author= validator.validateAuthorTitle("Author");

        String date = validator.validatePublishYear();

        bookService.addBook(title,author,date);

    }

    public void printAllBooks(){
        System.out.println("\n----------------------------------------------------------------------------------------------");
        System.out.format("%-5s%-21s%20s%20s","ID","TITLE","AUTHOR","PUBLISH YEAR");
        System.out.println("\n----------------------------------------------------------------------------------------------");

       List<Book> books=bookService.getAllBooks();
        for (Book book:books) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            book.printBooks();
        }
    }
    public void editBook(){
        /*
        System.out.print(BOOK_ID_PROMPT);
        int id=ConsoleReading.readInt();
        System.out.print(BOOK_TITLE_PROMPT);
        String title= ConsoleReading.readString();
        System.out.println(BOOK_AUTHOR_PROMPT);
        String author=ConsoleReading.readString();
        System.out.println(BOOK_DATE_PROMPT);

        String date = ConsoleReading.readString();

         */

        String id=validator.validateId();
        String title=   validator.validateAuthorTitle("Title");

        String author= validator.validateAuthorTitle("Author");

        String date = validator.validatePublishYear();
        bookService.editBook(Integer.parseInt(id),title,author,date);


    }
    public void deleteBook(){
        String id=validator.validateId();
        bookService.deleteBook(Integer.parseInt(id));

    }
}
