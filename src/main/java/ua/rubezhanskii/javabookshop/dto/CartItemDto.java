package ua.rubezhanskii.javabookshop.dto;

import ua.rubezhanskii.javabookshop.model.Book;

public class CartItemDto {


    public CartItemDto() {
    }

    public CartItemDto(Book book, String machineName,Integer bookQuantity) {
        this.book = book;
        this.machineName = machineName;
        this.bookQuantity=bookQuantity;
    }

    private Book book;
    private String machineName;
    private Integer bookQuantity;

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    public String getMachineName() {
        return machineName;
    }
    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public Integer getBookQuantity() {
        return bookQuantity;
    }
    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}



