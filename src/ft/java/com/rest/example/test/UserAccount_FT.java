package com.rest.example.test;

import com.rest.example.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.ALLOW;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.HEAD;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.TRACE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
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
    @Test
    public void options_on_users_returns_correct_methods() {
        ResponseEntity<User> response = restTemplate.exchange(
                USER_URL + "/1",
                OPTIONS,
                createHttpEntityForGet(),
                User.class
        );

        assertThat(response.getHeaders().get(ALLOW))
                .isNotNull()
                .isNotEmpty();

        assertThat(response.getHeaders().get(ALLOW).get(0))
                .contains(OPTIONS.name()).contains(GET.name()).contains(PATCH.name()).contains(DELETE.name())
                .doesNotContain(HEAD.name()).doesNotContain(PUT.name()).doesNotContain(TRACE.name());
    }

    @Test
    public void get_user_account_data_returns_correct_code() {
        ResponseEntity<User> response = restTemplate.exchange(
                getUrlForUser(userWithRandomUsername),
                GET,
                createHttpEntityForGet(),
                User.class
        );

        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void get_user_account_data_returns_correct_user() {
        ResponseEntity<User> response = restTemplate.exchange(
                getUrlForUser(userWithRandomUsername),
                GET,
                createHttpEntityForGet(),
                User.class
        );

        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUsername()).isEqualTo(userWithRandomUsername.getUsername());
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
        String urlForUser = getUrlForUser(userWithRandomUsername);
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

    @Test
    public void deleted_non_existing_user_returns_correct_status() {
        String urlForUser = getUrlForUser(userWithRandomUsername);
        restTemplate.exchange(urlForUser, DELETE, createHttpEntityForDelete(), Object.class);
        ResponseEntity<Object> secondTryToDeleteUser = restTemplate
                .exchange(urlForUser, DELETE, createHttpEntityForDelete(), Object.class);

        assertThat(secondTryToDeleteUser).isNotNull();
        assertThat(secondTryToDeleteUser.getStatusCode()).isEqualTo(NOT_FOUND);
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
