package com.hyperativa.rest_application.builder.dtos;

import com.hyperativa.rest_application.builder.ConstantsBuilder;
import com.hyperativa.rest_application.dtos.LoginDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoginDtoBuilder {
    public static LoginDto buildLoginDto() {
        return LoginDto.builder()
                .email(ConstantsBuilder.DEFAULT_EMAIL)
                .password(ConstantsBuilder.ANY_STRING)
                .build();
    }
}
