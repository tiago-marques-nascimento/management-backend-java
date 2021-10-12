package com.users.management.domain.service;

import java.util.UUID;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import org.springframework.test.util.ReflectionTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.users.management.domain.repository.UserRepositoryInterface;
import com.users.management.domain.validator.BaseValidatorInterface;
import com.users.management.domain.entity.User;
import com.users.management.domain.entity.Role;
import com.users.management.domain.entity.UserRole;
import com.users.management.infrastructure.exception.BusinessException;
import com.users.management.domain.factory.UserFactory;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceInterface userService;

    @MockBean
    private UserRepositoryInterface userRepository;

    @Autowired
    BaseValidatorInterface<User> userValidator;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {

        this.userValidator = Mockito.spy(userValidator);
        ReflectionTestUtils.setField(userService, "userValidator", this.userValidator);
    }

    @Test
    public void findUserByNameWithRolesTest() {

        //Given
        User user = UserFactory.mockUser();
        Mockito.when(userRepository.findByNameWithRoles(user.getName())).thenReturn(Optional.of(user));

        //When
        User found = userService.findByNameWithRoles(user.getName()).get();
        
        //Then
        assertEquals("No user was found", found.getName(), user.getName());
    }

    @Test
    public void findAllUsersWithRolesTest() {

        //Given
        List<User> users = UserFactory.mockUsers();
        Mockito.when(userRepository.findAllWithRoles()).thenReturn(users);

        //When
        List<User> found = new ArrayList<User>(userService.findAllWithRoles());
        
        //Then
        assertEquals("No user was found", found.size(), users.size());
    }

    @Test
    public void createUserFailedValidationTest() {

        //Given
        User user = UserFactory.mockUser();
        Mockito.when(userRepository.findByName(user.getName())).thenReturn(Optional.of(user));

        //When
        assertThrows(BusinessException.class, () -> {
            userService.save(user);
        });
    }

    @Test
    public void createUserSuccessfulValidationTest() {

        //Given
        User user = UserFactory.mockUser();
        Mockito.when(userRepository.findByName(user.getName())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        //When
        userService.save(user);

        //Then
        verify(userValidator, times(1)).validateCreate(any(User.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void updateUserFailedValidationTest() {

        //Given
        User user = UserFactory.mockUserWithId();
        Mockito.when(userRepository.findOtherUserWithSameName(any(String.class), any(UUID.class))).thenReturn(Optional.of(user));

        //When
        assertThrows(BusinessException.class, () -> {
            userService.save(user);
        });
    }

    @Test
    public void updateUserSuccessfulValidationTest() {

        //Given
        User user = UserFactory.mockUserWithId();
        Mockito.when(userRepository.findOtherUserWithSameName(any(String.class), any(UUID.class))).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        //When
        userService.save(user);

        //Then
        verify(userValidator, times(1)).validateUpdate(any(User.class));
        verify(userRepository, times(1)).save(any(User.class));
    }
}
