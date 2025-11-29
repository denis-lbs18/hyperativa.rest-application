package com.hyperativa.rest_application.services;


import com.hyperativa.rest_application.dtos.LoginDto;
import com.hyperativa.rest_application.dtos.UserDto;
import com.hyperativa.rest_application.entities.User;
import com.hyperativa.rest_application.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(UserDto input) {
        User user = User.builder()
                .id(0)
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .createdDate(LocalDate.now())
                .updatedDate(LocalDate.now())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        return userRepository.save(user);
    }

    public User authenticate(LoginDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
