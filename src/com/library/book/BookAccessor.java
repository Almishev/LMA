package com.library.book;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class BookAccessor {

    private static final String BOOKS_FILE_PATH = "C:\\Users\\Admin\\Desktop\\book.txt";
    private static final BufferedWriter writer;
    private static final BufferedReader reader;

    static {
        try {
            writer = new BufferedWriter(new FileWriter(BOOKS_FILE_PATH));
            reader= new BufferedReader(new FileReader(BOOKS_FILE_PATH));
        }
       catch (IOException e){
            throw new RuntimeException("File not found with path "+BOOKS_FILE_PATH,e);
       }
    }

    public List<String>  readAllBooks(){
        return reader.lines().collect(Collectors.toList());
    }

    public void addItem(String string ){
        try {
            writer.write(string);
        } catch (IOException e) {
            throw new RuntimeException("Issues with adding books",e);
        }

    }

}
