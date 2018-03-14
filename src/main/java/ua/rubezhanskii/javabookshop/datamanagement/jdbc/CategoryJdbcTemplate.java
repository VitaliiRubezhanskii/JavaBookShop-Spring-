package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CategoryService;
import ua.rubezhanskii.javabookshop.datamanagement.rowmappers.CategoryRowMapper;
import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.model.Category;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

public class CategoryJdbcTemplate implements CategoryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    private SimpleJdbcInsert jdbcInsert;

    @PostConstruct
    private void postConstruct() {

        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("category")
                .usingGeneratedKeyColumns("categoryId");
    }

    @Override
    public void update(Category category) {
        jdbcTemplate.update("UPDATE category SET category=? WHERE categoryId=?",category.getCategory(),getCategoryById(category.getCategoryId()).getCategoryId());
    }

    @Override
    public Integer save(Category category) {
        SqlParameterSource parameterSource=new BeanPropertySqlParameterSource(category);
        Number number=jdbcInsert.executeAndReturnKey(parameterSource);
        if (number!=null){
            return number.intValue();
        }
        throw new RuntimeException("Cannot retrieve primary key");
    /*    KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update("INSERT INTO category(category) VALUES (?)", category.getCategory(),keyHolder);
        return keyHolder.getKey().intValue();*/
    }

    @Override
    public void delete(Integer categoryId) {
        jdbcTemplate.update("DELETE FROM category WHERE categoryId=?", categoryId);
    }

    @Override
    public List<Category> getCategories() {
        return jdbcTemplate.query("SELECT * FROM category", new CategoryRowMapper());
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return (Category) jdbcTemplate.queryForObject("SELECT * FROM category WHERE categoryId=?", new Object[]{categoryId}, new CategoryRowMapper());
    }
    @Override
    public Category getCategoryOfBook(Book book) {
        return (Category) jdbcTemplate.queryForObject("SELECT category FROM book INNER  JOIN category" +
                " ON  book.categoryId=category.categoryId WHERE ISBN=?", new Object[]{book.getISBN()}, new CategoryRowMapper());
    }

    @Override
    public boolean exists(Integer categoryId) {
        boolean b=false;
         List<Category>categories=(List<Category>)jdbcTemplate.query("SELECT * FROM category WHERE categoryId=?",new Object[]{categoryId},
                 new CategoryRowMapper());
         return categories.size()<1 ? false : true;

    }
}
