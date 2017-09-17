package com.rest.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-16
 */
@SpringBootApplication
public class Application {
    private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Application...");
        SpringApplication.run(Application.class, args);
    }
}
