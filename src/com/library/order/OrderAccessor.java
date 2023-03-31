package com.library.order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderAccessor {


    private static final String READERS_FILE_PATH = "src/orders.txt";
    private static final String FILE_NOT_FOUND_MESSAGE="File not found with path "+READERS_FILE_PATH;

    public List<String> showAllOrders(){

        try (BufferedReader reader= new BufferedReader(new FileReader(READERS_FILE_PATH))) {
            return reader.lines().collect(Collectors.toList());
        }

        catch(IOException e){
            throw new RuntimeException("File not found with path " + READERS_FILE_PATH, e);
        }
    }
}
