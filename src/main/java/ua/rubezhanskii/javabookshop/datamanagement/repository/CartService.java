package ua.rubezhanskii.javabookshop.datamanagement.repository;

import org.springframework.stereotype.Repository;
import ua.rubezhanskii.javabookshop.dto.CartItemDto;
import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.model.Customer;

import java.util.List;
@Repository
public interface CartService {

    void update(Book book);
    void save(Book book);
    void delete(Integer bookId);
    void deleteAll();
    List<CartItemDto> getCartItems();
    void saveOrder(Customer customer);
    boolean exists(Integer bookId);
    String getLoggedUserName();
    Integer countItems(String login);
    List<CartItemDto>getCartItemsByLogin(String login);

}
