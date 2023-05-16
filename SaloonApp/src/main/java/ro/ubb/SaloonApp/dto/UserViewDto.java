package ro.ubb.SaloonApp.dto;

import lombok.Data;
import ro.ubb.SaloonApp.model.Category;

import java.util.Set;

@Data
public final class UserViewDto {
    private Integer id;
    private String name;
    private String email;
    private String role;
    private Set<Category> categories;
}
