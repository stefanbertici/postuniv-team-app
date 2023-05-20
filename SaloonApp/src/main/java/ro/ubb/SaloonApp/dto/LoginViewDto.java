package ro.ubb.SaloonApp.dto;

public record LoginViewDto(

        Integer id,
        String name,
        String email,
        String role,
        String token) {
}
