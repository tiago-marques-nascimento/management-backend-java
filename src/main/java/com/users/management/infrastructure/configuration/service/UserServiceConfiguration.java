package com.users.management.infrastructure.configuration.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.users.management.domain.service.UserService;
import com.users.management.domain.service.UserServiceInterface;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public UserServiceInterface userService() {

        UserServiceInterface userService = new UserService();
        return userService;
    }
}
