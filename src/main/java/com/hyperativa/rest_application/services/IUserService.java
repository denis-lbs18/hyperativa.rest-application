package com.hyperativa.rest_application.services;

import com.hyperativa.rest_application.dtos.UpdateUserDto;
import com.hyperativa.rest_application.dtos.UserDto;

import java.util.Set;

public interface IUserService {
    Set<UserDto> allUsers();
    UserDto retrieveLoggedUser();
    UserDto updateUser(Integer id, UpdateUserDto userDto);
}