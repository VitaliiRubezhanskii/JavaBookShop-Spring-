package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CartService;
import ua.rubezhanskii.javabookshop.dto.CartDTO;
import ua.rubezhanskii.javabookshop.dto.CartItemDto;
import ua.rubezhanskii.javabookshop.model.Book;
import ua.rubezhanskii.javabookshop.model.Cart;
import ua.rubezhanskii.javabookshop.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartJdbcTemplate implements CartService{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BookJdbcTemplate bookJdbcTemplate;
    @Autowired
    private CustomerJdbcTemplate customerJdbcTemplate;

    @Override
    public void update(Book book) {
        CartItemDto cartItem=new CartItemDto(book,null);
        jdbcTemplate.update("UPDATE cart SET bookQuantity=?, creationTime=now(),globalId=?, machineName=? WHERE bookId=?", cartItem.getBook().getBookId(),
                cartItem.getBook().getBookQuantity(),new Cart().generateGUID(),System.getProperty("user.name"));
            }

    @Override
    public void save(Book book) {
        CartItemDto cartItem=new CartItemDto(book,null);
        jdbcTemplate.update("INSERT INTO cart(bookId, bookQuantity, globalId,creationTime,machineName) VALUES (?,?,?,NOW(),?)",
                cartItem.getBook().getBookId(),cartItem.getBook().getBookQuantity(),new Cart().generateGUID(),System.getProperty("user.name"));
    }



    @Override
    public void delete(Integer bookId) {
        jdbcTemplate.update("DELETE FROM cart WHERE bookId=?", bookId);
    }

  /*  public void saveOrder(Integer machineName){
        jdbcTemplate.update("UPDATE cart SET status='completed' WHERE customerId=?",customerId);

    }*/

  public void deleteByCustomer(String machineName){
      jdbcTemplate.update("DELETE FROM cart WHERE machineName=?",machineName);
  }


    @Override
    public List<CartItemDto> getCartItems() {
        return jdbcTemplate.query("SELECT * FROM cart", new RowMapper<CartItemDto>() {
            @Override
            public CartItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                CartItemDto cartItemDto=new CartItemDto();
                cartItemDto.setBook(bookJdbcTemplate.getBookById(rs.getInt("bookId")));
                cartItemDto.setMachineName((rs.getString("machineName")));
                return  cartItemDto;
            }
        });
    }

    public List<CartItemDto>getCartItemsByMachineName(String machineName){
        return jdbcTemplate.query("SELECT * FROM cart WHERE machineName=?",new Object[]{machineName},new CartItemRowMapper());
    }

   public Integer countItems(String machineName){
       return getCartItemsByMachineName(machineName).size();
    }

    public CartDTO getCartDTO(){
        CartDTO dto=new CartDTO();
        List<CartItemDto> cartItemDtos= jdbcTemplate.query("SELECT * FROM cart",new CartItemRowMapper());
        dto.setCartItems(cartItemDtos);
        return dto;
    }

    @Override
    public boolean exists(Integer bookId) {
        boolean b=false;
        List<Book>books=(List<Book>)jdbcTemplate.query("SELECT * FROM cart WHERE bookId=?",new Object[]{bookId},
               new CartRowMapper());
        return books.size()<1 ? false : true;
    }

    @Override
    public void saveOrder(Customer customer){
        List<CartItemDto> cartItems= getCartItemsByMachineName(System.getProperty("user.name"));

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
            Object username=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String login=((UserDetails)username).getUsername();
          Customer customer1=customerJdbcTemplate.getCustomerByLogin(login);
            cartItems.forEach(item -> {
                jdbcTemplate.update("INSERT INTO ordertable(bookId,customerId, orderDate, globalId) VALUES (?,?,NOW(),?)"
                        , item.getBook().getBookId(), customer1.getCustomerId(), new Cart().generateGUID());
            });

        }
        else {
            Integer customerId = customerJdbcTemplate.save(customer);
            cartItems.forEach(item -> {
                jdbcTemplate.update("INSERT INTO ordertable(bookId,customerId, orderDate, globalId) VALUES (?,?,NOW(),?)"
                        , item.getBook().getBookId(), customerId, new Cart().generateGUID());
            });

        }

        deleteByCustomer(System.getProperty("user.name"));

    }

    private class CartRowMapper implements RowMapper {

        @Override
        public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cart cart = new Cart();
            cart.setBook(bookJdbcTemplate.getBookById(rs.getInt("bookId")));
            cart.setBookQuantity(rs.getInt("bookQuantity"));
            cart.setGlobalId(rs.getString("globalId"));
            cart.setCreationTime(rs.getString("creationTime"));
            cart.setCustomer(customerJdbcTemplate.getCustomerById(rs.getInt("customerId")));
            return cart;
        }
    }

    private class CartItemRowMapper implements RowMapper{
        @Override
        public CartItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            CartItemDto cartItemDto=new CartItemDto();
            cartItemDto.setBook(bookJdbcTemplate.getBookById(rs.getInt("bookId")));
            cartItemDto.setMachineName(rs.getString("machineName"));
            return cartItemDto;
        }
    }
}
