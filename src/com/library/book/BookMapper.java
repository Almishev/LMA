package com.library.book;

import java.time.LocalDate;
import java.util.List;

public class BookMapper {
    public Book mapStringToBook(String string){
        String [] tokens=string.split("_");
        int bookId;
        String title=tokens[1];
        String author=tokens[2];
        String date =tokens[3];
          try {
              bookId=Integer.parseInt(tokens[0]);
          } catch (Exception e){
              try {
                  throw new Exception("Book with title "+title+" saved with invalid state");
              } catch (Exception ex) {
                  throw new RuntimeException(ex);
              }
          }
                return new Book(bookId,title,author,date);
    }

    public String mapBookToString(Book book){

        int id=book.getBookId();
        String title=book.getTitle();
        String author=book.getAuthor();
        String date=book.getDate();
        return String.join("_",Integer.toString(id),title,author,date);

    }

    public String mapBookListToString(List<Book> bookList){
        StringBuilder stringBuilder = new StringBuilder();
        for (Book book:bookList) {
            String bookString = mapBookToString(book);
            stringBuilder.append(bookString).append("\n");
        }
        return stringBuilder.toString();
    }
}
