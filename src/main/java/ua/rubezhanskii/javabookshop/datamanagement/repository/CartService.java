package ua.rubezhanskii.javabookshop.datamanagement.repository;

import ua.rubezhanskii.javabookshop.dto.CartItemDto;
import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.model.Customer;

import java.util.List;

public interface CartService {

    void update(Book book);
    void save(Book book);
    void delete(Integer bookId);
    List<CartItemDto> getCartItems();
    void saveOrder(Customer customer);
    boolean exists(Integer bookId);

}
