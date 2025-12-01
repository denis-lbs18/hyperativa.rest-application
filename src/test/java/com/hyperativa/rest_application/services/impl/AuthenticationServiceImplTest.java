package com.hyperativa.rest_application.services.impl;

import com.hyperativa.rest_application.entities.User;
import com.hyperativa.rest_application.exceptions.DuplicatedUserEmailException;
import com.hyperativa.rest_application.exceptions.UserNotFoundException;
import com.hyperativa.rest_application.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.hyperativa.rest_application.builder.ConstantsBuilder.ANY_STRING;
import static com.hyperativa.rest_application.builder.dtos.LoginDtoBuilder.buildLoginDto;
import static com.hyperativa.rest_application.builder.dtos.UserDtoBuilder.buildUserDto;
import static com.hyperativa.rest_application.builder.entities.UserBuilder.buildUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings
class AuthenticationServiceImplTest {
    @InjectMocks
    private AuthenticationServiceImpl service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void shouldFail_WhenSignup_ThrowsDuplicatedUserEmailException() {
        when(userRepository.findByEmail(any())).thenThrow(DuplicatedUserEmailException.class);
        assertThrows(DuplicatedUserEmailException.class, () ->service.signup(buildUserDto()));
    }

    @Test
    void shouldPass_whenSignup_ReturnsUser() {
        when(userRepository.findByEmail(any())).thenReturn(java.util.Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn(ANY_STRING);
        when(userRepository.save(any())).thenReturn(buildUser());

        var user = service.signup(buildUserDto());

        assertNotNull(user);
        assertEquals(ANY_STRING, user.getPassword());
    }

    @Test
    void shouldPass_whenAuthenticate_ReturnsUser() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(buildUser()));
        User authenticated = service.authenticate(buildLoginDto());

        assertEquals(ANY_STRING, authenticated.getPassword());
    }

    @Test
    void shouldFail_whenAuthenticate_ThrowsUserNotFoundException() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> service.authenticate(buildLoginDto()));
    }
}