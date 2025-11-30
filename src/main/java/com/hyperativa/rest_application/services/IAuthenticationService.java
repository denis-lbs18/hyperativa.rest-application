package com.hyperativa.rest_application.services;

import com.hyperativa.rest_application.dtos.LoginDto;
import com.hyperativa.rest_application.dtos.UserDto;
import com.hyperativa.rest_application.entities.User;

public interface IAuthenticationService {
    User signup(UserDto input);

    User authenticate(LoginDto input);
}
