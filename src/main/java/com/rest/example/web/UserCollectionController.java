package com.rest.example.web;

import com.rest.example.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-16
 */

@RestController
@RequestMapping("/users")
public class UserCollectionController {

    @RequestMapping(method = OPTIONS, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Object> getOptions() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "POST");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_JSON).build();
    }

    @RequestMapping(method = POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Object> saveNewUser(
            @RequestBody User newUser) {
        HttpHeaders headers = new HttpHeaders();

        // TODO: Change to accepted
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_JSON).build();
    }
}
