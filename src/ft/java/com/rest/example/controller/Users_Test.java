package com.rest.example.controller;

import com.rest.example.model.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.HEAD;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.TRACE;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Users_Test {
    private User user;
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";
    private String password = "pass123456";
    private String baseUrl = "http://localhost:8080/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        User user = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setPassword(password)
                .addRole(1);
    }

    // TODO: DELETE OR MODIFY
    @Test
    @Ignore
    public void options_on_users_returns_correct_methods() {
        Set<HttpMethod> allowedHttpMethodsForUsers = this.restTemplate.optionsForAllow(baseUrl + "users");

        assertThat(allowedHttpMethodsForUsers)
                .isNotNull()
                .isNotEmpty()
                .contains(POST).contains(OPTIONS)
                .doesNotContain(GET).doesNotContain(HEAD).doesNotContain(PUT)
                .doesNotContain(PATCH).doesNotContain(DELETE).doesNotContain(TRACE);
    }

    @Test
    public void register_new_user() {
        ResponseEntity<User> answer = restTemplate.postForEntity(baseUrl + "users", user, User.class);

        assertThat(answer).isNotNull();
        assertThat(answer.getStatusCode()).isEqualTo(CREATED);
    }

    @Test
    public void register_new_user_returns_header_with_link_to_user() {
        HttpHeaders headers = restTemplate.postForEntity(baseUrl + "users", user, User.class).getHeaders();

        assertThat(headers).isNotNull().isNotEmpty();
        assertThat(headers.get("Location")).isNotNull().isNotEmpty().hasSize(1);
        assertThat(headers.get("Location").get(0)).isNotNull().isNotBlank();
    }
}
