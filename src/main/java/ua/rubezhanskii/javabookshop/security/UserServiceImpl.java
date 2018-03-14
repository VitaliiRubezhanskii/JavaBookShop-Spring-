package ua.rubezhanskii.javabookshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User findById(Integer id) {
        return (User) jdbcTemplate.queryForObject("select * from user where id=?",new Object[]{id},new UserRowMapper()) ;
    }

    @Override
    public Users findByUsername(String username) {
        return jdbcTemplate.queryForObject("select * from users where username=?", new Object[]{username}, new RowMapper<Users>() {

            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users users=new Users();
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                users.setEnabled(rs.getInt("enabled"));
                return users;
            }
        });
    }

    @Override
    public void save(Users users) {
    jdbcTemplate.update("insert into users(username, password, enabled) VALUES (?,?,1)",users.getUsername(),passwordEncoder.encode(users.getPassword()));
    jdbcTemplate.update("insert into user_roles(username, role) VALUES (?,?)",users.getUsername(),users.getRole());
    }

    @Override
    public void deleteBySSO(String sso) {

    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query("SELECT * from user",new UserRowMapper());
    }

    private final class UserRowMapper implements RowMapper{
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user=new User();
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}
