package com.rest.example.test;

import com.rest.example.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.ALLOW;
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
    @Before
    public void login() {
        if (jwtAuthToken == null) {
            ResponseEntity<String> result = restTemplate.exchange(
                    LOGIN_URL,
                    POST,
                    createHttpEntityForLogin(adminUsername, adminPass),
                    String.class
            );

            HttpHeaders headers = result.getHeaders();
            jwtAuthToken = headers.get("Authorization").get(0);
        }
    }

    @Test
    public void options_on_users_returns_correct_methods() {
        ResponseEntity<User> response = restTemplate.exchange(USER_URL, OPTIONS, createHttpEntityForGet(), User.class);

        assertThat(response.getHeaders().get(ALLOW)).isNotNull().isNotEmpty();

        assertThat(response.getHeaders().get(ALLOW).get(0)).contains(OPTIONS.name()).contains(POST.name())
                .doesNotContain(GET.name()).doesNotContain(PATCH.name()).doesNotContain(DELETE.name())
                .doesNotContain(HEAD.name()).doesNotContain(PUT.name()).doesNotContain(TRACE.name());
    }

    @Test
    public void register_new_user_returns_correct_code() {
        ResponseEntity<String> answer = restTemplate
                .exchange(USER_URL,
                        POST,
                        createHttpEntityForPost(user),
                        String.class);

        assertThat(answer).isNotNull();
        assertThat(answer.getStatusCode()).isEqualTo(CREATED);
    }

    @Test
    public void register_new_user_returns_header_with_link_to_user() {
        HttpHeaders headers = restTemplate
                .exchange(USER_URL,
                        POST,
                        createHttpEntityForPost(userWithRandomUsername),
                        String.class).getHeaders();

        assertThat(headers).isNotNull().isNotEmpty();
        assertThat(headers.get(LOCATION)).isNotNull().isNotEmpty().hasSize(1);
        assertThat(headers.get(LOCATION).get(0)).isNotNull().isNotBlank();
    }

}