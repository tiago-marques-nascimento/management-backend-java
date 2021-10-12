package com.users.management.application.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import com.users.management.application.facade.UserFacadeInterface;
import com.users.management.application.viewmodel.UserViewModel;
import com.users.management.infrastructure.response.ControllerResponse;
import com.users.management.infrastructure.configuration.jwt.WithAnyOfTheseRoles;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserFacadeInterface userFacade;

    public UserController(final UserFacadeInterface userFacade) {
        this.userFacade = userFacade;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @WithAnyOfTheseRoles(roles = {"standard", "admin"})
    public ResponseEntity findAll() {

        return new ResponseEntity(new ControllerResponse(this.userFacade.findAll()), HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @WithAnyOfTheseRoles(roles = {"standard", "admin"})
    public ResponseEntity findByName(@PathVariable final String name) {

        return new ResponseEntity(new ControllerResponse(this.userFacade.findByName(name)), HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @WithAnyOfTheseRoles(roles = "admin")
    public ResponseEntity create(@RequestBody final UserViewModel user) {

        this.userFacade.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @WithAnyOfTheseRoles(roles = "admin")
    public ResponseEntity update(@RequestBody final UserViewModel user) {

        this.userFacade.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @WithAnyOfTheseRoles(roles = "admin")
    public ResponseEntity delete(@PathVariable final UUID id) {

        this.userFacade.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
