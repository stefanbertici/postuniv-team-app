package ro.ubb.SaloonApp.dto;

public record CustomerViewDto(

        Integer id,
        String name,
        String email,
        String role) {
}
