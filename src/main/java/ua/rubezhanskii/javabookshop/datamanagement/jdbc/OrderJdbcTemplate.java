package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.rubezhanskii.javabookshop.datamanagement.repository.BookService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CustomerService;
import ua.rubezhanskii.javabookshop.datamanagement.repository.OrderService;
import ua.rubezhanskii.javabookshop.model.Customer;
import ua.rubezhanskii.javabookshop.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class OrderJdbcTemplate implements OrderService{

    private JdbcTemplate jdbcTemplate;

    private CustomerService customerService;

    private BookService bookService;

    public OrderJdbcTemplate(JdbcTemplate jdbcTemplate, CustomerService customerService, BookService bookService) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerService = customerService;
        this.bookService = bookService;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> getOrdersByDate(Date fromDate, Date toDate){
        final String ORDERS_BY_DATE="Select * from ordertable where orderDate between ? and ?";
        return jdbcTemplate.query(ORDERS_BY_DATE,new Object[]{fromDate,toDate},new OrderRowMapper());
    }

    @Override
    public Order getOrdersByCustomer(String customerAttribute){
        return (Order) jdbcTemplate.queryForObject("Select * from ordertable INNER JOIN customer ON ordertable.customerId=customer.customerId WHERE " +
                " customer.lastName like '%"+customerAttribute+"%' or customer.email LIKE '%"+customerAttribute+"%'",new Object[]{},new OrderRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public  List<Order>getOrders(){
        final  String ORDERS="SELECT * FROM ordertable";
        return jdbcTemplate.query(ORDERS,new OrderRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> getOrdersByCustomer(Customer customer) {
        final String ORDERS_BY_CUSTOMER="SELECT * FROM ordertable where customerId=?";
        return jdbcTemplate.query(ORDERS_BY_CUSTOMER,new Object[]{customer.getCustomerId()},new OrderRowMapper());
    }

    private class OrderRowMapper implements RowMapper{
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order=new Order();
            order.setOrderId(rs.getInt("orderId"));
            order.setCustomer(customerService.getCustomerById(rs.getInt("customerId")));
            order.setBook(bookService.getBookById(rs.getInt("bookId")));
            order.setOrderDate(rs.getDate("orderDate"));
            // order.setCartItems(cartJdbcTemplate.getCartItemsByMachineName(rs.getString("machineName")));
            order.setGlobalId(rs.getString("globalId"));
            order.setSoldItems(rs.getInt("soldItems"));
            return order ;
            }
        }
    }
