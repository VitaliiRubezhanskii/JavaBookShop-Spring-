package ua.rubezhanskii.javabookshop.datamanagement.repository;

import ua.rubezhanskii.javabookshop.model.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {

    List<Order> getOrdersByDate(Date fromDate, Date toDate);
    Order getOrdersByCustomer(String customerAttribute);
    List<Order>getOrders();
}
