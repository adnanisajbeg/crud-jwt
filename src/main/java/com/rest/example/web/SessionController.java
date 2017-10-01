package com.rest.example.web;

import com.rest.example.security.auth0.model.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-10-01
 */
@RequestMapping("/login")
public class SessionController {

    @RequestMapping(method = POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> login(
            @RequestBody
                    Login newUser, HttpServletRequest request) {
        return ResponseEntity.ok().body("test");
    }
}
