package com.rest.example.controller;

import com.rest.example.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.HEAD;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.TRACE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAccount_FT extends AbstractTest {
    @Ignore
    @Test
    public void options_on_users_returns_correct_methods() {
        Set<HttpMethod> allowedHttpMethodsForUsers = this.restTemplate.optionsForAllow(USER_URL + "/1");

        assertThat(allowedHttpMethodsForUsers)
                .isNotNull()
                .isNotEmpty()
                .contains(POST).contains(OPTIONS)
                .doesNotContain(GET).doesNotContain(HEAD).doesNotContain(PUT)
                .doesNotContain(PATCH).doesNotContain(DELETE).doesNotContain(TRACE);
    }

    @Test
    public void get_user_account_data_returns_correct_code() {
        ResponseEntity<User> getUserResponse = restTemplate.exchange(
                getUrlForUser(userWithRandomUsername),
                GET,
                createHttpEntityForGet(),
                User.class
        );

        assertThat(getUserResponse).isNotNull();
        assertThat(getUserResponse.getBody()).isEqualToComparingFieldByField(userWithRandomUsername);
    }

    @Test
    public void update_saved_user_returns_ok_status() {
        String userUrl = getUrlForUser(userWithRandomUsername);
        ResponseEntity<Object> exchange = updateUsersPhoneNumber(userWithRandomUsername, userUrl, newPhoneNumber);
        assertThat(exchange.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void update_saved_user_correctly() {
        String userUrl = getUrlForUser(userWithRandomUsername);
        updateUsersPhoneNumber(userWithRandomUsername, userUrl, newPhoneNumber);
        ResponseEntity<User> getUserResponse = restTemplate.exchange(
                userUrl,
                GET,
                createHttpEntityForGet(),
                User.class
        );

        assertThat(getUserResponse.getBody()).isNotNull();
        assertThat(getUserResponse.getBody().getPhoneNumber()).isNotNull().isNotBlank().isEqualTo(newPhoneNumber);
    }

    @Test
    public void delete_saved_user_returns_correct_status() {
        String urlForUser = getUrlForUser(user);
        ResponseEntity<Object> getUserResponse = restTemplate.exchange(
                urlForUser,
                DELETE,
                createHttpEntityForDelete(),
                Object.class
        );

        assertThat(getUserResponse).isNotNull();
        assertThat(getUserResponse.getStatusCode()).isEqualTo(NO_CONTENT);
    }

    @Test
    public void deleted_saved_user() {
        String urlForUser = getUrlForUser(user);
        restTemplate.exchange(
                urlForUser,
                DELETE,
                createHttpEntityForDelete(),
                Object.class
        );

        ResponseEntity<User> getUserResponse = restTemplate.exchange(
                urlForUser,
                GET,
                createHttpEntityForGet(),
                User.class
        );

        assertThat(getUserResponse.getStatusCode()).isEqualTo(NO_CONTENT);
    }

    private ResponseEntity<Object> updateUsersPhoneNumber(User user, String userUrl, String newPhoneNumber) {
        user.setPhoneNumber(newPhoneNumber);
        return restTemplate
                .exchange(userUrl + INFO_URL, PATCH, createHttpEntityForPatch(user), Object.class);
    }

    private String getUrlForUser(User user) {
        ResponseEntity<String> response = restTemplate.postForEntity(USER_URL, user, String.class);
        List<String> links = response.getHeaders().get(HttpHeaders.LOCATION);
        return links.get(0);
    }

    private HttpEntity<User> createHttpEntityForPatch(User user) {
        HttpHeaders headers = getHeaders();
        return new HttpEntity<>(user, headers);
    }

    private <T> HttpEntity<T> createHttpEntityForGet() {
        HttpHeaders headers = getHeaders();
        return new HttpEntity<>(headers);
    }

    private <T> HttpEntity<T> createHttpEntityForDelete() {
        HttpHeaders headers = getHeaders();
        return new HttpEntity<>(headers);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(APPLICATION_JSON));
        headers.setContentType(APPLICATION_JSON);
        return headers;
    }
}
