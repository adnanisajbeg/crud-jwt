package com.rest.example.db.repository;

import com.rest.example.db.UserDataService;
import com.rest.example.entity.UserDAO;
import com.rest.example.model.User;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class MockedUserDataService implements UserDataService {
    private static int USER_ID_GENERATOR = 0;
    private static final ConcurrentHashMap<Integer, UserDAO> USER_CACHE = new ConcurrentHashMap<>(30);
    private static final ConcurrentHashMap<String, UserDAO> USER_USERNAME_CACHE = new ConcurrentHashMap<>(30);
    private static final HashSet<String> USERNAME_CACHE = new HashSet<>(30);

    @Override
    public int save(User user) {
        if (!USERNAME_CACHE.contains(user.getUsername())) {
            return saveUserToCache(user);
        }
        return 0;
    }

    @Override
    public User getUserById(int id) {
        return convertDAOToUser(USER_CACHE.get(id));
    }

    private int saveUserToCache(User user) {
        UserDAO userDAO = convertUserToDAO(user);
        USERNAME_CACHE.add(userDAO.getUsername());
        USER_ID_GENERATOR++;
        userDAO.setId(USER_ID_GENERATOR);
        USER_CACHE.put(userDAO.getId(), userDAO);
        USER_USERNAME_CACHE.put(userDAO.getUsername(), userDAO);
        return userDAO.getId();
    }

    @Override
    public boolean deleteUserById(int id) {
        UserDAO user = USER_CACHE.get(id);
        if (user == null) {
            return false;
        }
        
        deleteUser(user);
        return true;
    }

    @Override
    public boolean update(int id, User user) {
        return !(id < 1 || id > USER_ID_GENERATOR || user == null) &&
                USER_CACHE.containsKey(id) && updateCache(id, user);

    }

    @Override
    public User getByUsername(String username) {
        UserDAO userDAO = USER_USERNAME_CACHE.get(username);
        return convertDAOToUser(userDAO);
    }

    private boolean updateCache(int id, User user) {
        UserDAO userDAO = convertUserToDAO(user);
        USER_CACHE.put(id, userDAO);
        USERNAME_CACHE.add(user.getUsername());
        USER_USERNAME_CACHE.put(userDAO.getUsername(), userDAO);
        return true;
    }

    private void deleteUser(UserDAO user) {
        USERNAME_CACHE.remove(user.getUsername());
        USER_USERNAME_CACHE.remove(user.getUsername());
        USER_CACHE.remove(user.getId());
    }

    private UserDAO convertUserToDAO(User user) {
        if (user != null) {
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

    private User convertDAOToUser(UserDAO userDAO) {
        if (userDAO != null) {
            return new User()
                    .setEmail(userDAO.getEmail())
                    .setFirstName(userDAO.getFirstName())
                    .setLastName(userDAO.getLastName())
                    .setPassword(userDAO.getPassword())
                    .setUsername(userDAO.getUsername())
                    .setPhoneNumber(userDAO.getPhoneNumber())
                    ;
        }

        return null;
    }
}
