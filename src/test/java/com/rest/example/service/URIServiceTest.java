package com.rest.example.service;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-20
 */
public class URIServiceTest {
    URIService uriService = new URIService();

    @Test
    public void creating_new_user_id_link_returns_null_when_given_request_null() throws URISyntaxException {
        assertThat(uriService.addingIdToHttpServletRequestURL(null, 1)).isNull();
    }

    @Test
    public void creating_new_user_id_link_returns_correct_URI() throws URISyntaxException {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        URI uri = uriService.addingIdToHttpServletRequestURL(mockHttpServletRequest, 1);
        assertThat(uri).isNotNull().isEqualTo(new URI("http://localhost/1"));

    }
}
