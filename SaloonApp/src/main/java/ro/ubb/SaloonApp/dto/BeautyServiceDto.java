package ro.ubb.SaloonApp.dto;

public record BeautyServiceDto(

        String name,
        int numOfAvailabilityBlocks,
        double price,
        String categoryName
) {
}
