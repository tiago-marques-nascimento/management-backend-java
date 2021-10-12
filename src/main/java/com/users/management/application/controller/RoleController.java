package com.users.management.application.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;
import com.users.management.application.facade.RoleFacadeInterface;
import com.users.management.infrastructure.response.ControllerResponse;
import com.users.management.infrastructure.configuration.jwt.WithAnyOfTheseRoles;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private final RoleFacadeInterface roleFacade;

    public RoleController(final RoleFacadeInterface roleFacade) {
        this.roleFacade = roleFacade;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @WithAnyOfTheseRoles(roles = {"standard", "admin"})
    public ResponseEntity findAll() {

        return new ResponseEntity(new ControllerResponse(this.roleFacade.findAll()), HttpStatus.OK);
    }
}
