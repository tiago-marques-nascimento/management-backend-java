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
import com.users.management.application.facade.RoleFacadeInterface;
import com.users.management.application.viewmodel.RoleViewModel;
import com.users.management.domain.service.RoleServiceInterface;
import com.users.management.domain.entity.Role;
import com.users.management.domain.factory.RoleFactory;
import static org.junit.Assert.*;

@SpringBootTest
public class RoleFacadeTest {

    @Autowired
    private RoleFacadeInterface roleFacade;

    @MockBean
    private RoleServiceInterface roleService;

    @Test
    public void findAllRolesTest() {

        //Given
        List<Role> roles = RoleFactory.mockRoles();
        Mockito.when(roleService.findAll()).thenReturn(roles);

        //When
        List<RoleViewModel> found = roleFacade.findAll();

        //Then
        assertEquals("No role was found", found.size(), roles.size());
    }
}
