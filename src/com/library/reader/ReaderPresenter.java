package com.library.reader;

import com.library.exception.ItemNotFoundException;
import com.library.util.ConsoleRangeReader;
import com.library.util.Validator;

import java.util.List;

public class ReaderPresenter {

    private static final ReaderService readerService=new ReaderService();
    private static final Validator validator = new Validator();

    private static final String OPTIONS_MESSAGE = "Choose what to the with the reader: \n" +
            "1. Read all readers\n2.Add reader\n3.Edit reader\n" + "4. Delete reader\n0. BACK";
    private static final int MIN_MENU_OPTION=0;
    private static final int MAX_MENU_OPTION=4;


    public void showReaderMenu(){
        System.out.println(OPTIONS_MESSAGE);
        int choice= ConsoleRangeReader.readInt(MIN_MENU_OPTION,MAX_MENU_OPTION);
        switch (choice){

            case 1:
               printAllReaders();
                break;
            case 2:
                addReader();
                break;
            case 3:
                editReader();
                break;
            case 4:
               deleteReader();
                break;

            case 0:
                return;

        }

    }
    public void addReader() {

        String reader = validator.validateAuthorTitle("Reader");
        List<Reader> readers = readerService.getAllReaders();
        boolean isHere = false;
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getName().equals(reader)) {
                isHere = true;
                break;
            }

        }
        if (isHere) {
            System.out.println(reader+" is here before.");
        } else{
            readerService.addReader(reader);
            System.out.println("Successful adding "+reader);
        }
    }

    public void printAllReaders(){
        System.out.println("\n----------------------------------------------------------------------------------------------");
        System.out.format("%-5s%-21s","ID","NAME");
        System.out.println("\n----------------------------------------------------------------------------------------------");

        List<Reader> readers=readerService.getAllReaders();
        for (Reader reader:readers) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
           reader.printReaders();
        }
    }
    public void editReader(){

        String id=validator.validateId();
        String name=validator.validateAuthorTitle("Name");

        try {
            readerService.editReader(Integer.parseInt(id),name);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }
    public void deleteReader(){
        int max=readerService.getAllReaders().size();
        System.out.println("Input reader ID: \nDelete:");
        List<Reader> readers=readerService.getAllReaders();
        for (int i = 0; i < readers.size() ; i++) {
            System.out.println(i+1 + " for "+readers.get(i).getName() );
        }

        int id= ConsoleRangeReader.readInt(1,max);
        try {
            readerService.deleteReader(id);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Deleting successful");

    }
}
