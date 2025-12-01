package com.hyperativa.rest_application.builder.dtos;

import com.hyperativa.rest_application.builder.ConstantsBuilder;
import com.hyperativa.rest_application.dtos.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDtoBuilder {
    public static UserDto buildUserDto() {
        return UserDto.builder()
                .fullName(ConstantsBuilder.ANY_STRING)
                .email(ConstantsBuilder.ANY_STRING)
                .password(ConstantsBuilder.ANY_STRING)
                .build();
    }
}
