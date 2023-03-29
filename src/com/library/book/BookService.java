package com.library.book;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private static final BookAccessor bookAccessor = new BookAccessor();
    private static final BookMapper bookMapper = new BookMapper();

    public List<Book> getAllBooks() {
        List<String> bookStrings = bookAccessor.readAllBooks();
        List<Book> books = new ArrayList<>();
        for (String bookString : bookStrings) {
            Book book = bookMapper.mapStringToBook(bookString);
            books.add(book);
        }
        return books;
    }

    public void addBook(String title, String author, String date) {
        int id = bookAccessor.readAllBooks().size() + 1;
        Book book = new Book(id, title, author, date);
        String bookString = bookMapper.mapBookToString(book);
        bookAccessor.addBook(bookString);

    }

    public void editBook(int id, String title, String author, String date) {
        /*
        List<Book> books=getAllBooks();
        if (books.size()<id || id<1){
            System.out.println("The ID doesn't exist.");
            return;
        }

            Book bookToEdit = null;
            for (Book book : books) {
                if (book.getBookId() == id) {
                    bookToEdit = book;
                    break;
                }
            }
            if (bookToEdit==null){
                return;
            }

         */
        List<Book> books=getAllBooks();
        Book bookToEdit = getBookByIDFromTheList(id,books);
        if (bookToEdit == null) {
            return;
        }

        bookToEdit.setTitle(title);
        bookToEdit.setAuthor(author);
        bookToEdit.setDate(date);

        StringBuilder stringBuilder = new StringBuilder();

        for (Book book : books) {
            String itemString = bookMapper.mapBookToString(book);
            stringBuilder.append(itemString).append("\n");
        }
        bookAccessor.overWriteFile(stringBuilder.toString());
    }

    public void deleteBook(int id) {
         List<Book> books = getAllBooks();
         Book bookToRemove= getBookByIDFromTheList(id,books);
         books.remove(bookToRemove);
         bookAccessor.

    }


    private Book getBookByIDFromTheList(int id,List<Book> books) {


        if (books.size() < id || id < 1) {
            System.out.println("The ID doesn't exist.");
            return null;
        }
        Book book = null;
        for (Book bookInTheList : books) {
            if (bookInTheList.getBookId() == id) {
                book = bookInTheList;
                break;
            }
        }
        return book;

    }

}
