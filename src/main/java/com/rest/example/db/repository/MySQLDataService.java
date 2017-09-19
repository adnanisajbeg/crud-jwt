package com.rest.example.db.repository;

import com.rest.example.db.UserDataService;
import com.rest.example.db.repository.jpa.UserRepository;
import com.rest.example.entity.UserDAO;
import com.rest.example.model.User;
import com.rest.example.model.convertor.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-19
 */
public class MySQLDataService implements UserDataService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @Override
    public int save(User user) {
        UserDAO userDAO = userConverter.convertUserToDAO(user);

        if (userDAO == null) {
            return 0;
        }

        UserDAO saved = userRepository.save(userDAO);

        if (saved == null) {
            return 0;
        }

        return saved.getId();
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public boolean deleteUserById(int id) {
        return false;
    }

    @Override
    public boolean update(int id, User user) {
        return false;
    }
}
