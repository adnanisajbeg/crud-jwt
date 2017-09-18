package com.rest.example.web;

import com.rest.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-18
 */
@RestController
@RequestMapping("/users/{id}")
public class UserAccountController {
    private static Logger LOGGER = LoggerFactory.getLogger(UserAccountController.class);

    private static final String USER_ID = "id";

    @RequestMapping(method = OPTIONS, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Object> getOptions() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAllow(getAllowedMethods());
        return ResponseEntity.ok().headers(headers).contentType(APPLICATION_JSON).build();
    }

    @RequestMapping(method = GET, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<User> getUser(@PathVariable(USER_ID) Integer id) {
        User user = new User().setFirstName("Test"); // TODO: get user from db!
        LOGGER.info("Geting user with id: {}", id);
        HttpHeaders headers = new HttpHeaders();

        return ResponseEntity.ok().headers(headers).contentType(APPLICATION_JSON).body(user);
    }

    private Set<HttpMethod> getAllowedMethods() {
        Set<HttpMethod> allowedMethods = new HashSet<>(7);
        allowedMethods.add(HttpMethod.OPTIONS);
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.PATCH);
        allowedMethods.add(HttpMethod.DELETE);
        return allowedMethods;
    }
}
