package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ua.rubezhanskii.javabookshop.datamanagement.repository.BookService;
import ua.rubezhanskii.javabookshop.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookJdbcTemplate  implements BookService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private CategoryJdbcTemplate categoryJdbcTemplate;

    @Autowired
    private AuthorJdbcTemplate authorJdbcTemplate;

    @Override
    public void update(Book book) {
          jdbcTemplate.update("UPDATE book SET coverImage=?, authorId=?, price=?, bookTitle=?, categoryId=?, publisher=?, ISBN=?, lang=?, details=? " +
                        "WHERE bookId=?", book.getCoverImage(), book.getAuthor().getAuthorId(),book.getPrice(),book.getBookTitle(),book.getCategory().getCategoryId(),book.getPublisher(),book.getISBN(),
                book.getLanguage(),book.getDetails(),book.getBookId());
    }

    @Override
    public void save(Book book) {
       Integer categoryId=categoryJdbcTemplate.save(book.getCategory());
        Integer authorId=authorJdbcTemplate.save(book.getAuthor());
        jdbcTemplate.update("INSERT INTO book(coverImage, authorId, price, bookTitle, categoryId,  publisher, ISBN, lang, details) " +
                                "VALUES (?,?,?,?,?,?,?,?,?)", book.getCoverImage(),authorId,book.getPrice(),book.getBookTitle(),categoryId,book.getPublisher(),
                                 book.getISBN(),book.getLanguage(),book.getDetails());

    }

    @Override
    public List<Book> search(String criteria) {
        final String query="SELECT * FROM book INNER JOIN author ON book.authorId=author.authorId where book.ISBN LIKE :criteria OR book.bookTitle LIKE :criteria or author.author1 LIKE :criteria";
        Map<String,Object> params = new HashMap<String,Object>();

        params.put("criteria", "%"+criteria+"%");
        return namedParameterJdbcTemplate.query(query,params,new BookRowMapper());

    }

    @Override
    public Book getBookByIdFromCart(Integer bookId) {
        return (Book) jdbcTemplate.queryForObject("SELECT * FROM book INNER JOIN  cart ON cart.bookId=book.bookId  WHERE cart.bookId=?", new Object[]{bookId}, new BookRowMapper());
    }

    @Override
    public void delete(Integer bookId) {
        jdbcTemplate.update("DELETE FROM book WHERE bookId=?", bookId);

    }

    @Override
    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM book", new BookRowMapper());
    }

    @Override
    public Book getBookById(Integer bookId) {
        return (Book) jdbcTemplate.queryForObject("SELECT * FROM book WHERE bookId=?", new Object[]{bookId}, new BookRowMapper());
    }

    @Override
    public Book getBookByISBN(String ISBN) {
        return (Book) jdbcTemplate.queryForObject("SELECT * FROM book WHERE ISBN=?", new Object[]{ISBN}, new BookRowMapper());
    }

   /* public List<Book> getBooksByAuthor(Integer authorId){
        return jdbcTemplate.query("Select * from book where authorId=?",new Object[]{authorId},new BookRowMapper());
    }*/



    @Override
    public List<Book> getBooksFromCategory(Integer categoryId){
        return jdbcTemplate.query("Select * from book INNER JOIN category ON category.categoryId=book.categoryId" +
                " where category.categoryId=? ",new Object[]{categoryId},new BookRowMapper());
    }

    @Override
    public boolean exists(Integer bookId) {
        boolean b=false;
        List<Book> books=(List<Book>)jdbcTemplate.query("SELECT * FROM book WHERE bookId=?",new Object[]{bookId},
                new BookRowMapper());
        return books.size()<1 ? false : true;
    }

    @Override
    public List<Book>getFirstSixBooks(){
        List<Book>books=new ArrayList<>();
        Arrays.asList("ISBN 978-1-4302-5999-2","ISBN 978-1-4842-0823-6","ISBN 978-1-4842-0127-5",
                "ISBN 978-1-4842-0268-5","ISBN 978-1-4842-0641-6","ISBN 978-1-4842-0793-2").forEach(item->{
            books.add((Book)jdbcTemplate.queryForObject("SELECT * FROM book WHERE ISBN=?", new Object[]{item}, new BookRowMapper()));
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
        book.setCategory(categoryJdbcTemplate.getCategoryById(rs.getInt("categoryId")));
        book.setPublisher(rs.getString("publisher"));
        book.setISBN(rs.getString("ISBN"));
        book.setLanguage(rs.getString("lang"));
        book.setDetails(rs.getString("details"));
        book.setBookQuantity(rs.getInt("bookQuantity"));
        book.setAuthor(authorJdbcTemplate.getAuthorOfBook(rs.getInt("authorId")));
        book.setInventoryStock(rs.getInt("inventoryStock"));
        return book;
    }
}

 }




