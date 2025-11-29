package com.hyperativa.rest_application.services;

import com.hyperativa.rest_application.converters.UserEntityToDtoConverter;
import com.hyperativa.rest_application.dtos.UserDto;
import com.hyperativa.rest_application.entities.User;
import com.hyperativa.rest_application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserEntityToDtoConverter userConverter;

    public Set<UserDto> allUsers() {
        Set<UserDto> users = new HashSet<>();

        userRepository.findAll().forEach(user -> users.add(userConverter.convert(user)));

        return users;
    }

    public UserDto retrieveLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User currentUser = (User) authentication.getPrincipal();

        return userConverter.convert(currentUser);
    }
}