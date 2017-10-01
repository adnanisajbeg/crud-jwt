package com.rest.example.security.auth0.model;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-10-01
 */
public class Login {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public Login setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Login setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Login login = (Login) o;

        if (username != null ? !username.equals(login.username) : login.username != null)
            return false;
        return password != null ? password.equals(login.password) : login.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Login{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
