package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CategoryService;
import ua.rubezhanskii.javabookshop.datamanagement.rowmappers.CategoryRowMapper;
import ua.rubezhanskii.javabookshop.herokuspecific.HerokuHelper;
import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.model.Category;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Service
public class CategoryJdbcTemplate implements CategoryService {


    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public CategoryJdbcTemplate(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void postConstruct() {
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("category")
                .usingGeneratedKeyColumns("categoryId");
    }

    @Override
    public void update(Category category) {
        final String UPDATE_QUERY="UPDATE category SET category=? WHERE categoryId=?";
        jdbcTemplate.update(UPDATE_QUERY,category.getCategory(),
                getCategoryById(category.getCategoryId()).getCategoryId());
    }

    @Override
    public Integer save(Category category) {
        try{
        Number number=0;
        if (category.getCategoryId().equals(getCategoryById(category.getCategoryId()).getCategoryId())) {
            update(category);
        }else {
            SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(category);
            number = jdbcInsert.executeAndReturnKey(parameterSource);
            if (number != null) {
                return number.intValue();
            }
            throw new RuntimeException("Cannot retrieve primary key");
        } return number.intValue();
    }catch (Exception ex){
            HerokuHelper.save(category);
        }
        return 1;
    }

    @Override
    public void delete(Integer categoryId) {
        final String DELETE_QUERY="DELETE FROM category WHERE categoryId=?";
        jdbcTemplate.update(DELETE_QUERY, categoryId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Category> getCategories() {
        final String RETRIEVE_CATEGORIES="SELECT * FROM category";
        return jdbcTemplate.query(RETRIEVE_CATEGORIES, new CategoryRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Category getCategoryById(Integer categoryId) {
        final String CATEGORIES_BY_ID="SELECT * FROM category WHERE categoryId=?";
        return (Category) jdbcTemplate.queryForObject(CATEGORIES_BY_ID, new Object[]{categoryId}, new CategoryRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Category getCategoryOfBook(Book book) {
        final String CATEGORY_OF_BOOK="SELECT category FROM book INNER  JOIN category ON  book.categoryId=category.categoryId WHERE ISBN=?";
        return (Category) jdbcTemplate.queryForObject(CATEGORY_OF_BOOK, new Object[]{book.getISBN()}, new CategoryRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Category getCategoryByName(String name){
        final String CATEGORY_BY_NAME="SELECT * FROM category WHERE category.category=?";
        return (Category)jdbcTemplate.queryForObject(CATEGORY_BY_NAME,new Object[]{name},new CategoryRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean exists(Integer categoryId) {
        final String COUNT_QUERY_CATEGORIES="SELECT count(*) FROM category WHERE categoryId=?";
        return (Integer)jdbcTemplate.queryForObject(COUNT_QUERY_CATEGORIES,new Object[]{categoryId},
                       new CategoryRowMapper())!=0;
    }
}
