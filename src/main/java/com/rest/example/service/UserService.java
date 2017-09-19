package com.rest.example.service;

import com.rest.example.db.UserDataService;
import com.rest.example.model.User;
import com.rest.example.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class UserService {

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserDataService userDataService;

    public int saveUser(User user) {
        if (userValidator.newUserIsValid(user)) {
            return userDataService.save(user);
        }

        return 0;
    }

    public User getUser(int userId) {
        if (userId > 0) {
            return userDataService.getUserById(userId);
        }

        return null;
    }

    public boolean deleteUser(int userId) {
        if (userId > 0) {
            return userDataService.deleteUserById(userId);
        }

        return false;
    }

}
