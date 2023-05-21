package ro.ubb.SaloonApp.dto;

import lombok.Data;
import ro.ubb.SaloonApp.model.Category;

@Data
public final class BeautyServiceViewDto {
    private Integer id;
    private Category category;
    private String name;
    private double duration;
    private double price;
}
