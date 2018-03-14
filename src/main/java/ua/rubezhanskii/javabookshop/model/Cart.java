package ua.rubezhanskii.javabookshop.model;

import java.io.Serializable;
import java.util.Random;


public class Cart implements Serializable {

    private static final long serial_UID=3L;


    private Book book;
    private Integer bookQuantity;
    private String globalId;
    private String creationTime;
    private Customer customer;

    public Cart() {

    }

    public Cart(Book book, Integer bookQuantity, String globalId, String creationTime,Customer customer) {
        this.book = book;
        this.bookQuantity = bookQuantity;
        this.globalId = generateGUID();
        this.creationTime = creationTime;
        this.customer=customer;
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


    public Integer getBookQuantity() {
        return bookQuantity;
    }
    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }


    public String getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String generateGUID(){
        String prefix="GUID-";
        Integer suffix=new Random().nextInt(1500);
        Integer postfix=new Random().nextInt(15000);
    return prefix+suffix.toString()+"-"+postfix.toString();

    }
}
