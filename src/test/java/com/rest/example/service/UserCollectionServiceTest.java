package com.rest.example.service;

import com.rest.example.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class UserCollectionServiceTest {
    private User user;
    private User userForDoubleSave;
    private User userWithoutUsername;
    private String firstName = "John";
    private String firstNameForDoubleSave = "Smith";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";
    private String password = "pass123456";
    private String username = "john.doe";
    private String usernameForDoubleSave = "smith.doe";

    @Before
    public void setup() {
        user = new User().setFirstName(firstName).setLastName(lastName).setEmail(email).setPhoneNumber(phoneNumber)
                .setUsername(username).setPassword(password).addRole(1);

        userForDoubleSave = new User().setFirstName(firstNameForDoubleSave).setLastName(lastName).setEmail(email)
                .setPhoneNumber(phoneNumber).setUsername(usernameForDoubleSave).setPassword(password).addRole(1);

        userWithoutUsername = new User().setFirstName(firstNameForDoubleSave).setLastName(lastName).setEmail(email)
                .setPhoneNumber(phoneNumber).setPassword(password).addRole(1);
    }

    @Test
    public void service_creation() {
        UserCollectionService userCollectionService = new UserCollectionService();
        assertThat(userCollectionService).isNotNull();
    }

    @Test
    public void service_saves_user() {
        UserCollectionService userCollectionService = new UserCollectionService();
        int id = userCollectionService.save(user);
        assertThat(id).isGreaterThan(0);
    }

    @Test
    public void service_does_not_save_null_user() {
        UserCollectionService userCollectionService = new UserCollectionService();
        int id = userCollectionService.save(null);
        assertThat(id).isEqualTo(0);
    }

    @Test
    public void service_does_not_save_new_user_with_existing_username() {
        UserCollectionService userCollectionService = new UserCollectionService();
        int id = userCollectionService.save(userForDoubleSave);
        int idAgain = userCollectionService.save(userForDoubleSave);
        assertThat(id).isGreaterThan(0);
        assertThat(idAgain).isEqualTo(0);
    }

    @Test
    public void service_does_not_save_user_without_username() {
        UserCollectionService userCollectionService = new UserCollectionService();
        int id = userCollectionService.save(userWithoutUsername);
        assertThat(id).isEqualTo(0);
    }
}