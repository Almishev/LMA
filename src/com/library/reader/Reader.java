package com.library.reader;

public class Reader {

    private int readerId;
    private String name;

    public Reader(int readerId, String name) {
        this.readerId = readerId;
        this.name = name;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printReaders(){
        System.out.printf("%-5s%-21s\n",readerId,name);
    }


}


