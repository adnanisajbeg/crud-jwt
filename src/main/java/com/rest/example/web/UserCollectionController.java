package com.rest.example.web;

import com.rest.example.model.User;
import com.rest.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-16
 */

@RestController
@RequestMapping("/users")
public class UserCollectionController {
    private static Logger LOGGER = LoggerFactory.getLogger(UserCollectionController.class);

    @Autowired
    UserService userService;

    @RequestMapping(method = OPTIONS, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Object> getOptions() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAllow(getAllowedMethods());

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(method = POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Object> saveNewUser(
            @RequestBody User newUser) {
        LOGGER.info("Saving new user: {}", newUser);
        int newUserId = userService.save(newUser);

        if (newUserId > 0) {
            HttpHeaders headers = new HttpHeaders();
            try {
                return ResponseEntity.created(createURI(newUserId)).headers(headers).contentType(MediaType.APPLICATION_JSON).build();
            } catch (URISyntaxException e) {
                LOGGER.error("Failed to create URI for user: {} and userId: {}", newUser, newUserId);
            }
        }

        // TODO: Find which code to return when save failed
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).build();
    }

    private URI createURI(int newUserId) throws URISyntaxException {
        return new URI("/users/" + newUserId);
    }

    private Set<HttpMethod> getAllowedMethods() {
        Set<HttpMethod> allowedMethods = new HashSet<>(4);
        allowedMethods.add(HttpMethod.OPTIONS);
        allowedMethods.add(HttpMethod.POST);
        return allowedMethods;
    }
}
