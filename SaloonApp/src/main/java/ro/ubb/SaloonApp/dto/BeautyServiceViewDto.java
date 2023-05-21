package ro.ubb.SaloonApp.dto;

import ro.ubb.SaloonApp.model.Category;

public record BeautyServiceViewDto(

        Integer id,
        Category category,
        String name,
        double duration,
        double price
) {
}
