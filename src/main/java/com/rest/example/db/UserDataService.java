package com.rest.example.db;

import com.rest.example.model.User;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public interface UserDataService {
    int save(User user);
    User getUserById(int id);
    boolean deleteUserById(int id);
    boolean update(int id, User user);
}
