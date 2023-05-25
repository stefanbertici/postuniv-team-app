package ro.ubb.SaloonApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "beautyservices")
public class BeautyService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double duration;
    private double price;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    private int numberOfAvailabilityBlock;
}