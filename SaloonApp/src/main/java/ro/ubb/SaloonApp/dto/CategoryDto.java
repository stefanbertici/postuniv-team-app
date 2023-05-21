package ro.ubb.SaloonApp.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(

        @NotBlank(message = "Name cannot be blank")
        String name) {
}
