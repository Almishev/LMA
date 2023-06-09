package com.library.order;

import com.library.book.Book;
import com.library.book.BookMapper;
import com.library.book.BookService;
import com.library.exception.ItemNotFoundException;
import com.library.reader.Reader;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderService {
    private static final OrderAccessor orderAccessor = new OrderAccessor();
    private static final OrderMapper orderMapper = new OrderMapper();
    private static BookService bookService= new BookService();

    private static final String ID_NOT_FOUND_EXCEPTION="Order with ID %d was not found.";

    public List<Order> showAllOrders() {
        List<String> orderStrings = orderAccessor.showAllOrders();
        List<Order> orders = new ArrayList<>();
        for (String orderString : orderStrings) {

            Order order = orderMapper.mapStringToOrder(orderString);
            orders.add(order);
        }
        return orders;
    }

    public void makeOrder(Reader reader, Book book)  {
        int id = orderAccessor.showAllOrders().size() + 1;
        Order order = new Order(id,reader,book,getSysdateAsString());
        String orderString = orderMapper.mapOrderToString(order);
        orderAccessor.makeOrder(orderString);
    }

    public void deleteOrder (int id) throws ItemNotFoundException {
        List<Order> orders = showAllOrders();
        Order orderToRemove = getOrderByIDFromTheList(id, orders);
        orders.remove(orderToRemove);
        for (int i = orderToRemove.getOrderId()-1; i<orders.size();i++) {
            orders.get(i).setOrderId(i+1);
        }
        String bookString = orderMapper.mapOrderListToString(orders);
        orderAccessor.overWriteFile(bookString);
    }

    public Order getOrderByIDFromTheList(int id, List<Order> orders) throws ItemNotFoundException {

        if (orders.size() <= id || id < 1) {

            throw  new ItemNotFoundException(String.format(ID_NOT_FOUND_EXCEPTION,id));
        }
        Order order = null;
        for (Order orderInTheList : orders) {
            if (orderInTheList.getOrderId() == id) {
                order = orderInTheList;
                break;
            }
        }
        if (order==null){
            throw  new ItemNotFoundException(String.format(ID_NOT_FOUND_EXCEPTION,id));
        }
        return order;

    }

    public List<Order> getOrderByReaderIDFromTheList(int id, List<Order> orders,List<Reader> readers) throws ItemNotFoundException {
          List<Order> searchingOrders=new ArrayList<>();
        if (readers.size() <= id || id < 1) {

            throw  new ItemNotFoundException(String.format(ID_NOT_FOUND_EXCEPTION,id));
        }
        for (Order order : orders) {
            if (order.getReader().getReaderId() == id) {
                searchingOrders.add(order);
            }
        }

        /*
        Reader reader = null;
        for (Reader readerInTheList : readers) {
            if (readerInTheList.getReaderId() == id) {
                reader = readerInTheList;
                break;
            }
        }
        if (reader==null){
            throw  new ItemNotFoundException(String.format(ID_NOT_FOUND_EXCEPTION,id));
        }

         */

         return searchingOrders;

    }

    public void editOrder(int id, Book book) throws ItemNotFoundException {


        List<Order> orders = showAllOrders();
        Order orderToEdit = getOrderByIDFromTheList(id, orders);
        if (orderToEdit == null) {
            return;
        }

        orderToEdit.setBook(book);

        String readerString = orderMapper.mapOrderListToString(orders);
        orderAccessor.overWriteFile(readerString.toString());

    }
    public String getSysdateAsString(){
        Date thisDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        return dateFormat.format(thisDate);
    }

    public boolean isTheBookFree(int id){
        List<Order> orders = showAllOrders();
        for (Order order : orders) {
            if (order.getBook().getBookId()==id&&order.getOrderingDate().equals(getSysdateAsString())) {
                return false;
            }
        }
        return true;
    }









}
