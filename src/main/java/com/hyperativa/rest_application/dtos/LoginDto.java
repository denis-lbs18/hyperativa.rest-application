package com.hyperativa.rest_application.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    private String email;
    private String password;
}
