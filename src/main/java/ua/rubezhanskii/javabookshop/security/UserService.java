package ua.rubezhanskii.javabookshop.security;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    Users findByUsername(String username);

    void save(Users user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();

}
