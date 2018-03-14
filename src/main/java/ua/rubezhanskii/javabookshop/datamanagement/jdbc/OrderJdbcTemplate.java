package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.rubezhanskii.javabookshop.datamanagement.repository.OrderService;
import ua.rubezhanskii.javabookshop.datamanagement.rowmappers.OrderRowMapper;
import ua.rubezhanskii.javabookshop.model.Order;

import java.util.Date;
import java.util.List;
@Repository
public class OrderJdbcTemplate implements OrderService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

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






}
