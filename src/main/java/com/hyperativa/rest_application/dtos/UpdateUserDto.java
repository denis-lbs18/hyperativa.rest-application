package com.hyperativa.rest_application.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserDto {
    @NotBlank(message = "Full name cannot be null or empty.")
    private String fullName;
    private String address;
}
