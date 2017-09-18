package com.rest.example.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.LOCATION;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserCollection_FT extends AbstractTest {
    // TODO: DELETE OR MODIFY
    @Ignore
    @Test
    public void options_on_users_returns_correct_methods() {
        Set<HttpMethod> allowedHttpMethodsForUsers = this.restTemplate.optionsForAllow(USER_URL);

        assertThat(allowedHttpMethodsForUsers)
                .isNotNull()
                .isNotEmpty()
                .contains(POST).contains(OPTIONS)
                .doesNotContain(GET).doesNotContain(HEAD).doesNotContain(PUT)
                .doesNotContain(PATCH).doesNotContain(DELETE).doesNotContain(TRACE);
    }

    @Test
    public void register_new_user_returns_correct_code() {
        ResponseEntity<String> answer = restTemplate.postForEntity(USER_URL, user, String.class);

        assertThat(answer).isNotNull();
        assertThat(answer.getStatusCode()).isEqualTo(CREATED);
    }

    @Test
    public void register_new_user_returns_header_with_link_to_user() {
        HttpHeaders headers = restTemplate.postForEntity(USER_URL, userWithRandomUsername, String.class).getHeaders();

        assertThat(headers).isNotNull().isNotEmpty();
        assertThat(headers.get(LOCATION)).isNotNull().isNotEmpty().hasSize(1);
        assertThat(headers.get(LOCATION).get(0)).isNotNull().isNotBlank();
    }
}
