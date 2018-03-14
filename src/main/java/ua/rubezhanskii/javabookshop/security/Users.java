package ua.rubezhanskii.javabookshop.security;

import java.util.Set;

public class Users {
    private String username;
    private String password;
    private Integer enabled;
    private Set<UserProfyle> userProfyles;
   private String role;

    public Users() {
    }

    public Users(String username, String password, Integer enabled, Set<UserProfyle> userProfyles, String role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userProfyles = userProfyles;
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Set<UserProfyle> getUserProfyles() {
        return userProfyles;
    }

    public void setUserProfyles(Set<UserProfyle> userProfyles) {
        this.userProfyles = userProfyles;
    }
}
