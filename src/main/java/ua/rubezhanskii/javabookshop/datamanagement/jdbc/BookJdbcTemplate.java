package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ua.rubezhanskii.javabookshop.datamanagement.repository.AuthorService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.BookService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CategoryService;
import ua.rubezhanskii.javabookshop.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
@Service
public class BookJdbcTemplate  implements BookService {


    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private CategoryService categoryService;

    private AuthorService authorService;

    @Autowired
    public BookJdbcTemplate(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, CategoryService categoryService, AuthorService authorService) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @Override
    public void update(Book book) {
        final String UPDATE_BOOK="UPDATE book SET coverImage=?,  price=?, bookTitle=?,  publisher=?, ISBN=?, lang=?, details=?,inventoryStock=? WHERE bookId=?";
          jdbcTemplate.update(UPDATE_BOOK, book.getISBN(), book.getPrice(),book.getBookTitle(),book.getPublisher(),book.getISBN(),
                book.getLanguage(),book.getDetails(),book.getInventoryStock(),book.getBookId());
    }

    @Override
    public void save(Book book) {
        final String INSERT_BOOK="INSERT INTO book(coverImage, authorId, price, bookTitle, categoryId,  publisher, ISBN, lang, details,inventoryStock) VALUES (?,?,?,?,?,?,?,?,?,?)";
        if (book.getBookId().equals(getBookById(book.getBookId()).getBookId())){
            update(book);
        }else {
            Integer categoryId = categoryService.save(book.getCategory());
            Integer authorId = authorService.save(book.getAuthor());
            jdbcTemplate.update(INSERT_BOOK, book.getISBN(), authorId, book.getPrice(), book.getBookTitle(), categoryId, book.getPublisher(),
                    book.getISBN(), book.getLanguage(), book.getDetails(),book.getInventoryStock());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> search(String criteria) {
        final String SEARCH_BOOK="SELECT * FROM book INNER JOIN author ON book.authorId=author.authorId where " +
                "book.ISBN LIKE :criteria OR book.bookTitle LIKE :criteria or author.author1 LIKE :criteria";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("criteria", "%"+criteria+"%");
        return namedParameterJdbcTemplate.query(SEARCH_BOOK,params,new BookRowMapper());
    }

    @Override
    public Book getBookByIdFromCart(Integer bookId) {
        final String BOOK_FROM_CART="SELECT * FROM book INNER JOIN  cart ON cart.bookId=book.bookId  WHERE cart.bookId=?";
        return (Book) jdbcTemplate.queryForObject(BOOK_FROM_CART, new Object[]{bookId}, new BookRowMapper());
    }

    @Override
    public void delete(Integer bookId) {
        final String DELETE_BOOK="DELETE FROM book WHERE bookId=?";
        jdbcTemplate.update(DELETE_BOOK, bookId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getBooks() {
        final String SELECT_BOOK="SELECT * FROM book";
        return jdbcTemplate.query(SELECT_BOOK, new BookRowMapper());
    }

    @Override
    public Book getBookById(Integer bookId) {
        final String BOOK_BY_ID ="SELECT * FROM book WHERE bookId=?";
        return (Book) jdbcTemplate.queryForObject(BOOK_BY_ID, new Object[]{bookId}, new BookRowMapper());
    }

    @Override
    public Book getBookByISBN(String ISBN) {
        final String BOOK_BY_ISBN="SELECT * FROM book WHERE ISBN=?";
        return (Book) jdbcTemplate.queryForObject(BOOK_BY_ISBN, new Object[]{ISBN}, new BookRowMapper());
    }

   /* public List<Book> getBooksByAuthor(Integer authorId){
        return jdbcTemplate.query("Select * from book where authorId=?",new Object[]{authorId},new BookRowMapper());
    }*/

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getBooksFromCategory(Integer categoryId){
        final  String BOOK_BY_CATEGORY="Select * from book INNER JOIN category ON category.categoryId=book.categoryId where category.categoryId=?";
        return jdbcTemplate.query(BOOK_BY_CATEGORY,new Object[]{categoryId},new BookRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean exists(String bookTitle) {
        List<Book>books=(List<Book>)jdbcTemplate.query("SELECT * FROM book WHERE bookTitle=?",new Object[]{bookTitle},
                new BookRowMapper());
        return books.size()>=1;
    }

    @Override
    public List<Book>getFirstSixBooks(){
        List<Book>books=new ArrayList<>();
        final String GET_FIRST_SIX_BOOKS="SELECT * FROM book WHERE ISBN=?";
        Arrays.asList("ISBN 978-1-4302-5999-2","ISBN 978-1-4842-0823-6","ISBN 978-1-4842-0127-5",
                "ISBN 978-1-4842-0268-5","ISBN 978-1-4842-0641-6","ISBN 978-1-4842-0793-2").forEach(item->{
            books.add((Book)jdbcTemplate.queryForObject(GET_FIRST_SIX_BOOKS, new Object[]{item}, new BookRowMapper()));
        });
        return books;
    }

            private  class BookRowMapper implements RowMapper {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setCoverImage(rs.getString("coverImage"));
                book.setPrice(rs.getDouble("price"));
                book.setBookTitle(rs.getString("bookTitle"));
                book.setCategory(categoryService.getCategoryById(rs.getInt("categoryId")));
                book.setPublisher(rs.getString("publisher"));
                book.setISBN(rs.getString("ISBN"));
                book.setLanguage(rs.getString("lang"));
                book.setDetails(rs.getString("details"));
                book.setBookQuantity(rs.getInt("bookQuantity"));
                book.setAuthor(authorService.getAuthorOfBook(rs.getInt("authorId")));
                book.setInventoryStock(rs.getInt("inventoryStock"));
                return book;
            }
        }

    }




