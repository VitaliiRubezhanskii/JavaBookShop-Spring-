package ua.rubezhanskii.javabookshop.datamanagement.repository;

import ua.rubezhanskii.javabookshop.model.Book;

import java.util.List;

public interface BookService {

    void update(Book book);
    void save(Book book);
    void delete(Integer bookId);
    List<Book> getBooks();
    Book getBookById(final Integer bookId);
    Book getBookByISBN( String ISBN);
    List<Book> getBooksFromCategory(Integer categoryId);
    boolean exists(Integer bookId);
    List<Book>getFirstSixBooks();
     Book getBookByIdFromCart(Integer bookId);
     List<Book> search(String criteria);


}
