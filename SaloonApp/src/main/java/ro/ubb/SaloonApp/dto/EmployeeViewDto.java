package ro.ubb.SaloonApp.dto;

import ro.ubb.SaloonApp.model.Category;

import java.util.Set;

public record EmployeeViewDto(

        Integer id,
        String name,
        String email,
        String role,
        Set<Category> categories) {
}
