package com.rest.example.web;

import com.rest.example.model.User;
import com.rest.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-18
 */
@RestController
@RequestMapping("/users/{id}")
public class UserAccountController {
    private static Logger LOGGER = LoggerFactory.getLogger(UserAccountController.class);

    private static final String USER_ID = "id";

    @Autowired
    private UserService userService;

    @RequestMapping(method = OPTIONS, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Object> getOptions() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAllow(getAllowedMethods());
        return ResponseEntity.ok().headers(headers).contentType(APPLICATION_JSON).build();
    }

    @RequestMapping(method = GET, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<User> getUser(@PathVariable(USER_ID) Integer id) {
        LOGGER.debug("Extracting user with id: {}", id);
        User user = userService.get(id);
        LOGGER.debug("Extracted user with id: {}", user);

        HttpHeaders headers = new HttpHeaders();

        if (user != null) {
            return ResponseEntity.ok().headers(headers).contentType(APPLICATION_JSON).body(user);
        } else {
            return ResponseEntity.noContent().headers(headers).build();
        }
    }

    @RequestMapping(method = PATCH, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<User> updateUser(@PathVariable(USER_ID) Integer id,
            @RequestBody User user) {
        LOGGER.debug("Updating user by id: {}, user: {}", id, user);
        boolean updated = userService.update(id, user);
        if (updated) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = DELETE, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<User> deleteUser(
            @PathVariable(USER_ID) Integer id) {
        LOGGER.info("Delete user by id: {}", id);
        HttpHeaders headers = new HttpHeaders();
        boolean deleted = userService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().headers(headers).build();
        } else {
            return ResponseEntity.notFound().headers(headers).build();
        }
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
