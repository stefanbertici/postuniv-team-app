package ro.ubb.SaloonApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "beautyservices")
public class BeautyService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Enumerated(EnumType.STRING)
    private String name;
    private double duration;
    private double price;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
}
