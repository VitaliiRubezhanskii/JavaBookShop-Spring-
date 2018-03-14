/*package ua.rubezhanskii.javabookshop.datamanagement.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import ua.rubezhanskii.javabookshop.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper {
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
}*/
