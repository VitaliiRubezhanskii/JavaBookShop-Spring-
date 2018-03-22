package ua.rubezhanskii.javabookshop.datamanagement.repository;

import org.springframework.stereotype.Repository;
import ua.rubezhanskii.javabookshop.model.Customer;

import java.util.List;
@Repository
public interface CustomerService {

    void update(Customer customer);
    Integer save(Customer customer);
    void delete(Integer customerId);
    List<Customer> getCustomers();
    Customer getCustomerById(Integer customerId);
    boolean exists(Integer customerId);
    Customer getCustomerByLogin(String login);
}
