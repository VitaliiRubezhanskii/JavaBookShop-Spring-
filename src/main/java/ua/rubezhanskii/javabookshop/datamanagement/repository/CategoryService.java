package ua.rubezhanskii.javabookshop.datamanagement.repository;

import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.model.Category;

import java.util.List;

public interface CategoryService {

    void update(Category category);
    Integer save(Category category);
    void delete(Integer categoryId);
    List<Category> getCategories();
    Category getCategoryById(Integer categoryId);
    Category getCategoryOfBook(Book book);
    boolean exists(Integer categoryId);

}
