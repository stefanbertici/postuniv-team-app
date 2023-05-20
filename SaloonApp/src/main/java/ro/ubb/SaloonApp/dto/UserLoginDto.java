package ro.ubb.SaloonApp.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDto(

        @NotBlank(message = "Email cannot be blank")
        String email,
        @NotBlank(message = "Password cannot be blank")
        String password) {
}
