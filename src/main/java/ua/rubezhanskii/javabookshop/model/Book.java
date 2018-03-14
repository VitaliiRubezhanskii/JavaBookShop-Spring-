package ua.rubezhanskii.javabookshop.model;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serial_UID=2L;

    private Integer bookId;
    private String coverImage;
    private Double price;
    private String bookTitle;
    private Category category;

    private String publisher;
    private String ISBN;
    private String language;
    private String details;
    private Author author;
    private Integer bookQuantity;
    private Integer InventoryStock;


    public Book() {
    }

    public Book(Integer bookId, String coverImage, Double price, String bookTitle, Category category , String publisher, String ISBN, String language, String details,
                Author author, Integer bookQuantity, Integer inventoryStock) {
        this.bookId = bookId;
        this.coverImage = coverImage;
        this.price = price;
        this.bookTitle = bookTitle;
        this.category = category;

        this.publisher = publisher;
        this.ISBN = ISBN;
        this.language = language;
        this.details = details;
        this.author = author;
        this.bookQuantity = bookQuantity;
        InventoryStock = inventoryStock;
    }

    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }


    public String getCoverImage() {
        return coverImage;
    }
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }


    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }


    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }


    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }


    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }


    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getBookQuantity() {
        return bookQuantity;
    }
    public void setBookQuantity(Integer bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public Integer getInventoryStock() {
        return InventoryStock;
    }
    public void setInventoryStock(Integer inventoryStock) {
        InventoryStock = inventoryStock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", coverImage='" + coverImage + '\'' +
                ", price=" + price +
                ", bookTitle='" + bookTitle + '\'' +
                ", category=" + category +

                ", publisher='" + publisher + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", language='" + language + '\'' +
                ", details='" + details + '\'' +
                ", author=" + author +
                ", bookQuantity=" + bookQuantity +
                ", InventoryStock=" + InventoryStock +
                '}';
    }
}
