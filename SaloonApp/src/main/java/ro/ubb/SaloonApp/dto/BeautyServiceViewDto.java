package ro.ubb.SaloonApp.dto;

public record BeautyServiceViewDto(

        Integer id,
        String categoryName,
        String name,
        double duration,
        double price
) {
}
