package com.rest.example.security.auth0;

import com.rest.example.model.User;
import com.rest.example.security.auth0.exception.NonExistingUserException;
import com.rest.example.security.auth0.model.UserDetailsImpl;
import com.rest.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-10-01
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private static Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getByUsername(username);
        try {
            return new UserDetailsImpl(user);
        } catch (NonExistingUserException e) {
            LOGGER.error("Could not create UserDetails!", e);
            return null;
        }
    }
}
