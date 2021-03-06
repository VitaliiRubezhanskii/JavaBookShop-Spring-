package ua.rubezhanskii.javabookshop.security;


import java.util.Set;

public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String password;
    private Set<UserProfyle>userProfyles;


    public User() {
    }

    public User(Integer id, String firstName, String lastName, String login,
                String email, String password, Set<UserProfyle> userProfyles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.password = password;
        this.userProfyles = userProfyles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserProfyle> getUserProfyles() {
        return userProfyles;
    }

    public void setUserProfyles(Set<UserProfyle> userProfyles) {
        this.userProfyles = userProfyles;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
