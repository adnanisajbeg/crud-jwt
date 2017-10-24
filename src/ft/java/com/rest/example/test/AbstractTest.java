package com.rest.example.test;

import com.rest.example.model.User;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-18
 */
public abstract class AbstractTest {
    static String USER_URL = "/users";
    static String INFO_URL = "/info";
    static String LOGIN_URL = "/login";

    User user;
    User userWithRandomUsername;
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";
    private String password = "pass123456";
    private String username = "john.doe";

    @Autowired
    TestRestTemplate restTemplate;

    @Before
    public void setup() {
        RestTemplate restTemplate = this.restTemplate.getRestTemplate();

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);
        restTemplate.setRequestFactory(requestFactory);

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
