package com.rest.example.validator;

import com.rest.example.model.User;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class UserValidator {
    public boolean newUserIsValid(User user) {
        if (user == null || !validateUsername(user.getUsername())) {
            return false;
        }
        return true;
    }

    boolean validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        if (username.length() > 2 && username.length() < 30) {
            return true;
        }

        return false;
    }
}
