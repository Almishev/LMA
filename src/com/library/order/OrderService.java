package com.library.order;

import com.library.book.Book;
import com.library.book.BookMapper;
import com.library.reader.Reader;

import java.util.ArrayList;
import java.util.List;


public class OrderService {
    private static final OrderAccessor orderAccessor = new OrderAccessor();
    private static final OrderMapper orderMapper = new OrderMapper();

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
        Order order = new Order(id,reader,book);
        String orderString = orderMapper.mapOrderToString(order);
        orderAccessor.makeOrder(orderString);
    }








}
