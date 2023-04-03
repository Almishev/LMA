package com.library.book;

import com.library.contract.AccessorCRUDAble;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class BookAccessor extends AccessorCRUDAble<Book> {

    private static final String BOOKS_FILE_PATH = "LMA/src/book.txt";
    private static final String FILE_NOT_FOUND_MESSAGE="File not found with path "+BOOKS_FILE_PATH;

    @Override
    public List<String>  readAll(){

        try (BufferedReader  reader= new BufferedReader(new FileReader(BOOKS_FILE_PATH))) {
            return reader.lines().collect(Collectors.toList());
        }
       catch(IOException e){
                throw new RuntimeException("File not found with path " + BOOKS_FILE_PATH, e);
            }
        }
       @Override
        public void overWriteFile(String items){
            try (BufferedWriter writer=new BufferedWriter(new FileWriter(BOOKS_FILE_PATH))) {
                writer.write(items);
            } catch (IOException e) {
                throw new RuntimeException(FILE_NOT_FOUND_MESSAGE,e);
            }

        }
    @Override
    public void add(String string){
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(BOOKS_FILE_PATH,true))) {
         writer.write(string+" \n");
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_MESSAGE,e);
        }

    }

}
