package com.users.management.infrastructure.configuration.facade;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.users.management.application.facade.RoleFacade;
import com.users.management.application.facade.RoleFacadeInterface;

@Configuration
public class RoleFacadeConfiguration {

    @Bean
    public RoleFacadeInterface roleFacade() {

        RoleFacadeInterface roleFacade = new RoleFacade();
        return roleFacade;
    }
}
