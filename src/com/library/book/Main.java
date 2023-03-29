package com.library.book;

import com.library.book.util.ConsoleRangeReader;
import com.library.book.util.ConsoleReading;

import java.util.Scanner;

public class Main {


    private static final String GREETINGS_MESSAGE="Please choose an option:\n"+"1: Books \n2: Readers \n3: Orders";
    private static final String PLACEHOLDER_1="Your choice is two";
    private static final String PLACEHOLDER_2="Your choice is three";
    private static final Scanner scanner  = new Scanner(System.in);
    private static final int MIN_MENU=1;
    private static final int MAX_MENU=3;
    private static final BookPresenter bookPresenter=new BookPresenter();

    public static void main(String[] args) {


        while (true) {
            System.out.println(GREETINGS_MESSAGE);
            int choice = ConsoleRangeReader.readInt(MIN_MENU,MAX_MENU);
            switch (choice){
                case 1:
                    bookPresenter.showBookMenu();
                    break;
                case 2:
                    System.out.println(PLACEHOLDER_1);
                    break;
                case 3:
                    System.out.println(PLACEHOLDER_2);
                    break;


            }

        }


    }


}
