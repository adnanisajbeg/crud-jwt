package com.rest.example.service;

import com.rest.example.model.User;
import com.rest.example.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class UserCollectionService {

    @Autowired
    UserValidator userValidator;

    public int save(User user) {
        if (userValidator.newUserIsValid(user)) {
            return 1;
        }

        return 0;
    }
}
