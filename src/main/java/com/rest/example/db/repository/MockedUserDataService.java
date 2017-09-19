package com.rest.example.db.repository;

import com.rest.example.db.UserDataService;
import com.rest.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class MockedUserDataService implements UserDataService {
    private static Logger LOGGER = LoggerFactory.getLogger(MockedUserDataService.class);

    private static int USER_ID_GENERATOR = 0;
    private static final ConcurrentHashMap<Integer, User> USER_CACHE = new ConcurrentHashMap<>(30);
    private static final HashSet<String> USERNAME_CACHE = new HashSet<>(30);

    @Override
    public int save(User user) {
        if (!USERNAME_CACHE.contains(user.getUsername())) {
            LOGGER.info("saving user: {}", user);
            int id = saveUserToCache(user);
            LOGGER.info("Saved new user under id: {}", id);
            return id;
        }
        LOGGER.info("Username {} already exists!", user.getUsername());
        return 0;
    }

    @Override
    public User getUserById(int id) {
        return USER_CACHE.get(id);
    }

    int saveUserToCache(User user) {
        USERNAME_CACHE.add(user.getUsername());
        USER_ID_GENERATOR++;
        user.setId(USER_ID_GENERATOR);
        USER_CACHE.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public boolean deleteUserById(int id) {
        User user = USER_CACHE.get(id);
        if (user == null) {
            return false;
        }
        
        deleteUser(user);
        return true;
    }

    private void deleteUser(User user) {
        USERNAME_CACHE.remove(user.getUsername());
        USER_CACHE.remove(user.getId());
    }
}
