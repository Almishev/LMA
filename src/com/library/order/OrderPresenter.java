package com.library.order;

import com.library.book.Book;
import com.library.book.BookService;
import com.library.util.ConsoleRangeReader;
import com.library.util.Validator;
import com.library.exception.ItemNotFoundException;
import com.library.reader.Reader;
import com.library.reader.ReaderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderPresenter {

    private static final OrderService orderService=new OrderService();
    private static final Validator validator = new Validator();
    Scanner scanner = new Scanner(System.in);
    private static final ReaderService readerService=new ReaderService();
    private static final BookService bookService = new BookService();

    private static final String OPTIONS_MESSAGE = "Choose what to the with ORDERS: \n" +
            "1. Read all ORDERS\n2. Make ORDER\n3. Delete ORDER\n4.Get Orders BY READER ID\n5.Edit ORDER\n0. BACK";
    private static final int MIN_MENU_OPTION=0;
    private static final int MAX_MENU_OPTION=5;



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
            case 3 :
                deleteOrder();
                break;
            case 4:
                getOrderByReaderID();
                break;
            case 5:
                editOrder();
                break;

            case 0:
                return;

        }

    }

    public void showAllOrders(){
        System.out.println("\n----------------------------------------------------------------------------------------------");
        System.out.format("%-12s%-15s%-21s%-10s%-17s%15s%20s,%20s","ORDERID","READERID","READERNAME" ,"BOOKID","TITLE","AUTHOR","PUBLISH YEAR","ORDERED AT");
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

        List<Order> orders=orderService.showAllOrders();

        System.out.println("Please select  reader ID : ");

        List<Reader> readers = readerService.getAllReaders();
        showReaders(readers);

        int readerId = ConsoleRangeReader.readInt(1,readers.size());

        Reader reader;
        try {

            reader = readerService.getReaderByIDFromTheList(readerId,readers);
        } catch (ItemNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("Please select book ID : ");

        List<Book> books = bookService.getAllBooks();
        showBooks(books);

        int bookId=ConsoleRangeReader.readInt(0,books.size());
        boolean isMaiden=isOrderMaiden(orders,readerId,bookId);


            Book book;
            try {

                book = bookService.getBookByIDFromTheList(bookId, books);
            } catch (ItemNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (!isMaiden) {
                orderService.makeOrder(reader, book);
                System.out.println("Successful accepted order.");
            } else {
                System.out.println("The order already exist.");
            }
        }

    private void showBooks(List<Book> books) {
        for (Book value : books) {
            System.out.print(value.getBookId() + " for " + value.getTitle() + ",");
        }
        System.out.println();
    }

    private void showReaders(List<Reader> readers) {
        for (Reader value : readers) {
            System.out.print(value.getReaderId() + " for " + value.getName() + ",");
        }
        System.out.println();
    }

    private  boolean isOrderMaiden(List<Order> orders,int readerId,int bookId){

            for (Order order : orders) {
                if (order.getReader().getReaderId() == readerId && order.getBook().getBookId() == bookId) {
                   return true;
                }

            }
              return false;
        }


     public void deleteOrder(){
         String id=validator.validateId();
         try {
             orderService.deleteOrder(Integer.parseInt(id));
             System.out.println("Deleting successful.");
         } catch (ItemNotFoundException e) {
             System.out.println(e.getMessage());
         }
   }

   public void getOrderByReaderID(){
        String id = validator.validateId();
        List<Order> orders= orderService.showAllOrders();
        List<Reader> readers=readerService.getAllReaders();
        List<Order> searchOrders;

       try {
          searchOrders= orderService.getOrderByReaderIDFromTheList(Integer.parseInt(id),orders,readers);
       } catch (ItemNotFoundException e) {
           throw new RuntimeException(e);
       }
       for (Order searchOrder : searchOrders) {
           searchOrder.printOrders();
       }


   }

   public void editOrder(){
       System.out.println("Select order ID");
       String id=validator.validateId();
       System.out.println("Select book ID");
       String bookId=validator.validateId();
       List<Book> books = bookService.getAllBooks();
       Book book;
       try {
           book = bookService.getBookByIDFromTheList(Integer.parseInt(bookId),books);
       } catch (ItemNotFoundException e) {
           throw new RuntimeException(e);
       }

       try {
           orderService.editOrder(Integer.parseInt(id), book);
           System.out.println("Edited successful.");
       } catch (ItemNotFoundException e) {
           System.out.println(e.getMessage());
       }

   }
   }





