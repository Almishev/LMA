package com.library.order;

import com.library.book.Book;
import com.library.book.BookService;
import com.library.book.util.ConsoleRangeReader;
import com.library.book.util.Validator;
import com.library.exception.ItemNotFoundException;
import com.library.reader.Reader;
import com.library.reader.ReaderService;

import java.util.List;
import java.util.Scanner;

public class OrderPresenter {

    private static final OrderService orderService=new OrderService();
    private static final Validator validator = new Validator();
    Scanner scanner = new Scanner(System.in);
    private static final ReaderService readerService=new ReaderService();
    private static final BookService bookService = new BookService();

    private static final String OPTIONS_MESSAGE = "Choose what to the with ORDERS: \n" +
            "1. Read all ORDERS\n2. Make ORDER\n0. BACK";
    private static final int MIN_MENU_OPTION=0;
    private static final int MAX_MENU_OPTION=2;



    public void showOrderMenu(){
        System.out.println(OPTIONS_MESSAGE);
        int choice= ConsoleRangeReader.readInt(MIN_MENU_OPTION,MAX_MENU_OPTION);
        switch (choice){

            case 1:
               showAllOrders();
                break;
            case 2:
                makeANewOrder();
                break;

            case 0:
                return;

        }

    }

    public void showAllOrders(){
        System.out.println("\n----------------------------------------------------------------------------------------------");
        System.out.format("%-12s%-15s%-21s%-10s%-17s%15s%20s","ORDERID","READERID","READERNAME" ,"BOOKID","TITLE","AUTHOR","PUBLISH YEAR");
        System.out.println("\n----------------------------------------------------------------------------------------------");

        List<Order> orders=orderService.showAllOrders();
        for (Order order:orders) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            order.printOrders();
        }
    }

    public void makeANewOrder()  {

        System.out.println("Please select  reader ID : ");

        List<Reader> readers = readerService.getAllReaders();
        for (int i = 0; i < readers.size()  ; i++) {
            if(i<readers.size()-1) {
                System.out.print(readers.get(i).getReaderId()+" for "+readers.get(i).getName() + ",");
            } else{
                System.out.print(readers.get(i).getReaderId()+" for "+readers.get(i).getName());
            }
        }
        System.out.println();

        int readerId = ConsoleRangeReader.readInt(1,readers.size())-1;

        Reader reader;
        try {
            reader = readerService.getReaderByIDFromTheList(readerId,readers);
        } catch (ItemNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("Please select book ID : ");

        List<Book> books = bookService.getAllBooks();
        for (int i = 0; i < books.size()  ; i++) {
            if(i<readers.size()-1) {
                System.out.print(books.get(i).getBookId() + " for "+books.get(i).getTitle()+",");
            } else{
                System.out.print(books.get(i).getBookId()+" for "+books.get(i).getTitle());
            }
        }
        System.out.println();

        int bookId=ConsoleRangeReader.readInt(1,books.size())-1;
        Book book;
        try {
            book = bookService.getBookByIDFromTheList(bookId,books);
        } catch (ItemNotFoundException e) {
            throw new RuntimeException(e);
        }

        orderService.makeOrder(reader,book);

    }

}
