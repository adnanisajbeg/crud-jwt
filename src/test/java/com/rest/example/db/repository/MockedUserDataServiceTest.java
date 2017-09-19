package com.rest.example.db.repository;

import com.rest.example.db.UserDataService;
import com.rest.example.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-17
 */
public class MockedUserDataServiceTest {
    UserDataService userDataService;

    private User user;
    private User baseUser;
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";
    private int someIdToBeDeleted = 5;
    private String newPhoneNumber = "33232322";

    @Before
    public void setup() {
        userDataService = new MockedUserDataService();

        user = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(randomAlphabetic(18))
                .setPassword(randomAlphanumeric(15))
                .addRole(1);

        baseUser = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(randomAlphabetic(11))
                .setPassword(randomAlphanumeric(24))
                .addRole(1);
    }

    @Test
    public void save_new_user_returns_id() {
        int id = userDataService.save(baseUser);
        assertThat(id).isGreaterThan(0);
    }

    @Test
    public void save_same_user_again_returns_zero() {
        userDataService.save(user);
        assertThat(userDataService.save(user)).isEqualTo(0);
    }

    @Test
    public void save_another_user_returns_greater_id() {
        int firstUserId = userDataService.save(user);
        assertThat(userDataService.save(baseUser)).isGreaterThan(firstUserId);
    }

    @Test
    public void get_user_by_id_returns_correct_user() {
        int savedUserId = userDataService.save(user);
        assertThat(userDataService.getUserById(savedUserId)).isNotNull().isEqualToComparingFieldByField(user);
    }

    @Test
    public void delete_correct_user_returns_true() {
        int savedUserId = userDataService.save(user);
        assertThat(userDataService.deleteUserById(savedUserId)).isTrue();
    }

    @Test
    public void delete_user_can_not_be_retrieved() {
        int savedUserId = userDataService.save(user);
        userDataService.deleteUserById(savedUserId);
        assertThat(userDataService.getUserById(savedUserId)).isNull();
    }

    @Test
    public void delete_non_existing_user_returns_false() {
        userDataService.deleteUserById(someIdToBeDeleted);
        assertThat(userDataService.deleteUserById(someIdToBeDeleted)).isFalse();
    }

    @Test
    public void update_user_returns_true() {
        int savedUserId = userDataService.save(user);
        user.setPassword(randomAlphanumeric(7)).setPhoneNumber(newPhoneNumber);
        assertThat(userDataService.update(savedUserId, user)).isTrue();
    }

    @Test
    public void update_user_correctly() {
        int savedUserId = userDataService.save(user);
        user.setPassword(randomAlphanumeric(7)).setPhoneNumber(newPhoneNumber);
        userDataService.update(savedUserId, user);
        User sameUser = userDataService.getUserById(savedUserId);
        assertThat(sameUser).isNotNull();
        assertThat(sameUser.getPhoneNumber()).isEqualTo(newPhoneNumber);
    }
}
