package ua.rubezhanskii.javabookshop.datamanagement.repository;

import org.springframework.stereotype.Repository;
import ua.rubezhanskii.javabookshop.model.Customer;
import ua.rubezhanskii.javabookshop.model.Order;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderService {

    List<Order> getOrdersByDate(Date fromDate, Date toDate);
    Order getOrdersByCustomer(String customerAttribute);
    List<Order>getOrders();
    List<Order> getOrdersByCustomer(Customer customer);
}
