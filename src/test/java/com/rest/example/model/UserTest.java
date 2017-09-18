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
    private int role_first = 1;
    private int role_two = 2;
    private int role_five = 5;
    private int role_nine = 9;

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
                .setPassword(randomAlphanumeric(13))
                .addRole(1);

        secondUser = new User()
                .setFirstName(firstName)
                .setLastName(lastNameForSecondUser)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setUsername(randomAlphabetic(22))
                .setPassword(randomAlphanumeric(11))
                .addRole(1);
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
    public void single_user_role_added() {
        firstUser.addRole(role_five);

        assertThat(firstUser.containsRole(role_first)).isTrue();
        assertThat(firstUser.containsRole(role_two)).isFalse();
        assertThat(firstUser.containsRole(role_five)).isTrue();
        assertThat(firstUser.containsRole(role_nine)).isFalse();
    }

    @Test
    public void multiple_user_role_added() {
        firstUser.addRole(role_first).addRole(role_five);

        assertThat(firstUser.containsRole(role_first)).isTrue();
        assertThat(firstUser.containsRole(role_two)).isFalse();
        assertThat(firstUser.containsRole(role_five)).isTrue();
        assertThat(firstUser.containsRole(role_nine)).isFalse();
    }

    @Test
    public void user_role_deleted() {
        firstUser.addRole(role_first).addRole(role_five).addRole(role_nine);
        firstUser.deleteRole(role_first).deleteRole(role_five).deleteRole(role_nine);

        assertThat(firstUser.containsRole(role_first)).isFalse();
        assertThat(firstUser.containsRole(role_two)).isFalse();
        assertThat(firstUser.containsRole(role_five)).isFalse();
        assertThat(firstUser.containsRole(role_nine)).isFalse();
    }

    @Test
    public void two_users_are_not_same_if_one_value_is_different() {
        assertThat(firstUser).isNotEqualTo(secondUser);
    }
}
