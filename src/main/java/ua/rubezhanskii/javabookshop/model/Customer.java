package ua.rubezhanskii.javabookshop.model;


import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serial_UID=5L;

    private Integer customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String country;
    private String phoneHome;
    private String phoneMobile;
    private String email;
    private String login;


    public Customer() {

    }


    public Customer(Integer customerId, String firstName, String lastName, String address, String city, String zip,
                    String country, String phoneHome, String phoneMobile, String email, String login) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.phoneHome = phoneHome;
        this.phoneMobile = phoneMobile;
        this.email = email;
        this.login = login;
    }

    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
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


    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }


    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }


    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }


    public String getPhoneHome() {
        return phoneHome;
    }
    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }


    public String getPhoneMobile() {
        return phoneMobile;
    }
    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", country='" + country + '\'' +
                ", phoneHome='" + phoneHome + '\'' +
                ", phoneMobile='" + phoneMobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
