package com.hyperativa.rest_application.controllers;

import com.hyperativa.rest_application.dtos.UpdateUserDto;
import com.hyperativa.rest_application.dtos.UserDto;
import com.hyperativa.rest_application.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User management endpoints")
public class UsersController {
    private final IUserService userService;

    @Operation(summary = "Get current authenticated user", description = "Returns the currently authenticated user's data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authenticated user returned", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/retrieve-my-data")
    public ResponseEntity<UserDto> authenticatedUser() {
        return ResponseEntity.ok(userService.retrieveLoggedUser());
    }

    @Operation(summary = "Retrieve all users", description = "Returns a collection of all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users returned", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @GetMapping("/retrieve-all-users")
    public ResponseEntity<Set<UserDto>> allUsers() {
        Set<UserDto> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Update user data", description = "Updates user data for the provided user id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PutMapping("/{id}/update-my-data")
    public ResponseEntity<UserDto> updateUser(
            @Parameter(description = "User identifier", required = true, example = "123")
            @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User data to update",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateUserDto.class))
            )
            @Valid @RequestBody UpdateUserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }
}