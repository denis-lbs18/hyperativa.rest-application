package com.hyperativa.rest_application.controllers;

import com.hyperativa.rest_application.dtos.UserDto;
import com.hyperativa.rest_application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping("/retrieve-my-data")
    public ResponseEntity<UserDto> authenticatedUser() {
        return ResponseEntity.ok(userService.retrieveLoggedUser());
    }

    @GetMapping("/retrieve-all-users")
    public ResponseEntity<Set<UserDto>> allUsers() {
        Set<UserDto> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}
