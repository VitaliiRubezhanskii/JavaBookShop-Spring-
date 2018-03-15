package ua.rubezhanskii.javabookshop.model;

import java.io.Serializable;
import java.util.Date;


public class Order implements Serializable {
    private static final long serial_UID=6L;


    private Integer orderId;
    private Book book;
    private Date orderDate;
  //  private List<CartItemDto> cartItems;
    private Customer customer;
    private String globalId;

    public Order() {
    }

    public Order(Integer orderId, Book book, Date orderDate, Customer customer, String globalId) {
        this.orderId = orderId;
        this.book = book;
        this.orderDate = orderDate;
        this.customer = customer;
        this.globalId = globalId;
    }

    public String getGlobalId() {
        return globalId;
    }
    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

  /*  public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }*/

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
                ", cartItem='" + /*cartItems + '\''*/
                ", customer=" + customer +
                '}';
    }
}
