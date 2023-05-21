package ro.ubb.SaloonApp.dto;

import ro.ubb.SaloonApp.model.Category;

public record BeautyServiceDto(

        Category category,
        String name,
        double duration,
        double price
) {
}
