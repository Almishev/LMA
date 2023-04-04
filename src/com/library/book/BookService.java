package com.library.book;

import com.library.contract.DeleteAble;
import com.library.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private static final BookAccessor bookAccessor = new BookAccessor();
    private static final BookMapper bookMapper = new BookMapper();

    private static final String ID_NOT_FOUND_EXCEPTION="Item with ID %d was not found.";

    public List<Book> getAllBooks() {
        List<String> bookStrings = bookAccessor.readAll();
        List<Book> books = new ArrayList<>();
        for (String bookString : bookStrings) {
            Book book = bookMapper.mapStringToBook(bookString);
            books.add(book);
        }
        return books;
    }

    public void addBook(String title, String author, String date) {

            int id = bookAccessor.readAll().size();
            Book book = new Book(id, title, author, date);

            String bookString = bookMapper.mapBookToString(book);
            bookAccessor.add(bookString);
    }

    public void editBook(int id, String title, String author, String date) throws ItemNotFoundException {
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
        List<Book> books = getAllBooks();
        Book bookToEdit = getBookByIDFromTheList(id, books);
        if (bookToEdit == null) {
            return;
        }

        bookToEdit.setTitle(title);
        bookToEdit.setAuthor(author);
        bookToEdit.setDate(date);
        String bookString = bookMapper.mapBookListToString(books);
        bookAccessor.overWriteFile(bookString.toString());

        /*
        StringBuilder stringBuilder = new StringBuilder();

        for (Book book : books) {
            String itemString = bookMapper.mapBookToString(book);
            stringBuilder.append(itemString).append("\n");
        }
        bookAccessor.overWriteFile(stringBuilder.toString());

         */
    }

    public void deleteBook(int id) throws ItemNotFoundException {
        List<Book> books = getAllBooks();
        Book bookToRemove = getBookByIDFromTheList(id, books);
        books.remove(bookToRemove);
        for (int i = bookToRemove.getBookId()-1; i<books.size();i++) {
                 books.get(i).setBookId(i+1);
        }
        String bookString = bookMapper.mapBookListToString(books);
        bookAccessor.overWriteFile(bookString);

    }

      public Book getBookByIDFromTheList(int id, List<Book> books) throws ItemNotFoundException {


        if (books.size()+1 <= id || id < 1) {

            throw  new ItemNotFoundException(String.format(ID_NOT_FOUND_EXCEPTION,id));
        }
        Book book = null;
        for (Book bookInTheList : books) {
            if (bookInTheList.getBookId() == id) {
                book = bookInTheList;
                break;
            }
        }
        if (book==null){
            throw  new ItemNotFoundException(String.format(ID_NOT_FOUND_EXCEPTION,id));
        }
        return book;

    }

}
