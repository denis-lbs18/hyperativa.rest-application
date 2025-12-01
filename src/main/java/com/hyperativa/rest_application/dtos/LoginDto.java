package com.hyperativa.rest_application.dtos;

import com.hyperativa.rest_application.converters.StringCryptoConverter;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotBlank(message = "Password must not be blank")
    @Convert(converter = StringCryptoConverter.class)
    private String password;
}
