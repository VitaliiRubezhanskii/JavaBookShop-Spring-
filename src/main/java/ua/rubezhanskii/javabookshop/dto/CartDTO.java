package ua.rubezhanskii.javabookshop.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    private String id;
    private List<CartItemDto> cartItems;

    public CartDTO() {}

    public CartDTO(String id) {
        this.id = id;
        cartItems = new ArrayList<>();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }
    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItemDto cartItemDto) {
        this.cartItems.add(cartItemDto);
    }


}
