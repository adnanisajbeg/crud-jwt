package com.rest.example.model.convertor;

import com.rest.example.entity.UserDAO;
import com.rest.example.model.User;
import com.rest.example.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-19
 */
public class UserConverter {
    @Autowired
    UserValidator userValidator;

    public UserDAO convertUserToDAO(User user) {
        if (userValidator.userIsValid(user)) {
            return new UserDAO()
                    .setEmail(user.getEmail())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setPassword(user.getPassword())
                    .setUsername(user.getUsername())
                    .setPhoneNumber(user.getPhoneNumber())
                    ;
        }

        return null;
    }
}
