package com.library.order;

import com.library.book.Book;
import com.library.reader.Reader;

public class OrderMapper {

    public Order mapStringToOrder(String string){
        String [] tokens=string.split("_");
        int orderId;
        int readerId;
        int bookId;
        String name=tokens[2];
        String title=tokens[4];
        String author=tokens[5];
        String date =tokens[6];

        try {
           orderId=Integer.parseInt(tokens[0]);
           readerId=Integer.parseInt(tokens[1]);
           bookId=Integer.parseInt(tokens[3]);

        } catch (Exception e){
            try {
                throw new Exception("Yor book order saved with invalid state");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        Reader r = new Reader(readerId,name);
        Book b = new Book(bookId,title,author,date);
        return new Order(orderId,r,b);
    }
}
