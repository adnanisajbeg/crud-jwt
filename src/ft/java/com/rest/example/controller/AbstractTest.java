package com.rest.example.controller;

import com.rest.example.model.User;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-18
 */
public abstract class AbstractTest {
    User user;
    User user2;
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";
    private String password = "pass123456";
    private String username1 = "john.doe";
    private String username2 = "doe.john";
    String userUrl = "/users";

    @Autowired
    TestRestTemplate restTemplate;

    @Before
    public void setup() {
        user = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(username1)
                .setPassword(password)
                .addRole(1);

        user2 = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(username2)
                .setPassword(password)
                .addRole(1);

    }
}
