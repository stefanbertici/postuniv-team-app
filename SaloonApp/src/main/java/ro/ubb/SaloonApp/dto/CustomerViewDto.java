package ro.ubb.SaloonApp.dto;

import lombok.Data;

@Data
public final class CustomerViewDto {
    private Integer id;
    private String name;
    private String email;
    private String role;
}
