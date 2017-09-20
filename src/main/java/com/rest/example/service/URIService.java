package com.rest.example.service;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-20
 */
public class URIService {
    public URI addingIdToHttpServletRequestURL(HttpServletRequest httpRequest, int id) throws URISyntaxException {
        if (httpRequest == null) {
            return null;
        } else {
            return joinHttpServletRequestWithStringId(httpRequest, Integer.toString(id));
        }
    }

    URI joinHttpServletRequestWithStringId(HttpServletRequest httpRequest, String newUserId) throws URISyntaxException {
        StringBuffer requestURL = httpRequest.getRequestURL();

        if (requestURL == null || requestURL.toString().trim().isEmpty()) {
            return null;
        }   else {
            return new URI(requestURL + "/" + newUserId);
        }
    }
}
