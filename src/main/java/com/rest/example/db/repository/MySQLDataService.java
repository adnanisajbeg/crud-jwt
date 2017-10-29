package com.rest.example.db.repository;

import com.rest.example.db.UserDataService;
import com.rest.example.db.repository.jpa.UserRepository;
import com.rest.example.entity.UserDAO;
import com.rest.example.model.User;
import com.rest.example.model.convertor.UserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-19
 */
public class MySQLDataService implements UserDataService {
    private static Logger LOGGER = LoggerFactory.getLogger(MySQLDataService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @Override
    public int save(User user) {
        LOGGER.info("Saving new user: {}", user);
        UserDAO userDAO = userConverter.convertUserToDAO(user);

        if (userDAO == null) {
            return 0;
        }

        return saveOrUpdateUserDAO(userDAO);
    }

    @Override
    public User getUserById(int id) {
        LOGGER.info("Getting user by id: {}", id);
        try {
            UserDAO userDAO = userRepository.findOne(id);

            if (userDAO == null) {
                return null;
            }
            User user = userConverter.convertDAOToUser(userDAO);
            LOGGER.debug("Retrieved user: {}", user);
            return user;
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve user!", e);
        }

        return null;
    }

    @Override
    public boolean deleteUserById(int id) {
        LOGGER.info("Deleting user by id: {}", id);

        try {
            userRepository.delete(id);
            LOGGER.info("Deleted user {} successfully!", id);
            return true;
        } catch (Exception e) {
            LOGGER.error("Failed to delete!", e);
            return false;
        }
    }

    @Override
    public boolean update(int id, User user) {
        LOGGER.info("Updated user by id = {}. New values: {}", id, user);

        UserDAO userDAO = userConverter.convertUserToDAO(user);

        if (userDAO == null || id < 1) {
            LOGGER.error("Updated failed!");
            return false;
        }
        userDAO.setId(id);

        return saveOrUpdateUserDAO(userDAO) > 0;
    }

    private int saveOrUpdateUserDAO(UserDAO userDAO) {
        UserDAO saved = null;

        try {
            saved = userRepository.save(userDAO);
        } catch(Exception e) {
            LOGGER.error("Saving failed!", e);
        }

        if (saved == null) {
            return 0;
        }

        LOGGER.info("Saved successful by id: {}", saved.getId());

        return saved.getId();
    }

    @Override
    public User getByUsername(String username) {
        try {
            UserDAO byUsername = userRepository.findByUsername(username);
            return userConverter.convertDAOToUser(byUsername);
        } catch (Exception e) {
            LOGGER.error("Failed to find by username!", e);
            return null;
        }
    }
}
