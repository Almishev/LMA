package com.library.book.util;

import java.util.Scanner;
import java.util.regex.Pattern;


public final class ConsoleReading {

        public static final String INVALID_IND="%s is not an int. Try again, please: ";
        public static final Scanner scanner=new Scanner(System.in);


        private ConsoleReading(){

            throw new UnsupportedOperationException();

        }

        public static String readString(){
            return scanner.nextLine();
        }

        public static int readInt(){
            while (!scanner.hasNextInt()){
                String input=scanner.next();
                System.out.printf(INVALID_IND,input);
            }
            int input= scanner.nextInt();
            scanner.nextLine();
            return input;
        }

}
