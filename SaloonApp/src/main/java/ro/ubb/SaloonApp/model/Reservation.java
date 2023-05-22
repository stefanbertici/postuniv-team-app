package ro.ubb.SaloonApp.model;

import jakarta.persistence.*;
import lombok.*;
import ro.ubb.SaloonApp.constant.Status;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "beautyservice_id")
    private BeautyService beautyService;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "res_date")
    private LocalDate resDate;
    @Column(name = "res_hour")
    private double resHour;
}