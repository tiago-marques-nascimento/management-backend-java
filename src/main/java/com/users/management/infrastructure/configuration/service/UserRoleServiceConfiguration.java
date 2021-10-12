package com.users.management.infrastructure.configuration.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.users.management.domain.service.UserRoleService;
import com.users.management.domain.service.UserRoleServiceInterface;

@Configuration
public class UserRoleServiceConfiguration {

    @Bean
    public UserRoleServiceInterface userRoleService() {

        UserRoleServiceInterface userRoleService = new UserRoleService();
        return userRoleService;
    }
}
