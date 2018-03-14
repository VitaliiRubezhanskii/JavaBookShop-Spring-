/*package ua.rubezhanskii.javabookshop.datamanagement.rowmappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.BookJdbcTemplate;
import ua.rubezhanskii.javabookshop.model.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowMapper implements RowMapper {

    @Autowired
    private BookJdbcTemplate bookJdbcTemplate;

    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cart cart=new Cart();
        cart.setBookId(bookJdbcTemplate.getBookById(rs.getInt("bookId")));
        cart.setBookQuantity(rs.getInt("bookQuantity"));
        cart.setGlobalId(rs.getString("globalId"));
        cart.setCreationTime(rs.getString("creationTime"));
        return cart;
    }
}*/
