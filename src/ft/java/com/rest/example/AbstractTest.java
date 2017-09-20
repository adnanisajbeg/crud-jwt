package com.rest.example;

import com.rest.example.model.User;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-18
 */
public abstract class AbstractTest {
    static String USER_URL = "/users";
    static String INFO_URL = "/info";

    User user;
    User userWithRandomUsername;
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";
    String newPhoneNumber = "23333332";
    private String password = "pass123456";
    private String username = "john.doe";

    @Autowired
    TestRestTemplate restTemplate;

    @Before
    public void setup() {
        user = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(username)
                .setPassword(password);

        userWithRandomUsername = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(randomAlphabetic(10))
                .setPassword(randomAlphanumeric(20));
    }
}
