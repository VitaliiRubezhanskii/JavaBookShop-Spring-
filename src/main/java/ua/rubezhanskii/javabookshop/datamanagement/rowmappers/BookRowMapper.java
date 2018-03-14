/*package ua.rubezhanskii.javabookshop.datamanagement.rowmappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.CategoryJdbcTemplate;
import ua.rubezhanskii.javabookshop.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper {

    @Autowired
    private CategoryJdbcTemplate categoryJdbcTemplate;

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book=new Book();
        book.setBookId(rs.getInt("bookId"));
        book.setCoverImage(rs.getString("coverImage"));
        book.setPrice(rs.getDouble("price"));
        book.setBookTitle(rs.getString("bookTitle"));
        book.setCategory(categoryJdbcTemplate.getCategoryById(rs.getInt("categoryId")));
        book.setPublishedYear(rs.getString("publishedYear"));
        book.setPublisher(rs.getString("publisher"));
        book.setISBN(rs.getString("ISBN"));
        book.setLanguage(rs.getString("lang"));
        book.setDetails(rs.getString("details"));
        return book;
    }
}*/
