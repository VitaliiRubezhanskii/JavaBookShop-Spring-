package ua.rubezhanskii.javabookshop.herokuspecific;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.rubezhanskii.javabookshop.datamanagement.repository.AuthorService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CategoryService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CustomerService;
import ua.rubezhanskii.javabookshop.model.Author;
import ua.rubezhanskii.javabookshop.model.Category;
import ua.rubezhanskii.javabookshop.model.Customer;

public  class HerokuHelper {


    @Autowired
    private static JdbcTemplate jdbcTemplate;
    @Autowired
    private static AuthorService authorService;
    @Autowired
    private static CustomerService customerService;
    @Autowired
    private static CategoryService categoryService;



    public static   void update(Author author) {
        final String UPDATE_AUTHOR="UPDATE author set author1=?, author2=?,author3=?,author4=? WHERE authorId=?";
        jdbcTemplate.update(UPDATE_AUTHOR,author.getAuthor1(),author.getAuthor2(),author.getAuthor3(),author.getAuthor4(),author.getAuthorId());
    }

    public static Integer save(Author author) {
        if (author.getAuthorId().equals(authorService.getAuthorOfBook(author.getAuthorId()).getAuthorId())) {
            update(author);
        } else {
            final String INSERT_AUTHOR="INSERT INTO author (author1, author2, author3, author4) VALUES (?,?,?,?)";
            jdbcTemplate.update(INSERT_AUTHOR,author.getAuthor1(),author.getAuthor2(),author.getAuthor3(),author.getAuthor4());
        }
        return authorService.getAuthorByName(author.getAuthor1()).getAuthorId() ;

    }


    public static Integer save(Customer customer) {
     final String INSERT_CUSTOMER="INSERT INTO customer (firstName, lastName, address, city, zip, country, phoneHome, phoneMobile, email, login) VALUES  (?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(INSERT_CUSTOMER, customer.getFirstName(),customer.getLastName(),customer.getAddress(),customer.getCity(),customer.getZip(),customer.getCountry(),
                customer.getPhoneHome(),customer.getPhoneMobile(),customer.getEmail(),customer.getLogin());
      return   customerService.getCustomerByLogin(customer.getLogin()).getCustomerId();
    }

    public static Integer save(Category category) {

        if (category.getCategoryId().equals(categoryService.getCategoryById(category.getCategoryId()).getCategoryId())) {
            categoryService.update(category);
        }else {
          final String INSERT_CATEGORY="INSERT  INTO category (category) VALUES (?)";
          jdbcTemplate.update(INSERT_CATEGORY,category.getCategory());

    }
        return  categoryService.getCategoryByName(category.getCategory()).getCategoryId();
    }
}
