package com.rest.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-19
 */
@Entity
public class UserDAO {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public UserDAO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDAO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDAO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDAO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDAO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDAO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDAO setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserDAO userDAO = (UserDAO) o;

        if (id != null ? !id.equals(userDAO.id) : userDAO.id != null)
            return false;
        if (firstName != null ? !firstName.equals(userDAO.firstName) : userDAO.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(userDAO.lastName) : userDAO.lastName != null)
            return false;
        if (email != null ? !email.equals(userDAO.email) : userDAO.email != null)
            return false;
        if (phoneNumber != null ? !phoneNumber.equals(userDAO.phoneNumber) : userDAO.phoneNumber != null)
            return false;
        if (username != null ? !username.equals(userDAO.username) : userDAO.username != null)
            return false;
        return password != null ? password.equals(userDAO.password) : userDAO.password == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDAO{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
                + ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", username='" + username + '\''
                + ", password='********" + '\'' + '}';
    }
}
