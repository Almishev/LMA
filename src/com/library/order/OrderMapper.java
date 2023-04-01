package com.library.order;

import com.library.book.Book;
import com.library.reader.Reader;

import java.util.List;

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

    public String mapOrderToString(Order order){

        int orderId=order.getOrderId();
        int readerId = order.getReader().getReaderId();
        int bookId=order.getBook().getBookId();
        String name=order.getReader().getName();
        String title = order.getBook().getTitle();
        String author=order.getBook().getAuthor();
        String date = order.getBook().getDate();
        return String.join("_",Integer.toString(orderId),Integer.toString(readerId),name,Integer.toString(bookId),
                title, author,date);
    }

    public String mapOrderListToString(List<Order> orderList){
        StringBuilder stringBuilder = new StringBuilder();
        for (Order order:orderList) {
            String bookString = mapOrderToString(order);
            stringBuilder.append(bookString).append("\n");
        }
        return stringBuilder.toString();
    }
}
