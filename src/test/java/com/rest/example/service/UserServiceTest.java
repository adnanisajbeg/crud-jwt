package com.rest.example.service;

import com.rest.example.db.repository.MockedUserDataService;
import com.rest.example.model.User;
import com.rest.example.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class UserServiceTest {
    UserService userService = new UserService();

    private User user;
    private User userWithoutUsername;
    private String firstName = "John";
    private String firstNameForDoubleSave = "Smith";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";

    @Before
    public void setup() {
        userService.userValidator = new UserValidator();  // TODO: mock this
        userService.userDataService = new MockedUserDataService();    // TODO: mock this

        user = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(randomAlphabetic(15))
                .setPassword(randomAlphanumeric(20))
                .addRole(1);

        userWithoutUsername = new User()
                .setFirstName(firstNameForDoubleSave)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setPassword(randomAlphanumeric(22))
                .addRole(1);
    }

    @Test
    public void service_creation() {
        assertThat(userService).isNotNull();
    }

    @Test
    public void service_saves_user() {
        int id = userService.saveUser(user);
        assertThat(id).isGreaterThan(0);
    }

    @Test
    public void service_does_not_save_null_user() {
        int id = userService.saveUser(null);
        assertThat(id).isEqualTo(0);
    }

    @Test
    public void service_does_not_save_new_user_with_existing_username() {
        int id = userService.saveUser(user);
        int idAgain = userService.saveUser(user);
        assertThat(id).isGreaterThan(0);
        assertThat(idAgain).isEqualTo(0);
    }

    @Test
    public void service_does_not_save_user_without_username() {
        int id = userService.saveUser(userWithoutUsername);
        assertThat(id).isEqualTo(0);
    }

    @Test
    public void service_returns_correct_user() {
        int userId = userService.saveUser(user);
        User savedUser = userService.getUser(userId);
        assertThat(savedUser).isNotNull().isEqualToComparingFieldByField(user);
    }

    @Test
    public void delete_saved_user_returns_true() {
        int userId = userService.saveUser(user);
        boolean deleted = userService.deleteUser(userId);
        assertThat(deleted).isTrue();
    }

    @Test
    public void delete_zero_user_returns_false() {
        assertThat(userService.deleteUser(0)).isFalse();
    }

    @Test
    public void delete_nonexisting_user_returns_false() {
        int userId = userService.saveUser(user);
        assertThat(userService.deleteUser(++userId)).isFalse();
    }
}