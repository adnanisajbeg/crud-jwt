package com.rest.example.db.repository;

import com.rest.example.db.repository.jpa.UserRepository;
import com.rest.example.entity.UserDAO;
import com.rest.example.model.User;
import com.rest.example.model.convertor.UserConverter;
import org.junit.Before;
import org.junit.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-19
 */
public class MySQLDataServiceTest {
    MySQLDataService mySQLDataService;

    private User user;
    private UserDAO userDAO;
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";
    private String newPhoneNumber = "33232322";

    @Before
    public void setup() {
        mySQLDataService = new MySQLDataService();
        mySQLDataService.userConverter = mock(UserConverter.class);
        mySQLDataService.userRepository = mock(UserRepository.class);

        String username = randomAlphabetic(18);
        String pass = randomAlphanumeric(15);

        user = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(username)
                .setPassword(pass);

        userDAO = new UserDAO()
                .setId(1)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(username)
                .setPassword(pass);

        when(mySQLDataService.userConverter.convertUserToDAO(any(User.class))).thenReturn(userDAO);
        when(mySQLDataService.userConverter.convertDAOToUser(any(UserDAO.class))).thenReturn(user);
        when(mySQLDataService.userRepository.save(any(UserDAO.class))).thenReturn(userDAO);
        when(mySQLDataService.userRepository.findOne(any(Integer.class))).thenReturn(userDAO);
    }

    @Test
    public void save_new_user_returns_id() {
        int id = mySQLDataService.save(user);
        assertThat(id).isGreaterThan(0);
    }

    @Test
    public void get_user_by_id_returns_correct_user() {
        int savedUserId = mySQLDataService.save(user);
        assertThat(mySQLDataService.getUserById(savedUserId)).isNotNull().isEqualToComparingFieldByField(user);
    }

    @Test
    public void delete_correct_user_returns_true() {
        int savedUserId = mySQLDataService.save(user);
        assertThat(mySQLDataService.deleteUserById(savedUserId)).isTrue();
    }

    @Test
    public void update_user_returns_true() {
        int savedUserId = mySQLDataService.save(user);
        user.setPassword(randomAlphanumeric(7)).setPhoneNumber(newPhoneNumber);
        assertThat(mySQLDataService.update(savedUserId, user)).isTrue();
    }
}
