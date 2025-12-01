package com.hyperativa.rest_application.services.impl;

import com.hyperativa.rest_application.converters.UserEntityToDtoConverter;
import com.hyperativa.rest_application.dtos.UserDto;
import com.hyperativa.rest_application.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;
import java.util.Set;

import static com.hyperativa.rest_application.builder.ConstantsBuilder.UPDATED_NAME;
import static com.hyperativa.rest_application.builder.entities.UserBuilder.*;
import static com.hyperativa.rest_application.builder.dtos.UserDtoBuilder.buildUserDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityToDtoConverter converter;

    @Test
    void shouldPass_whenAllUsers_ReturnsSetOfUserDto() {
        when(userRepository.findAll()).thenReturn(buildUserList());
        when(converter.convert(any())).thenCallRealMethod();

        Set<UserDto> userDtos = service.allUsers();
        assertEquals(2, userDtos.size());
    }


    @Test
    void shouldPass_whenUpdateUser_ReturnsUserDto() {
        when(userRepository.findById(any())).thenReturn(Optional.of(buildUser()));
        when(userRepository.save(any())).thenReturn(buildUpdatedUser());
        when(converter.convert(any())).thenCallRealMethod();

        UserDto inputDto = buildUserDto();
        inputDto.setFullName("Updated Name");

        UserDto updatedDto = service.updateUser(1, inputDto);

        assertNotNull(updatedDto);
        assertEquals(UPDATED_NAME, updatedDto.getFullName());
    }
}