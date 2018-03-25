package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ua.rubezhanskii.javabookshop.datamanagement.repository.BookService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CartService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CustomerService;
import ua.rubezhanskii.javabookshop.dto.CartItemDto;
import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.model.Cart;
import ua.rubezhanskii.javabookshop.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Service
public class CartJdbcTemplate implements CartService{

    private JdbcTemplate jdbcTemplate;

    private BookService bookService;

    private CustomerService customerService;

    @Autowired
    public CartJdbcTemplate(JdbcTemplate jdbcTemplate, BookService bookService, CustomerService customerService) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @Override
    public void update(Book book) {
        CartItemDto cartItem=new CartItemDto(book,null,null);
        final String UPDATE_CART="UPDATE cart SET bookQuantity=?, creationTime=now(),globalId=?, login=? WHERE bookId=?";
        jdbcTemplate.update(UPDATE_CART, cartItem.getBook().getBookId(),
                cartItem.getBook().getBookQuantity(),new Cart().generateGUID(),getLoggedUserName());
    }

    @Override
    public void save(Book book) {
        CartItemDto cartItem=new CartItemDto(book,null,null);
        final String INSRT_INTO_CART="INSERT INTO cart(bookId, bookQuantity, globalId,creationTime,login) VALUES (?,?,?,NOW(),?)";
        final String UPDATE_STOCK="Update book set inventoryStock=inventoryStock-?,bookQuantity=bookQuantity+? WHERE bookId=?";
        jdbcTemplate.update(INSRT_INTO_CART,cartItem.getBook().getBookId(),cartItem.getBook().getBookQuantity(),new Cart().generateGUID(),getLoggedUserName());
        jdbcTemplate.update(UPDATE_STOCK,book.getBookQuantity(),book.getBookQuantity(),book.getBookId());
    }

    @Override
    public void delete(Integer bookId) {
        final String DELETE_FROM_CART="DELETE FROM cart WHERE bookId=?";
        jdbcTemplate.update(DELETE_FROM_CART, bookId);
    }

    @Override
    public void deleteAll() {
        final String DELETE_ALL_FROM_CART="Delete FROM cart WHERE bookId>0";
        jdbcTemplate.update(DELETE_ALL_FROM_CART);
    }

    public void deleteByCustomer(String login){
        final String DELETE_BY_CUSTOMER="DELETE FROM cart WHERE login=?";
        jdbcTemplate.update(DELETE_BY_CUSTOMER,login);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CartItemDto> getCartItems() {
        final String SELECT_FROM_CART="SELECT * FROM cart";
        return jdbcTemplate.query(SELECT_FROM_CART, new CartItemRowMapper());
    }

    @SuppressWarnings("unchecked")
    public List<CartItemDto>getCartItemsByLogin(String login){
        final String CARTITEMS_BY_LOGIN="SELECT * FROM cart WHERE login=?";
        return jdbcTemplate.query(CARTITEMS_BY_LOGIN,new Object[]{login},new CartItemRowMapper());
    }

   public Integer countItems(String login){
       return getCartItemsByLogin(login).size();
    }

  /*  @SuppressWarnings("unchecked")
    public CartDTO getCartDTO(){
        CartDTO dto=new CartDTO();
        List<CartItemDto> cartItemDtos= jdbcTemplate.query("SELECT * FROM cart",new CartItemRowMapper());
        dto.setCartItems(cartItemDtos);
        return dto;
    }*/

    @Override
    @SuppressWarnings("unchecked")
    public boolean exists(Integer bookId) {
        List<Cart>carts=(List<Cart>)jdbcTemplate.query("SELECT * FROM cart WHERE bookId=?",new Object[]{bookId},
                new CartRowMapper());
        return carts.size()>=1;
    }

    @Override
    public void saveOrder(Customer customer){
        List<CartItemDto> cartItems= getCartItemsByLogin(getLoggedUserName());
        final String INSERT_ORDER="INSERT INTO ordertable(bookId,customerId, orderDate, globalId,soldItems) VALUES (?,?,NOW(),?,?)";
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
             String login=getLoggedUserName();
             Customer customer1=customerService.getCustomerByLogin(login);
             cartItems.forEach(item -> jdbcTemplate.update(INSERT_ORDER, item.getBook().getBookId(), customer1.getCustomerId(), new Cart().generateGUID(),item.getBookQuantity()));
        }
        else {
            Integer customerId = customerService.save(customer);
            cartItems.forEach(item -> jdbcTemplate.update(INSERT_ORDER, item.getBook().getBookId(), customerId, new Cart().generateGUID(),item.getBookQuantity()));
        }
        deleteByCustomer(getLoggedUserName());
    }

    public String getLoggedUserName(){
        try {
            Object username = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String login;
            if (username == null) {
                 login = "guest";
            } else {
                 login = ((UserDetails) username).getUsername();
            }
            return login;
        }catch (ClassCastException ex){
            return "guest";
        }
    }

    private class CartRowMapper implements RowMapper {
        @Override
        public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cart cart = new Cart();
            cart.setBook(bookService.getBookById(rs.getInt("bookId")));
            cart.setBookQuantity(rs.getInt("bookQuantity"));
            cart.setGlobalId(rs.getString("globalId"));
            cart.setCreationTime(rs.getString("creationTime"));
           // cart.setCustomer(customerJdbcTemplate.getCustomerById(rs.getInt("customerId")));
            return cart;
        }
    }

    private class CartItemRowMapper implements RowMapper{
        @Override
        public CartItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            CartItemDto cartItemDto=new CartItemDto();
            cartItemDto.setBook(bookService.getBookById(rs.getInt("bookId")));
            cartItemDto.setBookQuantity(rs.getInt("bookQuantity"));
            cartItemDto.setMachineName(rs.getString("login"));
            return cartItemDto;
        }
    }

}
