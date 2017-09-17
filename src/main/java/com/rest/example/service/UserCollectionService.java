package com.rest.example.service;

import com.rest.example.model.User;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class UserCollectionService {

    public int save(User user) {
        if (validUser(user)) {
            return 1;
        }

        return 0;
    }

    boolean validUser(User user) {
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
