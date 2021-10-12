package com.users.management.domain.service;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.users.management.domain.repository.RoleRepositoryInterface;
import com.users.management.domain.entity.Role;
import com.users.management.infrastructure.exception.BusinessException;
import com.users.management.domain.factory.RoleFactory;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleServiceInterface roleService;

    @MockBean
    private RoleRepositoryInterface roleRepository;

    @Test
    public void findAllRolesTest() {

        //Given
        List<Role> roles = RoleFactory.mockRoles();
        Mockito.when(roleRepository.findAll()).thenReturn(roles);

        //When
        List<Role> found = new ArrayList<Role>(roleService.findAll());
        
        //Then
        assertEquals("No role was found", found.size(), roles.size());
    }
}
