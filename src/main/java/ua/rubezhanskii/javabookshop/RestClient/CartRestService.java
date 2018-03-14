package ua.rubezhanskii.javabookshop.RestClient;

import org.springframework.http.HttpStatus;
import ua.rubezhanskii.javabookshop.model.Cart;

import java.util.List;

public interface CartRestService {


    List<Cart> getAllCart();

    Cart getById(Integer id);

    HttpStatus addCartItem(Cart item);

    void updateCartItem(Cart item);

    void deleteCartItem(Integer id);
}
