package com.rest.example.repository;

import com.rest.example.db.repository.MockedUserDataService;
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
    MockedUserDataService mockedUserDataService;

    private User user;
    private User baseUser;
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";
    private int someIdToBeDeleted = 5;

    @Before
    public void setup() {
        mockedUserDataService = new MockedUserDataService();

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
        assertThat(mockedUserDataService.save(baseUser)).isGreaterThan(firstUserId);
    }

    @Test
    public void get_user_by_id_returns_correct_user() {
        int savedUserId = mockedUserDataService.save(user);
        assertThat(mockedUserDataService.getUserById(savedUserId)).isNotNull().isEqualToComparingFieldByField(user);
    }

    @Test
    public void delete_correct_user_returns_true() {
        int savedUserId = mockedUserDataService.save(user);
        assertThat(mockedUserDataService.deleteUserById(savedUserId)).isTrue();
    }

    @Test
    public void delete_user_can_not_be_retrieved() {
        int savedUserId = mockedUserDataService.save(user);
        mockedUserDataService.deleteUserById(savedUserId);
        assertThat(mockedUserDataService.getUserById(savedUserId)).isNull();
    }

    @Test
    public void delete_nonexisting_user_returns_false() {
        mockedUserDataService.deleteUserById(someIdToBeDeleted);
        assertThat(mockedUserDataService.deleteUserById(someIdToBeDeleted)).isFalse();
    }
}
