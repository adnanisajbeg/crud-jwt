package com.rest.example;

import com.rest.example.db.UserDataService;
import com.rest.example.db.repository.MySQLDataService;
import com.rest.example.model.convertor.UserConverter;
import com.rest.example.service.UserService;
import com.rest.example.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author: Adnan Isajbegovic
 * @Created: 2017-09-16
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
public class Application {
    private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Application...");
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public UserValidator getUserValidator() {
        return new UserValidator();
    }

    @Bean
    public UserService getUserCollectionService() {
        return new UserService();
    }

//    @Bean
//    public UserDataService userDataService() {
//        return new MockedUserDataService();
//    }

    @Bean
    public UserDataService userDataService() {
        return new MySQLDataService();
    }


    @Bean
    public UserConverter getUserConverter() {return new UserConverter();}
}
