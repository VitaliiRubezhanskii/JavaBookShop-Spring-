package ua.rubezhanskii.javabookshop.model;

import ua.rubezhanskii.javabookshop.dto.CartItemDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Order implements Serializable {
    private static final long serial_UID=6L;


    private Integer orderId;
    private Integer bookId;
    private Date orderDate;
    private List<CartItemDto> cartItems;
    private Customer customer;

    public Order() {
    }

    public Order(Integer orderId, Integer bookId, Date orderDate, List<CartItemDto> cartItems, Customer customer) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.orderDate = orderDate;
        this.cartItems = cartItems;
        this.customer = customer;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }



    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate='" + orderDate + '\'' +
                ", cartItem='" + cartItems + '\'' +
                ", customer=" + customer +
                '}';
    }
}
