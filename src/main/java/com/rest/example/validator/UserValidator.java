package com.rest.example.validator;

import com.rest.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class UserValidator {
    private static Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);
    public boolean newUserIsValid(User user) {
        if (user == null || !validateUsername(user.getUsername())) {
            return false;
        }
        return true;
    }

    boolean validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            LOGGER.warn("Aborting saving new user, no username!");
            return false;
        }

        if (username.length() > 2 && username.length() < 30) {
            LOGGER.warn("Aborting saving new user, username length against the rules!");
            return true;
        }

        return false;
    }
}
