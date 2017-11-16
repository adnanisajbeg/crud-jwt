package com.rest.example.util;

import org.junit.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-11-16
 */
public class StringUtilsTest {

    @Test
    public void string_is_not_blank_returns_false_when_given_null() {
        assertThat(StringUtils.isNotBlank(null)).isFalse();
    }

    @Test
    public void string_is_not_blank_returns_false_when_given_empty_string() {
        assertThat(StringUtils.isNotBlank("")).isFalse();
    }

    @Test
    public void string_is_not_blank_returns_false_when_given_single_space_string() {
        assertThat(StringUtils.isNotBlank(" ")).isFalse();
    }

    @Test
    public void string_is_not_blank_returns_false_when_given_string_with_several_spaces_string() {
        assertThat(StringUtils.isNotBlank("     ")).isFalse();
    }

    @Test
    public void string_is_not_blank_returns_true_when_given_single_char_string() {
        assertThat(StringUtils.isNotBlank("t")).isTrue();
    }

    @Test
    public void string_is_not_blank_returns_true_when_given_single_number_string() {
        assertThat(StringUtils.isNotBlank("1")).isTrue();
    }

    @Test
    public void string_is_not_blank_returns_true_when_given_single_char_string_with_some_spaces() {
        assertThat(StringUtils.isNotBlank("   t   ")).isTrue();
    }

    @Test
    public void string_is_not_blank_returns_true_when_given_random_string_with_some_spaces() {
        assertThat(StringUtils.isNotBlank("   " + randomAlphanumeric(5) + " ")).isTrue();
    }

    @Test
    public void string_is_not_blank_returns_true_when_given_random_string() {
        assertThat(StringUtils.isNotBlank(randomAlphanumeric(24))).isTrue();
    }
}
