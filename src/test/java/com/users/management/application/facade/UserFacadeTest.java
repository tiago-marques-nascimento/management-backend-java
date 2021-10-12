package com.users.management.application.facade;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.users.management.application.facade.UserFacadeInterface;
import com.users.management.application.viewmodel.UserViewModel;
import com.users.management.domain.service.UserServiceInterface;
import com.users.management.domain.entity.User;
import com.users.management.domain.entity.Role;
import com.users.management.domain.entity.UserRole;
import com.users.management.domain.factory.UserFactory;
import static org.junit.Assert.*;

@SpringBootTest
public class UserFacadeTest {

    @Autowired
    private UserFacadeInterface userFacade;

    @MockBean
    private UserServiceInterface userService;

    @Test
    public void findUserByNameWithRolesTest() {

        //Given
        User user = UserFactory.mockUser();
        Mockito.when(userService.findByNameWithRoles(user.getName())).thenReturn(Optional.of(user));

        //When
        UserViewModel found = userFacade.findByName(user.getName());
        
        //Then
        assertEquals("No user was found", found.getName(), user.getName());
    }

    @Test
    public void findAllUsersWithRolesTest() {

        //Given
        List<User> users = UserFactory.mockUsers();
        Mockito.when(userService.findAllWithRoles()).thenReturn(users);

        //When
        List<UserViewModel> found = userFacade.findAll();

        //Then
        assertEquals("No user was found", found.size(), users.size());
    }
}
