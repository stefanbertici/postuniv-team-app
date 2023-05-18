package ro.ubb.SaloonApp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class UserLoginDto {

    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
