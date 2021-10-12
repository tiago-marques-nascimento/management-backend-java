package com.users.management.infrastructure.configuration.mapper;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import com.users.management.application.viewmodel.UserViewModel;
import com.users.management.application.viewmodel.RoleViewModel;
import com.users.management.domain.entity.User;
import com.users.management.domain.entity.Role;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        /*
        Mapping: ViewModel -> Entity
        */
        modelMapper.addMappings(new PropertyMap<UserViewModel, User>() {

            @Override
            protected void configure() {
                skip().setUserRoles(null);
            }
        });
        modelMapper.addMappings(new PropertyMap<RoleViewModel, Role>() {

            @Override
            protected void configure() {
                skip().setUserRoles(null);
            }
        });
        return modelMapper;
    }
}
