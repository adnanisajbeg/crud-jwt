package com.rest.example.service;

import com.rest.example.model.User;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class UserCollectionService {

    public int save(User user) {
        if (validUser(user)) {

        }

        return 1;
    }

    boolean validUser(User user) {


        return true;
    }
}
