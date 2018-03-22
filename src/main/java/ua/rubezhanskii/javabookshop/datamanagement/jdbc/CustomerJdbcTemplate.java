package ua.rubezhanskii.javabookshop.datamanagement.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.rubezhanskii.javabookshop.datamanagement.repository.CustomerService;
import ua.rubezhanskii.javabookshop.model.Customer;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerJdbcTemplate implements CustomerService {



    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public CustomerJdbcTemplate(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void postConstruct() {
        jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("customer")
                .usingGeneratedKeyColumns("customerId");
    }

    @Override
    public void update(Customer customer) {
        final String UPDATE_CUSTOMER="UPDATE customer SET firstName=?, lastName=?, address=?, city=?,zip=?, country=?, phoneHome=?, phoneMobile=?, email=?, login=? WHERE customerId=?";
        jdbcTemplate.update(UPDATE_CUSTOMER,customer.getFirstName(),customer.getLastName(),customer.getAddress(),customer.getCity(),customer.getZip(),customer.getCountry(),
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
      }

    @Override
    public void delete(Integer customerId) {
        final String DELETE_FROM_CUSTOMER="DELETE FROM customer WHERE customerId=?";
        jdbcTemplate.update(DELETE_FROM_CUSTOMER, customerId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Customer> getCustomers() {
        final String SELECT_CUSTOMERS="SELECT * FROM customer";
        return jdbcTemplate.query(SELECT_CUSTOMERS, new CustomerRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Customer getCustomerById(Integer customerId) {
        final String CUSTOMER_BY_ID="SELECT * FROM customer WHERE customerId=?";
        return (Customer) jdbcTemplate.queryForObject(CUSTOMER_BY_ID, new Object[]{customerId}, new CustomerRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Customer getCustomerByLogin(String login) {
        final String CUSTOMER_BY_LOGIN="SELECT * FROM customer WHERE login=?";
        return (Customer) jdbcTemplate.queryForObject(CUSTOMER_BY_LOGIN,new Object[]{login}, new CustomerRowMapper());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean exists(Integer customerId) {
      final String COUNT_CUSTOMERS="SELECT * FROM customer WHERE customerId=?";
        return (Integer)jdbcTemplate.queryForObject(COUNT_CUSTOMERS,new Object[]{customerId},new CustomerRowMapper())!=0;
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
