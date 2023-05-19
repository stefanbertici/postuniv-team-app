package ro.ubb.SaloonApp.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDto(

        String name,
        @NotBlank(message = "Email cannot be blank")
        String email,
        @NotBlank(message = "Password cannot be blank")
        String password) {
}
