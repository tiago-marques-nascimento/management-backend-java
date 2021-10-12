package com.users.management.infrastructure.configuration.validator;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.users.management.domain.validator.BaseValidatorInterface;
import com.users.management.domain.validator.UserValidator;
import com.users.management.domain.validator.RoleValidator;
import com.users.management.domain.validator.UserRoleValidator;

@Configuration
public class BaseValidatorConfiguration {

    @Bean
    public BaseValidatorInterface userValidator() {

        UserValidator validator = new UserValidator();
        return validator;
    }

    @Bean
    public BaseValidatorInterface roleValidator() {

        RoleValidator validator = new RoleValidator();
        return validator;
    }

    @Bean
    public BaseValidatorInterface userRoleValidator() {

        UserRoleValidator validator = new UserRoleValidator();
        return validator;
    }
}
