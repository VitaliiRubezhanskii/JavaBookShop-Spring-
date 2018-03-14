package ua.rubezhanskii.javabookshop.datamanagement.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ua.rubezhanskii.javabookshop.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setCategoryId(rs.getInt("categoryId"));
        category.setCategory(rs.getString("category"));
        return category;
    }
}
