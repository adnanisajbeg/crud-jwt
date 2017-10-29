package com.rest.example.test;

import com.rest.example.model.User;
import com.rest.example.security.auth0.model.Login;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

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
    String adminUsername = "admin";
    String adminPass = "password";
    String jwtAuthToken;

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

    <T> HttpEntity createHttpEntityForLogin(String username, String password) {
        HttpHeaders headers = getHeaders();
        return new HttpEntity<>(new Login().setUsername(username).setPassword(password), headers);
    }

    String getUrlForUser(User user) {
        ResponseEntity<String> response = restTemplate.exchange(
                USER_URL,
                POST,
                createHttpEntityForPost(user),
                String.class
        );
        //        ResponseEntity<String> response = restTemplate.postForEntity(USER_URL, user, String.class);
        List<String> links = response.getHeaders().get(HttpHeaders.LOCATION);
        return links == null ? null : links.get(0);
    }

    <T> HttpEntity<T> createHttpEntityForGet() {
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", jwtAuthToken);
        return new HttpEntity<>(headers);
    }

    <T> HttpEntity<T> createHttpEntityForPost(T bodyObject) {
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", jwtAuthToken);
        return new HttpEntity<>(bodyObject, headers);
    }

    <T> HttpEntity<T> createHttpEntityForDelete() {
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", jwtAuthToken);
        return new HttpEntity<>(headers);
    }

    HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(APPLICATION_JSON));
        headers.setContentType(APPLICATION_JSON);
        return headers;
    }
}
