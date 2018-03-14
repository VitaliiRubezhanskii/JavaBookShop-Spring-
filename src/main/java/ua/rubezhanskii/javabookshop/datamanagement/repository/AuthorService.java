package ua.rubezhanskii.javabookshop.datamanagement.repository;

import ua.rubezhanskii.javabookshop.model.Author;

public interface AuthorService {

    void update(Author author);
    Integer save(Author author);
    Author getAuthorOfBook(Integer authorId);
}
