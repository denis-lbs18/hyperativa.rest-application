package com.hyperativa.rest_application.services.impl;

import com.hyperativa.rest_application.converters.UserEntityToDtoConverter;
import com.hyperativa.rest_application.dtos.UpdateUserDto;
import com.hyperativa.rest_application.dtos.UserDto;
import com.hyperativa.rest_application.entities.User;
import com.hyperativa.rest_application.exceptions.UserNotFoundException;
import com.hyperativa.rest_application.repositories.UserRepository;
import com.hyperativa.rest_application.services.IUserService;
import com.hyperativa.rest_application.utils.LocalUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserEntityToDtoConverter converter;

    public Set<UserDto> allUsers() {
        Set<UserDto> users = new HashSet<>();

        userRepository.findAll().forEach(user -> users.add(converter.convert(user)));

        return users;
    }

    public UserDto retrieveLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final User currentUser = (User) authentication.getPrincipal();

        return converter.convert(currentUser);
    }

    public UserDto updateUser(Integer id, UpdateUserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        LocalUtils.copyProperties(userDto, user);
        user.setUpdatedDate(LocalDate.now());
        User saved = userRepository.save(user);
        return converter.convert(saved);
    }
}