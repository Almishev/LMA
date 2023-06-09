package com.library.order;

import com.library.book.Book;
import com.library.reader.Reader;

public class Order {


   private int orderId;
   private Reader reader;
   private Book book;
 //  private List<Book> order = new ArrayList<>();
   private String orderingDate ;


   public Order(int orderId, Reader reader, Book book,String date) {
      this.orderId=orderId;
      this.reader=reader;
      this.book=book;
      this.orderingDate=date;
   }

   public  int getOrderId() {
      return orderId;
   }

   public  void setOrderId(int orderId) {
     this.orderId = orderId;
   }

   public Reader getReader() {
      return reader;
   }

   public void setReader(Reader reader) {
      this.reader = reader;
   }

   public Book getBook() {
      return book;
   }

   public void setBook(Book book) {
      this.book = book;
   }

    public String getOrderingDate() {
        return orderingDate;
    }

    public void setOrderingDate(String date) {
        this.orderingDate = date;
    }
    // public List<Book> getOrder() {return order;}

//   public void setOrder(List<Book> order) {this.order = order;}

   public void printOrders(){
      System.out.printf("%-10s%-10s%-21s%-10s%-21s%20s%20s%19s\n",orderId,reader.getReaderId(),reader.getName(),book.getBookId(),
              book.getTitle(),book.getAuthor(),book.getDate(),orderingDate);
   }



/*
   public Order() {
      orderId++;
   }

   public int getSize(){
     return order.size();
   }

   public void takeABook(Book book){
      order.add(book);
   }

   public Book getOrder(int orderId){
     return order.get(orderId);
   }

   public void clearOrder(int orderId){
      order.remove(orderId);
   }
   public void clearAllOrders(){
      order.clear();
   }

    */
}
