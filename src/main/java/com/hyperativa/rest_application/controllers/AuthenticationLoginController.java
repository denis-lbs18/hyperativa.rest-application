package com.hyperativa.rest_application.controllers;

import com.hyperativa.rest_application.dtos.LoginDto;
import com.hyperativa.rest_application.dtos.LoginResponseDto;
import com.hyperativa.rest_application.dtos.UserDto;
import com.hyperativa.rest_application.entities.User;
import com.hyperativa.rest_application.services.IAuthenticationService;
import com.hyperativa.rest_application.services.IJwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication endpoints (signup / login)")
public class AuthenticationLoginController {
    private static final Logger logger = LogManager.getLogger(AuthenticationLoginController.class);

    private final IJwtService jwtService;
    private final IAuthenticationService authenticationService;

    @Operation(summary = "Register a new user", description = "Creates a user with the data provided in UserDto")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User registered", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
        @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping("/signup")
    public ResponseEntity<User> register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User data for registration",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserDto.class))
            )
            @Valid @RequestBody UserDto registerUserDto) {

        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @Operation(summary = "Authenticate user and return JWT", description = "Accepts credentials and returns a JWT token with expiration info")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Authenticated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class))),
        @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User credentials",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoginDto.class))
            )
            @Valid @RequestBody LoginDto loginDto) {

        User authenticatedUser = authenticationService.authenticate(loginDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = LoginResponseDto.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}