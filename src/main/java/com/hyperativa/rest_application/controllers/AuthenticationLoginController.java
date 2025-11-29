package com.hyperativa.rest_application.controllers;

import com.hyperativa.rest_application.entities.User;
import com.hyperativa.rest_application.dtos.LoginDto;
import com.hyperativa.rest_application.dtos.UserDto;
import com.hyperativa.rest_application.dtos.LoginResponseDto;
import com.hyperativa.rest_application.services.AuthenticationService;
import com.hyperativa.rest_application.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationLoginController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginDto loginDto) {
        User authenticatedUser = authenticationService.authenticate(loginDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = LoginResponseDto
                .builder()
                    .token(jwtToken)
                    .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
