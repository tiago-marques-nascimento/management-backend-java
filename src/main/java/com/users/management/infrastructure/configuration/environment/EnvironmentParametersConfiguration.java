package com.users.management.infrastructure.configuration.environment;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Configuration
public class EnvironmentParametersConfiguration {

    @Bean
    @Scope("singleton")
    public EnvironmentParameters environmentParameters() {
        return new EnvironmentParameters(System.getenv("EXPIRATION_TIME"),
            System.getenv("HEADER_STRING"),
            System.getenv("TOKEN_PREFIX"),
            System.getenv("SECRET"));
    }
}
