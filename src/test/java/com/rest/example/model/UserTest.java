package com.rest.example.model;

import org.junit.Before;
import org.junit.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-16
 */
public class UserTest {
    private User firstUser;
    private User secondUser;
    private String firstName = "John";
    private String lastName = "Doe";
    private String lastNameForSecondUser = "Smith";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";

    @Before
    public void setup() {
        firstUser = new User();
        secondUser = new User();

        firstUser = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(randomAlphabetic(10))
                .setPassword(randomAlphanumeric(13));

        secondUser = new User()
                .setFirstName(firstName)
                .setLastName(lastNameForSecondUser)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(randomAlphabetic(22))
                .setPassword(randomAlphanumeric(11));
    }

    @Test
    public void user_builds_correctly() {
        System.out.println(firstUser);
        assertThat(firstUser.toString())
                .isNotNull()
                .isNotBlank()
                .contains(firstName)
                .contains(lastName);
    }

    @Test
    public void two_users_are_not_same_if_one_value_is_different() {
        assertThat(firstUser).isNotEqualTo(secondUser);
    }
}
