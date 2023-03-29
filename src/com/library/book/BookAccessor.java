package com.library.book;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class BookAccessor {

    private static final String BOOKS_FILE_PATH = "C:\\Users\\Admin\\Desktop\\book.txt";
    private static final String FILE_NOT_FOUND_MESSAGE="File not found with path "+BOOKS_FILE_PATH;


    public List<String>  readAllBooks(){

        try (BufferedReader  reader= new BufferedReader(new FileReader(BOOKS_FILE_PATH))) {
            return reader.lines().collect(Collectors.toList());
        }
       catch(IOException e){
                throw new RuntimeException("File not found with path " + BOOKS_FILE_PATH, e);
            }
        }

        public void overWriteFile(String items){
            try (BufferedWriter writer=new BufferedWriter(new FileWriter(BOOKS_FILE_PATH))) {
                writer.write(items);
            } catch (IOException e) {
                throw new RuntimeException(FILE_NOT_FOUND_MESSAGE,e);
            }

        }

    public void addBook(String string ){
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(BOOKS_FILE_PATH,true))) {
         writer.write(string+" \n");
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_MESSAGE,e);
        }

    }

}
