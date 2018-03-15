package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.rubezhanskii.javabookshop.datamanagement.repository.OrderService;
import ua.rubezhanskii.javabookshop.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
@Repository
public class OrderJdbcTemplate implements OrderService{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CustomerJdbcTemplate customerJdbcTemplate;

    @Autowired
    private BookJdbcTemplate bookJdbcTemplate;

    @Override
    public List<Order> getOrdersByDate(Date fromDate, Date toDate){
       return jdbcTemplate.query("Select * from ordertable where orderDate between ? and ?",new Object[]{fromDate,toDate},new OrderRowMapper());
    }

    @Override
    public Order getOrdersByCustomer(String customerAttribute){
        return (Order) jdbcTemplate.queryForObject("Select * from ordertable INNER JOIN customer ON ordertable.customerId=customer.customerId WHERE " +
                " customer.lastName like '%"+customerAttribute+"%' or customer.email LIKE '%"+customerAttribute+"%'",new Object[]{},new OrderRowMapper());
    }

    @Override
    public  List<Order>getOrders(){
        return jdbcTemplate.query("SELECT * FROM ordertable",new OrderRowMapper());
    }



private class OrderRowMapper implements RowMapper{

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order=new Order();
        order.setOrderId(rs.getInt("orderId"));
        order.setCustomer(customerJdbcTemplate.getCustomerById(rs.getInt("customerId")));
        order.setBook(bookJdbcTemplate.getBookById(rs.getInt("bookId")));
        order.setOrderDate(rs.getDate("orderDate"));
        // order.setCartItems(cartJdbcTemplate.getCartItemsByMachineName(rs.getString("machineName")));
        order.setGlobalId(rs.getString("globalId"));
        return order ;
    }
}


}
