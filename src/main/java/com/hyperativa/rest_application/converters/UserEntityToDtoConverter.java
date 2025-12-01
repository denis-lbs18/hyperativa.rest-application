package com.hyperativa.rest_application.converters;

import com.hyperativa.rest_application.dtos.UserDto;
import com.hyperativa.rest_application.entities.User;
import com.hyperativa.rest_application.utils.LocalUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityToDtoConverter implements Converter<User, UserDto> {
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto convert(@NonNull User source) {
        UserDto tartget = new UserDto();
        LocalUtils.copyProperties(source, tartget);
        return tartget;
    }
}
