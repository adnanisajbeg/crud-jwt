package com.rest.example.repository;

import com.rest.example.db.repository.MockedUserDataService;
import com.rest.example.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class MockedUserDataServiceTest {
    MockedUserDataService mockedUserDataService;

    private User user;
    private User baseUser;
    private User userForDoubleSave;
    private String firstName = "John";
    private String firstNameForDoubleSave = "Smith";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";
    private String password = "pass123456";
    private String username = "john.doe";
    private String baseUsername = "test.user";
    private String usernameForDoubleSave = "smith.doe";


    @Before
    public void setup() {
        mockedUserDataService = new MockedUserDataService();

        baseUser = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(baseUsername)
                .setPassword(password)
                .addRole(1);

        user = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(username)
                .setPassword(password)
                .addRole(1);

        userForDoubleSave = new User()
                .setFirstName(firstNameForDoubleSave)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(usernameForDoubleSave)
                .setPassword(password)
                .addRole(1);
    }

    @Test
    public void save_new_user_returns_id() {
        int id = mockedUserDataService.save(baseUser);
        assertThat(id).isGreaterThan(0);
    }

    @Test
    public void save_same_user_again_returns_zero() {
        mockedUserDataService.save(user);
        assertThat(mockedUserDataService.save(user)).isEqualTo(0);
    }

    @Test
    public void save_another_user_returns_greater_id() {
        int firstUserId = mockedUserDataService.save(user);
        assertThat(mockedUserDataService.save(userForDoubleSave)).isGreaterThan(firstUserId);
    }

}
