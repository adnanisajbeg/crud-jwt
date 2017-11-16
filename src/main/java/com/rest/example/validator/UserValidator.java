package com.rest.example.validator;

import com.rest.example.entity.UserDAO;
import com.rest.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.rest.example.util.StringUtils.isNotBlank;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class UserValidator {
    private static Logger LOGGER = LoggerFactory.getLogger(UserValidator.class);
    public boolean userIsValid(User user) {
        if (user == null || !validateUsername(user.getUsername())) {
            return false;
        }
        return true;
    }

    public boolean validateUpdate(int userId, User user) {
        return userId > 0 && userIsValid(user);
    }

    public boolean userDAOIsValid(UserDAO userDAO) {
        return !(userDAO == null || !validateUsername(userDAO.getUsername()));
    }

    boolean validateUsername(String username) {
        if (!isNotBlank(username)) {
            LOGGER.warn("Aborting saving new user, no username!");
            return false;
        }

        if (username.length() < 3 || username.length() > 30) {
            LOGGER.warn("Aborting saving new user, username length against the rules! username: {}", username);
            return false;
        }

        return true;
    }


    boolean validatePassword(String passwordString) {
        if (!isNotBlank(passwordString)) {
            LOGGER.warn("Aborting saving new user, password is empty!");
            return false;
        }

        if (passwordString.length() < 8) {
            LOGGER.warn("Aborting saving new user, password is to small!");
            return false;
        }

        if (passwordString.contains(" ")) {
            LOGGER.warn("Aborting saving new user, password contains spaces!");
            return false;
        }

        return true;
    }

}
