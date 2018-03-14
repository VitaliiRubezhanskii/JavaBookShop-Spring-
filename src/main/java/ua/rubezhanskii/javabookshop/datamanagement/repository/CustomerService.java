package ua.rubezhanskii.javabookshop.datamanagement.repository;

import ua.rubezhanskii.javabookshop.model.Customer;

import java.util.List;

public interface CustomerService {

    void update(Customer customer);
    Integer save(Customer customer);
    void delete(Integer customerId);
    List<Customer> getCustomers();
    Customer getCustomerById(Integer customerId);
    boolean exists(Integer customerId);
    Customer getCustomerByLogin(String login);
}
