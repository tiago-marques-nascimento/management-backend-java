package com.users.management.infrastructure.configuration.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.users.management.domain.service.RoleService;
import com.users.management.domain.service.RoleServiceInterface;

@Configuration
public class RoleServiceConfiguration {

    @Bean
    public RoleServiceInterface roleService() {

        RoleServiceInterface roleService = new RoleService();
        return roleService;
    }
}
