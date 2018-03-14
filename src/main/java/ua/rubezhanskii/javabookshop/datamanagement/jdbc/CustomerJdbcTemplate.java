package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CustomerService;
import ua.rubezhanskii.javabookshop.model.Customer;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerJdbcTemplate implements CustomerService {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    private SimpleJdbcInsert jdbcInsert;

    @PostConstruct
    private void postConstruct() {

        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("customer")
                .usingGeneratedKeyColumns("customerId");

    }

    @Override
    public void update(Customer customer) {
        jdbcTemplate.update("UPDATE customer SET firstName=?, lastName=?, address=?, city=?,zip=?, country=?, phoneHome=?, phoneMobile=?, email=?, login=? WHERE" +
                " customerId=?",customer.getFirstName(),customer.getLastName(),customer.getAddress(),customer.getCity(),customer.getZip(),customer.getCountry(),
                customer.getPhoneHome(),SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(),
                customer.getPhoneMobile(),customer.getEmail(),customer.getCustomerId());
    }

    @Override
    public Integer save(Customer customer) {


        SqlParameterSource parameterSource=new BeanPropertySqlParameterSource(customer);
        Number number=jdbcInsert.executeAndReturnKey(parameterSource);
        if (number!=null){
            return number.intValue();
        }
        throw new RuntimeException("Cannot retrieve primary key");
      /*  jdbcTemplate.update("INSERT INTO customer(firstName, lastName, address, city, zip, country, phoneHome, phoneMobile, email,login) " +
                        "VALUES (?,?,?,?,?,?,?,?,?)", customer.getFirstName(),customer.getLastName(),customer.getAddress(),customer.getCity(),
                customer.getZip(),customer.getCountry(),customer.getPhoneHome(),customer.getPhoneMobile(),customer.getEmail(), SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
   */ }

    @Override
    public void delete(Integer customerId) {
        jdbcTemplate.update("DELETE FROM customer WHERE customerId=?", customerId);
    }

    @Override
    public List<Customer> getCustomers() {
        return jdbcTemplate.query("SELECT * FROM customer", new CustomerRowMapper());
    }

    @Override
    public Customer getCustomerById(Integer customerId) {
        return (Customer) jdbcTemplate.queryForObject("SELECT * FROM customer WHERE customerId=?", new Object[]{customerId}, new CustomerRowMapper());
    }

    @Override
    public Customer getCustomerByLogin(String login) {
         return (Customer) jdbcTemplate.queryForObject("SELECT * FROM customer WHERE login=?",
                new Object[]{login}, new CustomerRowMapper());
    }

    @Override
    public boolean exists(Integer customerId) {
        boolean b=false;
        List<Customer> customers=(List<Customer>)jdbcTemplate.query("SELECT * FROM customer WHERE customerId=?",new Object[]{customerId},
                new CustomerRowMapper());
        return customers.size()<1 ? false : true;
    }


    private class CustomerRowMapper implements RowMapper{
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer=new Customer();
            customer.setCustomerId(rs.getInt("customerId"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setLastName(rs.getString("lastName"));
            customer.setAddress(rs.getString("address"));
            customer.setCity(rs.getString("city"));
            customer.setZip(rs.getString("zip"));
            customer.setCountry(rs.getString("country"));
            customer.setPhoneHome(rs.getString("phoneHome"));
            customer.setPhoneMobile(rs.getString("phoneMobile"));
            customer.setEmail(rs.getString("email"));
            return customer;
    }
    }
}
