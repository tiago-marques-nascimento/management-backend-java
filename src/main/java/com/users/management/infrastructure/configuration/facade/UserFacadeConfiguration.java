package com.users.management.infrastructure.configuration.facade;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.users.management.application.facade.UserFacade;
import com.users.management.application.facade.UserFacadeInterface;

@Configuration
public class UserFacadeConfiguration {

    @Bean
    public UserFacadeInterface userFacade() {

        UserFacadeInterface userFacade = new UserFacade();
        return userFacade;
    }
}
