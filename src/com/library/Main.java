package com.library;

import com.library.book.BookPresenter;
import com.library.book.util.ConsoleRangeReader;
import com.library.book.util.ConsoleReading;
import com.library.order.OrderPresenter;
import com.library.reader.ReaderPresenter;

import java.util.Scanner;

public class Main {


    private static final String GREETINGS_MESSAGE="Please choose an option:\n"+"1: Books \n2: Readers \n3: Orders\n0: Exit";
    private static final String PLACEHOLDER_1="Your choice is two";
    private static final String PLACEHOLDER_2="Your choice is three";
    private static final Scanner scanner  = new Scanner(System.in);
    private static final int MIN_MENU=0;
    private static final int MAX_MENU=3;
    private static final BookPresenter bookPresenter=new BookPresenter();
    private static final ReaderPresenter readerPresenter=new ReaderPresenter();
    private static final OrderPresenter orderPresenter=new OrderPresenter();

    public static void main(String[] args) {

        boolean isEnd =true;
        while (isEnd) {
            System.out.println(GREETINGS_MESSAGE);
            int choice = ConsoleRangeReader.readInt(MIN_MENU,MAX_MENU);
            switch (choice){
                case 1:
                    bookPresenter.showBookMenu();
                    break;
                case 2:
                    readerPresenter.showReaderMenu();
                    break;
                case 3:
                   orderPresenter.showOrderMenu();
                    break;
                case 0:
                    System.out.println("Thank you uses our services.");
                    isEnd=false;
            }

        }


    }


}
