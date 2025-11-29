package com.hyperativa.rest_application.converters;

import com.hyperativa.rest_application.dtos.UserDto;
import com.hyperativa.rest_application.entities.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User source) {
        return UserDto.builder()
                .email(source.getEmail())
                .password(source.getPassword())
                .fullName(source.getFullName())
                .creationDate(source.getCreatedDate())
                .updateDate(source.getUpdatedDate())
                .build();
    }
}
