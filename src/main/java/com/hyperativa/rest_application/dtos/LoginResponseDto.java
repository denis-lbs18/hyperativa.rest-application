package com.hyperativa.rest_application.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {
    private String token;
    private long expiresIn;
}
