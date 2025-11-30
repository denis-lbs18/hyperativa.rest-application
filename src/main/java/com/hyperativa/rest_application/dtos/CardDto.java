package com.hyperativa.rest_application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    @NotBlank(message = "Card number must not be blank")
    @Pattern(regexp = "^\\d+$", message = "Card number must have only numeric characters")
    @Length(min = 13, max = 19, message = "Card number must be between 13 and 19 digits long")
    private String number;
}