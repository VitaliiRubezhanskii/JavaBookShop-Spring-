package ua.rubezhanskii.javabookshop.dto;

import ua.rubezhanskii.javabookshop.model.Book;

public class CartItemDto {


    public CartItemDto() {
    }

    public CartItemDto(Book book, String machineName) {
        this.book = book;
        this.machineName = machineName;
    }

    private Book book;
    private String machineName;

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
}



