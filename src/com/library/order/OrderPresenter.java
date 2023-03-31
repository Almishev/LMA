package com.library.order;

import com.library.book.Book;
import com.library.book.BookService;
import com.library.book.util.ConsoleRangeReader;
import com.library.book.util.Validator;

import java.util.List;

public class OrderPresenter {

    private static final OrderService orderService=new OrderService();
    private static final Validator validator = new Validator();

    private static final String OPTIONS_MESSAGE = "Choose what to the with ORDERS: \n" +
            "1. Read all ORDERS\n0. BACK";
    private static final int MIN_MENU_OPTION=0;
    private static final int MAX_MENU_OPTION=1;


    public void showOrderMenu(){
        System.out.println(OPTIONS_MESSAGE);
        int choice= ConsoleRangeReader.readInt(MIN_MENU_OPTION,MAX_MENU_OPTION);
        switch (choice){

            case 1:
               showAllOrders();
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

}
