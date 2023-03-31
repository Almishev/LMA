package com.library.book.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {
    public static final String RED="\u001B[31m";
    public static final String RESET="\u001B[0m";
    private static Pattern ID_PATTERN = Pattern.compile("^\\d{1,4}$");
    private static Pattern AuthorTitle_Pattern=Pattern.compile("^[a-zA-Z ]+$");
    private static Pattern PublishYear_Pattern=Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
    Scanner sc = new Scanner(System.in);

    public String validateId() {
        String bookId;
        while (true) {
            System.out.println("ENTER READER ID ");
            bookId = sc.nextLine();
            if (!ID_PATTERN.matcher(bookId).matches()) {
                System.out.println(RED+"SORRY ! PLEASE ENTER VALID READER ID "+RESET);
            } else {
                break;
            }
        }
        return bookId;
    }
    public String validateAuthorTitle(String input){
        String result;
        while (true){
            if(input.equals("Title")){
                System.out.println("Enter Title");
            }else if(input.equals("Author")){
                System.out.println("Enter Author");
            }
            else{
                System.out.println("Enter NAME");
            }
            result=sc.nextLine();
            if(!AuthorTitle_Pattern.matcher(result).matches()){
                System.out.println(RED+"Please Enter Valid "+input+RESET);
            }
            else{
                break;
            }

        }
        return result;
    }
    public String validatePublishYear(){
        String date;
        while(true){
            System.out.println("Enter Publish Year of Book ");
            date=sc.nextLine();
            if(!PublishYear_Pattern.matcher(date).matches()){
                System.out.println(RED+"Enter valid Publish Year"+RESET);
            }
            else{
                break;
            }
        }
        return date;
    }
}
