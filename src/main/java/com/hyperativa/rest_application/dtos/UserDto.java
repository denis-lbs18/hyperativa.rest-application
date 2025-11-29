package com.hyperativa.rest_application.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String email;
    private String password;
    private String fullName;
    private LocalDate creationDate;
    private LocalDate updateDate;
}
