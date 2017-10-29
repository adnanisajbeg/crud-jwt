package com.rest.example.security.auth0.exception;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-10-01
 */
public class NonExistingUserException extends Exception {
    public NonExistingUserException(String message) {
        super(message);
    }
}
