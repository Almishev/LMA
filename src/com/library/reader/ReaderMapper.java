package com.library.reader;

import com.library.book.Book;

import java.util.List;

public class ReaderMapper {

    public Reader mapStringToReader(String string){
        String [] tokens=string.split("_");
        int readerId;
        String name=tokens[1];

        try {
            readerId=Integer.parseInt(tokens[0]);
        } catch (Exception e){
            try {
                throw new Exception("Reader with name "+name+" saved with invalid state");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return new Reader(readerId,name);
    }
    /*
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
     */

    public String mapReaderToString(Reader reader){

        int id=reader.getReaderId();
        String name=reader.getName();

        return String.join("_",Integer.toString(id),name);

    }

    public String mapReaderListToString(List<Reader> readerList){
        StringBuilder stringBuilder = new StringBuilder();
        for (Reader reader:readerList) {
            String readerString = mapReaderToString(reader);
            stringBuilder.append(readerString).append("\n");
        }
        return stringBuilder.toString();
    }
}
