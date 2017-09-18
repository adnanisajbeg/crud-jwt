package com.rest.example.validator;

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
    private String username = "john.doe";
    private String veryShortUsername = "rr";
    private String veryLargeUsername = "ddsdasdasdasdasdasdaddsaasdasddasd";

    @Before
    public void setup() {
        userValidator = new UserValidator();

        userWithoutUsername = new User()
                .setFirstName(firstNameForDoubleSave)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setPassword(randomAlphanumeric(20))
                .addRole(1);
    }

    @Test
    public void validator_created() {
        assertThat(userValidator).isNotNull();
    }

    @Test
    public void return_false_when_user_is_null() {
        assertThat(userValidator.newUserIsValid(null)).isFalse();
    }

    @Test
    public void return_false_for_null_username() {
        assertThat(userValidator.newUserIsValid(userWithoutUsername)).isFalse();
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
}
