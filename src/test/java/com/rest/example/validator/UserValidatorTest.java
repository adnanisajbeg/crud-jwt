package com.rest.example.validator;

import com.rest.example.entity.UserDAO;
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
public class UserValidatorTest {
    UserValidator userValidator;

    private User userWithoutUsername;
    private String firstNameForDoubleSave = "Smith";
    private String lastName = "Doe";
    private String email = "john.doe@gmail.com";
    private String phoneNumber = "12345678";

    @Before
    public void setup() {
        userValidator = new UserValidator();

        userWithoutUsername = new User()
                .setFirstName(firstNameForDoubleSave)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setPassword(randomAlphanumeric(20));
    }

    @Test
    public void validator_created() {
        assertThat(userValidator).isNotNull();
    }

    @Test
    public void return_false_when_user_is_null() {
        assertThat(userValidator.userIsValid(null)).isFalse();
    }

    @Test
    public void return_false_for_null_username() {
        assertThat(userValidator.userIsValid(userWithoutUsername)).isFalse();
    }

    @Test
    public void validate_username_return_false_when_given_null_username() {
        assertThat(userValidator.validateUsername(null)).isFalse();
    }

    @Test
    public void validate_username_return_false_when_given_empty_username() {
        assertThat(userValidator.validateUsername("")).isFalse();
    }

    @Test
    public void validate_username_return_false_when_given_blank_username() {
        assertThat(userValidator.validateUsername("     ")).isFalse();
    }

    @Test
    public void validate_username_return_true_when_given_valid_username() {
        assertThat(userValidator.validateUsername(randomAlphabetic(15))).isTrue();
    }

    @Test
    public void validate_username_return_false_when_given_to_short_username() {
        assertThat(userValidator.validateUsername(randomAlphabetic(2))).isFalse();
    }

    @Test
    public void validate_username_return_false_when_given_to_large_username() {
        assertThat(userValidator.validateUsername(randomAlphabetic(35))).isFalse();
    }

    @Test
    public void validate_update_when_id_is_zero_returns_false() {
        assertThat(userValidator.validateUpdate(0, new User().setUsername(randomAlphabetic(10)))).isFalse();
    }

    @Test
    public void validate_update_returns_true_for_valid_input() {
        assertThat(userValidator.validateUpdate(12, new User().setUsername(randomAlphabetic(11)))).isTrue();
    }

    @Test
    public void validate_update_returns_false_when_username_is_not_present() {
        assertThat(userValidator.validateUpdate(12, new User())).isFalse();
    }

    @Test
    public void validate_update_returns_false_when_user_is_null() {
        assertThat(userValidator.validateUpdate(12, null)).isFalse();
    }

    @Test
    public void validate_user_dao_returns_true_when_given_valid_user_dao() {
        assertThat(userValidator.userDAOIsValid(new UserDAO().setUsername(randomAlphabetic(5)))).isTrue();
    }

    @Test
    public void validate_user_dao_returns_false_when_given_user_dao_without_username() {
        assertThat(userValidator.userDAOIsValid(new UserDAO())).isFalse();
    }

    @Test
    public void validate_user_dao_returns_false_when_given_null() {
        assertThat(userValidator.userDAOIsValid(null)).isFalse();
    }
}
