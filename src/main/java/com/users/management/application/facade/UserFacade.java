package com.users.management.application.facade;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Optional;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;
import com.users.management.application.viewmodel.UserViewModel;
import com.users.management.application.viewmodel.RoleViewModel;
import com.users.management.domain.service.UserServiceInterface;
import com.users.management.domain.service.UserRoleServiceInterface;
import com.users.management.domain.entity.User;
import com.users.management.domain.entity.Role;
import com.users.management.domain.entity.UserRole;
import com.users.management.infrastructure.exception.BusinessException;

public class UserFacade implements UserFacadeInterface {

    @Inject
    private UserServiceInterface userService;

    @Inject
    private UserRoleServiceInterface userRoleService;

    @Inject
    private ModelMapper modelMapper;

    public UserViewModel findByName(final String name) {

        Optional<User> user = this.userService.findByNameWithRoles(name);

        if (user.isPresent()) {
            UserViewModel userViewModel = modelMapper.map(user.get(), UserViewModel.class);
            userViewModel.setRoles(user.get().getUserRoles()
                .stream()
                .distinct()
                .map(userRole -> modelMapper.map(userRole.getRole(), RoleViewModel.class))
                .collect(Collectors.toList()));
            return userViewModel;
        }
        return null;
    }

    public List<UserViewModel> findAll() {

        return this.userService
            .findAllWithRoles()
            .stream()
            .distinct()
            .map(user -> {
                UserViewModel userViewModel = modelMapper.map(user, UserViewModel.class);
                userViewModel.setRoles(user.getUserRoles()
                    .stream()
                    .distinct()
                    .map(userRole -> modelMapper.map(userRole.getRole(), RoleViewModel.class))
                    .distinct()
                    .collect(Collectors.toList()));
                return userViewModel;
            })
            .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void save(final UserViewModel userViewModel) {

        if (userViewModel.getId() == null) {
            this.create(userViewModel);
        } else {
            this.update(userViewModel);
        }
    }

    private void create(final UserViewModel userViewModel) {
        User user = modelMapper.map(userViewModel, User.class);
        user.setUserRoles(userViewModel.getRoles().stream()
            .map(role -> new UserRole(user, modelMapper.map(role, Role.class)))
            .collect(Collectors.toList()));
        User savedUser = this.userService.save(user);
        user.setId(savedUser.getId());

        this.userRoleService.saveAll(new ArrayList<UserRole>(user.getUserRoles()));
    }

    private void update(final UserViewModel userViewModel) {

        Optional<User> persistedUser = this.userService.findByIdWithRoles(userViewModel.getId());

        if (persistedUser.isPresent()) {
            this.userRoleService.deleteAll(persistedUser.get().getUserRoles());
        }

        User user = modelMapper.map(userViewModel, User.class);
        user.setUserRoles(userViewModel.getRoles().stream()
            .map(role -> new UserRole(user, modelMapper.map(role, Role.class)))
            .collect(Collectors.toList()));
        User savedUser = this.userService.save(user);
        user.setId(savedUser.getId());

        this.userRoleService.saveAll(new ArrayList<UserRole>(user.getUserRoles()));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void delete(final UUID id) {

        Optional<User> user = this.userService.findByIdWithRoles(id);
        if (user.isPresent()) {
            this.userRoleService.deleteAll(user.get().getUserRoles());
            this.userService.delete(user.get());
        } else {
            throw new BusinessException("User to be deleted not found");
        }
    }
}
