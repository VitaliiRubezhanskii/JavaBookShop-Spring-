package ua.rubezhanskii.javabookshop.datamanagement.rowmappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.CartJdbcTemplate;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.CustomerJdbcTemplate;
import ua.rubezhanskii.javabookshop.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper {

    @Autowired
    private CustomerJdbcTemplate customerJdbcTemplate;
    @Autowired
    private CartJdbcTemplate cartJdbcTemplate;

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order=new Order();
        order.setOrderId(rs.getInt("orderId"));
        order.setCustomer(customerJdbcTemplate.getCustomerById(rs.getInt("customerId")));
        order.setOrderDate(rs.getDate("orderDate"));
        order.setCartItems(cartJdbcTemplate.getCartItemsByMachineName(rs.getString("machineName")));
        return order ;
    }
}
