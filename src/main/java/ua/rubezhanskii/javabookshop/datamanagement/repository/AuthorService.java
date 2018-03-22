package ua.rubezhanskii.javabookshop.datamanagement.repository;

import org.springframework.stereotype.Repository;
import ua.rubezhanskii.javabookshop.model.Author;
@Repository
public interface AuthorService {
    void update(Author author);
    Integer save(Author author);
    Author getAuthorOfBook(Integer authorId);
    Author getAuthorByName(String name);
    boolean exists(String bookTitle);
}
