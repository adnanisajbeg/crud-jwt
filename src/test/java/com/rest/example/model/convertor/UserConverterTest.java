package com.rest.example.model.convertor;

import com.rest.example.entity.UserDAO;
import com.rest.example.model.User;
import com.rest.example.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-19
 */
public class UserConverterTest {
    private User user;
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";
    private UserConverter userConverter;

    @Before
    public void setup() {
        userConverter = new UserConverter();
        userConverter.userValidator = new UserValidator();

        user = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(randomAlphabetic(15))
                .setPassword(randomAlphanumeric(20));
    }

    @Test
    public void converter_does_not_return_null_when_given_user() {
        UserDAO userDAO = userConverter.convertUserToDAO(user);
        assertThat(userDAO).isNotNull();
    }

    @Test
    public void converter_populated_all_fields() {
        UserDAO userDAO = userConverter.convertUserToDAO(user);
        assertThat(userDAO.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDAO.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(userDAO.getLastName()).isEqualTo(user.getLastName());
        assertThat(userDAO.getPassword()).isEqualTo(user.getPassword());
        assertThat(userDAO.getPhoneNumber()).isEqualTo(user.getPhoneNumber());
        assertThat(userDAO.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void converter_returns_null_when_given_null_user() {
        assertThat(userConverter.convertUserToDAO(null)).isNull();
    }

    @Test
    public void converter_returns_null_when_given_user_dao_without_username() {
        assertThat(userConverter.convertDAOToUser(new UserDAO())).isNull();
    }

    @Test
    public void converter_returns_user_when_given_user_dao() {
        UserDAO userDAO = userConverter.convertUserToDAO(user);
        assertThat(userConverter.convertDAOToUser(userDAO)).isNotNull();
    }
}
