package com.rest.example.test;

import com.rest.example.security.auth0.model.Login;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-10-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Session_FT extends AbstractTest {

    private String adminUsername = "admin";
    private String adminPass = "password";

    @Test
    public void login_returns_ok() {
        ResponseEntity<String> result = restTemplate.exchange(
                LOGIN_URL,
                POST,
                createHttpEntityForLogin(adminUsername, adminPass),
                String.class
        );
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void login_returns_unauthorized_when_given_random_login() {
        ResponseEntity<String> result = restTemplate.exchange(
                LOGIN_URL,
                POST,
                createHttpEntityForLogin(randomAlphabetic(10), randomAlphanumeric(20)),
                String.class
        );
        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    private <T> HttpEntity createHttpEntityForLogin(String username, String password) {
        HttpHeaders headers = getHeaders();
        return new HttpEntity<>(new Login().setUsername(username).setPassword(password), headers);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(APPLICATION_JSON));
        headers.setContentType(APPLICATION_JSON);
        return headers;
    }
}
